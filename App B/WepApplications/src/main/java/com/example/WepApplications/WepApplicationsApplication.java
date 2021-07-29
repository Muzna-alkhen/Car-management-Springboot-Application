package com.example.WepApplications;

import com.example.WepApplications.service.InventoryService;
import com.google.common.collect.Lists;
import com.mysql.cj.protocol.Message;
import com.rabbitmq.client.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.internet.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeoutException;

@SpringBootApplication


public class WepApplicationsApplication {


	public static void main(String[] args) throws IOException, TimeoutException {
		SpringApplication.run(WepApplicationsApplication.class, args);


	}

	@Component
	public class CommandLineAppStartupRunner implements CommandLineRunner {
		@Autowired
		private InventoryService myService;

		@Override
		public void run(String...args) throws Exception {
			myService.doInventory();

		}
	}

}
