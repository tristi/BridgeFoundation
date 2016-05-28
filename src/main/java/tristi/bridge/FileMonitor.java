package tristi.bridge;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileDeleteStrategy;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.apache.commons.io.monitor.FileEntry;

public final class FileMonitor {
	
	private static final String PARENT_DIR="/home/heri/Bridge";
	
	private static final String IN_FILE_PATH="/home/heri/Bridge/in/in.txt";
	private static final String IN_PARENT_DIR="/home/heri/Bridge/in";
	
	private static final String OUT_FILE_PATH="/home/heri/Bridge/out/out.txt";
	private static final String OUT_PARENT_DIR="/home/heri/Bridge/out";
	
	public static void run(){
		System.out.println("File Monitor Running....");
		
		FileEntry entry = new FileEntry(FileUtils.getFile(IN_FILE_PATH));
		
		System.out.println("File monitored: "+entry.getFile());
		System.out.println("File name: "+entry.getName());
		System.out.println("Is the file a directory ?: "+entry.isDirectory());
		
		File inDir = FileUtils.getFile(PARENT_DIR);
		
		FileAlterationObserver observer = new FileAlterationObserver(inDir);
		observer.addListener(new FileAlterationListenerAdaptor(){
			public void onFileCreate(final File file) {
				System.out.println("File created: "+file.getName());
		    }
			public void onFileDelete(final File file) {
				System.out.println("File deleted: "+file.getName());
			}
			public void onDirectoryCreate(final File directory) {
				System.out.println("Directory created: "+directory.getName());
		    }
			public void onDirectoryDelete(final File directory) {
				System.out.println("Directory deleted: "+directory.getName());
		    } 
		});
		
		FileAlterationMonitor monitor = new FileAlterationMonitor(500, observer);
        try {
            monitor.start();
        
            // After we attached the monitor, we can create some files and directories
            // and see what happens!
            File newDir = new File(OUT_PARENT_DIR);
            File newFile = new File(OUT_FILE_PATH);
            
            newDir.mkdirs();
            newFile.createNewFile();
                
            Thread.sleep(1000);
            
            FileDeleteStrategy.FORCE.delete(newDir);
            FileDeleteStrategy.FORCE.delete(newFile);
            
            Thread.sleep(1000);
            
            monitor.stop();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	

}
