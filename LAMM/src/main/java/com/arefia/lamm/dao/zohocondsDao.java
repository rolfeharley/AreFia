package com.arefia.lamm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arefia.lamm.entity.zohocondsEntity;

public interface zohocondsDao extends JpaRepository<zohocondsEntity, Long> {
	public List<zohocondsEntity> findAllByOrderByOrderAsc();
}
