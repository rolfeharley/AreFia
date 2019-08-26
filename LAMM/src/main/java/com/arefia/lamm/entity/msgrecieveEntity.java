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
@Table(name = "MSG_RECIEVE")
@Data
public class msgrecieveEntity {
	@Id
    @GenericGenerator(name = "aRefiaGenetor", strategy = "com.arefia.lamm.utility.identityGenerator")
    @GeneratedValue(strategy= GenerationType.IDENTITY, generator="aRefiaGenetor")
    @Column(name = "RECIEVEID", nullable = false, columnDefinition = "varchar(200)")
    private String recieveid;
	@Column(name = "SOURCERTYPE", nullable = false, columnDefinition = "varchar(100)")
    private String sourcertype;
	@Column(name = "SOURCERID", nullable = false, columnDefinition = "varchar(200)")
    private String sourcerid;
	@Column(name = "REPLYTOKEN", nullable = false, columnDefinition = "varchar(200)")
	private String replytoken;
	@Column(name = "MSGTYPE", nullable = false, columnDefinition = "varchar(50)")
    private String msgtype;
	@Column(name = "FILEEXT", nullable = true, columnDefinition = "varchar(30)")
	private String fileext;
	@Column(name = "MSGID", nullable = false, columnDefinition = "varchar(300)")
    private String msgid;
	@Column(name = "MSG", nullable = true, columnDefinition = "varchar(10000)")
    private String msg;
	@Column(name="DURATION", nullable = true,  columnDefinition = "varchar(10)")
	private String duration;
	@Column(name = "RECIEVETIME", nullable = false, columnDefinition = "datetime")
    private String recievetime;
	@Column(name = "CHECKFLAG", nullable = false, columnDefinition = "int")
	private String chkflag;
}
