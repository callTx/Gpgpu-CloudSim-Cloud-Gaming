package org.cloudbus.cloudsim.provisioners;

import org.cloudbus.cloudsim.VmGpgpu; 
import java.util.List;

public abstract class GpeProvisioner {

	/*
	 * O MFlops
	 */
	private double mflops;
	
	/* O MFlops disponível
	 */
	private double availableMFlops;
	
	
	
	
	
	/*
	 * Cria um novo GpeProvisioner
	 */
	public GpeProvisioner(double mflops) {
		setMflops(mflops);
		setAvailableMflops(mflops);
		
	}
	
	
	/*
	 * Aloca o MFlops para um dada VM
	 */
	public abstract boolean allocateMflopsForVm(VmGpgpu vm, double mflops);
	
	/*
	 * Aloca o Mflops par uma dada VM
	 */
	public abstract boolean allocateMflopsForVm(String vmUid,double mflops);
	
	
	/*
	 * Aloca o Mflops par uma dada VM
	 */
	
	public abstract boolean allocateMflopsForVm(VmGpgpu vm, List<Double> mflops);
	
	/*
	 * Pega o Mflops para uma dada VM
	 *
	 */
	public abstract List<Double> getAllocatedMflopsForVm(VmGpgpu vm);
	
	/*
	 * Pega o Mflops total alocado par uma dada VM para todos os Gpes
	 */
	public abstract double getTotalAllocatedMflopsForVm(VmGpgpu vm);
	
	
	/*
	 * PEga o Mflops alocado para uma dada VM por uma Gpe virtual
	 */
	public abstract double getAllocatedMflopsForVmByVirtualGpeId(VmGpgpu vm,int gpeid);
	
	/*
	 * Libera Mflops usado por uma VM
	 * 
	 */
	public abstract void deallocateMflopsForVm(VmGpgpu vm);
	
	/*
	 * Libera o Mflops usado por todas as VMs
	 */
	public void deallocateMflopsForAllVms() {
		setAvailableMflops(getMflops());
	}
	
	/*
	 * Pega o Mflops
	 */
	public double getMflops() {
		return mflops;
	}
	
	/*
	 * Seta o Mflops
	 */
	public void setMflops(double mflops) {
		this.mflops = mflops;
	}
	
	/*
	 * Pega os Mflops disponiveis na Gpe
	 * 
	 */
	public double getAvailableMflops() {
		return availableMFlops;
	}
	
	/*
	 * seta o Mflops disponivel
	 */
	protected void setAvailableMflops(double availableMFlops) {
		this.availableMFlops = availableMFlops;
	}
	
	/*
	 * Pega o total de Mflops alocado
	 */
	public double getTotalAllocatedMflops() {
		double totalAllocatedMflops = getMflops() - getAvailableMflops();
		if(totalAllocatedMflops > 0) {
			return totalAllocatedMflops;
		}
		return 0;
	}
	
	/*
	 * Pega a utilização da Gpe em porcentagem
	 */
	public double getUtilization() {
		return getTotalAllocatedMflops()/ getMflops();
	}
}
