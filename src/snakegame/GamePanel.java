package snakegame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

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
	int applesEaten;
	int foodX;
	int foodY;
	char direction = 'R'; // R = right
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
		
		for(int i = 0; i < ((SCREEN_WIDTH / UNIT_SIZE) + (UNIT_SIZE * 2)); i++) {
			g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, BELOW_LIMIT);
			g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
			//g.drawLine(0, i * UNIT_SIZE, SCREEN_HEIGHT, 0); // incredible thing 
		}
		g.setColor(Color.yellow);
		g.fillRect(foodX, foodY, UNIT_SIZE, UNIT_SIZE);
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
		
	}
	
	public void checkApple() {
		
	}
	
	public void checkCollisions() {
		
	}
	
	public void gameOver(Graphics g) {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) { 	
		// TODO Auto-generated method stub
		
	}
	
	public class MyKeyAdapter extends KeyAdapter {
		
		public void KeyPressed(KeyEvent e) {
			
		}
	}

}
