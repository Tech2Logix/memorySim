package model;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.*;
import listeners.listener_XML1;
import listeners.listener_XML2;
import listeners.listener_reset;

public class GUI extends JFrame {
	private static final long serialVersionUID = 6842220968403776582L;
	private JFrame main;
	private JPanel title;
	private JPanel panel;
	private JPanel inhoud;
	private JPanel inhoudEind;

	private JPanel knoppenPanel;
	private JButton xml1;
	private JButton xml2;
	private JButton reset;

	private JLabel lblNewLabel;

	private String timer = "Timer:";
	private String instructie = "Instructie:";
	private String pageTable = "Page table:";
	private String realAdress = "Real adress:";
	private String end = "";
	
	private String aantalGeschrPR = "Aantal schrijfopdrachten:";
	private String aantalGeschrRP = "Aantal leesopdrachten:";

	private JTextArea timerJ;
	private JTextArea instructieJ;
	private JTextArea pageTableJ;
	private JTextArea realAdressJ;
	private JLabel endJ;

	private JTextArea aantalGeschrPRJ;
	private JTextArea aantalGeschrRPJ;

	public GUI() {
		globalVar g = new globalVar();

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

		inhoud.add(timerJ);
		timerJ.setPreferredSize(new Dimension(600, 50));
		inhoud.add(instructieJ);
		instructieJ.setPreferredSize(new Dimension(600, 50));
		inhoud.add(pageTableJ);
		pageTableJ.setPreferredSize(new Dimension(600, 300));
		inhoud.add(realAdressJ);
		realAdressJ.setPreferredSize(new Dimension(600, 100));
		inhoud.setPreferredSize(new Dimension(600, 500));
		inhoud.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
		panel.add(inhoud, BorderLayout.CENTER);

		/*** InhoudEind ***/
		inhoudEind = new JPanel();
		inhoudEind.setBackground(Color.WHITE);
		inhoudEind.setOpaque(true);
		inhoudEind.setLayout(new BoxLayout(inhoudEind, 1));
		aantalGeschrPRJ = new JTextArea(aantalGeschrPR);
		aantalGeschrPRJ.setPreferredSize(new Dimension(200, 50));
		aantalGeschrRPJ = new JTextArea(aantalGeschrRP);
		aantalGeschrRPJ.setPreferredSize(new Dimension(200, 50));
		inhoudEind.add(aantalGeschrPRJ);
		inhoudEind.add(aantalGeschrRPJ);
		inhoudEind.setPreferredSize(new Dimension(200, 500));
		inhoudEind.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
		panel.add(inhoudEind, BorderLayout.EAST);

		/*** knoppenPanel ***/
		knoppenPanel = new JPanel();
		endJ = new JLabel(end);
		knoppenPanel.setLayout(new FlowLayout(3,1,0));
		xml1 = new JButton("Voer enkele instructie uit");
		xml1.addActionListener(new listener_XML1(timerJ, instructieJ, pageTableJ, realAdressJ, endJ, aantalGeschrPRJ, aantalGeschrRPJ, g));

		xml2 = new JButton("Voer alle instructies uit");
		xml2.addActionListener(new listener_XML2(timerJ, instructieJ, pageTableJ, realAdressJ, endJ, aantalGeschrPRJ, aantalGeschrRPJ, g));
		reset = new JButton("Reset");
		reset.addActionListener(new listener_reset(timerJ, instructieJ, pageTableJ, realAdressJ, endJ, aantalGeschrPRJ, aantalGeschrRPJ, g));
		knoppenPanel.add(xml1);
		knoppenPanel.add(xml2);
		knoppenPanel.add(reset);
		knoppenPanel.add(endJ);		
		endJ.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		knoppenPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
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