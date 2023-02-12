package main;

import java.awt.Graphics;

import gamestates.Gamestate;
import gamestates.Menu;
import gamestates.Playing;

public class Game implements Runnable{
	
	private Playing playing;
	private Menu menu;
	
	//Window and default size settings
	public final static int TILES_DEFAULT_SIZE = 32;
	public final static float SCALE = 1.5f;
	public final static int TILES_IN_WIDTH = 26;
	public final static int TILES_IN_HEIGHT = 14;
	public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE *  SCALE);
	//below variables for visible screen. Level can have bigger width and height value
	public final static int WINDOW_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
	public final static int WINDOW_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;
	
	//gravity & action related variables for entities
	public final static float DEFAULT_GRAVITY = 0.04f * SCALE;
	public final static float DEFAULT_JUMP_SPEED = -1f*SCALE;
	public final static float DEFAULT_FALLSPEED_AFTER_COLLISION = 1f * Game.SCALE;

	private boolean running;
	
	private GameWindow gameWindow;
	private GamePanel gamePanel;
	private Thread gameThread;
	
	//how many frames to be updated every second
	private final int FPS_SET = 120;
	//how many game updates happen every second
	private final int UPS_SET = 120;

	
	public Game() {
		initClasses();
		
		gamePanel = new GamePanel(this);
		gameWindow = new GameWindow(gamePanel);
		gamePanel.setFocusable(true);		
		gamePanel.requestFocus(); 
		
		startGameLoop();

	}
	
	private void initClasses() {
		//initialize segments of the game
		menu = new Menu(this);
		playing = new Playing(this);

	}

	private void startGameLoop() {
		gameThread = new Thread(this);
		gameThread.start();
		
	}
	
	public void update() {

		
		switch(Gamestate.state) {
		case MENU:
			menu.update();
			break;
		case PLAYING:
			playing.update();
			break;
		case OPTIONS:
			break;
		case QUIT:
			System.exit(0);
			break;
		default:
			System.exit(0);
			break;
		
		}
	}
	
	public void render(Graphics g) {
		//the order matters

		
		switch(Gamestate.state) {
		case MENU:
			menu.draw(g);
			break;
		case PLAYING:
			playing.draw(g);
			break;
		default:
			break;
		
		}
	}
	@Override
	public void run() {
		running = true;
		// TODO Auto-generated method stub
		
		double timePerFrame = 1000000000.0 / FPS_SET;
		
		double timePerUpdate = 1000000000.0 / UPS_SET; 
		
		long previousTime = System.nanoTime();
		
		//fps counter vars
		int frames = 0;
		int updates = 0;
		
		long lastCheck = System.currentTimeMillis();
		
		double deltaU = 0;
		double deltaF = 0;
		
		while(running) {
			
			long currentTime = System.nanoTime();
			
			deltaU+=(currentTime - previousTime)/timePerUpdate;
			deltaF+=(currentTime - previousTime)/timePerFrame;

			previousTime = currentTime;
			
			if(deltaU >=1) {
				update();
				updates++;
				deltaU--;
			}
			
			if (deltaF >=1 ) {
				gamePanel.repaint();
				frames++;
				deltaF--;
			}
			

			//fps counter
			if(System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
				System.out.printf("FPS : %s  | UPS : %s %n", frames,updates);
				frames = 0;
				updates = 0;
			}
		}
	}

	public void windowFocusLost() {
		// TODO Auto-generated method stub
		if(Gamestate.state == Gamestate.PLAYING)
			playing.getPlayer().resetDirBooleans();
	
	}
	
	public Menu getMenu() {
		return menu;
	}
	
	public Playing getPlaying() {
		return playing;
	}


}
