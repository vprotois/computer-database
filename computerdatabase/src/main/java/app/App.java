package app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controler.InterfaceControler;

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
			new InterfaceControler().start();
			log.info("App closing");
    }
}
