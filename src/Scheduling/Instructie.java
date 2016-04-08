package Scheduling;
import javax.xml.bind.annotation.*;

@XmlRootElement( name = "instruction")
public class Instructie {
	private int procesID, adress;
	private String operatie;
	
	public int getProcesID() {
		return procesID;
	}
	@XmlElement(name = "processID")
	public void setProcesID(int procesID) {
		this.procesID = procesID;
	}
	
	public String getOperatie() {
		return operatie;
	}
	@XmlElement(name = "operation")
	public void setOperatie(String operatie) {
		this.operatie = operatie;
	}
	public int getAdress() {
		return adress;
	}
	@XmlElement(name = "address")
	public void setAdress(int adress) {
		this.adress = adress;
	}

	
	public void print(){
		System.out.print("id:" +procesID);
		System.out.print("   operatie: "+operatie);
		System.out.println("   adres:"+adress);
	}
	
	@Override
	public String toString() {
		return "Instructie [procesID=" + procesID + ", adress=" + adress + ", operatie=" + operatie + "]";
	}
}
