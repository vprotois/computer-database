package app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import controler.CLIControler;

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
