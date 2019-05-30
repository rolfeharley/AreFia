package com.arefia.bcs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "USER_LIST")
@Data
public class userEntity {
    @Id
    @Column(name = "ACCOUNTID", nullable = false, columnDefinition = "varchar(200)")
    private String accountid;
    @Column(name = "USERNAME", nullable = false, columnDefinition = "varchar(500)")
    private String username;
    @Column(name = "PASSWD", nullable = false, columnDefinition = "varchar(1000)")
    private String passwd;
    @Column(name = "ISENABLE", nullable = false, columnDefinition = "varchar(2)")
    private String isenable;
    @Column(name = "IDENTITY", columnDefinition = "varchar(50)")
    private String identity;
    @Column(name = "GENDER", columnDefinition = "varchar(2)")
    private String gender;
    @Column(name = "BIRTHDAY", columnDefinition = "varchar(20)")
    private String birthday;
    @Column(name = "EMAIL", columnDefinition = "varchar(500)")
    private String email;
    @Column(name = "WORKTYPE", nullable = false, columnDefinition = "varchar(2)")
    private String worktype;
    @Column(name = "REFERRER", nullable = false, columnDefinition = "varchar(100)")
    private String referrer;
    @Column(name = "REFERRERID", nullable = false, columnDefinition = "varchar(50)")
    private String referrerid;
    @Column(name = "CREATEDATE", columnDefinition = "date")
    private String createdate;
    @Column(name = "CREATOR", columnDefinition = "varchar(100)")
    private String creator;
    @Column(name = "DISPLAYLIST", columnDefinition = "varchar(2)")
    private String displaylist;
    @Column(name = "ADDRESS", columnDefinition = "varchar(1000)")
    private String address;
}
