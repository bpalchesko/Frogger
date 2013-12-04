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
	JLabel levelNumber;
	JLabel frogCrossings;
	JLabel livesLeft;
	JLabel directions;
	Background background;
	View view;
	JPanel topBar;
	JPanel bottomBar;
	Timer timer;
	Cast cast;
	
	public Frogger() {
		level = 1;
		lives = 3;
	}
	
	public static void main(String[] args) {
		//new Frogger().createGame();
		Frogger frogger = new Frogger();
		frogger.createGame();
		frogger.runGame();
		//frogger.updateGame();
	}
	
	void runGame() {
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	    addKeyListener(new KeyListener() {
	        @Override
	        public void keyPressed(KeyEvent e) {
	            if(e.getKeyCode() == KeyEvent.VK_UP) {
	            	view.frog.orientation = Orientation.UP;
	                view.frog.y -= 42;
	            }
	            if(e.getKeyCode() == KeyEvent.VK_DOWN) {
	            	view.frog.orientation = Orientation.DOWN;
	            	view.frog.y += 42;
	            }
	            if(e.getKeyCode() == KeyEvent.VK_LEFT) {
	            	view.frog.orientation = Orientation.LEFT;
	            	view.frog.x -= 35;
	            }
	            if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
	            	view.frog.orientation = Orientation.RIGHT;
	            	view.frog.x += 35;
	            }
	            view.frog.update();
	            repaint();
	        }

	        @Override
	        public void keyReleased(KeyEvent e) {
	            System.out.println("Released key " + e.getKeyCode());
	        }

	        @Override
	        public void keyTyped(KeyEvent e) {
	            System.out.println("Typed key " + e.getKeyChar());
	        }
	    });
	    
		cast = new Cast();
		startLevel(cast);
		timer = new Timer(true);
		timer.schedule(new Strobe(), 0, 40); 
		timer.schedule(new CarCreator(), 0, 1800);
		//timer.scheduleAtFixedRate(strobe, 3000, 3000);
		//this.background.repaint();
	}
	  
	private class Strobe extends TimerTask {
	     public void run() {           
//	     model.setLimits(view.getWidth(), view.getHeight());        
	     updateGame();        
	     }    
	}
	
	private class CarCreator extends TimerTask {
	     public void run() {           
	    	 addCar(cast);
	     }    
	}
	
	void startLevel(Cast cast) {
		for(int i=0; i<4; i++) {
		    view.cast.castList.add(new Car(i,this));
		    System.out.println(cast.castList.size());
		}
	}
	
	void addCar(Cast cast) {
		 view.cast.castList.add(new Car(this));
	}
	
	void updateGame() {
//		view.car0.update();
//		view.car1.update();
//		view.car2.update();
//		view.car3.update();
		for(Sprite sprite: view.cast.castList) {
		    sprite.update();
		    System.out.println(sprite.x);
		}
		lives--;
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
	
//	void addBackground() {
//		background = new Background();
//		background.setPreferredSize(new Dimension(700, 600));
//		add(background, BorderLayout.CENTER);
//	}
	
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
