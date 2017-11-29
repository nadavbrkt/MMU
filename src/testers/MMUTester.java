package testers;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import memory.MemoryManagmentUnit;
import memory.Page;
import algorithms.FIFOAlgoImpl;
import algorithms.IAlgo;
import algorithms.MFUAlgoImpl;

@SuppressWarnings("unused")
public class MMUTester {
	
	// Main
	public static void main(String[] args){
		
		
		IAlgo<Integer> fifo = new FIFOAlgoImpl<Integer>(4);
		MemoryManagmentUnit mmu = new MemoryManagmentUnit(4, fifo);
		
		// Create HD Object to fill HD file
		HashMap<Integer, Page<byte[]>> hdObject = 
				new HashMap<Integer, Page<byte[]>>();
		
		hdObject.put(1, new Page<byte[]>(1, new byte[] {1,1}));
		hdObject.put(2, new Page<byte[]>(2, new byte[] {2,2}));
		hdObject.put(3, new Page<byte[]>(3, new byte[] {3,3}));
		hdObject.put(4, new Page<byte[]>(4, new byte[] {4,4}));
		hdObject.put(5, new Page<byte[]>(5, new byte[] {5,5}));
		hdObject.put(6, new Page<byte[]>(6, new byte[] {6,6}));
		hdObject.put(7, new Page<byte[]>(7, new byte[] {7,7}));
		hdObject.put(8, new Page<byte[]>(8, new byte[] {8,8}));
		hdObject.put(9, new Page<byte[]>(9, new byte[] {9,9}));
		hdObject.put(10, new Page<byte[]>(10, new byte[] {10,10}));
		hdObject.put(11, new Page<byte[]>(10, new byte[] {11,11}));
		
		// Write to HD file
		try{
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream("hdPages.txt"));
			out.writeObject(hdObject);
			out.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}

		// Round 1 - return 1 2
		Page<byte[]>[] arr1 = mmu.getPages(new Integer[] {1,2});
		
		System.out.println("Get pages: ");
		for (Page<byte[]> page : arr1){
			System.out.println(page.getPageId() + " ");
		}
		
		// Round 2 - return 3 4 5
		Page<byte[]>[] arr2 = mmu.getPages(new Integer[] {3,4,5});
		
		System.out.println("Get pages: ");
		for (Page<byte[]> page : arr2){
			System.out.println(page.getPageId() + " ");
		}
		
		// Round 3 - return 1 2 7 8
		Page<byte[]>[] arr3 = mmu.getPages(new Integer[] {1,2,7,8});
		
		System.out.println("Get pages: ");
		for (Page<byte[]> page : arr3){
			System.out.println(page.getPageId() + " ");
		}
		
		// Round 4 - return 1 2 5 7
		Page<byte[]>[] arr4 = mmu.getPages(new Integer[] {1,2,5,7});
		
		System.out.println("Get pages: ");
		for (Page<byte[]> page : arr4){
			System.out.println(page.getPageId() + " ");
		}
		
		
	}
	

}
