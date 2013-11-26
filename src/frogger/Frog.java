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
	Image frogImage;
	
	public Frog() {
		this.x = 350;
		this.y = 260;
		this.dx = 0;
		this.dy = 0;
		this.alive = true;
		this.orientation = Orientation.LEFT;
		this.frogUpImage = View.loadImage("frog-up.png");
		this.frogDownImage = View.loadImage("frog-down.png");
		this.frogLeftImage = View.loadImage("frog-left.png");
		this.frogRightImage = View.loadImage("frog-right.png");
		this.frogImage = selectFrogImage(orientation);
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
		return frogImage;
	}
	
	@Override
	void update() {};

	@Override
	void draw(Graphics g) {
        g.drawImage(frogImage, x, y, null);
	};
}
