package com.arefia.lamm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "FLEX_MAIN")
@Data
public class flexmainEntity {
	@Id
    @Column(name = "UID", columnDefinition = "varchar(200)")
    private String uid;
    @Column(name = "FLEXMOD", nullable = false, columnDefinition = "varchar(10)")
    private String flexmod;
    @Column(name = "TITLE", nullable = false, columnDefinition = "varchar(200)")
    private String title;
    @Column(name = "CREATEDATE", nullable = false, columnDefinition = "date")
    private String createdate;
    @Column(name = "CREATOR", nullable = false, columnDefinition = "varchar(50)")
    private String creator;
    @Column(name = "FLEXOBJSTR", nullable = false, columnDefinition = "varchar(20000)")
    private String flexobjstr;
}
