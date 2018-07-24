package org.cloudbus.cloudsim.examples;

import org.cloudbus.cloudsim.GpgpuCloudlet;
import org.cloudbus.cloudsim.UtilizationModel;
import org.cloudbus.cloudsim.UtilizationModelFull;

import java.util.List;
import java.util.LinkedList;

public class TesteSimulacaoGpgpu {
	private List<GpgpuCloudlet> cloudletList;
	
	
	private static List<GpgpuCloudlet> createCloudlet(int userId, int cloudlets){
		LinkedList<GpgpuCloudlet> list = new LinkedList<GpgpuCloudlet>();
		
		//Propriedades
		int id =0;
		long length = 400000; // (MI) tamanho das instruções
		long fileSizeInput = 900; // (KBytes) tamanho dos arquivos de entrada
		long fileSizeOutput = 900; // (KBytes) tamanho dos arquivos de saida
		int pesNumber =1; //numero de CPUs
		
		//Modelo que utiliza toda a capacidade do recurso.
		UtilizationModel utilizationModel = new UtilizationModelFull();
		
		//CPU:UtilizationModelFull
	    //ram: UtilizationModelFull
		//bw:UtilizationModelFull
		GpgpuCloudlet[] cloudlet = new GpgpuCloudlet[cloudlets];
		
		for(int i=0;i<cloudlets;i++) {
			cloudlet[i] = new GpgpuCloudlet();
		}
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		
		
		
		//5º: Criar 01 GpgpuCloudlet
		cloudletList = createCloudlet(brokerId,30);
	}
}
