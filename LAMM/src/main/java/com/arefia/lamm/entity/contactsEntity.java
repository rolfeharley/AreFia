package com.arefia.lamm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "CONTACTS")
@Data
public class contactsEntity {
	@Id
    @Column(name = "CONTID", columnDefinition = "varchar(200)")
    private String contid;
	@Column(name = "CONTACT_NAME", nullable = true, columnDefinition = "varchar(100)")
	private String contact_name;
	@Column(name = "COMPANY", nullable = true, columnDefinition = "varchar(300)")
	private String company;
	@Column(name = "JOBTITLE", nullable = true, columnDefinition = "varchar(100)")
	private String jobtitle;
	@Column(name = "APPELLATION", nullable = true, columnDefinition = "varchar(10)")
	private String appellation;
	@Column(name = "GENDER", nullable = true, columnDefinition = "varchar(45)")
	private String gender;
	@Column(name = "BIRTHDAY", nullable = true, columnDefinition = "date")
	private String birthday;
	@Column(name = "MAIL", nullable = true, columnDefinition = "varchar(500)")
	private String mail;
	@Column(name = "COUNTRY", nullable = true, columnDefinition = "varchar(200)")
	private String country;
	@Column(name = "STATE", nullable = true, columnDefinition = "varchar(200)")
	private String state;
	@Column(name = "COUNTY", nullable = true, columnDefinition = "varchar(200)")
	private String county;
	@Column(name = "ADDRESS", nullable = true, columnDefinition = "varchar(1000)")
	private String address;
	@Column(name = "ZIPCODE", nullable = true, columnDefinition = "varchar(20)")
	private String zipcode;
	@Column(name = "CELLPHONE", nullable = true, columnDefinition = "varchar(20)")
	private String cellphone;
	@Column(name = "HOMETEL", nullable = true, columnDefinition = "varchar(20)")
	private String hometel;
	@Column(name = "COMPTEL", nullable = true, columnDefinition = "varchar(20)")
	private String comptel;
	@Column(name = "COMPEXT", nullable = true, columnDefinition = "varchar(10)")
	private String compext;
	@Column(name = "COMP_COUNTRY", nullable = true, columnDefinition = "varchar(200)")
	private String comp_country;
	@Column(name = "COMP_STATE", nullable = true, columnDefinition = "varchar(200)")
	private String comp_state;
	@Column(name = "COMP_COUNTY", nullable = true, columnDefinition = "varchar(200)")
	private String comp_county;
	@Column(name = "COMP_ADDRESS", nullable = true, columnDefinition = "varchar(1000)")
	private String comp_address;
	@Column(name = "COMP_ZIPCODE", nullable = true, columnDefinition = "varchar(20)")
	private String comp_zipcode;
	@Column(name = "LINE_UID", nullable = true, columnDefinition = "varchar(500)")
	private String line_uid;
}
