package com.arefia.lamm.websocket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import com.arefia.lamm.model.linePushMessageObject;
import com.arefia.lamm.service.messagePush;

@Controller
public class websocketPushMessage {
	private static final Logger log = LogManager.getLogger(websocketPushMessage.class);
	@Autowired
    messagePush mps;
	
	@MessageMapping("/pushMessage")
	public void pushMessage(linePushMessageObject message) {
		mps.push(message.getSourcer(), message.getMessageType(), message.getFilename(), message.getMessage(), message.getPusher(), message.getPushfid());
	}
}
