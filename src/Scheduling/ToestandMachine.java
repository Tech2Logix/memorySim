package Scheduling;

import java.util.ArrayList;
import java.util.Arrays;

public class ToestandMachine {
	private RAMEntrie[] ram;
	private ArrayList<Proces> alleProcessen; // om statistieken over processen
												// die uit ram zijn bij te
												// kunnen houden
	private ArrayList<Proces> processenInRam;
	private Instructie huidigeInstr;
	private int timer;
	private int nLeesOpdrachten;
	private int nSchrijfOpdrachten;
	private int huidigRealAdres;

	public ToestandMachine() {
		ram = new RAMEntrie[12];
		for (int i = 0; i < 12; i++) {
			ram[i] = new RAMEntrie();
		}
		alleProcessen = new ArrayList<Proces>();
		processenInRam = new ArrayList<Proces>();
		timer = 0;
		nSchrijfOpdrachten = 0;
		nLeesOpdrachten = 0;
		huidigRealAdres = -1;
	}

	public ToestandMachine(ToestandMachine pc) {
		this.ram = pc.getRam();
		this.alleProcessen = pc.getAlleProcessen();
		this.processenInRam = pc.getProcessenInRam();
		this.timer = pc.getTimer();
		this.nSchrijfOpdrachten = pc.getnSchrijfOpdrachten();
		this.nLeesOpdrachten = pc.getnLeesOpdrachten();
	}

	public void reset() {
		ram = new RAMEntrie[12];
		for (int i = 0; i < 12; i++) {
			ram[i] = new RAMEntrie();
		}
		alleProcessen = new ArrayList<Proces>();
		processenInRam = new ArrayList<Proces>();
		timer = 0;
		nSchrijfOpdrachten = 0;
		nLeesOpdrachten = 0;
	}

	public RAMEntrie[] getRam() {
		return ram;
	}

