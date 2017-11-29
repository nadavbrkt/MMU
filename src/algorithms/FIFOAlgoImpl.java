package algorithms;

import java.util.LinkedList;


//FIFO Algorithm implements of IAlgo
public class FIFOAlgoImpl<T> implements IAlgo<T> {
	
	// Data members
	private LinkedList<T> queue;
	private int queueMaxSize;
	
	// Methods
	
	// C'tor
	public FIFOAlgoImpl(int queueMaxSize){
		this.queue = new LinkedList<>();
		this.queueMaxSize = queueMaxSize;
	}

	// Check if 't' exist
	@Override
	public T get(T t) {
		for (T element : queue)	{
			if (element.equals(t))
			{
				
				return element;
			}
		}
		
		return null;
	}

	// Add 't' to queue, if queue full remove element by FIFO
	@Override
	public T add(T t) {
		if (queue.size() < queueMaxSize)
		{
			queue.add(t);
			return null;
		}
		else
		{
			T temp = queue.pop();
			queue.add(t);
			return temp;
		}
	}

	// Remove 't' element
	@Override
	public void remove(T t) {
		queue.remove(t);
	}
}
