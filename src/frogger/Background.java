package frogger;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

/**
 * Loads the game's background image.
 * @author Brad Palchesko and Mike Junod
 */
public class Background {

	private Image backgroundImage;

	public Background() {
		this.backgroundImage = View.loadImage("FroggerBoard.jpg");
	}

	/**
	 * Sets the size of the image equal to the view
	 * @param g
	 */
	void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, g.getClipBounds().width, g.getClipBounds().height);
		g.drawImage(backgroundImage, 0, 0, g.getClipBounds().width,
				g.getClipBounds().height, null);
	}
}