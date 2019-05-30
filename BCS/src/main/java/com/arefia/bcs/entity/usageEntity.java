package com.arefia.bcs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Entity
@Table(name = "APP_USAGE")
@Data
public class usageEntity {
	@Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native", strategy = "native")
	@Column(name = "UID", columnDefinition = "varchar(200)")
    private String uid;
	@Column(name = "APPID", nullable = false, columnDefinition = "varchar(200)")
	private String appid;
	@Column(name = "ACCOUNTID", nullable = false, columnDefinition = "varchar(100)")
    private String accountid;
	@Column(name = "VISITDATE", columnDefinition = "date")
    private String visitdate;
	@Column(name = "LEAVEDATE", columnDefinition = "date")
    private String leavedate;
}
