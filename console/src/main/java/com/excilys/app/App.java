package com.excilys.app;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.GenericApplicationContext;

import com.excilys.console.CLIControler;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
/**
 * Hello world!
 *
 */
public class App 
{
	private static Logger log= LoggerFactory.getLogger(App.class);
	
	public static void main( String[] args )
    {
			log.info("App starting");
			GenericApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
			CLIControler controler = context.getBean(CLIControler.class);
			controler.start();
			context.close();
    }
}
