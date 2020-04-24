package com.jobtest.test.model

import javax.persistence.*

@Entity
class FieldEntity (
        @Id @GeneratedValue
        var id: Long? = null,
        var providerId : Int? = 0,
        var age: Int? = 0,
        var name: String? = "",
        var timestamp: Int? = 0
)