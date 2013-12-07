package frogger;

import java.awt.Graphics;
import java.awt.Image;

/**
 * Creates the frog and loads appropriate images
 * @author Brad Palchesko and Mike Junod
 */
public class Frog extends Sprite {
	
	enum Orientation {UP, RIGHT, DOWN, LEFT };
	Orientation orientation;
	Image frogUpImage;
	Image frogDownImage;
	Image frogLeftImage;
	Image frogRightImage;
	Image frogImage;
	
	/**
	 * Creates frog at starting position and loads images
	 */
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
	
	/**
	 * Selects frog image based on orientation
	 * @param orientation
	 * @return
	 */
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
	
	/**
	 * Specifies the change of position for each frog movement
	 */
	void hop() {
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
	
	/**
	 * Reverse the frog's direction (180 degrees)
	 */
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
	
	
	/**
	 * Handles 90 degree rotations counterclockwise
	 */
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
	
	/**
	 * Handles 90 degree clockwise rotations
	 */
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
	
	/**
	 * Update frog image
	 */
	@Override
	void update() {
		frogImage = selectFrogImage(orientation);
	}

	/**
	 * Draws the frog image
	 */
	@Override
	void draw(Graphics g) {
        g.drawImage(frogImage, x, y, null);
	}
}
