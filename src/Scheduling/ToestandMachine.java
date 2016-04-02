package Scheduling;

import java.util.ArrayList;

public class ToestandMachine {
	private RAMEntrie [] ram;
	private ArrayList<Proces> alleProcessen; //om statistieken over processen die uit ram zijn bij te kunnen houden
	private ArrayList<Proces> processenInRam;
	private Instructie huidigeInstr;
	private int timer;
	private int nLeesOpdrachten;
	private int nSchrijfOpdrachten; //van persistentGeheugen naar RAM
	
	public ToestandMachine(){
		ram=new RAMEntrie[12];
		for(int i=0;i<12;i++){
			ram[i]=new RAMEntrie(); 
		}
		alleProcessen = new ArrayList<Proces>();
		processenInRam = new ArrayList<Proces>();
		timer=0;
		nSchrijfOpdrachten=0;
		nLeesOpdrachten=0;
	}
	
	public void doorloopVolgendeInstructie(InstructieList i){ //dit is slechts 1 instructie uitvoeren
		Proces huidigProces;
		RAMEntrie oudsteFrame;
		int nodigeEntries,toegewezenEntries, oudsteTotNu, paginaNummer, huidigProcesID, oudsteEntrie, leegFrameNummer;
		huidigeInstr = i.getInstructie(timer);
		timer++;
		
		
		switch(huidigeInstr.getOperatie()){
			case "Write":  
				
				break;
			case "Read": 
				huidigProces=alleProcessen.get(huidigeInstr.getProcesID());
				paginaNummer=huidigeInstr.getAdress()/4096;
				if(!alleProcessen.get(huidigeInstr.getProcesID()).paginaNummerPresent(paginaNummer)){//als het niet aanwezig is in ram
					oudsteTotNu=timer;
					huidigProcesID=huidigeInstr.getProcesID();
					
					int teVervangenFrameNummer=0;//moet een beginwaarde hebben, maakt niet uit wat dit is
					for(int j=0;j<12;j++){
						if( (ram[j].getProces().getProcesNummer()==huidigProcesID) && (ram[j].getLastAcces()<oudsteTotNu) ){
							teVervangenFrameNummer=j;
							oudsteTotNu=ram[j].getLastAcces();
						}
					}

					//page table aanpassen:
					if(ram[teVervangenFrameNummer].getPageEntrie()!=-1){//-1 is een niet toegewezen entrie
						PagetableEntrie teVervangenPageTable = ram[teVervangenFrameNummer].getPagetableEntrie();
						if(teVervangenPageTable.isModify()){ 
							nSchrijfOpdrachten++;
						}
						teVervangenPageTable.doeUitRam();
					}
										
					huidigProces.getPagetableEntrie(paginaNummer).doeInRam(teVervangenFrameNummer, timer);
					
					//ramtable aanpassen:
					nLeesOpdrachten++;
					ram[teVervangenFrameNummer].voegEntrieToe(huidigProces, paginaNummer);
					
				}
				else{
					//lastacces nog aanpassen
					huidigProces.getPagetableEntrie(paginaNummer).setLastAcces(timer);
				}
				break;
				
			case "Start":
				huidigProces=new Proces();
				oudsteFrame=new RAMEntrie(); //dit heeft geen betekenis, is enkel zodat geen error komt door oningevulde variabele
				alleProcessen.add(huidigProces);
				
				
	
				if(processenInRam.size()==4){
					//proces uit ram halen->komt alleen voor in tweede voorbeelddata
				}
				
				else if(processenInRam.size()==0){
					for (RAMEntrie r: ram){
						r.vulMet(huidigProces);
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
							oudsteFrame.setUitRamEnVoegToe(huidigProces);
							
						}
					}
				}
				
				processenInRam.add(huidigProces);
				
				break;
			case "Terminate": 
				break;
			default: break;
		}
	}
	
	public void printToestand(InstructieList il){
		System.out.println("----------------------------------------------------------------------------------");
		System.out.println("\ntimer="+timer+"\tschrijf opdrachten: "+nSchrijfOpdrachten+"\tlees opdrachten: "+nLeesOpdrachten);
		
		System.out.println("\n  RAM:"+"\n  ----");
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
