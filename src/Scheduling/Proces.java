package Scheduling;

public class Proces {
	private Pagetable pagetable;
	private int nSchrijfopdrachten; //van persistentGeheugen naar RAM
	private int nLeesopdrachten;
	
	public Proces(){
		pagetable = new Pagetable();
		nSchrijfopdrachten=0;
		nLeesopdrachten=0;
	}
}
