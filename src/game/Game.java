package game;

/*	
 *   Gamestate Game Class
 */

import static org.lwjgl.opengl.GL11.*;
import static game.Main.MenuState.*;
import static game.Main.mState;

import java.util.HashMap;
import java.util.Map;

import org.lwjgl.input.Keyboard;

import entities.Level;
import entities.Player;
import entities.Sprite;

public class Game {
	private static float translate_x = 0f;
	private static float speed = 2.5f;
	static int spritesheet;
	static Map<String, Sprite> spriteMap = new HashMap<String, Sprite>();
	static Sprite currentSprite;
	static Level level;
	static Player PLAYER1;
	
	static void logic(double delta) {
		// TODO put Game Logic Stuff here
	}
	
	static void input() {
		if(mState == NONE) {
			if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
				if(((float)level.getHighestX() - 0.5 * (float) Boot.WINDOW_DIMENSION[0]) + (float) translate_x <= 0)
					translate_x = (float) -( (float) level.getHighestX() - 0.5 * (float) Boot.WINDOW_DIMENSION[0]);
				else
					translate_x -= speed;
			} 
			else if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {					
				if(translate_x + speed > 0)
					translate_x = 0;
				else
					translate_x += speed;
			}
		}
		
		while(Keyboard.next()) {
			if(Keyboard.getEventKeyState()) {
				switch(Keyboard.getEventKey()) {
				case Keyboard.KEY_ESCAPE:
					if(mState == NONE)
						mState = GAME_PAUSED;
					else
						mState = NONE;
				}
			}
		}
	}
	
	static void draw() {
		
		glTranslatef(translate_x, 0, 0);
		level.draw();
		PLAYER1.draw(spriteMap);
		
	}
	
}
