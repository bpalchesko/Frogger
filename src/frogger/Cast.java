package frogger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Cast {

	ArrayList<Sprite> castList;
	Frog frog;

	public Cast() {
		castList = new ArrayList<Sprite>();
		frog = new Frog();
		castList.add(frog);
	}

	synchronized boolean checkForCollision(View view, Frogger frogger) {
		boolean collision = false;
		for (Sprite sprite : castList) {
			sprite.update();
			if (sprite instanceof Car) {
				Car car = (Car) sprite;
				car.updateCarSpeed(frogger);
				if (frog.x >= car.x - 30 && frog.x <= car.x + 74
						&& frog.y >= car.y - 30 && frog.y <= car.y + 37) {
					collision = true;
				}
			}
		}
		return collision;
	}

	synchronized void addCar(Frogger frogger) {
		Random generator = new Random();
		if (generator.nextInt(15) + frogger.level > 6
				&& isSufficientCarInterval(frogger)) {
			castList.add(new Car(frogger));
		}
	}

	synchronized void removeCar(Frogger frogger) {
		Iterator<Sprite> iterator = castList.iterator();
		while (iterator.hasNext()) {
			Sprite sprite = iterator.next();
			if (sprite.x < -100 || sprite.x > 800) { // Off the screen
				iterator.remove();
			}
		}
	}

	boolean isSufficientCarInterval(Frogger frogger) {
		boolean sufficientSpaceBetweenCars;
		if (frogger.level < 3) {
			sufficientSpaceBetweenCars = frogger.carInterval % 4 == 0;
		} else if (frogger.level < 7) {
			sufficientSpaceBetweenCars = frogger.carInterval % 2 == 0;
		} else sufficientSpaceBetweenCars = true;
		return sufficientSpaceBetweenCars;
	}

	void updateCast(Frogger frogger) {
		addCar(frogger);
		removeCar(frogger);
	}

}
