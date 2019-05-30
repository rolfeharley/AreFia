package com.arefia.lamm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.arefia.lamm.entity.userEntity;

public interface userDao extends JpaRepository<userEntity, Long> {
	@Query(value = "SELECT A.USERNAME FROM USER_LIST A WHERE A.ACCOUNTID = :ACCOUNTID", nativeQuery = true)
    public String findUserNameById(@Param("ACCOUNTID") String accountid);
}
