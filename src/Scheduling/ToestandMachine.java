package Scheduling;

import java.util.ArrayList;

public class ToestandMachine {
	private RAMEntrie [] ram;
	private ArrayList<Proces> alleProcessen; //om statistieken over processen die uit ram zijn bij te kunnen houden
	private ArrayList<Proces> processenInRam;
	private Instructie huidigeInstr;
	private int timer;
	private int nLeesopdrachten;
	private int nSchrijfopdrachten; //van persistentGeheugen naar RAM
	
	public ToestandMachine(){
		ram=new RAMEntrie[12];
		for(int i=0;i<12;i++){
			ram[i]=new RAMEntrie(); 
		}
		alleProcessen = new ArrayList<Proces>();
		processenInRam = new ArrayList<Proces>();
		timer=0;
		nSchrijfopdrachten=0;
		nLeesopdrachten=0;
	}
	
	public void doorloopVolgendeInstructie(InstructieList i){ //dit is slechts 1 instructie uitvoeren
		Proces p;
		RAMEntrie oudsteFrame;
		int nodigeEntries,toegewezenEntries, oudsteTotNu, paginaNummer, procesID, oudsteEntrie, leegFrameNummer;
		huidigeInstr = i.getInstructie(timer);
		timer++;
		
		
		switch(huidigeInstr.getOperatie()){
			case "Write":  
				
				break;
			case "Read": 
				/*p=alleProcessen.get(huidigeInstr.getProcesID());
				paginaNummer=huidigeInstr.getAdress()/4096;
				if(!alleProcessen.get(huidigeInstr.getProcesID()).paginaNummerPresent(paginaNummer)){//als het niet aanwezig is in ram
					oudsteTotNu=timer;
					procesID=huidigeInstr.getProcesID();
					RAMEntrie teVervangenRam=ram[0];//moet een beginwaarde hebben, maakt niet uit wat dit is
					for(RAMEntrie e: ram){
						if( (e.getProces().getProcesNummer()==procesID) && (e.getLastAcces()<oudsteTotNu)){
							teVervangenRam=e;
						}
					}
					//page table aanpassen:
					if(teVervangenRam.getPageEntrie()!=-1){
						if((p.getPagetableEntrie(teVervangenRam.getPageEntrie()).isModify())){ //-1 is een niet toegewezen entrie
							nSchrijfopdrachten++;
						}
						
						leegFrameNummer=p.getPagetableEntrie(teVervangenRam.getPageEntrie()).doeUitRam();
					}
					p.getPagetableEntrie(paginaNummer).doeInRam(leegFrameNummer, timer);
					
					//ramtable aanpassen:
					nLeesopdrachten++;
					teVervangenRam.voegEntrieToe(p, paginaNummer);
				}
				else{
					//lastacces nog aanpassen
					p.getPagetableEntrie(paginaNummer).setLastAcces(timer);
				}
				break;
				*/
			case "Start":
				p=new Proces();
				oudsteFrame=new RAMEntrie(); //dit heeft geen betekenis, is enkel zodat geen error komt door oningevulde variabele
				alleProcessen.add(p);
				
				
	
				if(processenInRam.size()==4){
					//proces uit ram halen->komt alleen voor in tweede voorbeelddata
				}
				
				else if(processenInRam.size()==0){
					for (RAMEntrie r: ram){
						r.vulMet(p);
					}
				}
				
				else{
					nodigeEntries=12/(processenInRam.size()+1);
					toegewezenEntries=0;
					while(nodigeEntries > toegewezenEntries){
						for(Proces pr: processenInRam){
							oudsteTotNu=timer;
							
							for(RAMEntrie ra:ram){
								if( (ra.getProces()==pr) && (ra.getLastAcces() < oudsteTotNu)) {
									oudsteTotNu = ra.getLastAcces();
									oudsteFrame = ra;
								}
							}
							toegewezenEntries++;
							oudsteFrame.setUitRamEnVoegToe(p);
							
						}
					}
				}
				
				processenInRam.add(p);
				
				break;
			case "Terminate": 
				break;
			default: break;
		}
	}
	
	public void printToestand(InstructieList il){
		System.out.println("-------------------------------------------");
		System.out.println("\ntimer="+timer+"\n");
		
		System.out.println("  RAM:"+"\n  ----");
		for(int i=0;i<12;i++){
			System.out.print(i+"\t");
			ram[i].print();
		}
		
		int procesID=il.getInstructie(timer-1).getProcesID();
		Proces p=alleProcessen.get(procesID);
		System.out.println("\n  PAGETABLE van proces "+procesID+"\n  ---------");
		p.printPageTables();
		
	}
}
