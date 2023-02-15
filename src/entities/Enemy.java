package entities;

import static utils.HelpMethods.*;

import main.Game;

import static utils.Constants.EntityConstants.EnemyConstants.*;
import static utils.Constants.Directions.*;

public abstract class Enemy extends Entity{
	
	private int enemyType;
	
	private boolean firstUpdate = true;
	
	private int walkDir = LEFT;
	
	public Enemy(float x, float y, int width, int height, int enemyType) {
		super(x, y, width, height);
		this.enemyType = enemyType; 
		initHitbox(x, y, width,height);
		// TODO Auto-generated constructor stub
	}
	
	protected void updateAnimationTick() {
		animationTick+=1;
		
		if(animationTick > animationSpeed) {
			animationTick = 0;
			animationIndex++;
			if(animationIndex >= getSpriteAmount(enemyType, entityState)) {
				animationIndex = 0;
			}
		}
		
	}
	
	public void update(int[][] lvlData){
		updateMove(lvlData);
		updateAnimationTick();
	}
	
	private void updateMove(int[][] lvlData) {
		
		if(firstUpdate) {
			if(!IsEntityOnFloor(hitbox,lvlData)) {
				//the entity is in the floor
				inAir = true;
			}
			firstUpdate = false;
		}
		
		if(inAir) {
			if(canMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, lvlData)) {
				hitbox.y += airSpeed;
				airSpeed += Game.DEFAULT_GRAVITY;
			}
			else {
				inAir = false;
				hitbox.y = GetEntityYPosUnderRoofOrAboveFloor(hitbox, airSpeed);
			}
		}
		
		else {
			switch(entityState) {
						
			case IDLE:
				entityState = RUNNING;
				break;
			case RUNNING:
				float xSpeed = 0;
				
				if(walkDir == LEFT)
					xSpeed = -entitySpeed;
				else
					xSpeed = entitySpeed;
				
				if(canMoveHere(hitbox.x+xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData)) {
					//check if the enemy is on the edge
					if(IsFloor(hitbox,xSpeed, lvlData)) {
						hitbox.x += xSpeed;
						return;
					}
				}
				changeWalkDir();
			
				break;
			
			
			}
			
		}
			
		
	}

	private void changeWalkDir() {
		// TODO Auto-generated method stub
		if(walkDir == LEFT) {
			walkDir = RIGHT;
		}
		else {
			walkDir = LEFT;
		}
	}

}
