package com.arefia.lamm.utility;

import java.io.Serializable;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class identityGenerator implements IdentifierGenerator {
	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		String prifixStr = "aRefia_GUID_";
		String dynamicGuid = UUID.randomUUID().toString();
		
		return prifixStr + dynamicGuid;
	}
}
