package Scheduling;

public class PagetableEntrie {
	private boolean present, modify;
	private int lastAcces, frameNummer;
	
	public PagetableEntrie(){
		present=false;
		modify=false;
		lastAcces=-1;
		frameNummer=-1;
	}
	
}
