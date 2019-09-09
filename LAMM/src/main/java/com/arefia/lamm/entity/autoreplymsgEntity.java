package com.arefia.lamm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "AUTO_REPLY_MSG")
@Data
public class autoreplymsgEntity {
	@Id
    @Column(name = "AID", columnDefinition = "varchar(200)")
    private String aid;
	//1.Welcome 2.Birthday 3.Holiday 4.Routing 5.Custom
	@Column(name = "AUTO_TYPE", nullable = false, columnDefinition = "varchar(2)")
	private String auto_type;
	@Column(name = "MSG", nullable = true, columnDefinition = "varchar(2000)")
	private String msg;
	@Column(name = "IMAGE", nullable = true, columnDefinition = "varchar(2000)")
	private String image;
	@Column(name = "LINK", nullable = true, columnDefinition = "varchar(2000)")
	private String link;
	@Column(name = "BUTTON", nullable = true, columnDefinition = "varchar(20)")
	private String button;
}
