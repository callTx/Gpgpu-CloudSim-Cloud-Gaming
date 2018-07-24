package org.cloudbus.cloudsim;

import java.util.List;

import org.cloudbus.cloudsim.VmAllocationPolicy;

public abstract class VmGpgpuAllocationPolicy extends VmAllocationPolicy{
	
	private List<? extends HostGpgpu> hostGpgpuList;
	
	VmGpgpuAllocationPolicy(List<? extends Host> list){
		super (list);
	}

	public abstract HostGpgpu getHost(VmGpgpu vm);

	public void setHostGpgpuList(List<? extends HostGpgpu> hostGpgpuList) {
		this.hostGpgpuList = hostGpgpuList;
	}


	
	
	
	

	
}
