package Scheduling;

import java.util.ArrayList;

public class ToestandMachine {
	private RAMEntrie [] ram;
	private ArrayList<Proces> alleProcessen; //om statistieken over processen die uit ram zijn bij te kunnen houden
	private ArrayList<Proces> processenInRam;
	private Instructie huidigeInstr;
	private int timer;
	
	public ToestandMachine(){
		ram=new RAMEntrie[12];
		alleProcessen = new ArrayList<Proces>();
		processenInRam = new ArrayList<Proces>();
		timer=0;
	}
	
	public void doorloopVolgendeInstructie(InstructieList i){ //dit is slechts 1 instructie uitvoeren
		Proces p;
		RAMEntrie oudsteFrame;
		int nodigeEntries,toegewezenEntries, oudsteTotNu;
		huidigeInstr = i.getInstructie(timer);
		timer++;
		
		
		switch(huidigeInstr.getOperatie()){
			case "Write":  
				
				break;
			case "Read":    
				break;
			case "Start":
				p=new Proces();
				alleProcessen.add(p);
				
				if(processenInRam.size()==4){
					//proces uit ram halen->komt alleen voor in tweede voorbeelddata
				}
				
				
				
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
						ra.getPagetableEntrie
					}
				}
				processenInRam.add(p);
				
				break;
			case "Terminate": 
				break;
			default: break;
		}
	}
}
