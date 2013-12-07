package frogger;

import java.awt.Graphics;
import java.awt.Image;

public class Frog extends Sprite {

	enum Orientation {UP, RIGHT, DOWN, LEFT };
	Orientation orientation;
	Image frogUpImage;
	Image frogDownImage;
	Image frogLeftImage;
	Image frogRightImage;
	Image frogHoppingUpImage;
	Image frogImage;
	
	public Frog() {
		x = 350;
		y = 280;
		orientation = Orientation.UP;
		frogUpImage = View.loadImage("frog-up.png");
		frogDownImage = View.loadImage("frog-down.png");
		frogLeftImage = View.loadImage("frog-left.png");
		frogRightImage = View.loadImage("frog-right.png");
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
		return frogImage;
	}
	
	void jump() {
		switch (orientation) {
		case UP:
			if (y > 76) y -= 34;
			break;
		case DOWN:
			if (y < 280) y += 34;
			break;
		case LEFT:
			if (x > 23) x -= 35;
			break;
		case RIGHT:
			if (x < 665) x += 35;
			break;
		}
	}
	
	void reverse() {
		switch (orientation) {
		case UP:
			orientation = Orientation.DOWN;
			break;
		case DOWN:
			orientation = Orientation.UP;
			break;
		case LEFT:
			orientation = Orientation.RIGHT;
			break;
		case RIGHT:
			orientation = Orientation.LEFT;
			break;
		}
	}
	
	void counterclockwiseRotate() {
		switch (orientation) {
		case UP:
			orientation = Orientation.LEFT;
			break;
		case DOWN:
			orientation = Orientation.RIGHT;
			break;
		case LEFT:
			orientation = Orientation.DOWN;
			break;
		case RIGHT:
			orientation = Orientation.UP;
			break;
		}
	}
	
	void clockwiseRotate() {
		switch (orientation) {
		case UP:
			orientation = Orientation.RIGHT;
			break;
		case DOWN:
			orientation = Orientation.LEFT;
			break;
		case LEFT:
			orientation = Orientation.UP;
			break;
		case RIGHT:
			orientation = Orientation.DOWN;
			break;
		}
	}
	
	@Override
	void update() {
		frogImage = selectFrogImage(orientation);
	}

	@Override
	void draw(Graphics g) {
        g.drawImage(frogImage, x, y, null);
	}
}
