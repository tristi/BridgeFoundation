package tristi.bridge;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;

import org.apache.commons.io.FileSystemUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOCase;
import org.apache.commons.io.LineIterator;

public final class Utility {
	
	private static final String IN_FILE_PATH="/home/heri/Bridge/in/in.txt";
	private static final String IN_PARENT_DIR="/home/heri/Bridge/in";
	
	public static void run() throws IOException{
		System.out.println("Utility Running....");
		
		//filename utils
		System.out.println("Full path of file: "+FilenameUtils.getFullPath(IN_FILE_PATH));
		System.out.println("Full name of file: "+FilenameUtils.getName(IN_FILE_PATH));
		System.out.println("Extendsion of File: "+FilenameUtils.getExtension(IN_FILE_PATH));
		
		File file = FileUtils.getFile(IN_FILE_PATH);
		LineIterator iter = FileUtils.lineIterator(file);
		
		System.out.println("Content of File....");
		while(iter.hasNext()){
			System.out.println("\t"+iter.next());
		}
		iter.close();
		
		File parent = FileUtils.getFile(IN_PARENT_DIR);
		System.out.println("Parent directory contains file: "+FileUtils.directoryContains(parent, file));
		
		String text1 = "This is a new String.";
		String text2 = "This is another new String, Yes !";
		
		System.out.println("Ends with string (case sensitive): "+IOCase.SENSITIVE.checkEndsWith(text1, "string."));
		System.out.println("Ends with string (case insensitive): "+IOCase.INSENSITIVE.checkEndsWith(text1, "string."));
		System.out.println("String equality: "+IOCase.SENSITIVE.checkEquals(text1, text2));
		
		System.out.println("Free disk space in (KB): "+FileSystemUtils.freeSpaceKb("/home"));
		System.out.println("Free disk space in (MB): "+FileSystemUtils.freeSpaceKb("/home")/1024);
	}

}
