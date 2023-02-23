package com.showroom.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class SpringbootShowroomApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringbootShowroomApplication.class, args);
		ApplicationContext context = new ClassPathXmlApplicationContext("Config.xml");
		ApplicationManager applicationManager = (ApplicationManager) context.getBean("ApplicationManagerBean");
		applicationManager.start();
	}

}
