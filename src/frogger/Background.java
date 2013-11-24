package frogger;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Displays the game background.
 * Adapted from http://stackoverflow.com/questions/15711821/adding-a-background-image-to-a-jpanel
 * @author 
 *
 */
public class Background extends JPanel {
	
    private BufferedImage img;
    private ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

	public Background() {
		// load the background image
		try {
			// img = ImageIO.read(getClass().getResource("FroggerBoard.jpg"));
			// img =
			// ImageIO.read(getClass().getResourceAsStream("FroggerBoard.jpg"));
			InputStream input = classLoader
					.getResourceAsStream("FroggerBoard.jpg");
			img = ImageIO.read(input);
			System.out.println("success");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("fail");
		}
		setVisible(true);
    }
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // paint the background image and scale it to fill the entire space
        g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
        repaint();
    }

}