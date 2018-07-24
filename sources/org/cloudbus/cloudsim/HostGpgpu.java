package org.cloudbus.cloudsim;

import org.cloudbus.cloudsim.Host;
import org.cloudbus.cloudsim.provisioners.BwProvisioner;
import org.cloudbus.cloudsim.provisioners.RamProvisioner;
import  org.cloudbus.cloudsim.VmScheduler;
import  org.cloudbus.cloudsim.VmGpgpu;
import java.util.ArrayList;
import java.util.List;
import org.cloudbus.cloudsim.GpgpuDatacenter;

public class HostGpgpu extends Host{
	/*
	 * id
	 */
	//private int id;

	/*
	 * O storage
	 */
	//private long storage;
	
	/*
	 * O ram provisioner
	 */
	//private RamProvisioner ramProvisioner;
	
	/*
	 * O bw provisioner
	 * 
	 */
	//private BwProvisioner bwProvisioner;
	
	/*
	 * A lista de Pes
	 */
	//private List<?extends Pe> peList;
	
	
	/*
	 * A politica usada um gerenciador de VM para compartilhar 
	 * <br>Observação<br>: Será usado um VmScheduler ao invés de 
	 * de um VmGpgpuScheduler 
	 */
	 private VmGpgpuScheduler vmGpgpuScheduler;
	 
	
	 /*
	  * A list de VM vmList
	  */
	private final List<? extends VmGpgpu> vmGpgpuList = new ArrayList<VmGpgpu>();
	
	
	/*
	 * A lista de gpe 
	 */
	private List<? extends Gpe> gpeList;
	
	/*
	 * Diz se a maquina está funcionado propriamente ou se está falhando
	 */
	//private boolean failed;
	
	/*
	 * A VM em migração
	 */
	private final List<VmGpgpu> vmsMigratingIn = new ArrayList<VmGpgpu>();
	
	/*
	 * O datacenter onde o host está localizado
	 */
	private GpgpuDatacenter datacenter;
	
	public HostGpgpu(int id,
			RamProvisioner ramProvisioner,
			BwProvisioner bwProvisioner,
			long storage,
			List<? extends Pe> peList,
			VmScheduler vmScheduler,
			
			List<? extends Gpe> gpeList) throws Exception{
			super(id,ramProvisioner,bwProvisioner,storage,peList,vmScheduler);
			setGpeList(gpeList);
	}
	

	/*
	 * Pega a lista de VmGpgpu
	 */
	@SuppressWarnings("unchecked")
	public <T extends VmGpgpu> List<T> getVmGpgpuList(){
		return (List<T>) vmGpgpuList;
	}
	

	/*
	 * Pega a lista de Gpe 
	 */
	@SuppressWarnings("unchecked")
	public <T extends Gpe> List<T> getGpeList(){
		return (List<T>) gpeList;
	}
	
	/*
	 * Seta a lista de Gpe
	 */
	protected <T extends Gpe> void setGpeList(List<T> gpeList) {
		this.gpeList = gpeList;
	}


	public GpgpuDatacenter getDatacenter() {
		return datacenter;
	}


	public void setDatacenter(GpgpuDatacenter datacenter) {
		this.datacenter = datacenter;
	}


	public VmGpgpuScheduler getVmGpgpuScheduler() {
		return vmGpgpuScheduler;
	}


	protected void setVmGpgpuScheduler(VmGpgpuScheduler vmGpgpuScheduler) {
		this.vmGpgpuScheduler = vmGpgpuScheduler;
	}
	
}
