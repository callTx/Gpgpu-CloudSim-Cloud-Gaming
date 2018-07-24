package org.cloudbus.cloudsim;

import org.cloudbus.cloudsim.DatacenterBroker;
import org.cloudbus.cloudsim.GpgpuCloudlet;
import org.cloudbus.cloudsim.VmGpgpu;
import org.cloudbus.cloudsim.core.CloudSim;
import org.cloudbus.cloudsim.core.CloudSimTags;
import org.cloudbus.cloudsim.lists.VmGpgpuList;
import org.cloudbus.cloudsim.core.SimEntity;


import java.util.List;
import java.util.ArrayList;

public class GpgpuDatacenterBroker extends DatacenterBroker{

	protected List<? extends GpgpuCloudlet> gpgpucloudletSubmittedList;
	protected List<? extends GpgpuCloudlet> gpgpucloudletlist;
	protected List<? extends VmGpgpu> vmsGpgpuCreatedList;
	
	public GpgpuDatacenterBroker(String name) throws Exception{
		super(name);
		setGpgpuCloudletList(new ArrayList<GpgpuCloudlet>());
	}
	
	protected void submitCloudlets() {
		int vmIndex = 0;
		for (GpgpuCloudlet cloudlet : getGpgpuCloudletList() ) {
			VmGpgpu vm;
			
			//Se o usuário não linkou este cloudlet e o mesmo ainda não
			//tem sido executado
			if(cloudlet.getVmId() == -1) {
				vm = getVmsGpgpuCreatedList().get(vmIndex);
				
			//Submete para uma vm especifica
			}else {
				vm = VmGpgpuList.getById(getVmsGpgpuCreatedList(),cloudlet.getVmId() );
				// vm não foi criada
				if(vm == null) {
					Log.printLine(CloudSim.clock()+":"+getName() + ":"
							+ "Adiando a execução do cloudlet "+
							cloudlet.getCloudletId()+": bount Vm não disponível");
					continue;
				}
			}
			
			Log.printLine(CloudSim.clock()+": "+getName()+
					": Enviando cloudlet "+ cloudlet.getCloudletId()+
					" para a Vm #"+vm.getId());
			
			cloudlet.setVmId(vm.getId());
			
			sendNow(getVmsToDatacentersMap().get(vm.getId()),
					CloudSimTags.CLOUDLET_SUBMIT,cloudlet);
			
			cloudletsSubmitted++;
			
			vmIndex = (vmIndex +1) % getVmsCreatedList().size();
			
			getGpgpuCloudletSubmittedList().add(cloudlet);
			
			
		}
		//Remove cloudlets submetidos da list de espera
		for(GpgpuCloudlet cloudlet : getGpgpuCloudletSubmittedList()) {
			getGpgpuCloudletList().remove(cloudlet);
		}
	}
	
	
	
	public <T extends GpgpuCloudlet> List<T> getGpgpuCloudletSubmittedList(){
		return (List<T>) gpgpucloudletSubmittedList;
	}
	
	public <T extends GpgpuCloudlet> List<T> getGpgpuCloudletList(){
		return (List<T>) gpgpucloudletlist;
	}
	
	protected <T extends GpgpuCloudlet> void setGpgpuCloudletList(List<T> gpgpucloudletlist) {
	 this.gpgpucloudletlist = gpgpucloudletlist;	
	}
	
	public <T extends VmGpgpu> List<T> getVmsGpgpuCreatedList(){
		return (List<T>) vmsGpgpuCreatedList;
	}
	
	protected <T extends VmGpgpu> void setVmsGpgpuCreatedList (List<T> vmsgpgpuCreatedList) {
		this.vmsGpgpuCreatedList = vmsgpgpuCreatedList;
	}
	
	
}
