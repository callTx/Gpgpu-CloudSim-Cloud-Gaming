package org.cloudbus.cloudsim;

import org.cloudbus.cloudsim.provisioners.GpeProvisioner;

/*
 * Classe que representa a unidade de GPU, denifida em termos
 * de Milh�es de Opera��es de Ponto Flutuante por Segundo (MFlops).
 * Observa��o: � assumido que todas as Gpes sob a mesma maquina tem 
 * o mesmo MFlops. 
 */

public class Gpe {
	/*
	 * A Gpe est� livre para aloca��o
	 */
	public static final int FREE = 1;
	
	/*
	 * A Gpe est� alocada e portando est� ocupada no processamento de Cloudlet
	 */
	public static final int BUSY = 2;
	
	/*
	 * A Gpe falhou a ent�o ela n�o pode processar qualquer Cloudlet 
	 * no momento. Ela falhou porque percente a uma maquina que tamb�m
	 * falhou 
	 */
	public static final int FAILED = 3;
	
	/** O id. */
	private int id;
	
	//PAra recurosos de espa�o compartilhado
	/*
	 * O status da Gpe: FREE, BUSY, FAILED
	 */
	private int status;
	
	/** O provisioner da Gpe. */
	private GpeProvisioner gpeProvisioner;
	
	/*
	 * Aloca um novo objeto Gpe
	 */
	public Gpe(int id,
			GpeProvisioner gpeProvisioner) {
		setId(id);
		setGpeProvisioner(gpeProvisioner);
		
		//Quando crida a Gpe, ela dever� ser setada para FREE
		status = FREE;
	}

	public int getId() {
		return id;
	}

	protected void setId(int id) {
		this.id = id;
	}
	
	/*
	 * Seta a taxa de Mflops desta Gpe
	 */
	public void setMflops(double d) {
		getGpeProvisioner().setMflops(d);
	}
	
	/*
	 * Pega a taxa de Mflops para esta Gpe 
	 */
	public int getMflops() {
		return (int) getGpeProvisioner().getMflops();
	}
	
	/*
	 * Pega o status desta Gpe
	 */
	public int getStatus() {
		return status;
	}
	
	/*
	 * Seta o status da Gpe para free, o que significa 
	 * que ela est� dispon�vel para processamento. Isso dever� ser usado
	 *para apenas para hostlist de ESPA�O compartilhado  
	 */
	public void setStatusFree() {
		setStatus(FREE);
	}
	
	/*
	 * Seta o status da Gpe para busy, o que significa que isso 
	 * j� est� executando cloudlets. Isso j� deve ser usado
	 * apenas para hostlist de ESPA�O compartilhado 
	 */
	public void setStatusBusy() {
		setStatus(BUSY);
	}
	
	/*
	 * Set status Gpe para FAILED
	 */
	public void setStatusFailed() {
		setStatus(FAILED);
	}
	
	/*
	 * Set o status da Gpe
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	/*
	 * Pega o Gpe provisioner
	 */
	public GpeProvisioner getGpeProvisioner() {
		return gpeProvisioner;
	}

	/*
	 * Seta o Gpe provisioner
	 */
	protected void setGpeProvisioner(GpeProvisioner gpeProvisioner) {
		this.gpeProvisioner = gpeProvisioner;
	}
}
