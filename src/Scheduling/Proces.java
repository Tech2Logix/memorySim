package Scheduling;

public class Proces {
	static int nVoorgaandeProcessen=0;
	private PagetableEntrie[] pagetable;
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

		procesNummer=nVoorgaandeProcessen;
		nVoorgaandeProcessen++;
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


	public boolean paginaNummerPresent(int i){
		return pagetable[i].isPresent();
	}
	
	public void printPageTables(){
		for (int i=0;i<16;i++){
			System.out.print(i+"\t");
			pagetable[i].print();
		}
	}
	
}
