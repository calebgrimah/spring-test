package com.jobtest.test.repository

import com.jobtest.test.model.FieldEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface DataProviderRepository : JpaRepository<FieldEntity, Long> , JpaSpecificationExecutor<FieldEntity>{
    fun findByProviderId (providerId : Int) : FieldEntity
    fun findAllByProviderId (providerId: Int): List<FieldEntity>
}
