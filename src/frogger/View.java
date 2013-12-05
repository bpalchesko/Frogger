package frogger;

import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class View extends JPanel {

	private static final long serialVersionUID = 1L;

	Background background;
	Cast cast;

	public View(Cast cast) {
		background = new Background();
		this.cast = cast;
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
		synchronized (cast) {
			background.draw(g);
			for (Sprite sprite : cast.castList) {
				if (sprite instanceof Splat)
					sprite.draw(g);
			}
			cast.frog.draw(g);
			for (Sprite sprite : cast.castList) {
				if (sprite instanceof Car)
					sprite.draw(g);
			}
		}
	}

}
