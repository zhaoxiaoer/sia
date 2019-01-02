package com.nnniu.wxmp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.nnniu.wxmp.msgandevent.ImageMessage;
import com.nnniu.wxmp.msgandevent.NormalEvent;
import com.nnniu.wxmp.msgandevent.QrscanEvent;
import com.nnniu.wxmp.msgandevent.QrsubEvent;
import com.nnniu.wxmp.msgandevent.TextMessage;
import com.nnniu.wxmp.msgandevent.VideoMessage;
import com.nnniu.wxmp.msgandevent.VoiceMessage;

@Configuration
public class WXMPConfiguration { 

	@Bean
	public Jaxb2Marshaller jaxb2Marshaller() {
		Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
		jaxb2Marshaller.setClassesToBeBound(new Class[]{
				TextMessage.class,
				ImageMessage.class,
				VoiceMessage.class,
				VideoMessage.class,
				NormalEvent.class,
				QrsubEvent.class,
				QrscanEvent.class
			});
		return jaxb2Marshaller;
	}
	
}
