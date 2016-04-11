package listeners;

import java.awt.event.*;

import javax.swing.JLabel;
import javax.swing.JTextArea;

import model.globalVar;

public class listener_reset implements ActionListener {
	JTextArea timer;
	JTextArea instructie;
	JTextArea ram;
	JTextArea pageTable;
	JTextArea realAdress;
	JLabel end;
	JTextArea aantalGeschrPR;
	JTextArea aantalGeschrRP;
	
	globalVar g;
	
	public listener_reset(JTextArea timer, JTextArea instructie, JTextArea ram, JTextArea pageTable, JTextArea realAdress, JLabel end, JTextArea aantalGeschrPR, JTextArea aantalGeschrRP, globalVar g) {
		this.timer = timer;
		this.instructie = instructie;
		this.ram = ram;
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

		timer.setText("Timer:");
		instructie.setText("Instructie:");
		ram.setText("Ram:");
		pageTable.setText("Page table:");
		realAdress.setText("Real adress:");
		end.setText("");
		aantalGeschrPR.setText("Aantal schrijfopdrachten:");
		aantalGeschrRP.setText("Aantal leesopdrachten:");
		g.resetPC();
	}

}
