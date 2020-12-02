package main.gui;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import main.java.FileChooserHandler;
import main.java.MazeDrawer;

import java.awt.Dimension;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.Font;

public class MainFrame extends JFrame{

	private static final long serialVersionUID = -1966090415318542313L;
	private JTextField widthText;
	private JTextField heightText;
	
	public MainFrame() {
		getContentPane().setLayout(new GridLayout(3, 1, 0, 0));
		
		JLabel title = new JLabel("MAZE GENERATOR");
		title.setFont(new Font("Arial", Font.BOLD, 30));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(title);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
		
		JLabel widthLabel = new JLabel("Enter Width");
		widthLabel.setFont(new Font("Arial", Font.BOLD, 16));
		panel.add(widthLabel);
		
		widthText = new JTextField();
		panel.add(widthText);
		widthText.setColumns(10);
		
		JLabel heightLabel = new JLabel("Enter Height");
		panel.add(heightLabel);
		heightLabel.setFont(new Font("Arial", Font.BOLD, 16));
		
		heightText = new JTextField();
		panel.add(heightText);
		heightText.setColumns(10);
		
		JPanel bottomPanel = new JPanel();
		getContentPane().add(bottomPanel);
		
		JButton saveButton = new JButton("Save Maze");
		saveButton.setFocusable(false);
		saveButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int w = Integer.valueOf(widthText.getText());
					int h = Integer.valueOf(heightText.getText());
					FileChooserHandler fch = new FileChooserHandler(new MazeDrawer(w,h));
					fch.handle();
				}catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Width and Height need to be numbers", "Invalid Data", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		bottomPanel.add(saveButton);
		saveButton.setFont(new Font("Arial", Font.BOLD, 24));
		this.setTitle("Maze Generator");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		this.pack();
		this.setMinimumSize(new Dimension(400, 400));
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		
	}

}
