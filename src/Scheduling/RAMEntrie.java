package Scheduling;

public class RAMEntrie {
	private Proces proces;
	private int pageEntrie ;
	
	public RAMEntrie(){
	}
	public RAMEntrie(Proces p, int i){
		proces=p;
		pageEntrie=i;
	}
	
	public Proces getProces() {
		return proces;
	}
	public void setProces(Proces proces) {
		this.proces = proces;
	}
	public int getPageEntrie() {
		return pageEntrie;
	}
	public void setPageEntrie(int pageEntrie) {
		this.pageEntrie = pageEntrie;
	}
	
	public int getLastAcces(){
		if(pageEntrie == -1){
			return -1;//zo zal een lege entrie zeker voorrang krijgen op een andere
		}
		else return proces.getPagetableEntrie(pageEntrie).getLastAcces();
	}
	public PagetableEntrie getPagetableEntrie(){
		return proces.getPagetableEntrie(pageEntrie);
	}
	
	public void setUitRamEnVoegToe(Proces p){
		//OUD WISSEN:
		boolean schrijven = proces.getPagetableEntrie(pageEntrie).setUitRam();
		if(schrijven){
			proces.addSchrijven();
		}
		
		//NIEUW TOEVOEGEN:
		proces=p;
		pageEntrie=-1;
	}
	
	public void vulMet(Proces p){
		proces=p;
		pageEntrie=-1;
	}
	
	public void print(){
		if (proces != null){
			System.out.println("proces: " + proces.getProcesNummer()+"\tpage entrie: "+pageEntrie);
		}
		else{
			System.out.println("niet toegwezen aan een proces");
		}
	}
	
}
