package app;

import controler.InterfaceControler;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {	
    	try {
			InterfaceControler run = new InterfaceControler();
			run.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
        
    }
}
