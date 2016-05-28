package tristi.bridge;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
       try {
		Utility.run();
		//FileMonitor.run();
		//Filter.run();
		//Comparator.run();
		Input.run();
		//Output.run();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }
}
