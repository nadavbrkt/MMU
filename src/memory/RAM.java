package memory;

import java.util.HashMap;
import java.util.logging.Level;

import util.MMULogger;


// RAM class represent computer ram unit
public class RAM {
	
	// Data members
	private HashMap<Integer,Page<byte[]>> pages;
	private int maxPages;
	
	// Methods
	
	// C'tor
	public RAM(int initialCapacity){
			pages = new HashMap<Integer,Page<byte[]>>();
			this.maxPages = initialCapacity;
			MMULogger.getInstance().write("RC " + this.maxPages + "\n", Level.INFO);
	}
	
	// Get page by pageId
	public Page<byte[]> getPage(int pageId){
		for (Page<byte[]> value : pages.values()){
			if (value.getPageId() == pageId){
				return value;
			}
		}
		
		return null;
	}
	
	// Add page to HashMap if there is a place
	public void addPage(Page<byte[]> addPage){
		if (pages.size() < maxPages){
			pages.put(this.getNextFreePlace(), addPage);
		}
	}
	
	// Remove page
	public void removePage(Page<byte[]> removePage){
		for (Integer key : pages.keySet()){
			if ((pages.get(key)).equals(removePage)){
				pages.remove(key);
				break;
			}
		}
	}
	
	// Get pages by pageIds
	public Page<byte[]>[] getPages(Integer[] pageIds){
		
		@SuppressWarnings("unchecked")
		Page<byte[]>[] pages = new Page[pageIds.length];
		
		for (int i = 0; i < pageIds.length; ++i){
			pages[i] = this.getPage(pageIds[i]);
		}
		return pages;		
	}
	
	// Add pages to HashMap if there is a place
	public void addPages(Page<byte[]>[] addPages){
		for (Page<byte[]> page : addPages){
			this.addPage(page);
		}
	}
	
	// Remove pages
	public void removePages(Page<byte[]>[] removePages){
		for (Page<byte[]> page : removePages){
			this.removePage(page);
		}
	}
	
	// Check where next free place in HashMap
	private Integer getNextFreePlace(){
		
		int i;
		
		for (i = 0; i < maxPages; ++i){
			if (!pages.containsKey(i)){
				break;
			}
		}
		return i;
	}
}
