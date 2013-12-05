//import

package frogger;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import frogger.Frog.Orientation;

public class Frogger extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	int level;
	int crossings;
	int lives;
	boolean paused;
	JLabel levelNumber;
	JLabel frogCrossings;
	JLabel livesLeft;
	JLabel directions;
	Background background;
	View view;
	JPanel topBar;
	JPanel bottomBar;
	Timer timer;
	CarCreator carCreator;
	
	public Frogger() {
		carCreator = new CarCreator(this);
		level = 1;
		lives = 3;
		paused = true;
	}
	
	public static void main(String[] args) {
		Frogger frogger = new Frogger();
		frogger.createGame();
		frogger.runGame();
	}
	
	void runGame() {
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		view.cast = new Cast();
		startLevel(view.cast);
//		timer = new Timer();
//		timer.schedule(new Strobe(), 0, 40); 
//		timer.schedule(carCreator, 0, 1800);
		
		addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println(e.getKeyCode());
				if (e.getKeyCode() == KeyEvent.VK_UP && !paused) {
					view.frog.orientation = Orientation.UP;
					if (view.frog.y > 110) view.frog.y -= 34;
					System.out.println(carCreator);
				}
				if (e.getKeyCode() == KeyEvent.VK_UP && paused) {
					System.out.println("paused");
					System.out.println(paused);
				}
				if (e.getKeyCode() == KeyEvent.VK_DOWN && !paused) {
					view.frog.orientation = Orientation.DOWN;
					if (view.frog.y < 280) view.frog.y += 34;
				}
				if (e.getKeyCode() == KeyEvent.VK_LEFT && !paused) {
					view.frog.orientation = Orientation.LEFT;
					if (view.frog.x > 23) view.frog.x -= 35;
				}
				if (e.getKeyCode() == KeyEvent.VK_RIGHT && !paused) {
					view.frog.orientation = Orientation.RIGHT;
					if (view.frog.x < 665) view.frog.x += 35;
				}
				if (e.getKeyCode() == 'P') {
					if(paused) resume();
					else pause();
				}
				view.frog.update();
			}

	        @Override
	        public void keyReleased(KeyEvent e) {
	        }

	        @Override
	        public void keyTyped(KeyEvent e) {
	        }
	    });
	}
	  
	private class Strobe extends TimerTask {
	     public void run() {                
	     updateGame();        
	     }    
	}
	
	private class CarCreator extends TimerTask {
		Frogger frogger;

		CarCreator(Frogger frogger) {
			this.frogger = frogger;
		}

		public void run() {
			view.cast.addCar(frogger);
			//view.cast.removeCar(frogger);
		}
	}
	
	public void pause() {
		paused = true;
	    timer.cancel();
	    System.out.println(paused);
	    directions.setText("Press P to Resume");
	}
	
	public void resume() {
		paused = false;
	    timer = new Timer();
	    timer.schedule(new Strobe(), 0, 40); 
	    carCreator = new CarCreator(this);
		timer.schedule(carCreator, 1800, 1800);
		directions.setText("Press P to Pause");
	}
	
	void startLevel(Cast cast) {
		for(int i=0; i<4; i++) {
		    view.cast.castList.add(new Car(i,this));
		    //System.out.println(cast.castList.size());
		}
	}
		
	void updateGame() {
		for(Sprite sprite: view.cast.castList) {
		    sprite.update();
		    //System.out.println(sprite.x);
		}
		//System.out.println("Sprites" + view.cast.castList.size());
	    if(view.cast.checkForCollision(view)) {
	    	view.cast.castList.add(new Splat(view.frog.x+5, view.frog.y+5));
	    	view.frog = new Frog();
	    	lives--;
	    }
		livesLeft.setText("Frog lives: " + lives);
		view.repaint();
	}
	
	void createGame() {
		setSize(700, 600);
		setResizable(false);
		setLayout(new BorderLayout());
		addView();
		addLevelNumber();
		addFrogCrossings();
		addLivesLeft();
		addDirections();
		addTopBar();
		addBottomBar();	
	}
	
	void addView() {
		view = new View();
		view.setPreferredSize(new Dimension(700, 600));
		add(view, BorderLayout.CENTER);
	}
	
	void addTopBar(){
		topBar = new JPanel();
		topBar.setLayout(new BorderLayout());	
		topBar.setPreferredSize(new Dimension(700, 75));
		topBar.setBackground(Color.black);
		topBar.add(levelNumber, BorderLayout.WEST);
		topBar.add(frogCrossings, BorderLayout.EAST);
		add(topBar, BorderLayout.NORTH);		
	}
	
	void addBottomBar(){
		bottomBar = new JPanel();
		bottomBar.setLayout(new BorderLayout());	
		bottomBar.setPreferredSize(new Dimension(700, 75));
		bottomBar.setBackground(Color.black);
		bottomBar.add(livesLeft, BorderLayout.WEST);
		bottomBar.add(directions, BorderLayout.EAST);
		add(bottomBar, BorderLayout.SOUTH);		
	}
	
	void addLevelNumber() {
		levelNumber = new JLabel("Level: " + level, SwingConstants.CENTER);
		levelNumber.setPreferredSize(new Dimension(200, 75));
		levelNumber.setBackground(Color.black);
		levelNumber.setForeground(Color.white);			
	}
	
	void addFrogCrossings() {
		frogCrossings = new JLabel("Total Frog Crossings: " + crossings, SwingConstants.CENTER);
		frogCrossings.setPreferredSize(new Dimension(250, 75));
		frogCrossings.setBackground(Color.black);
		frogCrossings.setForeground(Color.white);	
	}
	
	void addLivesLeft() {
		livesLeft = new JLabel("Frog lives: " + lives, SwingConstants.CENTER);
		livesLeft.setPreferredSize(new Dimension(200, 75));
		livesLeft.setBackground(Color.black);
		livesLeft.setForeground(Color.white);			
	}
	
	void addDirections() {
		directions = new JLabel("Press P to Play", SwingConstants.CENTER);
		directions.setPreferredSize(new Dimension(250, 75));
		directions.setBackground(Color.black);
		directions.setForeground(Color.white);	
	}
	
}
