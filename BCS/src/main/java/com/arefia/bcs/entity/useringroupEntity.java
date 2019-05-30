package com.arefia.bcs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Data;

@IdClass(useringroupComposite.class)
@Entity
@Table(name = "USER_IN_GROUP")
@Data
public class useringroupEntity {
	@Id
    @Column(name = "ACCOUNTID", columnDefinition = "varchar(200)")
    private String accountid;
	@Id
    @Column(name = "GROUPID", columnDefinition = "varchar(200)")
    private String groupid;
	@Column(name = "CREATEDATE", columnDefinition = "date")
    private String createdate;
    @Column(name = "CREATOR", columnDefinition = "varchar(100)")
    private String creator;
}
