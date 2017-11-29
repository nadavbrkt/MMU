package memory;

import java.io.Serializable;

// Page class represent page in memory
@SuppressWarnings("serial")
public class Page <T> implements Serializable{
	
	// Data members
	private int pageId;
	private T content;
	
	// C'tor
	public Page(int id, T content){
		this.pageId = id;
		this.content = content;
	}
	
	// Methods
	
	// Get pageId
	public int getPageId(){
		return this.pageId;
	}
	
	// Set pageId
	public void setPageId(int pageId){
		this.pageId = pageId;
	}
	
	// Get content
	public T getContent(){
		return content;
	}
	
	// Set content
	public void setContent(T content){
		this.content = content;		
	}
	
	// Hash code override
	@Override
	public int hashCode(){
		return pageId;
	}
	
	// Equals override
	@Override
	public boolean equals(Object obj){
		return ((this.hashCode() == obj.hashCode()) ? true : false); 
	}
	
	// ToString override
	@Override
	public String toString(){
		return ("PageID:" + pageId + "\n" + 
				"Content: " + content.toString() + "\n" );
	}
}
