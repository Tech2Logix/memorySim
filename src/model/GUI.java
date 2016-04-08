package model;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;

import javax.swing.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import Scheduling.InstructieList;
import Scheduling.ToestandMachine;
import listeners.listener_XML1;
import listeners.listener_XML2;
import listeners.listener_reset;

public class GUI extends JFrame {
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

	private String timer = "begin";
	private String instructie = "begin";
	private String pageTable = "begin";
	private String realAdress = "begin";
	private String end = "";
	
	private String aantalGeschrPR = "";
	private String aantalGeschrRP = "";

	private JTextArea timerJ;
	private JTextArea instructieJ;
	private JTextArea pageTableJ;
	private JTextArea realAdressJ;
	private JTextArea endJ;

	private JTextArea aantalGeschrPRJ;
	private JTextArea aantalGeschrRPJ;

	public GUI() {
		globalVar g = new globalVar();

		// 8
		// pc.doorloopVolgendeInstructie(instructies);
		// pc.printToestand(instructies);

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
		inhoud.setBackground(Color.GRAY);
		inhoud.setOpaque(true);
		inhoud.setLayout(new BoxLayout(inhoud, 1));

		timerJ = new JTextArea(timer);
		instructieJ = new JTextArea(instructie);
		pageTableJ = new JTextArea(pageTable);
		realAdressJ = new JTextArea(realAdress);
		endJ = new JTextArea(end);

		inhoud.add(timerJ);
		inhoud.add(instructieJ);
		inhoud.add(pageTableJ);
		inhoud.add(realAdressJ);
		inhoud.add(endJ);
		inhoud.setPreferredSize(new Dimension(250, 500));
		panel.add(inhoud, BorderLayout.CENTER);

		/*** InhoudEind ***/
		inhoudEind = new JPanel();
		inhoudEind.setBackground(Color.DARK_GRAY);
		inhoudEind.setOpaque(true);
		inhoudEind.setLayout(new BoxLayout(inhoudEind, 1));
		aantalGeschrPRJ = new JTextArea(aantalGeschrPR);
		aantalGeschrRPJ = new JTextArea(aantalGeschrRP);
		inhoudEind.add(aantalGeschrPRJ);
		inhoudEind.add(aantalGeschrRPJ);
		inhoudEind.setPreferredSize(new Dimension(250, 500));
		panel.add(inhoudEind, BorderLayout.EAST);

		/*** knoppenPanel ***/
		knoppenPanel = new JPanel();
		knoppenPanel.setLayout(new BoxLayout(knoppenPanel, 1));
		xml1 = new JButton("Voer enkele instructie uit");
		xml1.addActionListener(new listener_XML1(timerJ, instructieJ, pageTableJ, realAdressJ, endJ, aantalGeschrPRJ, aantalGeschrRPJ, g));

		xml2 = new JButton("Voer alle instructies uit");
		xml2.addActionListener(new listener_XML2(timerJ, instructieJ, pageTableJ, realAdressJ, endJ, aantalGeschrPRJ, aantalGeschrRPJ, g));
		reset = new JButton("Reset");
		reset.addActionListener(new listener_reset(timerJ, instructieJ, pageTableJ, realAdressJ, endJ, aantalGeschrPRJ, aantalGeschrRPJ, g));
		knoppenPanel.add(xml1);
		knoppenPanel.add(xml2);
		knoppenPanel.add(reset);
		panel.add(knoppenPanel, BorderLayout.WEST);

		/*** Main ***/
		main.setLayout(new BorderLayout());
		main.add(panel, BorderLayout.CENTER);
		main.add(title, BorderLayout.NORTH);
		main.pack();
		main.setVisible(true);
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}