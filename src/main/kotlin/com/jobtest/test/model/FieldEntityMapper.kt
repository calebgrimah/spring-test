package com.jobtest.test.model

 fun FieldEntity.toFieldResponse() = FieldResponse (
      age =  age!!, name = name!!, timestamp = timestamp!!
 )
