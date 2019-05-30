package com.arefia.lamm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "SYS_INFO")
@Data
public class sysinfoEntity {
	@Id
	@Column(name = "UID", nullable = false, columnDefinition = "varchar(200)")
	private String uid;
	@Column(name = "CHANNEL_TOKEN", nullable = false, columnDefinition = "varchar(1000)")
    private String channel_token;
	@Column(name = "CHANNEL_SECRET", nullable = false, columnDefinition = "varchar(100)")
    private String channel_secret;
	@Column(name = "SECURITY_URL", nullable = false, columnDefinition = "varchar(500)")
    private String security_url;
	@Column(name = "ZOHO_CLIENTID", nullable = true, columnDefinition = "varchar(200)")
    private String zoho_clientid;
	@Column(name = "ZOHO_SECRET", nullable = true, columnDefinition = "varchar(200)")
    private String zoho_secret;
	@Column(name = "ZOHO_REFRESH_CODE", nullable = true, columnDefinition = "varchar(500)")
    private String zoho_refresh_code;
}
