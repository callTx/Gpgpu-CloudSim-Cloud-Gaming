package org.cloudbus.cloudsim;

import java.util .ArrayList;
import org.cloudbus.cloudsim.VmScheduler;
import org.cloudbus.cloudsim.Gpe;
import org.cloudbus.cloudsim.Pe;
import org.cloudbus.cloudsim.lists.GpeList;
import java.util. Map;
import java.util.List;
import java.util. HashMap;
import org.cloudbus.cloudsim. VmGpgpu;

public abstract class VmGpgpuScheduler extends VmScheduler{

	/*
	 * A Gpelist
	 */
	private List<? extends Gpe> gpeList;
	
	/*
	 * O mapa de Vms para Gpelist
	 */
	private Map<String,List<Gpe>> gpeMap;
	
	/*
	 * O Mflops que está atuamente alocado para cada VM
	 */
	private Map<String, List<Double>> mflopsMap;
	
	/*
	 * O total de Mflops disponivel
	 */
	private double availableMflops;
	
	/** A VMs migrating in. */
	//private List<String> vmsMigratingIn;

	/** A VMs migrating out. */
	//private List<String> vmsMigratingOut;
	
	/*
	 * Cria um novo objeto VmGpgpuScheduler
	 */
	public VmGpgpuScheduler(List<? extends Pe> peList
			,List<? extends Gpe> gpeList) {
		super(peList);
		
		setGpeList(gpeList);
		setGpeMap(new HashMap<String,List<Gpe>>());
		setMflopsMap(new HashMap<String,List<Double>>());
		setAvailableMflops(GpeList.getTotalMflops(getGpeList()));
		//setVmsMigratingIn(new ArrayList<String>());
		//setVmsMigratingOut(new ArrayList<String>());
	}
	
	/*
	 * Aloca Gpes para uma VM
	 */
	public abstract boolean allocateGpesForVm(VmGpgpu vm, List<Double> mflopsShare);
	
	/*
	 * Libera Gpes alocadas para uma VM
	 */
	public abstract void deallocateGpesForVm(VmGpgpu vm);
	
	/*
	 * Liberar Gpes alocadas para todas as VMs
	 */
	public void deallocateGpesForAllVms() {
		getMflopsMap().clear();
		setAvailableMflops(GpeList.getTotalMflops(getGpeList()));
		for(Gpe gpe: getGpeList()) {
			gpe.getGpeProvisioner().deallocateMflopsForAllVms();
		}
	}
	
	/*
	 * Pega a Gpe alocado para VM
	 */
	public List<Gpe> getGpesAllocatedForVM(VmGpgpu vm){
		return getGpeMap().get(vm.getUid());
	}
	
	
	/*
	 * Retorna o Mflops share de cada Gpe que é alocado to uma dada VM
	 */
	public List<Double> getAllocatedMflopsForVm(VmGpgpu vm){
		return getMflopsMap().get(vm.getUid());
	}
	
	/*
	 * Pega o total Mflops alocado para uma VM sobre todas as Gpes 
	 */
	public double getTotalAllocatedMflopsForVm(VmGpgpu vm) {
		double allocated = 0;
		List<Double> mflopsMap = getAllocatedMflopsForVm(vm);
		if(mflopsMap != null) {
			for(double mflops : mflopsMap) {
				allocated += mflops;
			}
		}
		return allocated;
	}
	
	/*
	 * Retorna o maximo de Mflops disponivel entre todas as Gpe
	 */
	public double getMaxAvailableMflops() {
		if(getGpeList() ==  null) {
			Log.printLine("Gpe list is empty");
			return 0;
		}
		
		double max = 0.0;
		for(Gpe gpe : getGpeList()) {
			double tmp = gpe.getGpeProvisioner().getAvailableMflops();
			if(tmp > max) {
				max = tmp;
			}
		}
		return max;
	}
	
	/*
	 * Retorna a capacidade de Gpe em Mflops
	 */
	public double getGpeCapacity() {
		if(getGpeList() == null) {
			Log.printLine("Gpe list is empty");
			return 0;
		}
		return getGpeList().get(0).getMflops();
	}
	
	public List<? extends Gpe> getGpeList() {
		return gpeList;
	}

	protected void setGpeList(List<? extends Gpe> gpeList) {
		this.gpeList = gpeList;
	}

	public Map<String,List<Gpe>> getGpeMap() {
		return gpeMap;
	}

	protected void setGpeMap(Map<String,List<Gpe>> gpeMap) {
		this.gpeMap = gpeMap;
	}

	protected Map<String, List<Double>> getMflopsMap() {
		return mflopsMap;
	}

	protected void setMflopsMap(Map<String, List<Double>> mflopsMap) {
		this.mflopsMap = mflopsMap;
	}

	public double getAvailableMflops() {
		return availableMflops;
	}

	protected void setAvailableMflops(double availableMflops) {
		this.availableMflops = availableMflops;
	}

	/*
	public List<String> getVmsMigratingIn() {
		return vmsMigratingIn;
	}

	protected void setVmsMigratingIn(List<String> vmsMigratingIn) {
		this.vmsMigratingIn = vmsMigratingIn;
	}

	public List<String> getVmsMigratingOut() {
		return vmsMigratingOut;
	}

	protected void setVmsMigratingOut(List<String> vmsMigratingOut) {
		this.vmsMigratingOut = vmsMigratingOut;
	}*/
}
