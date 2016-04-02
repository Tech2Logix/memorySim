package Main;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import Scheduling.InstructieList;
import Scheduling.ToestandMachine;


public class Main {

	public static void main(String[] args) {
		InstructieList instructies = new InstructieList();
		try {
			String instructieXML = "Instructions_eigenTest.xml";
			File file = new File(instructieXML);
			JAXBContext jaxbContext = JAXBContext.newInstance(InstructieList.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			instructies = (InstructieList) jaxbUnmarshaller.unmarshal(file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		System.out.println("data ingelezen");
		
		ToestandMachine pc = new ToestandMachine();
		
		
		
		pc.doorloopVolgendeInstructie(instructies);
		pc.printToestand(instructies);
		
		pc.doorloopVolgendeInstructie(instructies);
		pc.printToestand(instructies);
		
		pc.doorloopVolgendeInstructie(instructies);
		pc.printToestand(instructies);
		
		pc.doorloopVolgendeInstructie(instructies);
		pc.printToestand(instructies);
		
		pc.doorloopVolgendeInstructie(instructies);
		pc.printToestand(instructies);
		
		pc.doorloopVolgendeInstructie(instructies);
		pc.printToestand(instructies);
		
		pc.doorloopVolgendeInstructie(instructies);
		pc.printToestand(instructies);
		
		pc.doorloopVolgendeInstructie(instructies);
		pc.printToestand(instructies);

		
		
	}

}
