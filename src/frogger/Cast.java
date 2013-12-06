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
		
		//iterator2 = castList.iterator();
	}
	
	synchronized boolean checkForCollision(View view, Frogger frogger) {
		boolean collision = false;
		
		for (Sprite sprite : castList) {
			if (sprite instanceof Car) {
				Car car = (Car) sprite;
				if (frog.x >= car.x - 30 && frog.x <= car.x + 74
						&& frog.y >= car.y - 30
						&& frog.y <= car.y + 37) {
					collision = true;
				}
				if (car.lane == 0) car.dx = -2 * frogger.level;
				else car.dx = 2 * frogger.level;
			}
		}
		return collision;
	}
	
	synchronized void addCar(Frogger frogger) {
		Random generator = new Random();
		System.out.println(isSufficientCarInterval(frogger));
		if(generator.nextInt(10) + frogger.level > 7 && isSufficientCarInterval(frogger)) {
		 castList.add(new Car(frogger));
		}
		 //castList.add(1, new Car(frogger));
	}
	
	synchronized void removeCar(Frogger frogger) {
		Iterator<Sprite> iterator = castList.iterator();
//		for (Sprite sprite : castList) {
//			if (sprite.x < -75 || sprite.x > 775) { // Completely off the screen
//													// - only applies to cars
//				castList.remove(sprite);
//			}
//		}
		while (iterator.hasNext()) {
			Sprite sprite = iterator.next();
			if (sprite.x < -100 || sprite.x > 800) { // Completely off the screen
				System.out.println("removed car");		// - only applies to cars
				iterator.remove();
			}
		}
	}
	
	boolean isSufficientCarInterval(Frogger frogger) {
		return (frogger.level > 2 || frogger.carInterval % 2 == 0);
	}
	
	void updateCast(Frogger frogger) {
		addCar(frogger);
	}
	
}
