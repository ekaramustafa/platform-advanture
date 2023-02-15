package entities;

import static utils.Constants.EntityConstants.EnemyConstants.*;
import java.awt.Graphics;

import main.Game;
import utils.LoadSave;

public class Crabby extends Enemy{

	public Crabby(float x, float y) {
		super(x, y, CRABBY_WIDTH, CRABBY_HEIGHT, CRABBY);
		initHitbox(x, y,(int)(Game.SCALE *CRABBY_HITBOX_WIDTH),(int)(Game.SCALE * CRABBY_HITBOX_HEIGHT));
		entitySpeed = 0.5f;
		// TODO Auto-generated constructor stub
	}


	@Override
	public void render(Graphics g, int lvlOffset) {
		// TODO Auto-generated method stub

		
	}

	@Override
	protected void setAnimation() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void resetAnimationTick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void loadAnimations() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}


}
