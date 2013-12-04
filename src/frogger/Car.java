package frogger;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

public class Car extends Sprite {

	int lane;
	int color; // aqua, blue, green, red, white, yellow
	Image carImage;

	public Car(Frogger frogger) {
		Random generator = new Random();
		color = generator.nextInt(6);
		lane = generator.nextInt(4);
		placeCarInLane(lane, color, frogger);
//		dx = 0;
		dy = 0;
	}

	public Car(int lane, Frogger frogger) {
		Random generator = new Random();
		color = generator.nextInt(6);
		this.lane = lane;
		placeCarInLane(this.lane, color, frogger);
		x = 100 + generator.nextInt(426); // place in the middle
//		dx = 0;
		dy = 0;
	}

	void placeCarInLane(int lane, int color, Frogger frogger) {
		switch (lane) {
		case 0:
			y = 125;
			x = 775;
			dx = -2*frogger.level;
			carImage = View.loadImage(selectCarColor(color) + "-car-left.png");
			break;
		case 1:
			y = 170;
			x = 775;
			dx = -2*frogger.level;
			carImage = View.loadImage(selectCarColor(color) + "-car-left.png");
			break;
		case 2:
			y = 211;
			x = -75;
			dx = 2*frogger.level;
			carImage = View.loadImage(selectCarColor(color) + "-car-right.png");
			break;
		case 3:
			y = 254;
			x = -75;
			dx = 2*frogger.level;
			carImage = View.loadImage(selectCarColor(color) + "-car-right.png");
			break;
		}
	}

	String selectCarColor(int color) {
		String name = "";
		switch (color) {
		case 0:
			name += "aqua";
			break;
		case 1:
			name += "blue";
			break;
		case 2:
			name += "green";
			break;
		case 3:
			name += "red";
			break;
		case 4:
			name += "white";
			break;
		case 5:
			name += "yellow";
			break;
		}
		return name;
	}

	@Override
	void update() {
		x = x+dx;
	}

	@Override
	void draw(Graphics g) {
		g.drawImage(carImage, x, y, null);
	}
}
