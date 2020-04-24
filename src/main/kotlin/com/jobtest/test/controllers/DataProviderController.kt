package com.jobtest.test.controllers

import com.jobtest.test.model.DataProviderDTO
import com.jobtest.test.model.FieldEntity
import com.jobtest.test.model.FieldResponse
import com.jobtest.test.model.toFieldResponse
import com.jobtest.test.repository.DataProviderRepository
import com.jobtest.test.specification.FieldSpecification
import com.jobtest.test.util.FilterCriteria
import com.jobtest.test.util.FilterOperation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/api"])
class DataProviderController(val dataProviderRepository: DataProviderRepository) {

    @PostMapping(value = ["/create"])
    fun addDataProvider(@RequestBody dataDto: DataProviderDTO): ResponseEntity<DataProviderDTO> {
        dataDto.data.forEach {
            val fieldEntity  = FieldEntity()
            fieldEntity.providerId = dataDto.providerId
            fieldEntity.age = it.age
            fieldEntity.name = it.name
            fieldEntity.timestamp = it.timestamp
            dataProviderRepository.save(fieldEntity)
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(dataDto)
    }

    @GetMapping("/filter/{providerId}")
    @ResponseBody
    fun getProvider(
            @PathVariable providerId : Int,
            @RequestParam(value = "name", defaultValue = "", required = false) name : String,
            @RequestParam(value = "age", defaultValue = "", required = false) age : String,
            @RequestParam(value = "timestamp", defaultValue = "", required = false) timestamp : String
    ): ResponseEntity<List<FieldResponse>> {
        val specification = FieldSpecification();
        if(name.isNotBlank()){
            val operation = name.substringBefore(":")
            val data = timestamp.substringAfter(":")
            val op : FilterOperation = FilterOperation.values().first { operation == it.name }
            specification.add(FilterCriteria("name",data, op))
        }
        if (age.isNotBlank()){
            val operation = age.substringBefore(":")
            val data = timestamp.substringAfter(":")
            val op : FilterOperation = FilterOperation.values().first { operation == it.name }
            specification.add(FilterCriteria("age",data.toInt(), op))
        }
        if(timestamp.isNotBlank()){
            val operation = timestamp.substringBefore(":")
            val data = timestamp.substringAfter(":")
            val op : FilterOperation = FilterOperation.values().first { operation == it.name }
            specification.add(FilterCriteria("timestamp",data.toInt(), op))
        }
        specification.add(FilterCriteria("providerId",providerId, FilterOperation.eq))
        val items = dataProviderRepository.findAll(specification)
        val listOfResponse = mutableListOf<FieldResponse>()
        items.forEach{
            listOfResponse.add(it.toFieldResponse())
        }
        return ResponseEntity.status(HttpStatus.OK).body(listOfResponse)
    }

    @GetMapping("/all")
    @ResponseBody
    fun getProvider(): ResponseEntity<MutableIterable<FieldEntity>> {
        val dataProviders = dataProviderRepository.findAll()
        return ResponseEntity.status(HttpStatus.OK).body(dataProviders)
    }

}

