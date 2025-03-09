package com.example.AOP_XML;

import com.example.AOP_XML.service.BankService;
import com.example.AOP_XML.service.Hello;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopXmlApplication {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
		Hello hello = context.getBean("helloService",Hello.class);
		hello.sayhello();

		BankService service = context.getBean("bankService",BankService.class);
		service.deposit("89200GH",6799221.12);
		service.withdraw("8903NKSL",782902.345);
		service.newOnboard("8973RTYW");
	}

}
