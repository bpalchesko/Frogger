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
	Cast cast;
	Car car0;
	Car car1;
	Car car2;
	Car car3;

	public View() {
		background = new Background();
		frog = new Frog();
		cast = new Cast();
//		this.car0 = new Car(0, frogger);
//		this.car1 = new Car(1, frogger);
//		this.car2 = new Car(2, frogger);
//		this.car3 = new Car(3, frogger);
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
		for (Sprite sprite: cast.castList) {
			sprite.draw(g);
		}
//		car0.draw(g);
//		car1.draw(g);
//		car2.draw(g);
//		car3.draw(g);
		
	}

}
