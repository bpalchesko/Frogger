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

public class Frogger extends JFrame {

	private static final long serialVersionUID = 1L;

	int level;
	int crossings;
	int lives;
	boolean paused;
	int carInterval;
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
		view = new View(new Cast());
	}

	public static void main(String[] args) {
		Frogger frogger = new Frogger();
		frogger.createGame();
		frogger.runGame();
	}

	void runGame() {
		level = 1;
		lives = 3;
		paused = true;
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		placeCars(view.cast);

		addKeyListener(new KeyListener() {
			private long lastPress = 0;

			@Override
			public void keyPressed(KeyEvent e) {
				if (System.currentTimeMillis() - lastPress > 120) {
					if (e.getKeyCode() == KeyEvent.VK_UP && !paused) {
						view.cast.frog.jump();
						lastPress = System.currentTimeMillis();
					}
					if (e.getKeyCode() == KeyEvent.VK_DOWN && !paused) {
						view.cast.frog.reverse();
						lastPress = System.currentTimeMillis();
					}
					if (e.getKeyCode() == KeyEvent.VK_LEFT && !paused) {
						view.cast.frog.counterclockwiseRotate();
						lastPress = System.currentTimeMillis();
					}
					if (e.getKeyCode() == KeyEvent.VK_RIGHT && !paused) {
						view.cast.frog.clockwiseRotate();
						lastPress = System.currentTimeMillis();
					}
					if (e.getKeyCode() == 'P') {
						if (paused) resume();
						else pause();
						lastPress = System.currentTimeMillis();
					}
					view.cast.frog.update();
				}
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
			view.cast.updateCast(frogger);
			carInterval++;
		}
	}

	public void pause() {
		paused = true;
		timer.cancel();
		directions.setText("Press P to Resume");
	}

	public void resume() {
		paused = false;
		timer = new Timer();
		timer.schedule(new Strobe(), 0, 40);
		carCreator = new CarCreator(this);
		timer.schedule(carCreator, 0, 450);
		directions.setText("Press P to Pause");
	}

	void restart() {
		carCreator = new CarCreator(this);
		carInterval = 0;
		crossings = 0;
		level = 1;
		lives = 3;
		paused = true;
		view.cast = new Cast();
		placeCars(view.cast);
		directions.setText("Game over. Press P to replay");
	}

	void placeCars(Cast cast) {
		synchronized (view.cast) {
			for (int i = 0; i < 2; i++) {
				view.cast.castList.add(new Car(i, 0, this));
				view.cast.castList.add(new Car(i, 1, this));
			}
		}
	}

	void updateGame() {
		synchronized (view.cast) {
			updateText();
			if (view.cast.checkForCollision(view, this)) frogDeath();
			if (view.cast.frog.y <= 76) frogCrossing();
			level = crossings / 5 + 1;
			view.repaint();
		}
	}
	
	void frogDeath() {
		view.cast.castList.add(new Splat(view.cast.frog.x + 5,
				view.cast.frog.y + 5));
		lives--;
		if (lives < 1) {
			pause();
			restart();
			livesLeft.setText("Frog lives: 0");
		} else view.cast.frog = new Frog();
	}
	
	void frogCrossing() {
		crossings++;
		view.cast.frog = new Frog();
	}
	
	void updateText() {
		frogCrossings.setText("Total Frog Crossings: " + crossings);
		levelNumber.setText("Level: " + level);
		livesLeft.setText("Frog lives: " + lives);
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
		view.setPreferredSize(new Dimension(700, 600));
		add(view, BorderLayout.CENTER);
	}

	void addTopBar() {
		topBar = new JPanel();
		topBar.setLayout(new BorderLayout());
		topBar.setPreferredSize(new Dimension(700, 75));
		topBar.setBackground(Color.black);
		topBar.add(levelNumber, BorderLayout.WEST);
		topBar.add(frogCrossings, BorderLayout.EAST);
		add(topBar, BorderLayout.NORTH);
	}

	void addBottomBar() {
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
		frogCrossings = new JLabel("Total Frog Crossings: " + crossings,
				SwingConstants.CENTER);
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
