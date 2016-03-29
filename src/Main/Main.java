package Main;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import Scheduling.Algoritmes;
import Scheduling.InstructieList;
import Scheduling.RAM;



public class Main {

	private InstructieList instructies;
	
	public static void main(String[] args) {
		InstructieList instructies = new InstructieList();
		try {
			String instructieXML = "Instructions_20000_4.xml";
			File file = new File(instructieXML);
			JAXBContext jaxbContext = JAXBContext.newInstance(InstructieList.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			instructies = (InstructieList) jaxbUnmarshaller.unmarshal(file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		System.out.println("data ingelezen");
		
		RAM ram = new RAM();
		
		
		
		
		
		//Algoritmes al = new Algoritmes();
	}

}
