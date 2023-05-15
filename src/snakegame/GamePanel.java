package snakegame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static int SCREEN_WIDTH = 600;
	static int SCREEN_HEIGHT = 600;
	static final int UNIT_SIZE = 20;
	static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT)/ UNIT_SIZE;
	static final int DELAY = 75;
	static int BELOW_LIMIT = 0;
	final int x[] = new int[GAME_UNITS];
	final int y[] = new int[GAME_UNITS];
	int bodyParts = 6;
	int foodsEaten;
	int foodX;
	int foodY;
	String direction = "RIGHT"; // R = right
	boolean running = false;
	Timer timer;
	Random random;
	
	GamePanel(){
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		GamePanel.SCREEN_WIDTH = screenSize.width;
		GamePanel.SCREEN_HEIGHT = screenSize.height;
		BELOW_LIMIT = SCREEN_HEIGHT - UNIT_SIZE * 4;
		random = new Random();
		//this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setPreferredSize(screenSize);
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		startGame();
	}
	
	public void startGame() {
		newFood();
		running = true;
		timer = new Timer(DELAY, this);
		timer.start();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	
	public void draw(Graphics g) {
		if(running) {			
			for(int i = 0; i < ((SCREEN_WIDTH / UNIT_SIZE) + (UNIT_SIZE * 2)); i++) {
				g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, BELOW_LIMIT);
				g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
				//g.drawLine(0, i * UNIT_SIZE, SCREEN_HEIGHT, 0); // incredible thing 
			}
			g.setColor(Color.yellow);
			g.fillRect(foodX, foodY, UNIT_SIZE, UNIT_SIZE);
			
			for(int i = 0; i < bodyParts; i++) {
				if(i == 0) {
					g.setColor(Color.green);
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				} else {
					g.setColor(new Color(45, 180, 0));
					g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				}
			}
		} else {
			gameOver(g);
		}
	}
	
	public void newFood() {
		foodX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE)) * UNIT_SIZE;
		//foodX = 1900;
		foodY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE)) * UNIT_SIZE;
		while(foodY > (BELOW_LIMIT - UNIT_SIZE)) {
			foodY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE)) * UNIT_SIZE;
		}
		System.out.println("foodX: " + foodX + " foodY: " + foodY + " BELOW_LIMIT: " + BELOW_LIMIT);
	}
	
	public void move() {
		for(int i = bodyParts; i > 0; i--) {
			x[i] = x[i-1];
			y[i] = y[i-1];
		}
		
		switch(direction) {
		case "UP":
			y[0] = y[0] - UNIT_SIZE;
			break;
		case "DOWN":
			y[0] = y[0] + UNIT_SIZE;
			break;
		case "LEFT":
			x[0] = x[0] - UNIT_SIZE;
			break;
		case "RIGHT":
			x[0] = x[0] + UNIT_SIZE;
			break;
		}
	}
	
	public void checkFood() {
		if((x[0] == foodX) && (y[0] == foodY)) {
			bodyParts++;
			foodsEaten++;
			newFood();
		}
	}
	
	public void checkCollisions() {
		//check if head collides with body
		for(int i = bodyParts; i > 0; i--) {
			if((x[0] == x[i]) && (y[0] == y[i])) {
				running = false;
			}
		}
		//check if head touches left or right border
		if(x[0] < 0 || x[0] > SCREEN_WIDTH) {
			running = false;
		}
		//check if head touches top or down border
		if(y[0] < 0 || y[0] > (BELOW_LIMIT - UNIT_SIZE)) {
			running = false;
		}
		
		if(!running) {
			timer.stop();
		}
	}
	
	public void gameOver(Graphics g) {
		// Game over text
		g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
		g.setFont(new Font("Arial", Font.BOLD, 50));
		FontMetrics metrics = getFontMetrics(g.getFont());
		g.drawString("Game Over",
					(SCREEN_WIDTH - metrics.stringWidth("Game Over"))/2,
					DELAY);
		
		g.drawString("Score: " + foodsEaten,
					(SCREEN_WIDTH - metrics.stringWidth("Score: " + foodsEaten))/2,
					((SCREEN_HEIGHT - metrics.stringWidth("Score: " + foodsEaten))/2));
	}

	@Override
	public void actionPerformed(ActionEvent e) { 	
		if(running) {
			move();
			checkFood();
			checkCollisions();
		}
		repaint();
		
	}
	
	public class MyKeyAdapter extends KeyAdapter {
		
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if(direction != "RIGHT") {
					direction = "LEFT";
				}
				break;
			case KeyEvent.VK_RIGHT:
				if(direction != "LEFT") {
					direction = "RIGHT";
				}
				break;
			case KeyEvent.VK_UP:
				if(direction != "DOWN") {
					direction = "UP";
				}
				break;
			case KeyEvent.VK_DOWN:
				if(direction != "UP") {
					direction = "DOWN";
				}
				break;
			}
			
		}
	}

}
