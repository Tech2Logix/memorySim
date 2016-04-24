package Scheduling;

public class Proces {
	private static int nVoorgaandeProcessen=0;

	private PagetableEntry[] pagetable;
	private int procesNummer;
	
	public void reset() {
		Proces.nVoorgaandeProcessen = 0;
	}
	
	public int getProcesNummer() {
		return procesNummer;
	}

	public void setProcesNummer(int procesNummer) {
		this.procesNummer = procesNummer;
	}

	public Proces(){
		pagetable = new PagetableEntry[16];
		for (int i=0;i<16;i++){
			pagetable[i]=new PagetableEntry();
		}

		procesNummer=nVoorgaandeProcessen;
		nVoorgaandeProcessen++;
	}
	
	
	public PagetableEntry getPagetableentry(int i){
		return pagetable[i];
	}
	public PagetableEntry[] getPagetable() {
		return pagetable;
	}

	public void setPagetable(PagetableEntry[] pagetable) {
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
	
	public String pageTabletoString() {
		String pageTable = "";
		for (int i=0;i<16;i++){
			pageTable += i+"\t";
			pageTable += pagetable[i].toString();
			pageTable += "\n";
		}
		return pageTable;
	}
	
	public int getnVoorgaandeProcessen() {
		return nVoorgaandeProcessen;
	}

	public void setnVoorgaandeProcessen(int nVoorgaandeProcessen) {
		Proces.nVoorgaandeProcessen = nVoorgaandeProcessen;
	}
	
}
