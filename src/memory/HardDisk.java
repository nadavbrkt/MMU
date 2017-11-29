package memory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.logging.Level;

import util.HardDiskInputStream;
import util.MMULogger;

public class HardDisk {
	
	// Singleton
	private static final HardDisk _instance = new HardDisk();
	private HardDisk(){
			
	}
	public static HardDisk getInstance() { 
		return _instance;
	}
	
	// Data members
	final static String DEFAULT_FILE_NAME = "hdPages.txt";
	final static int _SIZE = 1000;
	private HashMap<Integer, Page<byte[]>> hdObject;
		
	
	// Methods
	
	// Return page from HD and delete it
	public Page<byte[]> pageFault(int pageId){
		try{
			HardDiskInputStream in = new HardDiskInputStream(new FileInputStream(DEFAULT_FILE_NAME));
			hdObject = (HashMap<Integer, Page<byte[]>>) in.readAllPages();
			in.close();
			
			// Removes page from HD
			Page<byte[]> tmp = hdObject.remove(pageId);
			
			writeHdObject(hdObject);
			
			MMULogger.getInstance().write("PF " + pageId + "\n", Level.INFO);
			
			return tmp;
		}
		catch (Exception e){
			MMULogger.getInstance().write(e.getMessage(), Level.SEVERE);
			return null;
		}
	}
	
	// Return page from HD, delete it from HD, and write page from ram to HD
	public Page<byte[]> pageReplacement(Page<byte[]> moveToHdPage, Integer moveToRamId){
		try{
			HardDiskInputStream in = new HardDiskInputStream(new FileInputStream(DEFAULT_FILE_NAME));
			hdObject = (HashMap<Integer, Page<byte[]>>) in.readAllPages();
			in.close();
			
			Page<byte[]> tmp = hdObject.remove(moveToRamId);
			hdObject.put(moveToHdPage.getPageId(), moveToHdPage);
			
			writeHdObject(hdObject);
			
			MMULogger.getInstance().write("PR MTH " + moveToHdPage.getPageId() + " MTR "
					+ moveToRamId + "\n", Level.INFO);
			
			return tmp;
		}
		catch (Exception e){
			MMULogger.getInstance().write(e.getMessage(), Level.SEVERE);
			return null;			
		}
	}
	
	// Write changes to file
	private void writeHdObject(HashMap<Integer, Page<byte[]>> hdMap) throws FileNotFoundException, IOException{
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(DEFAULT_FILE_NAME));
		out.writeObject(hdMap);
		out.close();
	}
}
