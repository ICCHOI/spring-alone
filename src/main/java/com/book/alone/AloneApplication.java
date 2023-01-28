package com.book.alone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;

@SpringBootApplication
public class AloneApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(AloneApplication.class);
		application.addListeners(new ApplicationPidFileWriter());
		application.run(args);
	}

}
