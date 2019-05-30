package com.arefia.lamm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Data;

@IdClass(flexdetailComposite.class)
@Entity
@Table(name = "FLEX_DETAIL")
@Data
public class flexdetailEntity {
	@Id
    @Column(name = "UID", columnDefinition = "varchar(210)")
    private String uid;
	@Id
    @Column(name = "BLOCKNUM", columnDefinition = "int")
	private Integer blocknum;
    @Column(name = "BLOCKTYPE", nullable = true, columnDefinition = "varchar(10)")
    private String blocktype;
    @Column(name = "BLOCKCONTENT", nullable = true, columnDefinition = "varchar(1000)")
    private String blockcontent;
    @Column(name = "BLOCKLINK", nullable = true, columnDefinition = "varchar(1000)")
    private String blocklink;
}