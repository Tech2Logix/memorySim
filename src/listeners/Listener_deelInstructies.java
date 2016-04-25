package listeners;

import java.awt.event.*;

import javax.swing.JLabel;
import javax.swing.JTextArea;

import model.GlobalVar;

public class Listener_deelInstructies implements ActionListener {
	JTextArea timer;
	JTextArea instructie;
	JTextArea ram;
	JTextArea pageTable;
	JTextArea realAdress;
	JLabel end;
	JTextArea aantalGeschrPR;
	JTextArea aantalGeschrRP;

	GlobalVar g;

	public Listener_deelInstructies(JTextArea timer, JTextArea instructie, JTextArea ram, JTextArea pageTable,
			JTextArea realAdress, JLabel end, JTextArea aantalGeschrPR, JTextArea aantalGeschrRP, GlobalVar g) {
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
		if (g.getPc().getTimer() < g.getInstructies().getInstructieLijst().size()) {
			int eindwaarde = (int) Math.ceil(g.getPc().getTimer()+((double) g.getInstructies().getInstructieLijst().size()/10));
			//System.out.println(eindwaarde);
			for (int i = g.getPc().getTimer();  i < eindwaarde && i < g.getInstructies().getInstructieLijst().size(); i++) {
				g.getPc().doorloopVolgendeInstructie(g.getInstructies());
			}
			end.setText("Instructielijst afgelopen!");
			aantalGeschrPR.setText("Aantal schrijfopdrachten: \n" + g.getPc().getnSchrijfOpdrachten());
			aantalGeschrRP.setText("Aantal leesopdrachten: \n" + g.getPc().getnLeesOpdrachten());

			timer.setText("Timer: \n" + String.valueOf(g.getPc().getTimer()));
			instructie.setText("Instructie: \n" + g.getPc().getHuidigeInstr().toString());
			ram.setText("Ram: \n" + g.getPc().printRam());
			pageTable.setText("Page table: \n" + g.getPc().getAlleProcessen()
					.get(g.getInstructies().getInstructie(g.getPc().getTimer() - 1).getProcesID()).pageTabletoString());
			if (g.getPc().getHuidigRealAdres() != -1) {
				realAdress.setText("Real adress: \n" + g.getPc().getHuidigRealAdres());
			} else {
				realAdress.setText("Real adress: \n");
			}
		} else {
			System.out.println("afgelopen");
		}

	}

}
