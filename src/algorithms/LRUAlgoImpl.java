package algorithms;

import java.util.LinkedList;

public class LRUAlgoImpl<T> implements IAlgo<T>{
	
	private LinkedList<T> queue;
	private int size;
	
	// Constructor
	public LRUAlgoImpl(int size) {
		this.size = size;
		queue = new LinkedList<T>();
	}
	
	@Override
	// Get t
	public T get(T t) {
		
		// Checks if t exist in queue
		if(queue.contains(t))
		{
			// Puts t in last and delete it from the middle of queue
			int delIndex = queue.indexOf(t);
			queue.addLast(t);
			queue.remove(delIndex);
			return t;
		}
		
		return null;
	}

	@Override
	// Add t to queue
	public T add(T t) {
		// If the queue is full
		if(queue.size() == size)
		{
			// Adds t and removes the first obj
			queue.addLast(t);
			return queue.removeFirst();
		}
		
		// Adds t in last place
		queue.addLast(t);
		return null;
	}

	@Override
	// Remove t from queue
	public void remove(T t) {
		if(queue.contains(t))
			queue.remove(t);
	}
	
	@Override
	public String toString() {
		return queue.toString();
	}
}


