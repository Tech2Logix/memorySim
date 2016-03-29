package Scheduling;

import java.util.ArrayList;

public class ToestandMachine {
	private RAMEntrie [] ram;
	private ArrayList<Proces> alleProcessen;
	private ArrayList<Proces> processenInRam;
	private Instructie huidigeInstr;
	
	public ToestandMachine(){
		ram=new RAMEntrie[12];
		alleProcessen = new ArrayList<Proces>();
		processenInRam = new ArrayList<Proces>();
	}
	
	public void doorloopVolgendeInstructie(InstructieList i){ //dit is slechts 1 instructie uitvoeren
		huidigeInstr = i.getNextInstructie();
		
		switch(huidigeInstr.getOperatie()){
			case "Write":  
				
				break;
			case "Read":    
				break;
			case "Start": 
				if(processenInRam.size()==4){
					//proces uit ram halen
				}
																	  //als het proces reeds vroeger aangemaakt is
				if(alleProcessen.size()<=huidigeInstr.getProcesID()){ //in de veronderstelling dat de idnummers optellen per binnenkomend proces
					processenInRam.add(alleProcessen.get(huidigeInstr.getProcesID()));
				}
				break;
			case "Terminate": 
				break;
			default: break;
		}
	}
}
