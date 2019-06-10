package com.arefia.lamm.dao.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import com.arefia.lamm.entity.contactsEntity;
import com.arefia.lamm.model.queryCriteriaModel;

public class contactsSpecification implements Specification<contactsEntity> {
	private static final long serialVersionUID = 1L;
	private queryCriteriaModel criteria;
	
	@Override
	public Predicate toPredicate(Root<contactsEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		if (criteria.getOperation().equalsIgnoreCase(">")) {
            return criteriaBuilder.greaterThanOrEqualTo(root.<String> get(criteria.getKey()), criteria.getValue().toString());
        } 
        else if (criteria.getOperation().equalsIgnoreCase("<")) {
            return criteriaBuilder.lessThanOrEqualTo(root.<String> get(criteria.getKey()), criteria.getValue().toString());
        } else if (criteria.getOperation().equalsIgnoreCase("@")) {
        	 return criteriaBuilder.isNotNull(root.<String> get(criteria.getKey()));
        } else if (criteria.getOperation().equalsIgnoreCase("!")) {
       	    return criteriaBuilder.isNull(root.<String> get(criteria.getKey()));
        } else if (criteria.getOperation().equalsIgnoreCase("*")) {
        	return criteriaBuilder.like(criteriaBuilder.upper(root.<String>get(criteria.getKey())), "%" + criteria.getValue().toUpperCase() + "%");
        } else if (criteria.getOperation().equalsIgnoreCase(":")) {
        	return criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue());
        }
		
        return null;
	}
	
	public contactsSpecification(queryCriteriaModel criteria) {
		this.criteria = criteria;
	}
}
