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
		lane = generator.nextInt(2);
		placeCarInLane(lane, color, frogger);
		dy = 0;
	}

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

	void updateCarSpeed(Frogger frogger) {
		if (lane == 0) dx = -2 * frogger.level;
		else dx = 2 * frogger.level;
	}

	@Override
	void update() {
		x = x + dx;
	}

	@Override
	void draw(Graphics g) {
		g.drawImage(carImage, x, y, null);
	}
}
