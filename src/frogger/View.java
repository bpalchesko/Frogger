package frogger;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class View extends JPanel {

	private static final long serialVersionUID = 1L;
	
	Background background;
	Frog frog;

	public View() {
		this.background = new Background();
		this.frog = new Frog();
	}

	static Image loadImage(String fileName) {
		Image img = null;
		try {
			img = ImageIO.read(new File("resources/" + fileName));
		} catch (IOException exc) {
			System.out.println("Can't load image.");
		}
		return img;
	}

	@Override
	public void paint(Graphics g) {
		background.draw(g);
		frog.draw(g);
	}

}
