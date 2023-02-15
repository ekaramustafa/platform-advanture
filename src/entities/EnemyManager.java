package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static utils.Constants.EntityConstants.EnemyConstants.*;
import gamestates.Playing;
import utils.LoadSave;

public class EnemyManager {
	
	private Playing playing;
	private BufferedImage[][] crabbyArr;
	private ArrayList<Crabby> crabbies = new ArrayList<>();
	
	public EnemyManager(Playing playing) {
		this.playing = playing;
		loadEnemyImgs();
		addEnemies();
	}

	private void addEnemies() {
		// TODO Auto-generated method stub
		crabbies = LoadSave.GetCrabs();
	}
	
	public void update(int[][] lvlData) {
		updateCrabs(lvlData);
	}
	private void updateCrabs(int[][] lvlData) {
		for(Crabby c : crabbies) {
			c.update(lvlData);
		}
	}
	
	public void draw(Graphics g,int xLvlOffset) {
		drawCrabs(g,xLvlOffset);
	}

	private void drawCrabs(Graphics g,int xLvlOffset) {
		for(Crabby c: crabbies) {	//- xLvlOffset - CRABBY_DRAW_OFFSET_X - CRABBY_DRAW_OFFSET_Y
			g.drawImage(crabbyArr[c.getEntityState()][c.getAnimationIndex()], (int)c.getHitbox().x - xLvlOffset - CRABBY_DRAW_OFFSET_X, (int)c.getHitbox().y,CRABBY_WIDTH,CRABBY_HEIGHT, null);
			c.drawHitbox(g, xLvlOffset);
		}
		
	}

	private void loadEnemyImgs() {
		crabbyArr = new BufferedImage[5][9];
		BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.CRABBY_SPRITE);
		
		for(int j=0; j< crabbyArr.length;j++) {
			for(int i=0; i<crabbyArr[j].length;i++) {
				crabbyArr[j][i] = temp.getSubimage(i * CRABBY_WIDTH_DEFAULT, j*CRABBY_HEIGHT_DEFAULT, CRABBY_WIDTH_DEFAULT, CRABBY_HEIGHT_DEFAULT);
			}
		}
		
	}

}
