package com.arefia.lamm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.arefia.lamm.entity.contactsEntity;

public interface contactsDao extends JpaRepository<contactsEntity, Long>, JpaSpecificationExecutor<contactsEntity> {
    @Query(value = "SELECT COUNT(*) FROM CONTACTS WHERE LINE_UID = :LINE_UID", nativeQuery = true)
    public List<Object[]> checkFollowerInContact(@Param("LINE_UID") String lineuid);
}