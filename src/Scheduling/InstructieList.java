package Scheduling;
import java.util.LinkedList;

import javax.xml.bind.annotation.*;


@XmlRootElement(name = "instructionlist")
public class InstructieList {
	private LinkedList<Instructie> instructieLijst;
	
	public InstructieList(){
		instructieLijst = new LinkedList<Instructie>();
	}
	
	public InstructieList(InstructieList instructieLijst){
		this.instructieLijst = new LinkedList<Instructie>();
		for(Instructie i : instructieLijst.getInstructieLijst()) {
			this.instructieLijst.add(i);
		}
	}
	
	public Instructie getInstructie(int tijd){
		return instructieLijst.get(tijd);
	}
	
	@XmlElement(name="instruction")
	public void setInstructieLijst(LinkedList<Instructie> i){
		instructieLijst = i;
	}
	
	@Override
	public String toString() {
		return "InstructieList [instructieLijst=" + instructieLijst + "]";
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
