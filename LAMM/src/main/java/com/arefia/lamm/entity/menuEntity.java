package com.arefia.lamm.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class menuEntity {
	@Id
    private String appid;
    private String appname;
    private String appurl;
}