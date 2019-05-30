package com.arefia.bcs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "IMTYPE")
@Data
public class imtypeEntity {
	@Id
    @Column(name = "IMUID", columnDefinition = "varchar(200)")
    private String imuid;
    @Column(name = "IMTYPE", columnDefinition = "varchar(500)")
    private String imtype;
}
