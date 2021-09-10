package com.skeqi.mes.common.lcy;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class getSomeValueByKey extends PropertyPlaceholderConfigurer{


	@Override
	protected String convertProperty(String propertyName, String propertyValue) {

		if("username".equals(propertyName)){
			return OracleEncrytion.getOraclePassword(propertyValue).toString();

		}
		if("password".equals(propertyName)){
			return OracleEncrytion.getOraclePassword(propertyValue).toString();
		}
		return propertyValue;
	}

}
