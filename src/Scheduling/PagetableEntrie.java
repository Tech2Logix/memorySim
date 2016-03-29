package Scheduling;

public class PagetableEntrie {
	private boolean present, modify;
	private int lastAcces, frameNummer;
	
	public PagetableEntrie(){
		present=false;
		modify=false;
		lastAcces=-1;
		frameNummer=-1;
	}

	public boolean isPresent() {
		return present;
	}

	public void setPresent(boolean present) {
		this.present = present;
	}

	public boolean isModify() {
		return modify;
	}

	public void setModify(boolean modify) {
		this.modify = modify;
	}

	public int getLastAcces() {
		return lastAcces;
	}

	public void setLastAcces(int lastAcces) {
		this.lastAcces = lastAcces;
	}

	public int getFrameNummer() {
		return frameNummer;
	}

	public void setFrameNummer(int frameNummer) {
		this.frameNummer = frameNummer;
	}
	
	public boolean setUitRam(){
		boolean schrijven=false;
		if(modify=true){
			schrijven=true;
			modify=false;
		}
		present=false;
		frameNummer=-1;
		
		return schrijven;
	}
	
	
}
