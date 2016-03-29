package Scheduling;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class Algoritmes {
	private InstructieList instructies;
	
	
	public Algoritmes() {
		instructies=new InstructieList();
		try {
			String instructieXML = "Instructions_20000_4.xml";
			File file = new File(instructieXML);
			JAXBContext jaxbContext = JAXBContext.newInstance(InstructieList.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			instructies = (InstructieList) jaxbUnmarshaller.unmarshal(file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
		RAM ram = new RAM();
	}
}
