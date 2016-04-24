package Scheduling;

public class RAMEntry {
	private Proces proces;
	private int pageEntry ;
	
	public RAMEntry(){
	}
	
	public RAMEntry(Proces p, int i){
		proces=p;
		pageEntry=i;
	}
	
	public void reset() {
		proces = null;
		pageEntry = -1;
	}
	
	public Proces getProces() {
		return proces;
	}
	
	public int getProcesID(){
		return proces.getProcesNummer();
	}

	public int getPageEntry() {
		return pageEntry;
	}
	
	public int getLastAcces(){
		if(pageEntry == -1){
			return -1;//zo zal een lege entry zeker voorrang krijgen op een andere
		}
		else return proces.getPagetableentry(pageEntry).getLastAcces();
	}
	public PagetableEntry getPagetableentry(){
		return proces.getPagetableentry(pageEntry);
	}
	
	public void setUitRamEnVoegToe(Proces p){
		//OUD WISSEN:
		//boolean schrijven = proces.getPagetableentry(pageEntry).setUitRam();
		//if(schrijven){
		//	proces.addSchrijven();
		//}
		
		//NIEUW TOEVOEGEN:
		proces=p;
		pageEntry=-1;
	}
	
	public void vulMet(Proces p){
		proces=p;
		pageEntry=-1;
	}
	public void voegentryToe(Proces p, int nr){
		proces=p;
		pageEntry=nr;
	}
	
	public void print(){
		if (proces != null){
			System.out.println("proces: " + proces.getProcesNummer()+"\tpage entry: "+pageEntry);
		}
		else{
			System.out.println("niet toegewezen aan een proces");
		}
	}
	
	@Override
	public String toString() {
		String ret = null;
		if(proces != null) {
			if(pageEntry == -1) ret= "proces: " + proces.getProcesNummer();
			else ret = "proces: " + proces.getProcesNummer()+"\tpage entry: "+pageEntry;
		} else
			ret = "niet toegewezen aan een proces";
		
		return ret;
	}
	
}
