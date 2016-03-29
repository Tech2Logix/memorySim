package Scheduling;
import java.util.LinkedList;

import javax.xml.bind.annotation.*;


@XmlRootElement(name = "instructionlist")
public class InstructieList {
	private LinkedList<Instructie> instructieLijst;
	
	public InstructieList(){
		instructieLijst = new LinkedList<Instructie>();
	}
	
	@XmlElement(name="instruction")
	public void setInstructieLijst(LinkedList<Instructie> i){
		instructieLijst = i;
	}
	
	public LinkedList<Instructie> getInstructieLijst(){
		return instructieLijst;
	}
	
	public void printList(){
		for(Instructie i: instructieLijst){
			i.print();
		}
	}
}