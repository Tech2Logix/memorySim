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
	
	public int getProcesID(){
		return proces.getProcesNummer();
	}

	public int getPageEntrie() {
		return pageEntrie;
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
		//boolean schrijven = proces.getPagetableEntrie(pageEntrie).setUitRam();
		//if(schrijven){
		//	proces.addSchrijven();
		//}
		
		//NIEUW TOEVOEGEN:
		proces=p;
		pageEntrie=-1;
	}
	
	public void vulMet(Proces p){
		proces=p;
		pageEntrie=-1;
	}
	public void voegEntrieToe(Proces p, int nr){
		proces=p;
		pageEntrie=nr;
	}
	
	public void print(){
		if (proces != null){
			System.out.println("proces: " + proces.getProcesNummer()+"\tpage entrie: "+pageEntrie);
		}
		else{
			System.out.println("niet toegewezen aan een proces");
		}
	}
	
	@Override
	public String toString() {
		if(proces != null) {
			return "proces: " + proces.getProcesNummer()+"\tpage entrie: "+pageEntrie;
		} else
			return "niet toegewezen aan een proces";
	}
	
}
