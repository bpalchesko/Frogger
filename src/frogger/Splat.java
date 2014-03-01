package frogger;

import java.awt.Graphics;
import java.awt.Image;

/**
 * Handles displaying dead frog image
 * @author Brad Palchesko and Mike Junod
 */
public class Splat extends Sprite {
	Image splatImage;

	Splat(int x, int y) {
		this.x = x;
		this.y = y;
		splatImage = View.loadImage("splat2.png");
	}
	
	@Override
	void update() {
	};

	@Override
	void draw(Graphics g) {
		g.drawImage(splatImage, x, y, null);
	};

}
