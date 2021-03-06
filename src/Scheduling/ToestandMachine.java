package Scheduling;

import java.util.ArrayList;
import java.util.Arrays;

public class ToestandMachine {
	private RAMEntry[] ram;
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
		ram = new RAMEntry[12];
		for (int i = 0; i < 12; i++) {
			ram[i] = new RAMEntry();
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
		if (alleProcessen.size() != 0)
			alleProcessen.get(0).reset();

		for (int i = 0; i < 12; i++) {
			ram[i] = new RAMEntry();
		}

		alleProcessen = new ArrayList<Proces>();
		processenInRam = new ArrayList<Proces>();
		timer = 0;
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

	public void doorloopVolgendeInstructie(InstructieList i) {
		if (timer < i.getInstructieLijst().size()) {
			//System.out.println("DEBUG: " + timer + " - " + i.getInstructie(timer).toString());
			huidigeInstr = i.getInstructie(timer);
			timer++;
		}
		
		switch (huidigeInstr.getOperatie()) {
		case "Write":
			read();
			write();
			break;
		case "Read":
			read();
			break;
		case "Start":
			start();
			break;
		case "Terminate":
			terminate();
			break;
		default:
			break;
		}
	}

	public void write() {
		Proces huidigProces = alleProcessen.get(huidigeInstr.getProcesID());
		PagetableEntry huidigePageentry = huidigProces.getPagetableentry(huidigeInstr.getAdress() / 4096);
		huidigePageentry.setModify(true);
		huidigRealAdres = huidigeInstr.getAdress() % 4096 + huidigePageentry.getFrameNummer() * 4096;
	}

	public void read() {
		int paginaNummer, huidigProcesID, oudsteTotNu;
		Proces huidigProces;
		huidigProces = alleProcessen.get(huidigeInstr.getProcesID());
		paginaNummer = huidigeInstr.getAdress() / 4096;
		if (!alleProcessen.get(huidigeInstr.getProcesID()).paginaNummerPresent(paginaNummer)) {
			// als het niet aanwezig is in ram
			oudsteTotNu = timer;
			huidigProcesID = huidigeInstr.getProcesID();

			int teVervangenFrameNummer = 0;
			for (int j = 0; j < 12; j++) {
				if ((ram[j].getProces() == huidigProces) && (ram[j].getLastAcces() < oudsteTotNu)) {
					teVervangenFrameNummer = j;
					oudsteTotNu = ram[j].getLastAcces();
				}
			}

			// page table aanpassen:
			if (ram[teVervangenFrameNummer].getPageEntry() != -1) {
				// -1 is een niet toegewezen entry
				PagetableEntry teVervangenPageTable = ram[teVervangenFrameNummer].getPagetableentry();
				if (teVervangenPageTable.isModify()) {
					//System.out.println("NU DOEN WE SCHRIJF ++");
					nSchrijfOpdrachten++;
				}
				teVervangenPageTable.doeUitRam();
			}

			huidigProces.getPagetableentry(paginaNummer).doeInRam(teVervangenFrameNummer, timer);

			// ramtable aanpassen:
			nLeesOpdrachten++;
			//System.out.println("NU DOEN WE LEES ++");
			ram[teVervangenFrameNummer].voegentryToe(huidigProces, paginaNummer);

		} else {
			// lastacces nog aanpassen
			huidigProces.getPagetableentry(paginaNummer).setLastAcces(timer);
		}

		huidigRealAdres = huidigeInstr.getAdress() % 4096
				+ huidigProces.getPagetableentry(paginaNummer).getFrameNummer() * 4096;
	}

	public void start() {
		int nodigeentrys, toegewezenentrys, oudsteTotNu;
		
		Proces huidigProces = new Proces();
		RAMEntry oudsteFrame = new RAMEntry();
		alleProcessen.add(huidigProces);

		if (processenInRam.size() == 4) {
			// proces uit ram halen->komt alleen voor in tweede voorbeelddata
		} else if (processenInRam.size() == 0) {
			for (RAMEntry r : ram) {
				r.vulMet(huidigProces);
			}
		} else {
			nodigeentrys = 12 / (processenInRam.size() + 1);
			toegewezenentrys = 0;
			while (nodigeentrys > toegewezenentrys) {
				for (Proces pr : processenInRam) {
					oudsteTotNu = timer;
					for (RAMEntry ra : ram) {
						if ((ra.getProces() == pr) && (ra.getLastAcces() < oudsteTotNu)) {
							oudsteTotNu = ra.getLastAcces();
							oudsteFrame = ra;
						}
					}
					toegewezenentrys++;
					oudsteFrame.setUitRamEnVoegToe(huidigProces);
				}
			}
		}
		processenInRam.add(huidigProces);

		huidigRealAdres = -1;
	}

	public void terminate() {
		//System.out.println("Nu doen we terminate!");
		int nNieuwePlaatsenPerProces;
		int oudProcesID = huidigeInstr.getProcesID();
		Proces pr = null;
		for (Proces p : processenInRam) {
			if (p.getProcesNummer() == oudProcesID) {
				pr = p;
			}
		}
		if(pr != null) processenInRam.remove(pr);

		int loper = 0, k = 0, nOverigeActieveProcessen = processenInRam.size();
		if (nOverigeActieveProcessen != 0) {
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
				// System.out.println(teller);
				while (teller > 0) {
					if (ram[loper].getProcesID() == oudProcesID) {
						if (ram[loper].getPageEntry() != -1) {
							if (ram[loper].getPagetableentry().isModify()) {
								//System.out.println("NU DOEN WE SCHRIJF ++");
								nSchrijfOpdrachten++;
							}
							// oudepagetables aanpassen
							ram[loper].getPagetableentry().doeUitRam();
						}
						// ram vullen
						ram[loper].vulMet(processenInRam.get(j));
						teller--;
					}
					loper++;
				}
			}
		} else {
			for(int a=0; a<12; a++) {
				if (ram[a].getPageEntry() != -1) {
					if (ram[a].getPagetableentry().isModify()) {
						//System.out.println("NU DOEN WE SCHRIJF ++");
						nSchrijfOpdrachten++;
					}
					// oudepagetables aanpassen
					ram[a].getPagetableentry().doeUitRam();
					//System.out.println("Proberen uit ram te kicken " + a);
				}
				ram[a].reset();
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
