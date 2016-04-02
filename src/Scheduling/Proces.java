package Scheduling;

public class Proces {
	static int nVoorgaandeProcessen=0;
	private PagetableEntrie[] pagetable;
	private int nSchrijfopdrachten; //van persistentGeheugen naar RAM
	private int nLeesopdrachten;
	private int procesNummer;
	
	public int getProcesNummer() {
		return procesNummer;
	}

	public void setProcesNummer(int procesNummer) {
		this.procesNummer = procesNummer;
	}

	public Proces(){
		pagetable = new PagetableEntrie[16];
		for (int i=0;i<16;i++){
			pagetable[i]=new PagetableEntrie();
		}
		nSchrijfopdrachten=0;
		nLeesopdrachten=0;
		procesNummer=nVoorgaandeProcessen;
		nVoorgaandeProcessen++;
	}
	
	public void addSchrijven(){
		nSchrijfopdrachten++;
	}
	
	public PagetableEntrie getPagetableEntrie(int i){
		return pagetable[i];
	}
	public PagetableEntrie[] getPagetable() {
		return pagetable;
	}

	public void setPagetable(PagetableEntrie[] pagetable) {
		this.pagetable = pagetable;
	}

	public int getnSchrijfopdrachten() {
		return nSchrijfopdrachten;
	}

	public void setnSchrijfopdrachten(int nSchrijfopdrachten) {
		this.nSchrijfopdrachten = nSchrijfopdrachten;
	}

	public int getnLeesopdrachten() {
		return nLeesopdrachten;
	}

	public void setnLeesopdrachten(int nLeesopdrachten) {
		this.nLeesopdrachten = nLeesopdrachten;
	}
	public boolean paginaNummerPresent(int i){
		return pagetable[i].isPresent();
	}
	
}
