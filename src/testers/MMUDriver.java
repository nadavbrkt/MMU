package testers;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import processes.Process;
import processes.ProcessCycles;
import processes.RunConfiguration;
import util.MMULogger;
import algorithms.FIFOAlgoImpl;
import algorithms.LRUAlgoImpl;
import algorithms.MFUAlgoImpl;
import memory.MemoryManagmentUnit;
import memory.Page;

@SuppressWarnings("unused")
public class MMUDriver {
	
	// Data members
	private final static String CONFIG_FILE_NAME = "C:\\Configuration.json";
	
	// Methods
	
	// Create runConfiguration object from json file
	private static RunConfiguration readConfigurationFile(){

		try{
			return new Gson().fromJson(new JsonReader(new FileReader(CONFIG_FILE_NAME)), RunConfiguration.class);			
		}
		catch (Exception e){
			MMULogger.getInstance().write(e.getMessage(), Level.SEVERE);
			return null;
		}
		
	}

	// Create list of processes in size of processCycles
	private static List<Process> createProcesses(List<ProcessCycles> processCycles, MemoryManagmentUnit mmu){
		
		List<Process> processes = new ArrayList<Process>();
		
		for (int i = 0; i < processCycles.size(); ++i){
			processes.add(new Process(i, mmu, processCycles.get(i)));
		}
		
		MMULogger.getInstance().write("PN " + processes.size() + "\n", Level.INFO);
		
		return processes;
	}
	
	//
	private static void runProcesses(List<Process> processes){
		
		Executor executor = Executors.newCachedThreadPool();
		
		for (Process process : processes){
			executor.execute(process);
		}
		
		((ExecutorService) executor).shutdown();
	}
	
	//Main
	public static void main(String[] args) {
		// Create HD Object to fill HD file
		System.out.flush();
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
		hdObject.put(11, new Page<byte[]>(11, new byte[] {11,11}));
		
		// Write to HD file
		try{
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream("hdPages.txt"));
			out.writeObject(hdObject);
			out.close();
		}
		catch(Exception e){
			MMULogger.getInstance().write(e.getMessage(), Level.SEVERE);
		}
		
		MemoryManagmentUnit mmu = new MemoryManagmentUnit(5, new FIFOAlgoImpl<Integer>(5));
		RunConfiguration runConfig = readConfigurationFile();
		List<ProcessCycles> processCycles = runConfig.getProcessesCycles();
		List<Process> processes = createProcesses(processCycles, mmu);
		runProcesses(processes);
		
	}

}
