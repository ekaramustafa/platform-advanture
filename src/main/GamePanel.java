package main;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import inputs.KeyboardInput;
import inputs.MouseInput;
import static main.Game.WINDOW_HEIGHT;
import static main.Game.WINDOW_WIDTH;


public class GamePanel extends JPanel{
	
	private MouseInput mouseInput;
	private Game game;

	public GamePanel(Game game) {
		this.game = game;
		mouseInput = new MouseInput(this);
		setPanelSize();
		addKeyListener(new KeyboardInput(this));
		addMouseListener(mouseInput);
		addMouseMotionListener(mouseInput);
	}
	public Game getGame() {
		return game;
	}

	private void setPanelSize() {
		Dimension size = new Dimension(WINDOW_WIDTH,WINDOW_HEIGHT);
		setPreferredSize(size);
	}
	

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		game.render(g);

	}

}
