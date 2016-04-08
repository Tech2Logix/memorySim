package listeners;

import java.awt.event.*;

import javax.swing.JTextArea;

import Scheduling.InstructieList;
import Scheduling.ToestandMachine;
import model.globalVar;

public class listener_reset implements ActionListener {
	JTextArea timer;
	JTextArea instructie;
	JTextArea pageTable;
	JTextArea realAdress;
	JTextArea end;
	JTextArea aantalGeschrPR;
	JTextArea aantalGeschrRP;
	
	globalVar g;
	
	public listener_reset(JTextArea timer, JTextArea instructie, JTextArea pageTable, JTextArea realAdress, JTextArea end, JTextArea aantalGeschrPR, JTextArea aantalGeschrRP, globalVar g) {
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
		System.out.println("Button reset activated");

		timer.setText("Begin");
		instructie.setText("Begin");
		pageTable.setText("Begin");
		realAdress.setText("Begin");
		end.setText("");
		aantalGeschrPR.setText("");
		aantalGeschrRP.setText("");
		g.resetPC();
	}

}
