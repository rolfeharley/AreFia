package com.arefia.lamm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.arefia.lamm.entity.contactscondsEntity;

public interface contactscondsDao extends JpaRepository<contactscondsEntity, Long> {
	@Query(value = "SELECT * FROM CONTACTS_QRY_CONDS WHERE SYSTEM_TYPE = :SYSTEM_TYPE ORDER BY ORDER_NUM", nativeQuery = true)
    public List<contactscondsEntity> getConditionList(@Param("SYSTEM_TYPE") String system_type);
}
