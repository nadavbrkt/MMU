package testers;

import algorithms.FIFOAlgoImpl;
import algorithms.IAlgo;
import algorithms.MFUAlgoImpl;
import memory.Page;
import memory.RAM;

// Tester 1
public class AlgorithmTest {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		// Test Page
		System.out.println("Test page:\n");
		
		Page<String> p1 = new Page<String>(1, "aa");
		Page<String> p2 = new Page<String>(3, "bb");
		Page<String> p3 = new Page<String>(4, "ccc");
		
		System.out.println("p1: " + p1.getContent());
		p1.setContent("bb");
		p1.setPageId(2);
		System.out.println(p1.toString());
		System.out.println(p2.hashCode());
		System.out.println(p2.equals(p3));
		
		// Test RAM
		System.out.println("Test RAM:\n");
		
		RAM ram = new RAM(3);
		
		if (ram.getPage(1) == null){
			System.out.println("Work good");
		}
		else{
			System.out.println("Work bad");
		}
		
		byte[] a = {1,2};
		byte[] b = {3,4};
		byte[] c = {5,6};
		byte[] d = {7,8};
		byte[] e = {9,10};
		Page<byte[]> p5 = new Page<byte[]>(5, a);
		Page<byte[]> p6 = new Page<byte[]>(6, b);
		Page<byte[]> p7 = new Page<byte[]>(7, c);
		Page<byte[]> p8 = new Page<byte[]>(8, d);
		Page<byte[]> p9 = new Page<byte[]>(9, e);
		
		ram.addPage(p5);
		ram.addPage(p6);
		ram.addPage(p7);
		ram.addPage(p8);
		System.out.println(ram.getPage(2)); // NULL
		System.out.println(ram.getPage(8)); // NULL
		System.out.println(ram.getPage(5)); 
		ram.removePage(p5);
		System.out.println(ram.getPage(5)); // NULL
		
		ram.removePage(p5);
		ram.removePage(p6);
		ram.removePage(p7);
		
		@SuppressWarnings("rawtypes")
		Page[] pageArr = {p5, p8, p9};
		
		ram.addPages(pageArr);
		
		System.out.println(ram.getPage(8));
		ram.removePages(pageArr);
		System.out.println(ram.getPage(8)); // NULL
		
		// Test IAlgo
		
		// Test FIFO
		System.out.println("Test FIFO:\n");
		
		IAlgo<String> fifo = new FIFOAlgoImpl<String>(3);
		
		fifo.add("aa");
		fifo.add("bb");
		fifo.add("cc");
		
		System.out.println(fifo.get("aa"));
	
		fifo.add("dd");
		System.out.println(fifo.get("aa")); // NULL - because FIFO
		
		System.out.println(fifo.get("bb"));
		fifo.remove("bb");
		System.out.println(fifo.get("bb")); // NULL
		
		// Test MFU
		System.out.println("Test MFU:\n");
		
		IAlgo<String> mfu = new MFUAlgoImpl<String>(3);
		
		mfu.add("a");
		mfu.add("c");
		mfu.add("b");
		
		System.out.println(mfu.get("a")); 
		System.out.println(mfu.get("b"));
		System.out.println(mfu.get("b"));
		
		mfu.add("d");
		System.out.println(mfu.get("a")); 
		System.out.println(mfu.get("b")); // null - MFU
		System.out.println(mfu.get("c"));
		System.out.println(mfu.get("d"));
	}
	
}
