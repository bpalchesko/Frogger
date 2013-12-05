package frogger;

import java.util.ArrayList;

public class Cast {

	ArrayList<Sprite> castList;

	public Cast() {
		castList = new ArrayList<Sprite>();
	}
	
	boolean checkForCollision(View view) {
		boolean collision = false;
		 for(Sprite sprite: castList) {
			 if(sprite instanceof Car && view.frog.x >= sprite.x-30 && view.frog.x <= sprite.x+74 && 
			    view.frog.y >= sprite.y-30 && view.frog.y <= sprite.y+37) {
				 collision = true;
			 }	 
		 }
		 return collision;
	}
	
	void addCar(Frogger frogger) {
		 castList.add(0, new Car(frogger));
		 castList.add(1, new Car(frogger));
	}
	
	void removeCar(Frogger frogger) {
		 for(Sprite sprite: castList) {
			 if(sprite.x < -75 || sprite.x > 775) { //Completely off the screen - only applies to cars
		         castList.remove(sprite);
			 }	 
		 }
	}
	
}
