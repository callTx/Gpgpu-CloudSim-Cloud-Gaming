package org.cloudbus.cloudsim;

import org.cloudbus.cloudsim.provisioners.GpeProvisioner;

/*
 * Classe que representa a unidade de GPU, denifida em termos
 * de Milhões de Operações de Ponto Flutuante por Segundo (MFlops).
 * Observação: É assumido que todas as Gpes sob a mesma maquina tem 
 * o mesmo MFlops. 
 */

public class Gpe {
	/*
	 * A Gpe está livre para alocação
	 */
	public static final int FREE = 1;
	
	/*
	 * A Gpe está alocada e portando está ocupada no processamento de Cloudlet
	 */
	public static final int BUSY = 2;
	
	/*
	 * A Gpe falhou a então ela não pode processar qualquer Cloudlet 
	 * no momento. Ela falhou porque percente a uma maquina que também
	 * falhou 
	 */
	public static final int FAILED = 3;
	
	/** O id. */
	private int id;
	
	//PAra recurosos de espaço compartilhado
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
		
		//Quando crida a Gpe, ela deverá ser setada para FREE
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
	 * que ela está disponível para processamento. Isso deverá ser usado
	 *para apenas para hostlist de ESPAÇO compartilhado  
	 */
	public void setStatusFree() {
		setStatus(FREE);
	}
	
	/*
	 * Seta o status da Gpe para busy, o que significa que isso 
	 * já está executando cloudlets. Isso já deve ser usado
	 * apenas para hostlist de ESPAÇO compartilhado 
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
