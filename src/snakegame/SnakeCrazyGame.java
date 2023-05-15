package snakegame;

public class SnakeCrazyGame {
	
	public static void main(String[] args) {
		GameFrame gameFrame = new GameFrame();
		while(true) {
			gameFrame.setTitle("Crazy Snake, food eaten: " + String.valueOf(gameFrame.gamePanel.foodsEaten));
		}
	}

}
