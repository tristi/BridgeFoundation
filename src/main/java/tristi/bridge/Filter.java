package tristi.bridge;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOCase;
import org.apache.commons.io.filefilter.AndFileFilter;
import org.apache.commons.io.filefilter.NameFileFilter;
import org.apache.commons.io.filefilter.NotFileFilter;
import org.apache.commons.io.filefilter.OrFileFilter;
import org.apache.commons.io.filefilter.PrefixFileFilter;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.apache.commons.io.filefilter.WildcardFileFilter;

public final class Filter {
	
	private static final String PARENT_DIR="/home/heri/Bridge";
	
	private static final String IN_FILE_PATH="/home/heri/Bridge/in/in.txt";
	private static final String IN_PARENT_DIR="/home/heri/Bridge/in";
	
	private static final String OUT_FILE_PATH="/home/heri/Bridge/out/out.txt";
	private static final String OUT_PARENT_DIR="/home/heri/Bridge/out";
	
	public static void run() {
        System.out.println("File Filter Running....");
        
        File dir = FileUtils.getFile(PARENT_DIR);
        String[] acceptedNames = {"in", "in.txt"};
        for (String file: dir.list(new NameFileFilter(acceptedNames,IOCase.INSENSITIVE))) {
            System.out.println("File found, named: " + file);
        }
        
        
        //WildcardFileFilter
        // We can use wildcards in order to get less specific results
        //      ? used for 1 missing char
        //      * used for multiple missing chars
        for (String file: dir.list(new WildcardFileFilter("*in*"))) {
            System.out.println("Wildcard file found, named: " + file);
        }
        
        
        // PrefixFileFilter 
        // We can also use the equivalent of startsWith
        // for filtering files.
        for (String file: dir.list(new PrefixFileFilter("in"))) {
            System.out.println("Prefix file found, named: " + file);
        }
        
        
        // SuffixFileFilter
        // We can also use the equivalent of endsWith
        // for filtering files.
        for (String file: dir.list(new SuffixFileFilter(".txt"))) {
            System.out.println("Suffix file found, named: " + file);
        }
        
        
        // OrFileFilter 
        // We can use some filters of filters.
        // in this case, we use a filter to apply a logical 
        // or between our filters.
        for (String file: dir.list(new OrFileFilter(
                new WildcardFileFilter("*i*"), new SuffixFileFilter(".txt")))) {
            System.out.println("Or file found, named: " + file);
        }
        
        // And this can become very detailed.
        // Eg, get all the files that have "ample" in their name
        // but they are not text files (so they have no ".txt" extension.
        for (String file: dir.list(new AndFileFilter( // we will match 2 filters...
                new WildcardFileFilter("*z*"), // ...the 1st is a wildcard...
                new NotFileFilter(new SuffixFileFilter(".txt"))))) { // ...and the 2nd is NOT .txt.
            System.out.println("And/Not file found, named: " + file);
        }
    }

}
