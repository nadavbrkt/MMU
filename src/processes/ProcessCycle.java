package processes;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ProcessCycle {
	
	// Data members
	private List<Integer> pages;
	private int sleepMs;
	private List<byte[]> data;
	
	// C'tor
	public ProcessCycle(List<Integer> pages, int sleepMs, List<byte[]> data){
		this.pages = pages;
		this.sleepMs = sleepMs;
		this.data = data;
	}
	
	// Methods
	
	// Get pages
	public List<Integer> getPages() {
		return pages;
	}
	
	// Set pages
	public void setPages(List<Integer> pages) {
		this.pages = pages;
	}
	
	// Get sleepMs
	public int getSleepMs() {
		return sleepMs;
	}
	
	// Set sleepMs
	public void setSleepMs(int sleepMs) {
		this.sleepMs = sleepMs;
	}
	
	// Get data
	public List<byte[]> getData() {
		return data;
	}

	//
	public String toString(){
		List<String> dataToString = new LinkedList<>();
		
		for(byte[] d : data)
			dataToString.add(Arrays.toString(d));
		
		return "Pages : " + pages + "\n" +
			   "Sleep : " + sleepMs + "\n" +
			   "Data  : " + dataToString + "\n";
	}

}
