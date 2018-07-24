package org.cloudbus.cloudsim;

import org.cloudbus.cloudsim.Vm;
import java.util.List;
import org.cloudbus.cloudsim.GpgpuCloudletScheduler;
import org.cloudbus.cloudsim.HostGpgpu;

public class VmGpgpu extends Vm{
   
	/*o Mips*/
	//private double mips;	
	
	/*Numero de PEs*/
	//private int numberOfPes;
	
	/*ram, bw, vmm*/
	//private int ram;
	//private long bw;
	//private String vmm;
	
	/*O tamanho atual alocado*/
	//private long currentAllocatedSize;
	
	/*
	 *A ram atual alocada 
	 */
	//private int currentAllocatedRam;
	
	/*A bw atual alocada*/
	//private long currentAllocatedBw;
	
	/*O Mips atual alocado*/
	//private List<Double> currentAllocatedMips;
	
	
	
	/*MFlops*/
	private double mflops;
	
	/*Numero de GPes*/
	private int numberOfGpes;
	
	/*O agendador de Cloudlet*/
	private GpgpuCloudletScheduler cloudletScheduler;
	
	/*Host*/
	private HostGpgpu host;
	
	/*O atual mflops alocado*/
	private List<Double> currentAllocatedMflops;
	
	
	public VmGpgpu(int id,
			int userId,
			double mips,
			int numberOfPes,
			int ram,
			long bw,
			long size,
			String vmm,
			CloudletScheduler CloudletScheduler,
			
			 double mflops,
			 int numberOfGpes,
			 GpgpuCloudletScheduler cloudletScheduler,
			 HostGpgpu host,
			 List<Double> currentAllocatedMflops) {
		super(id,userId,mips,numberOfPes,ram,bw,size,vmm,CloudletScheduler);
		setMflops(mflops);
		setNumberOfGpes(numberOfGpes);
		setCurrentAllocatedMflops(currentAllocatedMflops);
		
	}

	public double updateVmProcessing(double currentTime, List<Double> mipsShare,
			List<Double> mflopsShare) {
		if(mipsShare != null || mflopsShare != null) {
			return updateVmProcessing(currentTime,mipsShare,mflopsShare);
		}
		return 0.0;
	}

	
	

	public double getMflops() {
		return mflops;
	}

	protected void setMflops(double mflops) {
		this.mflops = mflops;
	}

	public int getNumberOfGpes() {
		return numberOfGpes;
	}

	protected void setNumberOfGpes(int numberOfGpes) {
		this.numberOfGpes = numberOfGpes;
	}

	public List<Double> getCurrentAllocatedMflops() {
		return currentAllocatedMflops;
	}

	protected void setCurrentAllocatedMflops(List<Double> currentAllocatedMflops) {
		this.currentAllocatedMflops = currentAllocatedMflops;
	}



	public GpgpuCloudletScheduler getCloudletScheduler() {
		return cloudletScheduler;
	}



	protected void setCloudletScheduler(GpgpuCloudletScheduler cloudletScheduler) {
		this.cloudletScheduler = cloudletScheduler;
	}



	public HostGpgpu getHost() {
		
		return host;
	}



	public void setHost(HostGpgpu host) {
		this.host = host;
	}


	/*
	public long getCurrentAllocatedSize() {
		return currentAllocatedSize;
	}*/


	/*
	public void setCurrentAllocatedSize(long currentAllocatedSize) {
		this.currentAllocatedSize = currentAllocatedSize;
	}*/
	
	
}
