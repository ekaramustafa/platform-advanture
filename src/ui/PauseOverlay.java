package ui;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import gamestates.Gamestate;
import gamestates.Playing;
import main.Game;
import utils.LoadSave;
import static utils.Constants.UI.pauseButtons.*;
import static utils.Constants.UI.URMButtons.*;
import static utils.Constants.UI.VolumeButtons.*;


public class PauseOverlay {
	
	private BufferedImage pauseBackground;
	private int bgX,bgY,bgW,bgH;
	private SoundButton musicButton, sfxButton;
	private UrmButton menuB,replayB,unpauseB;
	private Playing playing;
	private VolumeButton volumeButton;
	
	public PauseOverlay(Playing playing) {
		loadBackground();
		createSoundButtons();
		createUrmButtons();
		createVolumeButton();
		this.playing = playing;
	}
	
	private void createVolumeButton() {
		// TODO Auto-generated method stub
		int vX = (int)(309 * Game.SCALE);
		int vY = (int)(278 * Game.SCALE);
		volumeButton = new VolumeButton(vX, vY, SLIDER_WIDTH, VOLUME_SIZE_HEIGTH);
	}

	//UI Buttons for unpause reaplay and n'ggas
	private void createUrmButtons() {
		// TODO Auto-generated method stub
		int menuX = (int)(313 * Game.SCALE);
		int replayX = (int)(387 * Game.SCALE);
		int unpauseX = (int)(462 * Game.SCALE);
		int bY = (int)(325 * Game.SCALE);
		
		menuB = new UrmButton(menuX, bY, URM_SIZE, URM_SIZE, 2);
		replayB = new UrmButton(replayX, bY, URM_SIZE, URM_SIZE, 1);
		unpauseB = new UrmButton(unpauseX, bY, URM_SIZE, URM_SIZE, 0);

	}

	private void createSoundButtons() {
		int soundX = (int)(450 * Game.SCALE);
		int musicY = (int)(140 * Game.SCALE);
		//adding one button size of 46 pixels
		int sfxY = (int)(186 * Game.SCALE);
		
		musicButton = new SoundButton(soundX, musicY, SOUND_SIZE, SOUND_SIZE);
		sfxButton = new SoundButton(soundX, sfxY, SOUND_SIZE, SOUND_SIZE);

	}

	private void loadBackground() {		
		pauseBackground = LoadSave.GetSpriteAtlas(LoadSave.PAUSE_BACKGROUND);
		bgW = (int)(pauseBackground.getWidth() * Game.SCALE);
		bgH = (int)(pauseBackground.getHeight() * Game.SCALE);
		bgX = Game.WINDOW_WIDTH /2 - bgW/2;
		bgY = (int)(25 * Game.SCALE);

	}
	
	public void update() {
		musicButton.update();
		sfxButton.update();
		
		menuB.update();
		replayB.update();
		unpauseB.update();
		volumeButton.update();
				
	}
	
	public void draw(Graphics g) {
		//background
		g.drawImage(pauseBackground, bgX, bgY, bgW, bgH, null);
		
		//sound buttons
		musicButton.draw(g);
		sfxButton.draw(g);
		
		menuB.draw(g);
		replayB.draw(g);
		unpauseB.draw(g);
		volumeButton.draw(g);
	}
	
	public void mouseDragged(MouseEvent e) {
		if(volumeButton.isMousePressed()) {
			volumeButton.updateX(e.getX());
		}
					
	}
	
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(isIn(e,musicButton))
			musicButton.setMousePressed(true);
		else if(isIn(e,sfxButton))
			sfxButton.setMousePressed(true);
		else if(isIn(e,menuB))
			menuB.setMousePressed(true);
		else if(isIn(e,replayB))
			replayB.setMousePressed(true);
		else if(isIn(e,unpauseB))
			unpauseB.setMousePressed(true);
		else if(isIn(e,volumeButton))
			volumeButton.setMousePressed(true);
		
		

	}


	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if(isIn(e,musicButton)) {
			if(musicButton.isMousePressed()) {
				musicButton.setMuted(!musicButton.isMuted());
			}
		}
		
		else if (isIn(e,sfxButton)) {
			if(sfxButton.isMousePressed()) {
				sfxButton.setMuted(!sfxButton.isMuted());
			}
		}
		
		else if (isIn(e,menuB)) {
			if(menuB.isMousePressed()) {
				Gamestate.state = Gamestate.MENU;
				playing.unpauseGame();
			}
		}
		
		else if (isIn(e,replayB)) {
			if(replayB.isMousePressed()) {
				playing.resetGame();
				playing.unpauseGame();
			}
		}
		else if (isIn(e,unpauseB)) {
			if(unpauseB.isMousePressed()) {
				playing.unpauseGame();
			}
		}
		
		musicButton.resetBools();
		sfxButton.resetBools();
		menuB.resetBools();
		unpauseB.resetBools();
		replayB.resetBools();
		volumeButton.resetBools();
		
		
		
	}


	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		musicButton.setMouseOver(false);
		sfxButton.setMouseOver(false);
		menuB.setMouseOver(false);
		unpauseB.setMouseOver(false);
		replayB.setMouseOver(false);
		volumeButton.setMouseOver(false);

		
		if(isIn(e,musicButton))
			musicButton.setMouseOver(true);
		else if(isIn(e,sfxButton))
			sfxButton.setMouseOver(true);
		else if(isIn(e,menuB))
			menuB.setMouseOver(true);
		else if(isIn(e,replayB))
			replayB.setMouseOver(true);
		else if(isIn(e,unpauseB))
			unpauseB.setMouseOver(true);
		else if(isIn(e,volumeButton))
			volumeButton.setMouseOver(true);
		
	}

	private boolean isIn(MouseEvent e, PauseButton b) {
		return b.getBounds().contains(e.getX(),e.getY());	
		
	}
		
}
	


