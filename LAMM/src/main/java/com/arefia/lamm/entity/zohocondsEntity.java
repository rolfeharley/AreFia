package com.arefia.lamm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "ZOHO_QRY_CONDS")
@Data
public class zohocondsEntity {
	@Id
    @Column(name = "GUID", columnDefinition = "varchar(200)")
    private String guid;
	@Column(name = "DISPLAY_NAME", nullable = false, columnDefinition = "varchar(200)")
	private String display_name;
	@Column(name = "VALUE", nullable = false, columnDefinition = "varchar(200)")
	private String value;
	@Column(name = "ORDER", nullable = false, columnDefinition = "int")
	private int order;
}
