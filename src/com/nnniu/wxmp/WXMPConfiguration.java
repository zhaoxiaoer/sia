package com.nnniu.wxmp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.nnniu.wxmp.msgandevent.ImageMessage;
import com.nnniu.wxmp.msgandevent.TextMessage;

@Configuration
public class WXMPConfiguration { 

	@Bean
	public Jaxb2Marshaller jaxb2Marshaller() {
		Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
		jaxb2Marshaller.setClassesToBeBound(new Class[]{
				TextMessage.class,
				ImageMessage.class
			});
		return jaxb2Marshaller;
	}
	
}
