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
			new InterfaceControler().start();
		} catch (Exception e) {
			e.printStackTrace();
		}
        
    }
}
