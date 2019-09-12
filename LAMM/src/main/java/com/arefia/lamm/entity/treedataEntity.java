package com.arefia.lamm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "TREEDATA")
@Data
public class treedataEntity {
	@Id
    @Column(name = "TREEID", columnDefinition = "varchar(200)")
    private String treeid;
	@Column(name = "AREAID", nullable = false, columnDefinition = "varchar(200)")
	private String areaid;
	@Column(name = "TREENAME", nullable = false, columnDefinition = "varchar(200)")
	private String treename;
}
