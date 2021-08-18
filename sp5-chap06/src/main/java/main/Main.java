package main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import config.AppCtx;
import spring.Client;

public class Main {

	public static void main(String[] args) {
		AbstractApplicationContext ctx =
			new AnnotationConfigApplicationContext(AppCtx.class);
		
		Client client = ctx.getBean("client", Client.class);
		client.send();
		
		ctx.close();
	}

}
