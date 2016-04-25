package model;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import Scheduling.InstructieList;
import Scheduling.ToestandMachine;

public class GlobalVar {
	InstructieList instructies;
	ToestandMachine pc;
	String instructieXML;
	
	public GlobalVar() {
		instructies = new InstructieList();
		try {
			instructieXML = "Instructions_30_3.xml";
			File file = new File(instructieXML);
			JAXBContext jaxbContext = JAXBContext.newInstance(InstructieList.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			instructies = (InstructieList) jaxbUnmarshaller.unmarshal(file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		System.out.println("\nDataset("+instructieXML+") ingelezen \n");
		pc = new ToestandMachine();
	}

	
	public GlobalVar(String instructieXML) {
		instructies = new InstructieList();
		try {
			this.instructieXML = instructieXML;
			File file = new File(instructieXML);
			JAXBContext jaxbContext = JAXBContext.newInstance(InstructieList.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			instructies = (InstructieList) jaxbUnmarshaller.unmarshal(file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		System.out.println("\nDataset("+instructieXML+") ingelezen \n");
		pc = new ToestandMachine();
	}
	
	
	public void setNieuweInstructielijst(String instructieXML) {
		this.instructieXML = instructieXML;
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
	
	public void resetPC(boolean syso) {
		pc.reset();
		instructies = new InstructieList();
		try {
			File file = new File(instructieXML);
			JAXBContext jaxbContext = JAXBContext.newInstance(InstructieList.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			instructies = (InstructieList) jaxbUnmarshaller.unmarshal(file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		if(syso) System.out.println(instructieXML+" dataset ingelezen");
		//System.out.println(pc.toString());
	}
}
