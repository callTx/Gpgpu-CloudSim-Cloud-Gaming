package org.cloudbus.cloudsim.lists;

import org.cloudbus.cloudsim.Gpe;
import org.cloudbus.cloudsim.Log;
import org.cloudbus.cloudsim. VmGpgpu;
import java.util.List;

public class GpeList {
	/*
	 * Pega a taxa de Mflops para Gpe ID especificada
	 */
	public static <T extends Gpe> Gpe getById(List<T> gpeList,int id) {
		for(Gpe gpe: gpeList) {
			if(gpe.getId() == id) {
				return gpe;
			}
		}
		return null;
	}
	
	/*
	 * Pega a taxa de Mflops para um Gpe ID especificado
	 */
	public static <T extends Gpe> int getMflops(List<T> gpeList, int id) {
		Gpe gpe = getById(gpeList,id);
		if(gpe != null) {
			return gpe.getMflops();
		}
		return -1;
	}
	
	/*
	 * Pega o total da taxa de Mflops para todos as Gpes
	 */
	public static <T extends Gpe> int getTotalMflops(List<T> gpeList) {
		int totalMflops=0;
		for(Gpe gpe: gpeList) {
			totalMflops += gpe.getMflops();
		}
		return totalMflops;
	}
	
	/*
	 * Pega a máxima utilização entre todas as Gpes
	 */
	public static <T extends Gpe> double getMaxUtilization(List<T> gpeList) {
		double maxUtilization = 0;
		for (Gpe gpe : gpeList) {
			double utilization = gpe.getGpeProvisioner().getUtilization();
			if(utilization > maxUtilization) {
				maxUtilization = utilization;
			}
		}
		return maxUtilization;
	}
	
	/*
	 * Pega a máxima utilização entre todas as Gpes alocadas a VM
	 */
	public static <T extends Gpe> double getMaxUtilizationAmongVmsGpes(List<T> gpeList,VmGpgpu vm) {
		double maxUtilization = 0;
		for(Gpe gpe: gpeList) {
			if(gpe.getGpeProvisioner().getAllocatedMflopsForVm(vm) == null) {
				continue;
			}
			double utilization = gpe.getGpeProvisioner().getUtilization();
			if(utilization > maxUtilization) {
				maxUtilization = utilization;
			}
		}
		return maxUtilization;
	}
	
	/*
	 * Pega o ID da Gpe a qual é FREE 
	 */
	public static <T extends Gpe> Gpe getFreeGpe(List<T> gpeList) {
		for (Gpe gpe: gpeList) {
			if(gpe.getStatus() == gpe.FREE) {
				return gpe;
			}
		}
		return null;
	}
	
	/*
	 * Pega o número de Gpes FREE ou non-BUSY
	 */
	public static <T extends Gpe> int getNumberOfFreeGpes(List<T> gpeList) {
		int cnt =0;
		for(Gpe gpe : gpeList) {
			if(gpe.getStatus() == gpe.FREE) {
				cnt++;
			}
		}
		return cnt;
	}
	
	/*
	 * Seta o status das Gpes
	 */
	public static <T extends Gpe> boolean setGpeStatus(List<T> gpeList, int id,int status) {
		Gpe gpe = getById(gpeList,id);
		if(gpe != null) {
			gpe.setStatus(status);
			return true;
		}
		return false;
	}
	
	/*
	 * Pega o numero de Gpe BUSY
	 */
	public static <T extends Gpe> int getNumberOfBusyGpes(List<T> gpeList) {
		int cnt = 0;
		for(Gpe gpe : gpeList) {
			if(gpe.getStatus() == Gpe.BUSY) {
				cnt++;
			}
		}
		return cnt;
	}
	
	/*
	 * Seta o status da GPes desta maquina para FAILED. NOTA: resName e machineID
	 * são usadas para propositos de debugação, o que é ON por padrão. Use
	 * {@link #setStatusFailed(boolean)} se você não quer essa informação
	 */
	public static <T extends Gpe> void setStatusFailed(
			List<T> gpeList,
			String resName,
			int hostId,
			boolean failed) {
		String status = null;
		if(failed) {
			status = "FAILED";
		}else {status = "WORKING";
		}
		
		Log.printLine(resName + "- Machine: "+hostId + " is "+ status);
		setStatusFailed(gpeList,failed);
	}
	
	/*
	 * Seta o status o Gpe desta maquina para FAIELD 
	 */
	public static <T extends Gpe> void setStatusFailed(List<T> gpeList, boolean failed) {
		// um loop para seta o status de todas as Gpes nesta maquina
		for(Gpe gpe: gpeList) {
			if(failed) {
				gpe.setStatus(gpe.FAILED);
			}else {
				gpe.setStatus(gpe.FREE);
			}
		}
	}
}
