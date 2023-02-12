package utils;

import java.awt.geom.Rectangle2D;

import main.Game;

public class HelpMethods {
	
	
	
	public static boolean canMoveHere(float x,float y, float width, float height, int[][] lvlData) {
		//top_left corner
		if(!isSolid(x,y,lvlData))
			//bottom_right corner
			if(!isSolid(x+width, y+height, lvlData))
				//top_right corner
				if(!isSolid(x+width, y, lvlData))
					//top_left corner
					if(!isSolid(x, y+height, lvlData))
						return true;
		return false;
	}
	
	private static boolean isSolid(float x, float y, int[][] lvlData) {
		//The width of the level
		int maxWidth = lvlData[0].length * Game.TILES_SIZE;
		
		if(x < 0 || x >= maxWidth)
			return true;
		if(y < 0 || y >= Game.WINDOW_HEIGHT)
			return true;
		
		float xIndex = x / Game.TILES_SIZE;
		float yIndex = y / Game.TILES_SIZE;
		
		int value = lvlData[(int)yIndex][(int)xIndex];
		
		//11 is transparent sprite
		if(value >= 48 || value <0 || value != 11)
			return true;
		return false;
	
	}
	
	//Gravity related 
	public static float GetEntityXPosNextToWall(Rectangle2D.Float hitbox, float xSpeed) {
		int currentTile = (int)(hitbox.x/Game.TILES_SIZE);
		if(xSpeed > 0) {
			// Right
			int tileXPos = currentTile * Game.TILES_SIZE;
			int offset = (int)(Game.TILES_SIZE - hitbox.width); 
			return tileXPos + offset -1;
		}
		else {
			// Left
			return currentTile * Game.TILES_SIZE;
		}
		
		
	}
	//Gravity related
	public static float GetEntityYPosUnderRoofOrAboveFloor(Rectangle2D.Float hitbox, float airSpeed) {
		int currentTile = (int)(hitbox.y / Game.TILES_SIZE);
		if(airSpeed > 0) {
			//falling
			int tileYPos = currentTile * Game.TILES_SIZE;
			int offset = (int)(Game.TILES_SIZE - hitbox.height);
			return tileYPos + offset -1;
		}
		else {
			//jumping
			return currentTile * Game.TILES_SIZE;
		}
	}
	
	public static boolean IsEntityOnFloor(Rectangle2D.Float hitbox, int[][] lvlData) {
		//check the pixel below bottom left and bottom right
		if(!isSolid(hitbox.x, hitbox.y+hitbox.height+1,lvlData))
			if(!isSolid(hitbox.x+hitbox.width,hitbox.y+hitbox.height+1,lvlData))
				return false;
		
		return true;
	
		
	}

}
