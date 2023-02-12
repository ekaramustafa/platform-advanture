package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import main.Game;

public abstract class Entity {
	
	protected float x;
	protected float y;
	protected int width, height;
	
	protected Rectangle2D.Float hitbox;
	
	protected BufferedImage[][] animations;
	
	protected int animationTick,animationSpeed = 15;
	protected int animationIndex;
	
	protected float entitySpeed = 1.0f * Game.SCALE;
	
	protected int entityState;
	protected boolean moving = false;
	protected boolean attacking = false;
	protected boolean inAir = false;
	
	protected float gravity = Game.DEFAULT_GRAVITY;
	protected float jumpSpeed = 2.25f * Game.DEFAULT_JUMP_SPEED ;
	protected float airSpeed = 0f;
	protected float fallSpeedAfterCollision = 0.5f * Game.DEFAULT_FALLSPEED_AFTER_COLLISION;
	
	public Entity(float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

	}
	
	public abstract void render(Graphics g, int lvlOffset);
	public abstract void update();
	protected abstract void updateAnimationTick();
	protected abstract void setAnimation();
	protected abstract void resetAnimationTick();
	protected abstract void loadAnimations();
	
	protected void drawHitbox(Graphics g, int xLvlOffset) {
		// For debugging the hitbox
		g.setColor(Color.pink);
		g.drawRect((int)hitbox.x - xLvlOffset, (int) hitbox.y, (int) hitbox.width, (int)hitbox.height);
	}

	protected void initHitbox(float x, float y, int width, int height) {
		hitbox = new Rectangle2D.Float(x,y,width,height);
	}
	
	public Rectangle2D.Float getHitbox() {
		return hitbox;
	}
	
	
	public int getAnimationIndex() {
		return animationIndex;
	}
	
	public int getEntityState() {
		return entityState;
		
	}

}
