package com.arefia.lamm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "LINE_FOLLOWERS")
@Data
public class followersEntity {
	@Id
    @Column(name = "USERID", columnDefinition = "varchar(500)")
    private String userid;
    @Column(name = "DISPLAYNAME", nullable = false, columnDefinition = "varchar(1000)")
    private String displayname;
    @Column(name = "PICTUREURL", nullable = false, columnDefinition = "varchar(2000)")
    private String pictureurl;
    @Column(name = "STATUSMESSAGE", nullable = true, columnDefinition = "varchar(1000)")
    private String statusmessage;
}
