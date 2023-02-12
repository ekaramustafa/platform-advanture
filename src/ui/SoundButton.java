package ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import utils.LoadSave;
import static utils.Constants.UI.pauseButtons.*;

public class SoundButton extends PauseButton{

	
	private BufferedImage[][] soundImgs;
	private boolean mouseOver, mousePressed;
	private boolean muted;
	private int rowIndx,colIndx;
	
	public SoundButton(int x, int y, int width, int height) {
		super(x, y, width, height);
		
		loadSoundImgs();
	
	}
	private void loadSoundImgs() {

		BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.SOUND_BUTTONS);
		soundImgs = new BufferedImage[2][3];
		
		for(int j=0;j< soundImgs.length;j++) {
			for(int i =0; i< soundImgs[j].length;i++) {
				soundImgs[j][i] = temp.getSubimage(i*SOUND_SIZE_DEFAULT, j*SOUND_SIZE_DEFAULT,SOUND_SIZE_DEFAULT, SOUND_SIZE_DEFAULT);
				}
		
			}
		}
		
	
	public void update() {
		if(muted)
			rowIndx = 1;
		else
			rowIndx = 0;
		
		colIndx = 0;
		if(mouseOver)
			colIndx = 1;
		if(mousePressed)
			colIndx = 2;
		
	}
	
	public void draw(Graphics g) {

		g.drawImage(soundImgs[rowIndx][colIndx], x, y, width, height,null);
		
	}
	
	public void resetBools() {
		mouseOver = false;
		mousePressed = false;
	}
	
	
	public BufferedImage[][] getSoundImgs() {
		return soundImgs;
	}
	public void setSoundImgs(BufferedImage[][] soundImgs) {
		this.soundImgs = soundImgs;
	}
	public boolean isMouseOver() {
		return mouseOver;
	}
	public void setMouseOver(boolean mouseOver) {
		this.mouseOver = mouseOver;
	}
	public boolean isMousePressed() {
		return mousePressed;
	}
	public void setMousePressed(boolean mousePressed) {
		this.mousePressed = mousePressed;
	}
	public boolean isMuted() {
		return muted;
	}
	public void setMuted(boolean muted) {
		this.muted = muted;
	}
	

}



	
