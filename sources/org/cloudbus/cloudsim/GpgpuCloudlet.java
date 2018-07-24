package org.cloudbus.cloudsim;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.cloudbus.cloudsim.core.CloudSim;
import org.cloudbus.cloudsim.Cloudlet;

/*
 * Armazena todas as informações encapsuladas na GpgpuCloudlet, o ID da VM executando.
 */
public class GpgpuCloudlet extends Cloudlet{
	
	/*
	 * Array de instruções de CPU em Milhões
	 */
	private long ciLength[];
	
	/*
	 * Array de instruções de GPU em Milhões
	 */
	private long giLength[];
	
	/*
	 * Array de tamanho de input data de tarefas de GPU
	 */
	private long gpuInData[];
	

	/*
	 * Array de tamanho de output data de tarefas de GPU
	 */
	private long gpuOutData[];
	
	/*
	 * O numero de tarefas
	 */
	private int numOfTasks;
	
	/*
	 * O numero de GPUs
	 */
	private int numOfGpes;
	
	/*
	 * Tamanho total de instruções de CPU
	 */
	private long ciCloudletLength;
	
	/*
	 * Tamanho total de instruções de GPU
	 */
	private long giCloudletLength;
	
	/*
	 * Variaveis que são um conjunto em ordem pra processar o Job
	 */
	private int cpuJobSequence =0;
	private int gpuJobSequence=0;
	
	/*
	 * O modo de operação da CPU e GPU
	 */
	private boolean isCPUJobMode = true;
	
	
	public GpgpuCloudlet(
			final int cloudletId,
			final long cloudletLength,
			final int pesNumber,
			final long cloudletFileSize,
			final long cloudletOutputSize,
			final UtilizationModel utilizationModelCpu,
			final UtilizationModel utilizationModelRam,
			final UtilizationModel utilizationModelBw,
			
			long ciLength[],
			long giLength[],
			long gpuInData[],
			long gpuOutData[],
			int numOfTasks,
			int numOfGpes,
			long ciCloudletLength,
			long giCloudletLength,
			int cpuJobSequence,
			int gpuJobSequence
			)throws Exception {
		super(cloudletId,
				cloudletLength,
				pesNumber,
				cloudletFileSize,
				cloudletOutputSize,
				utilizationModelCpu,
				utilizationModelRam,
				utilizationModelBw);
		
		setCpuJobSequence(cpuJobSequence);
		setGpuJobSequence(gpuJobSequence);
		setNumOfTasks(numOfTasks);
	}
	
	
	private int getNumOfTasks() {
		return numOfTasks;
	}

	private void setNumOfTasks(int numOfTasks) {
		this.numOfTasks = numOfTasks;
	}





	public int getCpuJobSequence() {
		if(this.cpuJobSequence == this.getNumOfTasks())
			cpuJobSequence--;
		
		return this.cpuJobSequence;
	}
	void setCpuJobSequence(int cpuJobSequence) {
		this.cpuJobSequence = cpuJobSequence;
	}
	public void addCpuJobSequence() {
		this.cpuJobSequence++;
	}


	public int getGpuJobSequence() {
		if(this.gpuJobSequence == this.getNumOfTasks())
			gpuJobSequence--;
		
		return this.gpuJobSequence;
	}
	private void setGpuJobSequence(int gpuJobSequence) {
		this.gpuJobSequence = gpuJobSequence;
	}
	public void addGpuJobSequence() {
		this.gpuJobSequence++;
	}

	
	
}
