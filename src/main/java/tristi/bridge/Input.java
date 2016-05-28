package tristi.bridge;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.input.TeeInputStream;
import org.apache.commons.io.input.XmlStreamReader;

public final class Input {
	
	private static final String PARENT_DIR="/home/heri/Bridge";
	
	private static final String IN_FILE_PATH="/home/heri/Bridge/in/in.xml";
	private static final String IN_PARENT_DIR="/home/heri/Bridge/in";
	
	private static final String OUT_FILE_PATH="/home/heri/Bridge/out/out.txt";
	private static final String OUT_PARENT_DIR="/home/heri/Bridge/out";
	
	private static final String INPUT = "Select * from his_menu_list";
	
	public static void run(){
		System.out.println("Input Running....");
		 XmlStreamReader xmlReader = null;
	        TeeInputStream tee = null;
	        
	        try {
	            
	            // XmlStreamReader
	            
	            // We can read an xml file and get its encoding.
	            File xml = FileUtils.getFile(IN_FILE_PATH);
	            
	            xmlReader = new XmlStreamReader(xml);
	            System.out.println("XML encoding: " + xmlReader.getEncoding());
	            
	            
	            // TeeInputStream
	            
	            // This very useful class copies an input stream to an output stream
	            // and closes both using only one close() method (by defining the 3rd
	            // constructor parameter as true).
	            ByteArrayInputStream in = new ByteArrayInputStream(INPUT.getBytes("US-ASCII"));
	            ByteArrayOutputStream out = new ByteArrayOutputStream();
	            
	            tee = new TeeInputStream(in, out, true);
	            tee.read(new byte[INPUT.length()]);

	            System.out.println("Output stream: " + out.toString());         
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            try { xmlReader.close(); }
	            catch (IOException e) { e.printStackTrace(); }
	            
	            try { tee.close(); }
	            catch (IOException e) { e.printStackTrace(); }
	        }
	}

}
