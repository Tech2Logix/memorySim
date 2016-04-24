package Scheduling;

public class RAMEntry {
	private Proces proces;
	private int pageentry ;
	
	public RAMEntry(){
	}
	
	public RAMEntry(Proces p, int i){
		proces=p;
		pageentry=i;
	}
	
	public void reset() {
		proces = new Proces();
		pageentry = 0;
	}
	
	public Proces getProces() {
		return proces;
	}
	
	public int getProcesID(){
		return proces.getProcesNummer();
	}

	public int getPageentry() {
		return pageentry;
	}
	
	public int getLastAcces(){
		if(pageentry == -1){
			return -1;//zo zal een lege entry zeker voorrang krijgen op een andere
		}
		else return proces.getPagetableentry(pageentry).getLastAcces();
	}
	public PagetableEntry getPagetableentry(){
		return proces.getPagetableentry(pageentry);
	}
	
	public void setUitRamEnVoegToe(Proces p){
		//OUD WISSEN:
		//boolean schrijven = proces.getPagetableentry(pageentry).setUitRam();
		//if(schrijven){
		//	proces.addSchrijven();
		//}
		
		//NIEUW TOEVOEGEN:
		proces=p;
		pageentry=-1;
	}
	
	public void vulMet(Proces p){
		proces=p;
		pageentry=-1;
	}
	public void voegentryToe(Proces p, int nr){
		proces=p;
		pageentry=nr;
	}
	
	public void print(){
		if (proces != null){
			System.out.println("proces: " + proces.getProcesNummer()+"\tpage entry: "+pageentry);
		}
		else{
			System.out.println("niet toegewezen aan een proces");
		}
	}
	
	@Override
	public String toString() {
		if(proces != null) {
			return "proces: " + proces.getProcesNummer()+"\tpage entry: "+pageentry;
		} else
			return "niet toegewezen aan een proces";
	}
	
}
