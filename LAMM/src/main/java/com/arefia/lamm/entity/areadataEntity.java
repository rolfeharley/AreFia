package com.arefia.lamm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "AREADATA")
@Data
public class areadataEntity {
	@Id
    @Column(name = "AREAID", columnDefinition = "varchar(200)")
    private String areaid;
	@Column(name = "CITYID", nullable = false, columnDefinition = "varchar(200)")
	private String cityid;
	@Column(name = "AREANAME", nullable = false, columnDefinition = "varchar(100)")
	private String areaname;
}
