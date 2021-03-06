package com.apri.common.masterHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * KeyValueHelperをService付きで生成する。
 */
@Component
public class KeyValueFactory {
	@Autowired
	private AutowireCapableBeanFactory beanFactory;
	
	public KeyValueHelper newInstance() {
		KeyValueHelper bean = new KeyValueHelper();
		beanFactory.autowireBean(bean);
		return bean;
	} 
}
