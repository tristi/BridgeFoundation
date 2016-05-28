package tristi.bridge;

import java.io.File;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOCase;
import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.comparator.NameFileComparator;
import org.apache.commons.io.comparator.SizeFileComparator;

public final class Comparator {
	
	private static final String PARENT_DIR="/home/heri/Bridge";
	
	private static final String IN_FILE_PATH="/home/heri/Bridge/in/in.txt";
	private static final String IN_PARENT_DIR="/home/heri/Bridge/in";
	
	private static final String OUT_FILE_PATH="/home/heri/Bridge/out/out.txt";
	private static final String OUT_PARENT_DIR="/home/heri/Bridge/out";
	
	public static void run(){
System.out.println("Comparator Running....");
        
        //NameFileComparator
        
        // Let's get a directory as a File object
        // and sort all its files.
        File parentDir = FileUtils.getFile(PARENT_DIR);
        NameFileComparator comparator = new NameFileComparator(IOCase.SENSITIVE);
        File[] sortedFiles = comparator.sort(parentDir.listFiles());
        
        System.out.println("Sorted by name files in parent directory: ");
        for (File file: sortedFiles) {
            System.out.println("\t"+ file.getAbsolutePath());
        }
        
        
        // SizeFileComparator
        
        // We can compare files based on their size.
        // The boolean in the constructor is about the directories.
        //      true: directory's contents count to the size.
        //      false: directory is considered zero size.
        SizeFileComparator sizeComparator = new SizeFileComparator(true);
        File[] sizeFiles = sizeComparator.sort(parentDir.listFiles());
        
        System.out.println("Sorted by size files in parent directory: ");
        for (File file: sizeFiles) {
            System.out.println("\t"+ file.getName() + " with size (kb): " + file.length());
        }
        
        
        // LastModifiedFileComparator
        
        // We can use this class to find which file was more recently modified.
        LastModifiedFileComparator lastModified = new LastModifiedFileComparator();
        File[] lastModifiedFiles = lastModified.sort(parentDir.listFiles());
        
        System.out.println("Sorted by last modified files in parent directory: ");
        for (File file: lastModifiedFiles) {
            Date modified = new Date(file.lastModified());
            System.out.println("\t"+ file.getName() + " last modified on: " + modified);
        }
        
        // Or, we can also compare 2 specific files and find which one was last modified.
        //      returns > 0 if the first file was last modified.
        //      returns  0)
//            System.out.println("File " + file1.getName() + " was modified last because...");
//        else
//            System.out.println("File " + file2.getName() + "was modified last because...");
//        
//        System.out.println("\t"+ file1.getName() + " last modified on: " +
//                new Date(file1.lastModified()));
//        System.out.println("\t"+ file2.getName() + " last modified on: " +
//                new Date(file2.lastModified()));
	}

}
