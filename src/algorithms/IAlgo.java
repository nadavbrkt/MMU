package algorithms;

// Algorithm interface
public interface IAlgo <T> {
	
	// Check if 't' exist
	public T get(T t);
	
	// Add 't'
	public T add(T t);
	
	// Remove 't' element
	public void remove(T t);
}