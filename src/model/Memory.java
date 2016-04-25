package model;

import java.util.ArrayList;

import Scheduling.Instructie;
import Scheduling.Proces;
import Scheduling.RAMEntry;

public class Memory {
	private RAMEntry[] ram;
	private ArrayList<Proces> alleProcessen;
	private ArrayList<Proces> processenInRam;
	private int nLeesOpdrachten;
	private int nSchrijfOpdrachten;
	private int huidigRealAdres;
	
	
	public Memory(RAMEntry[] ram, ArrayList<Proces> alleProcessen, ArrayList<Proces> processenInRam,
			int nLeesOpdrachten, int nSchrijfOpdrachten, int huidigRealAdres) {
		super();
		this.ram = ram;
		this.alleProcessen = alleProcessen;
		this.processenInRam = processenInRam;
		this.nLeesOpdrachten = nLeesOpdrachten;
		this.nSchrijfOpdrachten = nSchrijfOpdrachten;
		this.huidigRealAdres = huidigRealAdres;
	}

	public Memory() {
		ram = new RAMEntry[12];
		for (int i = 0; i < 12; i++) {
			ram[i] = new RAMEntry();
		}
		alleProcessen = new ArrayList<Proces>();
		processenInRam = new ArrayList<Proces>();
		nSchrijfOpdrachten = 0;
		nLeesOpdrachten = 0;
		huidigRealAdres = -1;
	}
	
	public Memory(Memory memory) {
		super();
		this.ram = memory.ram;
		this.alleProcessen = memory.alleProcessen;
		this.processenInRam = memory.processenInRam;
		this.nLeesOpdrachten = memory.nLeesOpdrachten;
		this.nSchrijfOpdrachten = memory.nSchrijfOpdrachten;
		this.huidigRealAdres = memory.huidigRealAdres;
	}

	public void reset() {
		if (alleProcessen.size() != 0)
			alleProcessen.get(0).reset();

		for (int i = 0; i < 12; i++) {
			ram[i] = new RAMEntry();
		}

		alleProcessen = new ArrayList<Proces>();
		processenInRam = new ArrayList<Proces>();
		nSchrijfOpdrachten = 0;
		nLeesOpdrachten = 0;
	}

	public RAMEntry[] getRam() {
		return ram;
	}


	public void setRam(RAMEntry[] ram) {
		this.ram = ram;
	}


	public ArrayList<Proces> getAlleProcessen() {
		return alleProcessen;
	}


	public void setAlleProcessen(ArrayList<Proces> alleProcessen) {
		this.alleProcessen = alleProcessen;
	}


	public ArrayList<Proces> getProcessenInRam() {
		return processenInRam;
	}


	public void setProcessenInRam(ArrayList<Proces> processenInRam) {
		this.processenInRam = processenInRam;
	}


	public int getnLeesOpdrachten() {
		return nLeesOpdrachten;
	}


	public void setnLeesOpdrachten(int nLeesOpdrachten) {
		this.nLeesOpdrachten = nLeesOpdrachten;
	}


	public int getnSchrijfOpdrachten() {
		return nSchrijfOpdrachten;
	}


	public void setnSchrijfOpdrachten(int nSchrijfOpdrachten) {
		this.nSchrijfOpdrachten = nSchrijfOpdrachten;
	}


	public int getHuidigRealAdres() {
		return huidigRealAdres;
	}


	public void setHuidigRealAdres(int huidigRealAdres) {
		this.huidigRealAdres = huidigRealAdres;
	}
	
	
}
