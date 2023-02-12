package entities;

import static utils.Constants.EntityConstants.PlayerConstants.*;
import static utils.HelpMethods.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import utils.LoadSave;

public class Player extends Entity{
	private boolean left,right,jump;
	
	private int[][] lvlData;
	
	//for better hitbox
	private float xDrawOffset = 21 * Game.SCALE;
	private float yDrawOffset = 4 * Game.SCALE;

	public Player(float x, float y,int width,int height) {
		super(x, y,width,height);
		entityState = IDLE;
		loadAnimations();
		initHitbox(x,y,(int)(20*Game.SCALE),(int)(27*Game.SCALE));

	}
	
	@Override
	public void update() {
		updatePos();	
		updateAnimationTick();
		setAnimation();
	}
	@Override
	public void render(Graphics g, int lvlOffset) {
		g.drawImage(animations[entityState][animationIndex], (int)(hitbox.x - xDrawOffset) - lvlOffset, (int)(hitbox.y-yDrawOffset),width,height,null);	
		drawHitbox(g, lvlOffset);
	}
	@Override
	protected void updateAnimationTick() {
		// TODO Auto-generated method stub
		animationTick++;
		
		//update the frame if required time passed
		if(animationTick >= animationSpeed) {
			animationTick = 0;
			animationIndex++;
			//get back to initial frame
			if(animationIndex >= getSpriteAmount(entityState)) {
				animationIndex = 0;
				attacking = false;
			}
		}
	}
	
	@Override
	protected void setAnimation() {
		// TODO Auto-generated method stub
		int startAni = entityState;
		
		if (moving)
			
			entityState = RUNNING;
		else 
			entityState = IDLE;	
		
		if(attacking)
			entityState = ATTACK_1;
		
		if(inAir)
			if(airSpeed < 0)
				entityState = JUMP;
			else
				entityState = FALLING;
		
		if (startAni != entityState) 
			resetAnimationTick();
	}
	
	@Override
	protected void resetAnimationTick() {
		// TODO Auto-generated method stub
		animationIndex = 0;
		animationTick = 0;
	}

	@Override
	protected void loadAnimations() {
		BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);
		
		animations = new BufferedImage[9][6];
		for (int j = 0; j < animations.length;j++) {
				for(int i = 0;i < animations[j].length;i++) {
					animations[j][i] = img.getSubimage(i*64, j*40, 64, 40);
				} 
			}
	}
	
	public void loadLvlData(int[][] lvlData) {
		this.lvlData = lvlData;
		if(!IsEntityOnFloor(hitbox, lvlData))
			inAir = true;
	}
	
	
	private void updatePos() {
		moving = false;
		
		if(jump)
			jump();
		//check whether any input is given
		if(!inAir)
			if((!left && !right) || left && right)
				return;
		
		float xSpeed = 0;
		
		if (left) 
			xSpeed -=entitySpeed;
		if (right) 
			xSpeed +=entitySpeed;
		
		if(!inAir) {
			
			if(!IsEntityOnFloor(hitbox, lvlData)) {
				inAir = true;
			}
		}
		
		if(inAir) 
		{
			if(canMoveHere(hitbox.x,hitbox.y+airSpeed,hitbox.width,hitbox.height,lvlData)) {
				hitbox.y +=airSpeed;
				airSpeed +=gravity;
				updateXPos(xSpeed);
			}
			else {
				hitbox.y = GetEntityYPosUnderRoofOrAboveFloor(hitbox,airSpeed);
				if(airSpeed > 0)
					resetInAir();
				else
					airSpeed = fallSpeedAfterCollision;
				updateXPos(xSpeed);
			}
			
		}
		else 
			updateXPos(xSpeed);
		
		moving = true;
			
	}

	private void jump() {
		// TODO Auto-generated method stub
		if(inAir)
			return;
		inAir = true;
		airSpeed = jumpSpeed;
	}


	private void resetInAir() {
		// TODO Auto-generated method stub
		inAir = false;
		airSpeed = 0f;
		
	}

	private void updateXPos(float xSpeed) {
		if(canMoveHere(hitbox.x+xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData)) {
		hitbox.x +=xSpeed;
		}
		else {
			hitbox.x = GetEntityXPosNextToWall(hitbox, xSpeed);
		}
	}

	public void resetDirBooleans() {
		// TODO Auto-generated method stub
		left = false;
		right = false;
	}
		
	//getters & setters
	public void setAttacking(boolean attacking) {
		this.attacking = attacking;
	}
	

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public void setJump(boolean jump) {
		this.jump = jump;
	}


	
	
	
}
