package org.cloudbus.cloudsim;

import java.util.List;
import org.cloudbus.cloudsim.CloudletScheduler;
import org.cloudbus.cloudsim.GpgpuCloudlet;
/*
 * Classe que representa a politica de agendamento de Gpgpu desempenhada
 * por uma VM. Então, classes extendidas deste devem executar
 * Cloudlets. 
 */

public abstract class GpgpuCloudletScheduler extends CloudletScheduler{

	public GpgpuCloudletScheduler() {
		
	}
	/*Atuliza o processamento do Cloudlet executando sob
	 * a gerência de seu agendador
	 */
	public abstract double updateVmProcessing(double currentTime,
			List<Double> mipsShare,
			List<Double> mflopsShare);
	
	/*
	 * Recebe um Cloudlet para ser executado na Vm gerenciada 
	 * por este agendador.
	 */
	public abstract double cloudletSubmit(GpgpuCloudlet gl,
			double fileTransferTime);
}

