package com.jobtest.test

import com.jobtest.test.repository.DataProviderRepository
import com.jobtest.test.util.FilterCriteria
import com.jobtest.test.util.FilterOperation
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.util.*

@SpringBootApplication
class TestApplication

fun main(args: Array<String>) {
    runApplication<TestApplication>(*args)
}