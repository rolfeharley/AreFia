package com.arefia.lamm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.arefia.lamm.entity.autoreplymsgEntity;

public interface autoreplymsgDao extends JpaRepository<autoreplymsgEntity, Long> {
	@Query(value = "SELECT MSG FROM AUTO_REPLY_MSG WHERE AUTO_TYPE = 1", nativeQuery = true)
    public List<Object[]> getWelcomeMsg();
}
