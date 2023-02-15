package gamestates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import entities.EnemyManager;
import entities.Player;
import levels.LevelManager;
import main.Game;
import ui.PauseOverlay;
import utils.Constants.EntityConstants.EnemyConstants;
import utils.LoadSave;

public class Playing extends State implements StateMethods{


	private Player player;
	private LevelManager levelManager;
	private EnemyManager enemyManager;
	private PauseOverlay pauseOverlay;
	private boolean paused = false;
	
	//variables for moving background
	private int xLvlOffset;
	//these are values that is used to calculate when to move the camera right or left
	private int leftBorder = (int) (0.4 * Game.WINDOW_WIDTH);
	private int rightBorder = (int) (0.6 * Game.WINDOW_WIDTH);
	//getting width of img which is level
	private int lvlTilesWide = LoadSave.GetLevelData()[0].length;
	private int maxTilesOffset = lvlTilesWide - Game.TILES_IN_WIDTH;
	private int maxLvlOffsetX = maxTilesOffset * Game.TILES_SIZE;
	
	public Playing(Game game) {
		super(game);
		initClasses();
		
	}

	
	private void initClasses() {
		levelManager = new LevelManager(game);
		player = new Player(200,200,(int)(64*Game.SCALE),(int) (40*Game.SCALE));
		player.loadLvlData(levelManager.getCurrentLevel().getLevelData());
		pauseOverlay = new PauseOverlay(this);
		enemyManager = new EnemyManager(this);
		
	}
	
	public void resetGame() {
		initClasses();
	}


	@Override
	public void update() {
		if(!paused) {
			levelManager.update();
			player.update();
			enemyManager.update();
			//for moving background
			checkCloseToBorder();
			}
		else {
			pauseOverlay.update();
		}
	}

	//calculates to level offset to move background accurately 
	private void checkCloseToBorder() {
		// TODO Auto-generated method stub
		int playerX = (int) player.getHitbox().x;
		int diff = playerX - xLvlOffset;
		if(diff > rightBorder) {
			xLvlOffset += diff - rightBorder;
		}
		else if (diff < leftBorder) {
			xLvlOffset += diff - leftBorder;
		}
		
		//max and min values of offset
		if(xLvlOffset > maxLvlOffsetX)
			xLvlOffset = maxLvlOffsetX;
		else if(xLvlOffset < 0)
			xLvlOffset = 0;
		
		
	}


	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		levelManager.draw(g, xLvlOffset);
		player.render(g, xLvlOffset);
		enemyManager.draw(g, xLvlOffset);
		//if the game paused show the pause menu
		if(paused) {
			g.setColor(new Color(0,0,0,150));
			g.fillRect(0, 0, Game.WINDOW_WIDTH, Game.WINDOW_HEIGHT);
			pauseOverlay.draw(g);
			}
		}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getButton() == MouseEvent.BUTTON1) {
			player.setAttacking(true);
		}
		
	}

	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		if(paused)
			pauseOverlay.mouseDragged(e);
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(paused)
			pauseOverlay.mousePressed(e);
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if(paused)
			pauseOverlay.mouseReleased(e);
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		if(paused)
			pauseOverlay.mouseMoved(e);
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			player.setLeft(true);
			break;
		case KeyEvent.VK_D:
			player.setRight(true);
			break;
		case KeyEvent.VK_SPACE:
			player.setJump(true);
			break;
		case KeyEvent.VK_ESCAPE:
			paused = !paused;
			break;
		case KeyEvent.VK_R:
			resetGame();;
			break;

		}
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			player.setLeft(false);
			break;
		case KeyEvent.VK_D:
			player.setRight(false);
			break;
		case KeyEvent.VK_SPACE:
			player.setJump(false);
			break;
		}
		
	}
	
	public void unpauseGame() {
		paused = false;
		
	}
	
	//getter
	public Player getPlayer() {
		
		return player;
	}


	public void windowFocusLost() {
		// TODO Auto-generated method stub
		player.resetDirBooleans();
	}

}
