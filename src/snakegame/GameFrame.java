package snakegame;

import javax.swing.JFrame;

public class GameFrame extends JFrame{
	
	GameFrame(){
		this.add(new GamePanel());
		this.setTitle("Crazy Snake");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

}
