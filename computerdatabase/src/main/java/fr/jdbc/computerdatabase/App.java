package fr.jdbc.computerdatabase;


import model.ComputerDB;

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
			ComputerDB db = new ComputerDB();
			//db.showCompDetails(3);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
    }
}
