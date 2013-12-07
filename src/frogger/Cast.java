package frogger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Creates and removes sprites
 * @author Brad Palchesko and Mike Junod
 */
public class Cast {

	ArrayList<Sprite> castList;
	Frog frog;
	
	/**
	 * Creates the list of sprites and adds a frog to it
	 */
	public Cast() {
		castList = new ArrayList<Sprite>();
		frog = new Frog();
		castList.add(frog);
	}
	
	/**
	 * Checks for shared coordinates between frog and car
	 * @param view
	 * @param frogger
	 * @return
	 */
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
	
	/**
	 * Adds a car to the GUI
	 * @param frogger
	 */
	synchronized void addCar(Frogger frogger) {
		Random generator = new Random();
		if (generator.nextInt(15) + frogger.level > 6
				&& isSufficientCarInterval(frogger)) {
			castList.add(new Car(frogger));
		}
	}

	/**
	 * Removes a car once it is off the screen
	 * @param frogger
	 */
	synchronized void removeCar(Frogger frogger) {
		Iterator<Sprite> iterator = castList.iterator();
		while (iterator.hasNext()) {
			Sprite sprite = iterator.next();
			if (sprite.x < -100 || sprite.x > 800) { // Off the screen
				iterator.remove();
			}
		}
	}

	/**
	 * Prevents cars from overlapping with each other
	 * @param frogger
	 * @return
	 */
	boolean isSufficientCarInterval(Frogger frogger) {
		boolean sufficientSpaceBetweenCars;
		if (frogger.level < 3) {
			sufficientSpaceBetweenCars = frogger.carInterval % 4 == 0;
		} else if (frogger.level < 7) {
			sufficientSpaceBetweenCars = frogger.carInterval % 2 == 0;
		} else sufficientSpaceBetweenCars = true;
		return sufficientSpaceBetweenCars;
	}

	/**
	 * Updates the list of sprites
	 * @param frogger
	 */
	void updateCast(Frogger frogger) {
		addCar(frogger);
		removeCar(frogger);
	}

}
