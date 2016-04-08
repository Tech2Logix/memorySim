package listeners;

import java.awt.event.*;

import javax.swing.JTextArea;

import Scheduling.InstructieList;
import Scheduling.ToestandMachine;
import model.globalVar;

public class listener_XML1 implements ActionListener {
	JTextArea timer;
	JTextArea instructie;
	JTextArea pageTable;
	JTextArea realAdress;
	JTextArea end;
	JTextArea aantalGeschrPR;
	JTextArea aantalGeschrRP;
	
	globalVar g;
	
	public listener_XML1(JTextArea timer, JTextArea instructie, JTextArea pageTable, JTextArea realAdress, JTextArea end, JTextArea aantalGeschrPR, JTextArea aantalGeschrRP, globalVar g) {
		this.timer = timer;
		this.instructie = instructie;
		this.pageTable = pageTable;
		this.realAdress = realAdress;
		this.end = end;
		this.aantalGeschrPR = aantalGeschrPR;
		this.aantalGeschrRP = aantalGeschrRP;
		this.g = g;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("Button 1 activated");
//		System.out.println(instructies.getInstructieLijst().size());
		if(g.getPc().getTimer() < g.getInstructies().getInstructieLijst().size()) {
			g.getPc().doorloopVolgendeInstructie(g.getInstructies());
			timer.setText(String.valueOf(g.getPc().getTimer()));
			instructie.setText(g.getPc().getHuidigeInstr().toString());
			pageTable.setText("TO*DO");
			realAdress.setText("TO*DO");
		} else {
			end.setText("Instructielijst afgelopen, druk op reset om opnieuw te beginnen.");
			aantalGeschrPR.setText("TO*DO");
			aantalGeschrRP.setText("TO*DO");
		}

	}

}
