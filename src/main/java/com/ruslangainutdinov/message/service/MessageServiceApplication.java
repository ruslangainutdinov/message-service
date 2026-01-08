package com.ruslangainutdinov.message.service;

import com.ruslangainutdinov.message.service.config.MyRuntimeHints;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportRuntimeHints;

@ImportRuntimeHints(MyRuntimeHints.class)
@SpringBootApplication
public class MessageServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessageServiceApplication.class, args);
	}

}
