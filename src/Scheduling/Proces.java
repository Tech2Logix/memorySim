package Scheduling;

public class Proces {
	private PagetableEntrie[] pagetable;
	private int nSchrijfopdrachten; //van persistentGeheugen naar RAM
	private int nLeesopdrachten;
	
	public Proces(){
		pagetable = new PagetableEntrie[16];
		nSchrijfopdrachten=0;
		nLeesopdrachten=0;
	}
}
