package com.arefia.bcs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Data;

@IdClass(agentComposite.class)
@Entity
@Table(name = "AGENT_LIST")
@Data
public class agentEntity {
	@Id
    @Column(name = "ACCOUNTID", columnDefinition = "varchar(200)")
    private String accountid;
    @Column(name = "MOBILE", columnDefinition = "varchar(50)")
    private String mobile;
    @Id
    @Column(name = "IMTYPE", columnDefinition = "varchar(2)")
    private String imtype;
    @Id
    @Column(name = "IMID", columnDefinition = "varchar(100)")
    private String imid;
    @Column(name = "SHOPNAME", columnDefinition = "varchar(1000)")
    private String shopname;	
    @Column(name = "REFERRER", nullable = false, columnDefinition = "varchar(100)")
    private String referrer;
    @Column(name = "REFERRERID", nullable = false, columnDefinition = "varchar(50)")
    private String referrerid;
    @Column(name = "JOINEDDATE", columnDefinition = "varchar(2)")
    private String joineddate;
    @Column(name = "REMARK", columnDefinition = "varchar(100)")
    private String remark;
}
