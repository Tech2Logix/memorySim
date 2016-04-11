package model;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import Scheduling.InstructieList;
import Scheduling.ToestandMachine;

public class globalVar {
	InstructieList instructies;
	ToestandMachine pc;
	
	public globalVar() {
		instructies = new InstructieList();
		try {
			String instructieXML = "Instructions_eigenTest.xml";
			//String instructieXML = "Instructions_30_3.xml";
			//String instructieXML = "Instructions_20000_4.xml";
			File file = new File(instructieXML);
			JAXBContext jaxbContext = JAXBContext.newInstance(InstructieList.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			instructies = (InstructieList) jaxbUnmarshaller.unmarshal(file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		System.out.println("data ingelezen");
		
		pc = new ToestandMachine();
	}

	public InstructieList getInstructies() {
		return instructies;
	}

	public void setInstructies(InstructieList instructies) {
		this.instructies = instructies;
	}

	public ToestandMachine getPc() {
		return pc;
	}

	public void setPc(ToestandMachine pc) {
		this.pc = pc;
	}
	
	public void resetPC() {
		this.pc = new ToestandMachine();
	}
}
