package fr.jdbc.computerdatabase;


import ui.InterfaceConsole;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {	
    	System.out.println( "Hello World!" );
    	try {
			InterfaceConsole i = new InterfaceConsole();
			i.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
        
    }
}
