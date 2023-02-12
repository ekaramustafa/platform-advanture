package entities;

import java.awt.image.BufferedImage;
import static utils.Constants.EntityConstants.EnemyConstants.*;

public abstract class Enemy extends Entity{
	
	private int enemyState, enemyType;
	private int aniTick, aniSpeed = 15;
	
	public Enemy(float x, float y, int width, int height, int enemyType) {
		super(x, y, width, height);
		this.enemyType = enemyType;
		initHitbox(x, y, width, height);
		// TODO Auto-generated constructor stub
	}
	
	protected void updateAnimationTick() {
		aniTick+=1;
		
		if(aniTick > aniSpeed) {
			aniTick = 0;
			animationIndex++;
			if(animationIndex >= getSpriteAmount(enemyType, enemyState)) {
				animationIndex = 0;
			}
		}
		
	}
	
	@Override
	public void update(){
		updateAnimationTick();
	}

}
