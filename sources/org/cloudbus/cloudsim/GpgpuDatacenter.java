package org.cloudbus.cloudsim;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.cloudbus.cloudsim.core.CloudSim;
import org.cloudbus.cloudsim.core.CloudSimTags;
import org.cloudbus.cloudsim.core.SimEvent;
import org.cloudbus.cloudsim.Datacenter;
import org.cloudbus.cloudsim.VmGpgpu;
import org.cloudbus.cloudsim.GpgpuDatacenterCharacteristics;

public class GpgpuDatacenter extends Datacenter{
	
	
	
	private VmGpgpuAllocationPolicy vmGpgpuAllocationPolicy;

	public GpgpuDatacenter(
			String name,
			GpgpuDatacenterCharacteristics characteristics,
			VmAllocationPolicy vmAllocationPolicy,
			List<Storage> storageList,
			double schedulingInterval
	) throws Exception{
		super (name,characteristics,vmAllocationPolicy,storageList,schedulingInterval);
		
	}
	
	protected void processVmCreate(SimEvent ev, boolean ack) {
		VmGpgpu vm = (VmGpgpu) ev.getData();
		
		boolean result = getVmAllocationPolicy().allocateHostForVm(vm);
		
		if(ack) {
			int[] data = new int[3];
			data[0] = getId();
			data[1] = vm.getId();
			
			if(result) {
				data[2] = CloudSimTags.TRUE;
				
			}else {
				data[2] = CloudSimTags.FALSE;
			}
			//Envia requisição para o Broker
			send(vm.getUserId(),CloudSim.getMinTimeBetweenEvents(),CloudSimTags.VM_CREATE_ACK,data);
		}
		
		if(result) {
			getVmList().add(vm);
			if(vm.isBeingInstantiated()) {
				vm.setBeingInstantiated(false);
			}
			
			vm.updateVmProcessing(CloudSim.clock(),
					getVmAllocationPolicy().getHost(vm).getVmScheduler().getAllocatedMipsForVm(vm),
					getVmGpgpuAllocationPolicy().getHost(vm).getVmGpgpuScheduler().getAllocatedMflopsForVm(vm)
					);
		}
		
	}

	public VmGpgpuAllocationPolicy getVmGpgpuAllocationPolicy() {
		return vmGpgpuAllocationPolicy;
	}

	public void setVmGpgpuAllocationPolicy(VmGpgpuAllocationPolicy vmGpgpuAllocationPolicy) {
		this.vmGpgpuAllocationPolicy = vmGpgpuAllocationPolicy;
	}
	
	
}
