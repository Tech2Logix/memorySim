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
	String instructieXML;
	boolean firstRun;
	
	public globalVar() {
		instructies = new InstructieList();
		try {
			instructieXML = "Instructions_eigenTest.xml";
			File file = new File(instructieXML);
			JAXBContext jaxbContext = JAXBContext.newInstance(InstructieList.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			instructies = (InstructieList) jaxbUnmarshaller.unmarshal(file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		System.out.println("data ingelezen");
		
		pc = new ToestandMachine();
		firstRun = true;
	}

	public boolean isFirstRun() {
		return firstRun;
	}
	
	public void setNieuweInstructielijst(String instructieXML) {
		this.instructieXML = instructieXML;
		instructies = new InstructieList();
		try {
			File file = new File(instructieXML);
			JAXBContext jaxbContext = JAXBContext.newInstance(InstructieList.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			instructies = (InstructieList) jaxbUnmarshaller.unmarshal(file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		System.out.println(instructieXML+" dataset ingelezen");
	}
	
	public void setFirstRun(boolean firstRun) {
		this.firstRun = firstRun;
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
		instructies = new InstructieList();
		try {
			instructieXML = "Instructions_eigenTest.xml";
			File file = new File(instructieXML);
			JAXBContext jaxbContext = JAXBContext.newInstance(InstructieList.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			instructies = (InstructieList) jaxbUnmarshaller.unmarshal(file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		firstRun = true;
		System.out.println(pc.toString());
	}
}
