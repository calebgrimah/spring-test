package com.jobtest.test.specification

import com.jobtest.test.model.FieldEntity
import com.jobtest.test.util.FilterCriteria
import com.jobtest.test.util.FilterOperation
import org.springframework.data.jpa.domain.Specification
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

class FieldSpecification : Specification<FieldEntity>{
    val listCriteria  = mutableListOf<FilterCriteria>()

    fun add(criteria: FilterCriteria) {
        listCriteria.add(criteria)
    }

    override fun toPredicate(root: Root<FieldEntity>, query: CriteriaQuery<*>, criteriaBuilder: CriteriaBuilder): Predicate? {
        val predicates = mutableListOf<Predicate>()

        listCriteria.forEach {
            when (it.filterOperation) {
                FilterOperation.eqc -> {
                    predicates.add(criteriaBuilder.equal(root.get<String>(it.key?.toLowerCase()),it.value.toString().toLowerCase()))
                }
                FilterOperation.eq -> {
                    predicates.add(criteriaBuilder.equal(root.get<String>(it.key),it.value.toString()))
                }
                FilterOperation.gt -> {
                    predicates.add(criteriaBuilder.greaterThan(root.get<String>(it.key),it.value.toString()))
                }
                FilterOperation.lt -> {
                    predicates.add(criteriaBuilder.lessThan(root.get<String>(it.key),it.value.toString()))
                }
            }

        }
        return criteriaBuilder.and(*predicates.toTypedArray())
    }
}