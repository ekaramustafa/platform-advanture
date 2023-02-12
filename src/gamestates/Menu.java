package gamestates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import main.Game;
import ui.MenuButton;
import utils.LoadSave;

public class Menu extends State implements StateMethods{
	
	private MenuButton[] buttons = new MenuButton[3];
	
	
	private BufferedImage menuBackground, background;
	private int menuX,menuY,menuWidth,menuHeight;

	public Menu(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
		loadButtons();
		loadBackground();
	}

	private void loadBackground() {
		// TODO Auto-generated method stub
		background = LoadSave.GetSpriteAtlas(LoadSave.BACKGROUND_MENU);
		
		menuBackground = LoadSave.GetSpriteAtlas(LoadSave.MENU_BACKGROUND);
		menuWidth = (int)(menuBackground.getWidth() * Game.SCALE);
		menuHeight = (int)(menuBackground.getHeight() * Game.SCALE);
		menuX = Game.WINDOW_WIDTH/2 - menuWidth / 2 ;
		menuY = (int)(45 * Game.SCALE);
		
		
		
	}

	private void loadButtons() {
		buttons[0] = new MenuButton(Game.WINDOW_WIDTH/2, (int) (150 * Game.SCALE), 0, Gamestate.PLAYING);
		buttons[1] = new MenuButton(Game.WINDOW_WIDTH/2, (int) (220 * Game.SCALE),1, Gamestate.OPTIONS);
		buttons[2] = new MenuButton(Game.WINDOW_WIDTH/2, (int) (290 * Game.SCALE),2, Gamestate.QUIT);
		
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
		for(MenuButton mb : buttons)
			mb.update();
		
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(background, 0, 0, Game.WINDOW_WIDTH, Game.WINDOW_HEIGHT, null);
		g.drawImage(menuBackground, menuX, menuY, menuWidth, menuHeight, null);
 
		for(MenuButton mb : buttons)
			mb.draw(g);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		for(MenuButton mb : buttons) {
			if(isIn(e,mb)) {
				mb.setMousePressed(true);
				break;
			}
		}
			
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		for(MenuButton mb : buttons) {
			if(isIn(e,mb)) {
				if(mb.isMousePressed()) {
					mb.applyGamestate();
				}
				break;
			}
		}
		
		resetButtons();
		
	}
	
	private void resetButtons() {
		
		for(MenuButton mb: buttons)
			mb.resetBools();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		for(MenuButton mb : buttons)
			mb.setMouseOver(false);
		
		for(MenuButton mb : buttons)
			if(isIn(e, mb)) {
				mb.setMouseOver(true);
				break;
			}
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_ENTER)
			Gamestate.state = Gamestate.PLAYING;
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
