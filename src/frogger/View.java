package frogger;

import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

/**
 * Creates a new view for the game background and sprites
 * @author Brad Palchesko and Mike Junod
 */
public class View extends JPanel {

	private static final long serialVersionUID = 1L;

	Background background;
	Cast cast;

	/**
	 * Construct the view
	 * @param cast
	 */
	public View(Cast cast) {
		background = new Background();
		this.cast = cast;
	}

	/**
	 * Provide the name of a file under resources and this 
	 * method will return the image
	 * @param fileName
	 * @return
	 */
	static Image loadImage(String fileName) {
		Image img = null;
		try {
			//img = ImageIO.read(new File("resources/" + fileName));
			img = ImageIO.read(ResourceLoader.load(fileName));
		} catch (IOException exc) {
			System.out.println("Can't load image.");
		}
		return img;
	}
	
	/**
	 * Prints the background and sprites to the view
	 */	
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
