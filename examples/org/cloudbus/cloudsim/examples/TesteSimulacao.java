package org.cloudbus.cloudsim.examples;

/*
 * Title:        CloudSim Toolkit
 * Description:  CloudSim (Cloud Simulation) Toolkit for Modeling and Simulation
 *               of Clouds
 * Licence:      GPL - http://www.gnu.org/copyleft/gpl.html
 *
 * Copyright (c) 2009, The University of Melbourne, Australia
 */

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.CloudletSchedulerTimeShared;
import org.cloudbus.cloudsim.Datacenter;
import org.cloudbus.cloudsim.DatacenterBroker;
import org.cloudbus.cloudsim.DatacenterCharacteristics;
import org.cloudbus.cloudsim.Host;
import org.cloudbus.cloudsim.Log;
import org.cloudbus.cloudsim.Pe;
import org.cloudbus.cloudsim.Storage;
import org.cloudbus.cloudsim.UtilizationModel;
import org.cloudbus.cloudsim.UtilizationModelFull;
import org.cloudbus.cloudsim.Vm;
import org.cloudbus.cloudsim.VmAllocationPolicySimple;
import org.cloudbus.cloudsim.VmSchedulerTimeShared;
import org.cloudbus.cloudsim.core.CloudSim;
import org.cloudbus.cloudsim.provisioners.BwProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.PeProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.RamProvisionerSimple;

import org.cloudbus.cloudsim.GpgpuCloudlet;

public class TesteSimulacao {

	private static List<Cloudlet> cloudletList;
	
	private static List<Vm> vmList;
	
	private static List<Vm> createVM(int userId,int vms){
		//Cria um container para armazenar as VMs. 
		//Isto é passado ao broker mais tarde
		LinkedList<Vm> list = new LinkedList<Vm>();
		
		//Descrição da Vm
		int mips = 1000;
		
		// 1GB --- 1000(MB)/ 10GB ---- 10000(MB) / 100GB --- 100000(MB) / 1TB ----- 1000000 (MB)
		long size = 100;//(MB)
		
		//1GB --- 1024MB/ 2GB ---- 2048MB / 4GB ---- 4096MB
		int ram = 1042;
		
		//Operadora: vende 10Mbits/s --(1/8)-- 1.25MBytes/s  
		// bit -(/8)-> Byte       bit <--(*8)-- Byte 
		long bw =10000; // (Kbits/s) 10Mbits
		int pesNumber = 1; // numero de CPUs
		String vmm = "Xen"; // nome da VM
		
		Vm[] vm = new Vm[vms];
		
		for (int i=0; i<vms;i++) {
			//CloudletSchedulerTimeShared: Frações de PEs(CPU) são compartilhadas entre os CloudLets executando,
			// e todos eles executam simultaneamente.
			vm[i] = new Vm(i,userId,mips,pesNumber,ram,bw,size,vmm,new CloudletSchedulerTimeShared());
			list.add(vm[i]);
		}
		return list;
	}
	
