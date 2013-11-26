//import

package frogger;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

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
	
	public Frogger() {
		this.level = 0;
		this.lives = 3;
	}
	
	public static void main(String[] args) {
		//new Frogger().createGame();
		Frogger frogger = new Frogger();
		frogger.createGame();
		frogger.runGame();
		//frogger.updateGame();
	}
	
	void runGame() {
		this.setVisible(true);
		//this.background.repaint();
	}
	
//	void updateGame() {
//		this.lives--;
//		livesLeft.setText("Frog lives: " + lives);
//		repaint();
//	}
	
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
