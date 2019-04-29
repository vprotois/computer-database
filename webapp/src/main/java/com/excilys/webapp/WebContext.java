package com.excilys.webapp;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.excilys.app.AppConfig;

@Configuration
public class WebContext implements WebApplicationInitializer {
	
	public static AnnotationConfigWebApplicationContext context;
	
	public void onStartup(ServletContext container) throws ServletException {
		context = new AnnotationConfigWebApplicationContext();
		
		context.register(WebConfig.class);
		context.register(AppConfig.class);
		context.setServletContext(container);
		context.refresh();		

		container.addListener(new ContextLoaderListener(context));
	
		DispatcherServlet servlet = new DispatcherServlet(context);
		ServletRegistration.Dynamic registration = container.addServlet("computerdatabase", servlet);
		registration.setLoadOnStartup(1);
		registration.addMapping("/");

	}

	
	
	
}
