package listeners;

import java.awt.event.*;

import javax.swing.JLabel;
import javax.swing.JTextArea;

import model.GlobalVar;

public class Listener_XML1 implements ActionListener {
	JTextArea timer;
	JTextArea instructie;
	JTextArea ram;
	JTextArea pageTable;
	JTextArea realAdress;
	JLabel end;
	JTextArea aantalGeschrPR;
	JTextArea aantalGeschrRP;
	GlobalVar g;

	public Listener_XML1(JTextArea timer, JTextArea instructie, JTextArea ram, JTextArea pageTable, JTextArea realAdress, JLabel end,
			JTextArea aantalGeschrPR, JTextArea aantalGeschrRP, GlobalVar g) {
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
		System.out.println("Button 1 activated");
		if (g.isFirstRun()) {
			timer.setText("Timer: \n" + String.valueOf(g.getPc().getTimer()));
			instructie.setText("Instructie: \n" + g.getInstructies().getInstructie(0).toString());
			g.setFirstRun(false);
		} else {
			if (g.getPc().getTimer() <= g.getInstructies().getInstructieLijst().size()) {
				g.getPc().doorloopVolgendeInstructie(g.getInstructies());
				timer.setText("Timer: \n" + String.valueOf(g.getPc().getTimer()));
				instructie.setText("Instructie: \n" + g.getPc().getHuidigeInstr().toString());
				ram.setText("Ram: \n" + g.getPc().printRam());
				pageTable.setText("Page table: \n" + g.getPc().getAlleProcessen()
						.get(g.getInstructies().getInstructie(g.getPc().getTimer() - 1).getProcesID())
						.pageTabletoString());
				if (g.getPc().getHuidigRealAdres() != -1) {
					realAdress.setText("Real adress: \n" + g.getPc().getHuidigRealAdres());
				} else {
					realAdress.setText("Real adress: \n");
				}
				aantalGeschrPR.setText("Aantal schrijfopdrachten: \n" + g.getPc().getnSchrijfOpdrachten());
				aantalGeschrRP.setText("Aantal leesopdrachten: \n" + g.getPc().getnLeesOpdrachten());
				//g.getPc().printToestand(g.getInstructies());
			} else {
				end.setText("Instructielijst afgelopen!");
				System.out.println("afgelopen");
			}
		}
	}

}