	public void setRam(RAMEntrie[] ram) {
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

	public Instructie getHuidigeInstr() {
		return huidigeInstr;
	}

	public void setHuidigeInstr(Instructie huidigeInstr) {
		this.huidigeInstr = huidigeInstr;
	}

	public int getTimer() {
		return timer;
	}

	public void setTimer(int timer) {
		this.timer = timer;
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

	public void doorloopVolgendeInstructie(InstructieList i) { // dit is slechts
																// 1 instructie
																// uitvoeren
		huidigeInstr = i.getInstructie(timer);
		timer++;

		switch (huidigeInstr.getOperatie()) {
		case "Write":
			read(i);
			write(i);
			break;
		case "Read":
			read(i);
			break;
		case "Start":
			start(i);
			break;
		case "Terminate":
			terminate(i);
			break;
		default:
			break;
		}
	}

	public void write(InstructieList i) {
		Proces huidigProces = alleProcessen.get(huidigeInstr.getProcesID());
		PagetableEntrie huidigePageEntrie = huidigProces.getPagetableEntrie(huidigeInstr.getAdress() / 4096);
		huidigePageEntrie.setModify(true);
		huidigRealAdres = huidigeInstr.getAdress() % 4096 + huidigePageEntrie.getFrameNummer() * 4096;
	}

	public void read(InstructieList i) {
		int paginaNummer, huidigProcesID, oudsteTotNu;
		Proces huidigProces;
		huidigProces = alleProcessen.get(huidigeInstr.getProcesID());
		paginaNummer = huidigeInstr.getAdress() / 4096;
		if (!alleProcessen.get(huidigeInstr.getProcesID()).paginaNummerPresent(paginaNummer)) {// als
																								// het
																								// niet
																								// aanwezig
																								// is
																								// in
																								// ram
			oudsteTotNu = timer;
			huidigProcesID = huidigeInstr.getProcesID();

			int teVervangenFrameNummer = 0;// moet een beginwaarde hebben, maakt
											// niet uit wat dit is
			for (int j = 0; j < 12; j++) {
				if ((ram[j].getProces().getProcesNummer() == huidigProcesID) && (ram[j].getLastAcces() < oudsteTotNu)) {
					teVervangenFrameNummer = j;
					oudsteTotNu = ram[j].getLastAcces();
				}
			}

			// page table aanpassen:
			if (ram[teVervangenFrameNummer].getPageEntrie() != -1) {// -1 is een
																	// niet
																	// toegewezen
																	// entrie
				PagetableEntrie teVervangenPageTable = ram[teVervangenFrameNummer].getPagetableEntrie();
				if (teVervangenPageTable.isModify()) {
					nSchrijfOpdrachten++;
				}
				teVervangenPageTable.doeUitRam();
			}

			huidigProces.getPagetableEntrie(paginaNummer).doeInRam(teVervangenFrameNummer, timer);

			// ramtable aanpassen:
			nLeesOpdrachten++;
			ram[teVervangenFrameNummer].voegEntrieToe(huidigProces, paginaNummer);

		} else {
			// lastacces nog aanpassen
			huidigProces.getPagetableEntrie(paginaNummer).setLastAcces(timer);
		}

		huidigRealAdres = huidigeInstr.getAdress() % 4096
				+ huidigProces.getPagetableEntrie(paginaNummer).getFrameNummer() * 4096;
	}

	public void start(InstructieList i) {
		int nodigeEntries, toegewezenEntries, oudsteTotNu;
		huidigeInstr = i.getInstructie(timer);
		Proces huidigProces = new Proces();
		RAMEntrie oudsteFrame = new RAMEntrie(); // dit heeft geen betekenis, is
													// enkel zodat geen error
													// komt door oningevulde
													// variabele
		alleProcessen.add(huidigProces);

		if (processenInRam.size() == 4) {
			// proces uit ram halen->komt alleen voor in tweede voorbeelddata
		} else if (processenInRam.size() == 0) {
			for (RAMEntrie r : ram) {
				r.vulMet(huidigProces);
			}
		} else {
			nodigeEntries = 12 / (processenInRam.size() + 1);
			toegewezenEntries = 0;
			while (nodigeEntries > toegewezenEntries) {
				for (Proces pr : processenInRam) {
					oudsteTotNu = timer;
					for (RAMEntrie ra : ram) {
						if ((ra.getProces() == pr) && (ra.getLastAcces() < oudsteTotNu)) {
							oudsteTotNu = ra.getLastAcces();
							oudsteFrame = ra;
						}
					}
					toegewezenEntries++;
					oudsteFrame.setUitRamEnVoegToe(huidigProces);
				}
			}
		}
		processenInRam.add(huidigProces);

		huidigRealAdres = -1;
	}

	public void terminate(InstructieList i) {
		int nNieuwePlaatsenPerProces;
		
		System.out.println("*******" + processenInRam.size());
		int oudProcesID = i.getInstructie(timer - 1).getProcesID();
		Proces pr = processenInRam.get(0);// de beginwaarde speelt geen rol
		for (Proces p : processenInRam) {
			if (p.getProcesNummer() == oudProcesID) {
				pr = p;
			}
		}
		processenInRam.remove(pr);

		int loper = 0, k = 0, nOverigeActieveProcessen = processenInRam.size();
		System.out.println("hallo!" + nOverigeActieveProcessen);
		if (nOverigeActieveProcessen == 0) {
			// hier moet niets gebeuren want het ram kan niet doorgegeven
			// worden?

		} else {
			switch (nOverigeActieveProcessen) {
			case 1:
				nNieuwePlaatsenPerProces = 6;
				break;
			case 2:
				nNieuwePlaatsenPerProces = 2;
				break;
			default:
				nNieuwePlaatsenPerProces = 1;
				break; // = case 3
			}
			for (int j = 0; j < nOverigeActieveProcessen; j++) {
				int teller = nNieuwePlaatsenPerProces;
				System.out.println(teller);
				while (teller > 0) {
					/*** DEBUG ***/
					System.out.println("loper: " + loper + "grootte ram:" + ram.length);
					System.out.println("process ID: " + ram[loper].getProcesID());
					/*** DEBUG ***/
					if (ram[loper].getProcesID() == oudProcesID) { // hier
																	// gaat'm
																	// RIP
						// eventueel +write:
						if (ram[loper].getPageEntrie() != -1) {
							if (ram[loper].getPagetableEntrie().isModify()) {
								nSchrijfOpdrachten++;
							}
							// oudepagetables aanpassen
							ram[loper].getPagetableEntrie().doeUitRam();
						}
						// ram vullen
						ram[loper].vulMet(processenInRam.get(j));
						teller--;
					}
					loper++;
				}
			}
		}
		
		huidigRealAdres = -1;
	}

	public int getHuidigRealAdres() {
		return huidigRealAdres;
	}

	public void setHuidigRealAdres(int huidigRealAdres) {
		this.huidigRealAdres = huidigRealAdres;
	}

	public void printToestand(InstructieList il) {
		System.out.println("----------------------------------------------------------------------------------");
		System.out.println("\ntimer=" + timer + "\tschrijf opdrachten: " + nSchrijfOpdrachten + "\tlees opdrachten: "
				+ nLeesOpdrachten);

		System.out.println("\n  RAM:" + "\n  ----");
		for (int i = 0; i < 12; i++) {
			System.out.print(i + "\t");
			ram[i].print();
		}

		int procesID = il.getInstructie(timer - 1).getProcesID();
		Proces p = alleProcessen.get(procesID);
		System.out.println("\n  PAGETABLE van proces " + procesID + "\n  ---------");
		p.printPageTables();
	}

	public String printRam() {
		String ram = "";
		for (int i = 0; i < 12; i++) {
			ram += i + "\t";
			ram += this.ram[i].toString();
			ram += "\n";
		}
		return ram;
	}

	@Override
	public String toString() {
		return "ToestandMachine: \n [ram=" + Arrays.toString(ram) + ",\n alleProcessen=" + alleProcessen
				+ ",\n processenInRam=" + processenInRam + "/" + processenInRam.size() + ",\n huidigeInstr="
				+ huidigeInstr + ",\n timer=" + timer + ",\n nLeesOpdrachten=" + nLeesOpdrachten
				+ ",\n nSchrijfOpdrachten=" + nSchrijfOpdrachten + ",\n huidigRealAdres=" + huidigRealAdres + "]";
	}

}
