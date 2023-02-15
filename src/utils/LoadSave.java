package utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import entities.Crabby;
import main.Game;
import static utils.Constants.EntityConstants.EnemyConstants.*;
public class LoadSave {
	
	//player
	public static final String PLAYER_ATLAS = "/player_sprites.png";
	
	//level 
	public static final String LEVEL_ATLAS = "/outside_sprites.png";
	public static final String LEVEL_ONE_DATA = "/level_one_data_long.png";
//	public static final String LEVEL_ONE_DATA = "/level_one_data.png";
	
	//backgrounds
	public static final String MENU_BACKGROUND = "/menu_background.png";
	public static final String BACKGROUND_MENU = "/background_menu.png";
	public static final String PAUSE_BACKGROUND = "/pause_menu.png";
	
	//buttons
	public static final String MENU_BUTTONS = "/button_atlas.png";
	public static final String SOUND_BUTTONS = "/sound_button.png";
	public static final String URM_BUTTONS = "/urm_buttons.png";
	public static final String VOLUME_BUTTONS = "/volume_buttons.png";
	
	//enemies
	public static final String CRABBY_SPRITE = "/crabby_sprite.png";


	
	public static BufferedImage GetSpriteAtlas(String path) {
		
		BufferedImage img = null;
		InputStream is = LoadSave.class.getResourceAsStream(path);
		
		try {
			img = ImageIO.read(is);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return img;
	}
	
	public static ArrayList<Crabby> GetCrabs(){
		BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);
		ArrayList<Crabby> list = new ArrayList<Crabby>();
		
		for(int j = 0; j < img.getHeight();j++) {
			for(int i =0;i < img.getWidth();i++) {
				Color color = new Color(img.getRGB(i, j));
				//we have green value for crabbies
				int value = color.getGreen();
				//if value == 0
				if(value == CRABBY) 
					list.add(new Crabby(i*Game.TILES_SIZE, j*Game.TILES_SIZE));
			}
		}
		return list;
		
	}
	
	public static int[][] GetLevelData(){
		BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);
		int[][] lvlData = new int[img.getHeight()][img.getWidth()];

		for(int j = 0; j < img.getHeight();j++) {
			for(int i =0;i < img.getWidth();i++) {
				Color color = new Color(img.getRGB(i, j));
				//we have red value for sprite in level editing
				int value = color.getRed();
				if(value >= 48) value = 0;
				lvlData[j][i] = value;
			}
		}
		return lvlData;
	}

}
