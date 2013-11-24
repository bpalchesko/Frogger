package frogger;

public class Frog extends Sprite {

	boolean alive;
	enum Orientation {UP, RIGHT, DOWN, LEFT };
	Orientation orientation;
	
	public Frog() {
		//this.x = [starting point x]
		//this.y = [starting point y]
		this.dx = 0;
		this.dy = 0;
		this.alive = true;
		this.orientation = Orientation.UP; 
	}
	
	@Override
	void update() {};

	@Override
	void draw() {};
}
