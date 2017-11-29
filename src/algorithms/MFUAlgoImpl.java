package algorithms;

import java.util.HashMap;


// MFU Algorithm implements of IAlgo
public class MFUAlgoImpl<T> implements IAlgo<T> {
	
	// Data Members
	private HashMap<T, Integer> queue;
	private int queueMaxSize;
	
	// C'tor
	public MFUAlgoImpl(int queueMaxSize) {
		this.queue = new HashMap<T, Integer>();
		this.queueMaxSize = queueMaxSize;
	}
	
	// Methods
	
	// Check if 't' exist and increase value by 1
	@Override
	public T get(T t) {
		if (queue.containsKey(t)){
			Integer newValue = queue.get(t) + 1;
			queue.replace(t, newValue);
			return t;
		}
		else{
			return null;
		}
	}

	// Add 't' to queue, if queue full remove MFU
	@Override
	public T add(T t) {
		if (queue.size() < queueMaxSize){
			queue.put(t,1);
			return null;
		}
		else{
			T tmp = maxValue();
			queue.remove(tmp);
			queue.put(t, 1);
			return tmp;
		}
	}

	// Remove map by 't' value
	@Override
	public void remove(T t) {
		queue.remove(t);
	}
	
	// Find the element with the max value and return it's key
	private T maxValue(){
		
		T max = null;
		
		// RunOnce for initialize max  
		for (T key : queue.keySet()){
			max = key;
			break;
		}
		
		for (T key : queue.keySet()){			
			if (queue.get(key) >  queue.get(max)){
				max = key;
			}
		}
		
		return max;
	}

}
