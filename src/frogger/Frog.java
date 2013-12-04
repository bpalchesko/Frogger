package frogger;

import java.awt.Graphics;
import java.awt.Image;

public class Frog extends Sprite {

	boolean alive;
	enum Orientation {UP, RIGHT, DOWN, LEFT };
	Orientation orientation;
	Image frogUpImage;
	Image frogDownImage;
	Image frogLeftImage;
	Image frogRightImage;
	Image frogSplatImage;
	Image frogImage;
	
	public Frog() {
		x = 350;
		y = 300;
		dx = 0;
		dy = 0;
		alive = true;
		orientation = Orientation.UP;
		frogUpImage = View.loadImage("frog-up.png");
		frogDownImage = View.loadImage("frog-down.png");
		frogLeftImage = View.loadImage("frog-left.png");
		frogRightImage = View.loadImage("frog-right.png");
		frogSplatImage = View.loadImage("splat.gif");
		frogImage = selectFrogImage(orientation);
	}
	
	Image selectFrogImage(Orientation orientation) {
		Image frogImage = null;
		switch(orientation) {
		case UP:
			frogImage = frogUpImage;
			break;
		case DOWN:
			frogImage = frogDownImage;
			break;
		case LEFT:
			frogImage = frogLeftImage;
			break;
		case RIGHT:
			frogImage = frogRightImage;
			break;
		}
		if (!alive) frogImage = frogSplatImage; 
		return frogImage;
	}
	
	@Override
	void update() {
		frogImage = selectFrogImage(orientation);
	};

	@Override
	void draw(Graphics g) {
        g.drawImage(frogImage, x, y, null);
	};
}
