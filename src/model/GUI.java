package model;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import listeners.Listener_enkeleInstructie;
import listeners.Listener_alleInstructies;
import listeners.Listener_deelInstructies;
import listeners.Listener_Reset;

public class GUI extends JFrame {
	private static final long serialVersionUID = 6842220968403776582L;
	private JFrame main;
	private JPanel title;
	private JPanel panel;
	private JPanel inhoud;
	private JPanel inhoudEind;

	private JPanel knoppenPanel;
	private JButton enkeleI;
	private JButton alleI;
	private JButton deelI;
	private JButton reset;

	private JComboBox<String> cb;

	private JLabel lblNewLabel;

	private String timer = "Timer:";
	private String instructie = "Instructie:";
	private String ram = "Ram:";
	private String pageTable = "Page table:";
	private String realAdress = "Real adress:";
	private String end = "";

	private String aantalGeschrPR = "Aantal schrijfopdrachten:";
	private String aantalGeschrRP = "Aantal leesopdrachten:";

	private JTextArea timerJ;
	private JTextArea instructieJ;
	private JTextArea ramJ;
	private JTextArea pageTableJ;
	private JTextArea realAdressJ;
	private JLabel endJ;

	private JTextArea aantalGeschrPRJ;
	private JTextArea aantalGeschrRPJ;

	public GUI() {
		GlobalVar g = new GlobalVar();

		/*** Initiatie ***/
		main = new JFrame();
		main.setTitle("Labo Besturingssystemen");

		title = new JPanel();
		lblNewLabel = new JLabel("Memory Simulator - 3ELICTI - 2015/2016 - Michiel Dhont & Rhino Van Boxelaere");
		title.add(lblNewLabel);

		panel = new JPanel();
		panel.setLayout(new BorderLayout());

		/*** Inhoud ***/
		inhoud = new JPanel();
		inhoud.setBackground(Color.WHITE);
		inhoud.setOpaque(true);
		inhoud.setLayout(new BoxLayout(inhoud, 1));

		timerJ = new JTextArea(timer);
		instructieJ = new JTextArea(instructie);
		pageTableJ = new JTextArea(pageTable);
		realAdressJ = new JTextArea(realAdress);
		ramJ = new JTextArea(ram);

		inhoud.add(timerJ);
		timerJ.setPreferredSize(new Dimension(600, 75));
		inhoud.add(instructieJ);
		instructieJ.setPreferredSize(new Dimension(600, 75));
		inhoud.add(ramJ);
		ramJ.setPreferredSize(new Dimension(600, 250));
		inhoud.add(pageTableJ);
		pageTableJ.setPreferredSize(new Dimension(600, 250));
		inhoud.add(realAdressJ);
		realAdressJ.setPreferredSize(new Dimension(600, 75));
		
		inhoud.setPreferredSize(new Dimension(600, 725));
		inhoud.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		panel.add(inhoud, BorderLayout.CENTER);

		/*** InhoudEind ***/
		inhoudEind = new JPanel();
		inhoudEind.setBackground(Color.white);
		inhoudEind.setOpaque(true);
		inhoudEind.setLayout(new BoxLayout(inhoudEind, 1));
		aantalGeschrPRJ = new JTextArea(aantalGeschrPR);
		aantalGeschrPRJ.setPreferredSize(new Dimension(200, 50));
		aantalGeschrRPJ = new JTextArea(aantalGeschrRP);
		aantalGeschrRPJ.setPreferredSize(new Dimension(200, 50));
		inhoudEind.add(aantalGeschrPRJ);
		inhoudEind.add(aantalGeschrRPJ);
		inhoudEind.setPreferredSize(new Dimension(200, 725));
		inhoudEind.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		panel.add(inhoudEind, BorderLayout.EAST);

		/*** knoppenPanel ***/
		knoppenPanel = new JPanel();
		endJ = new JLabel(end);
		knoppenPanel.setLayout(new FlowLayout(3, 1, 0));
		enkeleI = new JButton("Voer enkele instructie uit");
		enkeleI.addActionListener(new Listener_enkeleInstructie(timerJ, instructieJ, ramJ, pageTableJ, realAdressJ, endJ, aantalGeschrPRJ,
				aantalGeschrRPJ, g));

		alleI = new JButton("Voer alle instructies uit");
		alleI.addActionListener(new Listener_alleInstructies(timerJ, instructieJ, ramJ, pageTableJ, realAdressJ, endJ, aantalGeschrPRJ,
				aantalGeschrRPJ, g));
		deelI = new JButton("10%");
		deelI.addActionListener(new Listener_deelInstructies(timerJ, instructieJ, ramJ, pageTableJ, realAdressJ, endJ, aantalGeschrPRJ,
				aantalGeschrRPJ, g));
		
		reset = new JButton("Reset");
		reset.addActionListener(new Listener_Reset(timerJ, instructieJ, ramJ, pageTableJ, realAdressJ, endJ, aantalGeschrPRJ,
				aantalGeschrRPJ, g));
		knoppenPanel.add(enkeleI);
		knoppenPanel.add(alleI);
		knoppenPanel.add(deelI);
		knoppenPanel.add(reset);

		cb = new JComboBox<String>();
		cb.addItem("Instructions_eigenTest.xml");
		cb.addItem("Instructions_30_3.xml");
		cb.addItem("Instructions_20000_4.xml");
		cb.addItem("Instructions_20000_20.xml");
		cb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g.setNieuweInstructielijst(cb.getSelectedItem().toString());
				timerJ.setText("Timer:");
				instructieJ.setText("Instructie:");
				ramJ.setText("Ram:");
				pageTableJ.setText("Page table:");
				realAdressJ.setText("Real adress:");
				endJ.setText("");
				aantalGeschrPRJ.setText("Aantal schrijfopdrachten:");
				aantalGeschrRPJ.setText("Aantal leesopdrachten:");
				g.resetPC();
			}
		});
		cb.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		knoppenPanel.add(cb);

		knoppenPanel.add(endJ);
		endJ.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		knoppenPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		panel.add(knoppenPanel, BorderLayout.NORTH);

		/*** Main ***/
		main.setLayout(new BorderLayout());
		main.add(panel, BorderLayout.CENTER);
		main.add(title, BorderLayout.SOUTH);
		main.pack();
		main.setVisible(true);
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}