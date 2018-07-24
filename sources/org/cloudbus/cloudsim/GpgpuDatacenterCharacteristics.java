package org.cloudbus.cloudsim;


import java.util.List;

import org.cloudbus.cloudsim.DatacenterCharacteristics;

public class GpgpuDatacenterCharacteristics extends DatacenterCharacteristics{

	//Parte do datacenter que define cada politica como uma constante
	/*
	 * Sistema Time-shared usando o algoritmo Round-Robin
	 */
	public static final int TIME_SHARED = 0;
	
	/*
	 * Sistema Spaced-Shared usando o algoritmo First Come First Serve (FCFS)
	 */
	public static final int SPACE_SHARED = 1;
	
	/*
	 * Assumindo que todas as Pes in todas as Machines tem
	 * o mesmo rating
	 */
	public static int OTHER_POLICY_SAME_RATING = 2;
	
	/*
	 * Assumindo que todas as Pes em uma Machine tem o mesmo rating. Contudo,
	 * cada Machine tem diferentes rating cada uma.
	 */
	public static final int OTHER_POLICY_DIFFERENT_RATING = 3;
	
	/*
	 * Uma recurso que suporta Advanced Reservation Mechanisms
	 */
	public static final int ADVANCE_RESERVATION = 4;
	
	public GpgpuDatacenterCharacteristics(
			String architecture,
			String os,
			String vmm,
			List<? extends Host> hostList,
			double timeZone,
			double costPerSec,
			double costPerMem,
			double costPerStorage,
			double costPerBw
			) {
		super(architecture,os,vmm,hostList,timeZone,costPerSec,costPerMem,costPerStorage,costPerBw);
	}
	
}
