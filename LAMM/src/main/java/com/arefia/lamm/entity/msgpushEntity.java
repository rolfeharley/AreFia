package com.arefia.lamm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Entity
@Table(name = "MSG_PUSH")
@Data
public class msgpushEntity {
	@Id
	@GenericGenerator(name = "aRefiaGenetor", strategy = "com.arefia.lamm.utility.identityGenerator")
    @GeneratedValue(strategy= GenerationType.IDENTITY, generator="aRefiaGenetor")
    @Column(name = "PUSHID", nullable = false, columnDefinition = "varchar(200)")
    private String pushid;
	@Column(name = "SOURCERID", nullable = false, columnDefinition = "varchar(200)")
    private String sourcerid;
	@Column(name = "MSGTYPE", nullable = false, columnDefinition = "varchar(50)")
    private String msgtype;
	@Column(name = "MSGID", nullable = false, columnDefinition = "varchar(300)")
    private String msgid;
	@Column(name = "MSG", nullable = true, columnDefinition = "varchar(10000)")
    private String msg;
	@Column(name = "PUSHUSER", nullable = true, columnDefinition = "varchar(100)")
    private String pushuser;
	@Column(name = "PUSHTIME", nullable = false, columnDefinition = "datetime")
    private String pushtime;
	@Column(name = "CHECKFLAG", nullable = false, columnDefinition = "int")
	private String checkflag;
}
