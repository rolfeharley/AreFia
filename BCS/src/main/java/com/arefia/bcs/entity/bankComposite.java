package com.arefia.bcs.entity;

import java.io.Serializable;

import javax.persistence.Column;

@SuppressWarnings("unused")
public class bankComposite implements Serializable {
	private static final long serialVersionUID = 1L;
	private String accountid;
    private String bankcode;
    private String bankaccount;
}
