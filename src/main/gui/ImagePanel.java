package main.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImagePanel extends JPanel implements ComponentListener, MouseWheelListener{

	private static final long serialVersionUID = -6835256087095322846L;
	
	private Image image;
	private int width, height, scale;
	public ImagePanel(Image image, int width, int height, int scale) {
		this.width = width * scale;
		this.height = height * scale;
		this.scale = scale;
		this.image = image;
		this.addComponentListener(this);
		this.addMouseWheelListener(this);
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		centerImage(g);
		
	}
	
	public void centerImage(Graphics g) {
		int screenWidth = getSize().width;
		int screenHeight = getSize().height;
		g.drawImage(image, (screenWidth / 2) - width / 2, (screenHeight / 2) - width / 2, null);
	}
	private int increment = 20;
	public void scaleImageUp() {
		if(width < getSize().width && height < getSize().height) {
			width += increment;
			height += increment;
			ImageIcon newImage = new ImageIcon(image.getScaledInstance(width, height, Image.SCALE_DEFAULT));
			image = newImage.getImage();
			revalidate();
			repaint();
		}
	}
	public void scaleImageDown() {
		if(width > 100 && height > 100) {
			width -= increment;
			height -= increment;
			ImageIcon newImage = new ImageIcon(image.getScaledInstance(width, height, Image.SCALE_DEFAULT));
			image = newImage.getImage();
			revalidate();
			repaint();
		}
	}

	@Override
	public void componentResized(ComponentEvent e) {
		revalidate();
		repaint();
		
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
//		int amount = e.getUnitsToScroll();
//		if(amount > 0) {
//			scaleImageDown();
//		}else {
//			scaleImageUp();
//		}
	}

}
