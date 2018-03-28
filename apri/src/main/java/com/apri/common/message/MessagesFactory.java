package com.apri.common.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class MessagesFactory {
	@Autowired
	private AutowireCapableBeanFactory beanFactory;
	
	public Messages newInstance() {
		Messages messages = new Messages();
		beanFactory.autowireBean(messages);
		return messages;
	} 
}
