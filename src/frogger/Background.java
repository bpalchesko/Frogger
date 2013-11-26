package frogger;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

/**
 * Loads the game's background image.
 * Adapted from http://stackoverflow.com/questions/15711821/adding-a-background-image-to-a-jpanel
 * @author 
 *
 */
public class Background {
	
    private Image backgroundImage;

	public Background() {
		this.backgroundImage = View.loadImage("FroggerBoard.jpg");
    }
	
	void draw(Graphics g) {
	    g.setColor(Color.WHITE);
	    g.fillRect(0, 0, g.getClipBounds().width, g.getClipBounds().height);
	    g.drawImage(backgroundImage, 0, 0, g.getClipBounds().width, g.getClipBounds().height, null);
	}
}