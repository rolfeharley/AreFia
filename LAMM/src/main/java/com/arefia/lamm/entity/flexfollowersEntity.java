package com.arefia.lamm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.Data;

@Entity
@Table(name = "FLEX_FOLLOWERS")
@Data
public class flexfollowersEntity {
	@Id
    @Column(name = "FID", columnDefinition = "varchar(200)")
    private String fid;
    @Column(name = "UID", nullable = false, columnDefinition = "varchar(200)")
    private String uid;	
    @Column(name = "FOLLOWERID", nullable = false, columnDefinition = "varchar(500)")
    private String followerid;
    @Column(name = "SENDTIME", nullable = false, columnDefinition = "datetime")
    private String sendtime;
}
