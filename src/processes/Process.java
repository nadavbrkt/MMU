package processes;


import java.util.Arrays;
import java.util.logging.Level;

import util.MMULogger;
import memory.MemoryManagmentUnit;
import memory.Page;

public class Process implements Runnable {
	
	// Data members
	private int id;
	private ProcessCycles processCycles;
	private MemoryManagmentUnit mmu;
	
	// C'tor
	public Process(int id, MemoryManagmentUnit mmu, ProcessCycles processCycles){
		this.id = id;
		this.processCycles = processCycles;
		this.mmu = mmu;		
	}

	@Override
	public void run() {
		
		// For every cycle in processCycles
		for (ProcessCycle cycle : processCycles.getProcessCycles()){
			
			// Get pages from ram	
			Page<byte[]>[] pagesFromRam = mmu.getPages(cycle.getPages().toArray(new Integer[cycle.getPages().size()]));
			
			// Set pages from ram content with cycle data
			for (int i = 0; i < cycle.getPages().size(); ++i){
				pagesFromRam[i].setContent(cycle.getData().get(i));
				MMULogger.getInstance().write("P" + this.id + " R " +
						pagesFromRam[i].getPageId() + " W " + 
						Arrays.toString(pagesFromRam[i].getContent()) + "\n",
						Level.INFO);
			}
			
			// Process go to sleep
			try{
				Thread.sleep(cycle.getSleepMs());
			}
			catch (InterruptedException e){
				MMULogger.getInstance().write("Process cycle interrupted while sleeping", Level.SEVERE);
			}
			
			
		}
		
	}
}
