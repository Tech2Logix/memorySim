package listeners;

import java.awt.event.*;

import javax.swing.JLabel;
import javax.swing.JTextArea;

import model.globalVar;

public class listener_XML1 implements ActionListener {
	JTextArea timer;
	JTextArea instructie;
	JTextArea pageTable;
	JTextArea realAdress;
	JLabel end;
	JTextArea aantalGeschrPR;
	JTextArea aantalGeschrRP;
	globalVar g;

	public listener_XML1(JTextArea timer, JTextArea instructie, JTextArea pageTable, JTextArea realAdress, JLabel end,
			JTextArea aantalGeschrPR, JTextArea aantalGeschrRP, globalVar g) {
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
		if (g.isFirstRun()) {
			timer.setText("Timer: \n" + String.valueOf(g.getPc().getTimer()));
			instructie.setText("Instructie: \n" + g.getInstructies().getInstructie(0).toString());
			g.setFirstRun(false);
		} else {
			// System.out.println(instructies.getInstructieLijst().size());
			if (g.getPc().getTimer() < g.getInstructies().getInstructieLijst().size()) {
				g.getPc().doorloopVolgendeInstructie(g.getInstructies());
				timer.setText("Timer: \n" + String.valueOf(g.getPc().getTimer()));
				instructie.setText("Instructie: \n" + g.getPc().getHuidigeInstr().toString());
				pageTable.setText("Page table: \n" + g.getPc().getAlleProcessen()
						.get(g.getInstructies().getInstructie(g.getPc().getTimer() - 1).getProcesID())
						.pageTabletoString());
				if (g.getPc().getHuidigRealAdres() != -1) {
					realAdress.setText("Real adress: \n" + g.getPc().getHuidigRealAdres());
				} else {
					// niets... voorlopig??
				}
				aantalGeschrPR.setText("Aantal schrijfopdrachten: \n" + g.getPc().getnSchrijfOpdrachten());
				aantalGeschrRP.setText("Aantal leesopdrachten: \n" + g.getPc().getnLeesOpdrachten());
			} else {
				end.setText("Instructielijst afgelopen, druk op reset om opnieuw te beginnen.");
			}
			g.getPc().printToestand(g.getInstructies());
		}
	}

}
