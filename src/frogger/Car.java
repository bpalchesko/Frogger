package frogger;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

/**
 * Creates a car sprite to move across the screen
 * @author Brad Palchesko and Mike Junod
 */
public class Car extends Sprite {

	int lane;
	int color; // aqua, blue, green, red, white, yellow
	Image carImage;

	/**
	 * Generates a random car in a random lane
	 * @param frogger
	 */
	public Car(Frogger frogger) {
		Random generator = new Random();
		color = generator.nextInt(6);
		lane = generator.nextInt(2);
		placeCarInLane(lane, color, frogger);
		dy = 0;
	}

	/**
	 * Generates a car in the specified lane and side of 
	 * the screen at a random location
	 * @param lane
	 * @param zone
	 * @param frogger
	 */
	public Car(int lane, int zone, Frogger frogger) {
		Random generator = new Random();
		color = generator.nextInt(6);
		this.lane = lane;
		if (zone == 0) {
			placeCarInLane(this.lane, color, frogger);
			x = 100 + generator.nextInt(176); // left half of street
		} else {
			placeCarInLane(this.lane, color, frogger);
			x = 350 + generator.nextInt(176); // right half of street
		}
		dy = 0;
	}

	/**
	 * Places a car in either the west bound or east bond lane 
	 * just off the edge of the screen and sets correct image file
	 * @param lane
	 * @param color
	 * @param frogger
	 */
	void placeCarInLane(int lane, int color, Frogger frogger) {
		if (lane == 0) {
			y = 150;
			x = 775;
			dx = -2 * frogger.level;
			carImage = View.loadImage(selectCarColor(color) + "-car-left.png");
		} else {
			y = 225;
			x = -75;
			dx = 2 * frogger.level;
			carImage = View.loadImage(selectCarColor(color) + "-car-right.png");
		}
	}
	
	/**
	 * Returns the color name for the car
	 * @param color
	 * @return
	 */
	String selectCarColor(int color) {
		String name = "";
		switch (color) {
		case 0:
			name += "aqua"; //Batmobile
			break;
		case 1:
			name += "blue";
			break;
		case 2:
			name += "green"; //Tank
			break;
		case 3:
			name += "red";
			break;
		case 4:
			name += "white";
			break;
		case 5:
			name += "yellow"; //Lamborghini
			break;
		}
		return name;
	}

	/**
	 * Changes speed of cars based on level
	 * @param frogger
	 */
	void updateCarSpeed(Frogger frogger) {
		if (lane == 0) dx = -2 * frogger.level;
		else dx = 2 * frogger.level;
	}

	/**
	 * Updates the position of the car	
	 */
	@Override
	void update() {
		x = x + dx;
	}

	/**
	 * Draws the car image
	 */
	@Override
	void draw(Graphics g) {
		g.drawImage(carImage, x, y, null);
	}
}
