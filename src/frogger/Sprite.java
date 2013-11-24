package frogger;

public abstract class Sprite {
	
	int x;
	int y;
	int dx;
	int dy;
	
	abstract void update();
    abstract void draw();
	
}
