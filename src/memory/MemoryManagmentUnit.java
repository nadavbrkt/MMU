package memory;

import java.util.Arrays;
import algorithms.*;

public class MemoryManagmentUnit {
	
	// Data members
	private IAlgo<Integer> algo;
	private RAM ram;
	
	// C'tor
	public MemoryManagmentUnit(int ramCapacity, IAlgo<Integer> algo){
		this.algo = algo;
		this.ram = new RAM(ramCapacity);		
	}
	
	// Methods
	
	// Return array pages
	public synchronized Page<byte[]>[] getPages(Integer[] pageIds){
		for (int i = 0; i < pageIds.length; ++i){
			// If IAlgo not contains pageIds[i]
			if (algo.get(pageIds[i]) == null){
								
				Integer iAlgoPopPageId = algo.add(pageIds[i]);

				// Holds needed pages in RAM
				while(Arrays.asList(pageIds).contains(iAlgoPopPageId))
				{
					iAlgoPopPageId = algo.add(iAlgoPopPageId);
				}
				
				// If IAlgo is not full do pageFault			
				if (iAlgoPopPageId == null){
					Page<byte[]> tmp = HardDisk.getInstance().pageFault(pageIds[i]);
					ram.addPage(tmp);
				}
				// If IAlgo is full do pageReplacement
				else{
					Page<byte[]> iAlgoPopPage = ram.getPage(iAlgoPopPageId);
					Page<byte[]> moveToRamPage = HardDisk.getInstance().pageReplacement(iAlgoPopPage, pageIds[i]);
					ram.removePage(iAlgoPopPage);
					ram.addPage(moveToRamPage);
				}
			}	
		}
		
		return ram.getPages(pageIds);
	}
}