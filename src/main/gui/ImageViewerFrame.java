package main.gui;

import javax.swing.JFrame;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.GridLayout;

public class ImageViewerFrame extends JFrame{
	
	private ImagePanel panel;
	
	public ImageViewerFrame(Image image, int width, int height, int scale) {
		
		
		
		panel = new ImagePanel(image, width, height, scale);
		getContentPane().setLayout(new GridLayout());
		getContentPane().add(panel);
		
		this.setTitle("Maze Viewer");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(true);
		this.pack();
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
	private static final long serialVersionUID = -3455831194864125215L;



}
