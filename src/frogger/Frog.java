package frogger;

import java.awt.Graphics;
import java.awt.Image;

/**
 * Creates the frog and loads appropriate images
 * @author Brad Palchesko and Mike Junod
 */
public class Frog extends Sprite {
	
	enum Orientation {
		UP("up"), RIGHT("right"), DOWN("down"), LEFT("left");
		
		String name;
		Image facing;
		Image hopping;
		
		private Orientation(String name) {
			this.name = name;
			facing = View.loadImage("facing-" + name +".png");
			hopping = View.loadImage("hopping-" + name +".png");
		}
	};
	Orientation orientation;
	Image frogImage;
	int animationFrame;
	
	/**
	 * Creates frog at starting position and loads images
	 */
	public Frog() {
		x = 350;
		y = 280;
		orientation = Orientation.UP;
		frogImage = selectFrogImage();
		animationFrame = 0;
	}
	
	/**
	 * Selects frog image based on orientation
	 * @param orientation
	 * @return
	 */
	Image selectFrogImage() {
		if (animationFrame == 0 || animationFrame == 3 || animationFrame == 4) {
			return orientation.facing;
		} else {
			return orientation.hopping;
		}
	}
	
	/**
	 * Specifies the change of position for each frog movement
	 */
	void hop() {
		frogImage = selectFrogImage();
		int hopDistance;
		if (animationFrame == 3) hopDistance = 6;
		else hopDistance = 7;
		switch (orientation) {
		case UP:
			if (y > 76) y -= hopDistance;
			break;
		case DOWN:
			if (y < 280) y += hopDistance;
			break;
		case LEFT:
			if (x > 23) x -= 7;
			break;
		case RIGHT:
			if (x < 665) x += 7;
			break;
		}
		if(animationFrame < 5) animationFrame++;
		else animationFrame = 0;
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
		frogImage = selectFrogImage();
		if(animationFrame > 0) hop();
	}

	/**
	 * Draws the frog image
	 */
	@Override
	void draw(Graphics g) {
        g.drawImage(frogImage, x, y, null);
	}
}
