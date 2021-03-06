package com.arefia.lamm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "CONTACTS_QRY_CONDS")
@Data
public class contactscondsEntity {
	@Id
    @Column(name = "GUID", columnDefinition = "varchar(200)")
    private String guid;
	@Column(name = "DISPLAY_NAME", nullable = false, columnDefinition = "varchar(200)")
	private String display_name;
	@Column(name = "VALUE", nullable = false, columnDefinition = "varchar(200)")
	private String value;
	@Column(name = "ORDER_NUM", nullable = false, columnDefinition = "int")
	private int order_num;
	@Column(name = "SYSTEM_TYPE", nullable = false, columnDefinition = "varchar(10)")
	private String system_type;
}
