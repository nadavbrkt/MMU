package processes;

import java.util.List;

public class ProcessCycles {
	
	// Data members
	private List<ProcessCycle> processCycles;
	
	// C'tor
	public ProcessCycles(List<ProcessCycle> processCycles){
		this.setProcessCycles(processCycles);
	}

	// Methods
	
	// Get processCycles
	public List<ProcessCycle> getProcessCycles() {
		return processCycles;
	}
	
	// Set processCycles
	public void setProcessCycles(List<ProcessCycle> processCycles) {
		this.processCycles = processCycles;
	}
	
	//
//	public String toString(){
		
//	}
}
