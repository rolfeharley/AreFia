package com.arefia.bcs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Data;

@IdClass(bankComposite.class)
@Entity
@Table(name = "BANK_RECORD")
@Data
public class bankEntity {
	@Id
    @Column(name = "ACCOUNTID", nullable = false, columnDefinition = "varchar(200)")
    private String accountid;
	@Id
    @Column(name = "BANKCODE", nullable = false, columnDefinition = "varchar(20)")
    private String bankcode;
	@Id
    @Column(name = "BANKACCOUNT", nullable = false, columnDefinition = "varchar(30)")
    private String bankaccount;
	@Column(name = "ISENABLED", nullable = false, columnDefinition = "varchar(2)")
    private String isenabled;
    @Column(name = "ISDEFAULT", nullable = false, columnDefinition = "varchar(2)")
    private String isdefault;
}
