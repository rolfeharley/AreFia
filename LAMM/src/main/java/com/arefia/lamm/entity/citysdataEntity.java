package com.arefia.lamm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "CITYSDATA")
@Data
public class citysdataEntity {
	@Id
    @Column(name = "CITYID", columnDefinition = "varchar(200)")
    private String cityid;
	@Column(name = "CITYNAME", nullable = false, columnDefinition = "varchar(100)")
	private String cityname;
}
