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
		return proces.getPagetableEntrie(pageEntrie).getLastAcces();
	}
	public PagetableEntrie getPagetableEntrie(){
		return proces.getPagetableEntrie(pageEntrie);
	}
	public void setUitRam(){
		boolean schrijven = proces.getPagetableEntrie(pageEntrie).setUitRam();
		
	}
	
}