	private static List<Cloudlet> createCloudlet(int userId,int cloudlets){
		LinkedList<Cloudlet> list = new LinkedList<Cloudlet>();
		
		//Propriedades
		int id = 0;
		long length = 400000; //(MI) tamanho da instruções 
		long fileSizeInput = 900; // (KBytes) tamanho dos arquivos de entrada
		long fileSizeOutput = 900; // (KBytes) tamanho dos arquivos de saida
		int pesNumber =1; //numero de CPUs
		UtilizationModel utilizationModel = new UtilizationModelFull(); //Modelo que utiliza toda a capacidade do recurso.
		
		//CPU:UtilizationModelFull
		//ram: UtilizationModelFull
		//bw:UtilizationModelFull
		Cloudlet[] cloudlet = new Cloudlet[cloudlets];
		
		for(int i=0;i<cloudlets;i++){
			cloudlet[i] = new Cloudlet(i,length,pesNumber,fileSizeInput,fileSizeOutput,utilizationModel,utilizationModel,utilizationModel);
			cloudlet[i].setUserId(userId);
			list.add(cloudlet[i]);
		}
		
		return list;
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		Log.printLine("Iniciando um teste de simulação...");
		
		try {
		//	1º: Inicializar o pacote CloudSim
			int num_user = 40000;
			Calendar calendar = Calendar.getInstance();
			boolean trace_flag = false;
			
			//Inicializar a biblioteca CloudSim
			CloudSim.init(num_user, calendar, trace_flag);
			
			//2ª: Criar o(s) DataCenter(s)
			@SuppressWarnings("unused")
			Datacenter datacenter0 = createDatacenter("Datacenter_0");
			
			//3º: Criar o Broker
			DatacenterBroker broker = createBroker();
			int brokerId = broker.getId();
			
			//4º: Criar 20 maquinas virtuais e envia ao broker
			vmList = createVM(brokerId,20);// cria 20 VMs
			broker.submitVmList(vmList);
			
			//5º: Criar 01 CloudLet
			cloudletList = createCloudlet(brokerId,30);
			broker.submitCloudletList(cloudletList);
			
			//6º: Iniciar a simulação
			CloudSim.startSimulation();
			
			//7º: Imprimir os resultados quando a simulação termianr
			List<Cloudlet> newList = broker.getCloudletReceivedList();
			CloudSim.stopSimulation();
			printCloudletList(newList);
			
			Log.printLine("Simulação finalizada!");
			
		}catch (Exception e) {
			e.printStackTrace();
			Log.printLine("Erros inesperados aconteceram");
		}
	}
	
	
	private static Datacenter createDatacenter(String name) {
		
		//1º Passo  - Criar a lista para armazenar maquina(s)
		List<Host> hostList = new ArrayList<Host>();
		
		//A maquina contem um ou mais CPUs/Cores
		//2º Passo  - Criar uma lista para armazenar 4 PEs para uma maquina
		List<Pe> peList = new ArrayList<Pe>();
		
		int mips = 10000;
		
		//3º Passo  - Cria 4 PEs e adiciona a lista
		peList.add(new Pe(0, new PeProvisionerSimple(mips)));
		peList.add(new Pe(1, new PeProvisionerSimple(mips)));
		peList.add(new Pe(2, new PeProvisionerSimple(mips)));
		peList.add(new Pe(3, new PeProvisionerSimple(mips)));
		
		//4º Passo  - Cria o Host e adiciona a lista de maquina(s)
		//Base 10^:    KiloByte 10^3 MegaByte 10^6 GigaByte 10^9 TeraByte 10^12
		//Base 2^:     KiloByte 2^10 MegaByte 2^20 GigaByte 2^30 TeraByte 2^40
		int hostId = 0;
		
		// Base 2^
		//1GB --- 1024MB/ 2GB ---- 2048MB / 4GB ---- 4096MB /.... /20GB ----- 20480MB 
		int ram = 21864; //(MB) Byte     ***2048
		
		//Base 10^
		// 1GB --- 1000(MB)/ 10GB ---- 10000(MB) / 100GB --- 100000(MB) / 1TB ----- 1000000 (MB) /
		long storage = 100000;// (MB) Byte
		
		//Base 2^
		//Operadora: vende 10Mbits/s --(1/8)-- 1.25MBytes/s  
		// bit -(/8)-> Byte       bit <--(*8)-- Byte 
		//10Mbits/s------10000Kbits/s / 20Mbits/s------20000Kbits/s / 40Mbits/s------40000Kbits/s/ ... / 200Mbits/s------200000Kbits/s  
		int bw = 200000; //(Kbits/s)  ***10000
		
		hostList.add(new Host(
				hostId,
				new RamProvisionerSimple(ram),
				new BwProvisionerSimple(bw),
				storage,
				peList,
				new VmSchedulerTimeShared(peList)
				));//Maquina 
		
		//5° Passo - Criar um objeto DataCenterCharacteristics que armazena propriedades do datacenter:
		//arquitetura, OS, list de Maquina, politica de alocação: tempo ou espaço compartilhado, time-zone e preço
		String arch = "x86";
		String os = "Linux";
		String vmm = "Xen";
		double time_zone = 4.0; //América do Sul
		double costCPU = 0.10;// $/segundo 
		double costPerMem = 0.05; // 
		double costPerStorage = 0.001;
		double costPerBw = 0.10;
		
		LinkedList<Storage> storageList = new LinkedList<Storage>();
		
		DatacenterCharacteristics characteristics = new DatacenterCharacteristics(
				arch,os,vmm,hostList,time_zone,costCPU,costPerMem,costPerStorage,
				costPerBw);
		
		//6° Passo - Criar o objeto PowerDataCenter
		Datacenter datacenter = null;
		try {
			//VmAllocationPolicySimple: Escolhe a VM no host com o menor uso de PE(CPU).
			datacenter = new Datacenter(name,characteristics, new VmAllocationPolicySimple(hostList),storageList,0);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return datacenter;
		
		
	}
	
	private static DatacenterBroker createBroker() {
		
		DatacenterBroker broker = null;
		try {
			broker = new DatacenterBroker("Broker");
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		return broker;
	}
	
	
	private static void printCloudletList(List<Cloudlet> list) {
		
		int size = list.size();
		Cloudlet cloudlet;
		String identa = "      ";
		Log.printLine();
		Log.printLine("============ Saida ================");
		Log.printLine("Cloudlet ID"+ identa + "Status"+ identa+
				"Datacenter ID"+ identa + "VM ID" + identa + identa + 
				"Tempo(segunodos)" + identa + "Tempo inicio(segundos)" + identa + "Tempo fim(segundos)");
		
		DecimalFormat dft = new DecimalFormat("###.##");
		for(int i=0; i<size;i++) {
			
			cloudlet = list.get(i);
			Log.print(identa+ cloudlet.getCloudletId()+ identa+ identa);
			if(cloudlet.getCloudletStatus() == Cloudlet.SUCCESS) {
				Log.print("SUCESSO");
				
				Log.printLine(identa + identa + cloudlet.getResourceId()+ identa + identa + identa + cloudlet.getVmId() +
						identa + identa + identa + dft.format(cloudlet.getActualCPUTime())+
						identa + identa + dft.format(cloudlet.getExecStartTime())+ identa+ identa+identa+
						dft.format(cloudlet.getFinishTime()));
			}
			
		}
	}
	
}
