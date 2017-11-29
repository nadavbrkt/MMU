package util;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.Map;

import memory.Page;

// 
public class HardDiskInputStream extends ObjectInputStream{
	
	// Data members
	
	
	// C'tor
	public HardDiskInputStream(InputStream in) throws IOException{
		super(in);
	}
	
	// Read all pages from hdInputStream
	@SuppressWarnings("unchecked")
	public Map<Integer, Page<byte[]>> readAllPages() throws ClassNotFoundException, IOException{
		return (Map<Integer, Page<byte[]>>) this.readObject();
	}
		
	
	// Read one page by pageId from hdInputStream
	@SuppressWarnings("unchecked")
	public Page<byte[]> readSinglePage(Integer pageId) throws ClassNotFoundException, IOException{
		Map<Integer, Page<byte[]>> tmp = (Map<Integer, Page<byte[]>>) this.readObject();
		return tmp.get(pageId);
	}	
}