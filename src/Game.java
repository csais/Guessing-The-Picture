import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.Action;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends JPanel implements KeyListener, Action {
	private int screenWidth;
	private int screenHeight;
	private Timer time;
	private int delay = 500;
	private CoverImage cover;
	// play is true as long as the game is not over
	private boolean play = true;
	private int cols = 12;
	private int rows = 12;
	private int squares = cols * rows;
	public static String guess = "";
	public static String answer;
	private int count = 1;
	private Image img;
	private boolean fulldisplay = true;
	private int totalimagecount;
	private int datasize;

	private String[] answers = { "pig", "lion", "fox", "rabbit", "eagle", "elephant", "deer", "swan", "parrot", "frog",
			"raccoon", "lizard", "koala", "giraffe", "dolphin", "fish", "squirrel", "owl", "horse", "mouse", "seal",
			"turtle", "shark", "panda", "lemur", "hippo", "bear", "wolf", "zebra", "monkey", "ostrich", "penguin",
			"turkey", "snake", "otter", "robin" };

	public Game() {
		answer = answers[0];
		totalimagecount = answers.length;
		datasize = answers.length;
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		time = new Timer(delay, this);
		time.start();
		screenWidth = 648;
		screenHeight = 648;

		cover = new CoverImage(cols, rows, this.screenWidth, this.screenHeight);
	}

	public int getScreenWidth() {
		return screenWidth;
	}

	public int getScreenHeight() {
		return screenHeight;
	}

	public void paint(Graphics g) {
		// for background
		if (count == 1) {
			img = Toolkit.getDefaultToolkit().getImage("pics/pic1.jpg");
		}
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
		if (totalimagecount <= 0) {
			play = false;
			g.setColor(Color.black);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("You finished!", 250, getScreenHeight() / 2 - 50);
		} else if (fulldisplay)
			cover.draw((Graphics2D) g);

		else {
			cover.clearAll();
			cover.draw((Graphics2D) g);

		}
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		time.start();

		if (play) {
			if (!(guess.equals(answer))) {
				Random r = new Random();
				int i = r.nextInt(cover.map.length);
				int j = r.nextInt(cover.map.length);
				while (cover.map[i][j] == 0) {
					i = r.nextInt(cover.map.length);
					j = r.nextInt(cover.map.length);
				}
				cover.setCoverValue(0, i, j);
				squares--;
			}

			if (guess.equals(answer) && fulldisplay == true) {
				fulldisplay = false;
			} else if (guess.equals(answer) && fulldisplay == false) {
				fulldisplay = true;
				totalimagecount--;
				try {
					TimeUnit.MILLISECONDS.sleep(300);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				if (count < datasize) {
					count++;
					img = Toolkit.getDefaultToolkit().getImage("pics/pic" + count + ".jpg");
					cover = new CoverImage(cols, rows, this.screenWidth, this.screenHeight);
					guess = "";
					answer = answers[count - 1];
				}
			}
		}

		repaint();

	}

	@Override
	public Object getValue(String arg0) {
		return null;
	}

	@Override
	public void putValue(String arg0, Object arg1) {
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

}
