package com.anzisolutions.bankingsimulator;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.anzisolutions.bankingsimulator.bankingsystem.BankingSystem;
import com.anzisolutions.bankingsimulator.client.ClientBase;
import com.anzisolutions.bankingsimulator.thread.Controller;

public class Simulator {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		Controller controller = context.getBean("controller", Controller.class);
		ClientBase clientBase = (ClientBase) context.getBean("clientBase", ClientBase.class); 	
		BankingSystem bankingSystem = context.getBean("bankingSystem", BankingSystem.class);
		
		bankingSystem.start(2);
		clientBase.start(2);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(controller.finish());
		((AbstractApplicationContext) context).close();
	}
}
