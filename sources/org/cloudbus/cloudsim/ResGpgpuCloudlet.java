package org.cloudbus.cloudsim;

import org.cloudbus.cloudsim.ResCloudlet;

public class ResGpgpuCloudlet extends ResCloudlet{
	private long ciCloudletFinishedSoFar[];
	private long giCloudletFinishedSoFar[];
	private long cpuCloudletFinishedSoFar;
	private long gpuCloudletFinishedSoFar;
	
	//Atualiza o tamanho da informa��o da opera��o do Cpu Cloudlet
	public void updateCpuCloudletFinishedSoFar(long mipsLength) {
		cpuCloudletFinishedSoFar += mipsLength;
		updateCloudletFinishedSoFar(mipsLength);
	}
	
	//Atualiza o tamanho da informa��o da opera��
	public void updateGpuCloudletFinishedSoFar(long mflopsLength) {
		gpuCloudletFinishedSoFar += mflopsLength;
		updateCloudletFinishedSoFar(mflopsLength);
	}
	
	//Atualiza o tamanho da informa��o da opera��o de CPU de Cloudlet espec�fico
	public void updateCpuCloudletFinishedSoFar(int i,long mipsLength) {
		
		ciCloudletFinishedSoFar[i] += mipsLength;
		cpuCloudletFinishedSoFar += mipsLength;
		updateCloudletFinishedSoFar(mipsLength);
	}
	
	//Atualiza o tamanho da informa��o de uma opera��o de Gpu especifica do Cloudlet
	public void updateGpuCloudletFinishedSoFar(int i,long mflopsLength) {
		giCloudletFinishedSoFar[i] += mflopsLength;
		gpuCloudletFinishedSoFar += mflopsLength;
	    updateCloudletFinishedSoFar(mflopsLength);
	}
	
	public ResGpgpuCloudlet(Cloudlet cloudlet) {
		super(cloudlet);
	}
	
}
