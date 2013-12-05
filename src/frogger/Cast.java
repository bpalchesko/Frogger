package frogger;

import java.util.ArrayList;
import java.util.Iterator;

public class Cast {

	ArrayList<Sprite> castList;
	Iterator<Sprite> iterator;
	Iterator<Sprite> iterator2;
	Frog frog;

	public Cast() {
		castList = new ArrayList<Sprite>();
		frog = new Frog();
		castList.add(frog);
		iterator = castList.iterator();
		iterator2 = castList.iterator();
	}
	
	boolean checkForCollision(View view, Frogger frogger) {
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
	
	void addCar(Frogger frogger) {
		 castList.add(new Car(frogger));
		 //castList.add(1, new Car(frogger));
	}
	
	void removeCar(Frogger frogger) {
		for (Sprite sprite : castList) {
			if (sprite.x < -75 || sprite.x > 775) { // Completely off the screen
													// - only applies to cars
				castList.remove(sprite);
			}
		}
//		while (iterator.hasNext()) {
//			Sprite sprite = iterator.next();
//			if (sprite.x < -75 || sprite.x > 775) { // Completely off the screen
//													// - only applies to cars
//				iterator.remove();
//			}
//		}
	}
	
	void updateCast(Frogger frogger) {
		synchronized(this) {
		addCar(frogger);
		removeCar(frogger);
		}
	}
	
}
