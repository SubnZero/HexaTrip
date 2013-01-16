package game;

/*	
 *   Gamestate Game Class
 */

import static org.lwjgl.opengl.GL11.*;

import java.util.HashMap;
import java.util.Map;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.opengl.Texture;

import entities.Level;
import entities.Player;
import entities.Sprite;


public class Game {
	private static float translate_x = 0f;
	private static float speed = 2.5f;
	public static Texture texPlayer;
	static int spritesheet;
	static Map<String, Sprite> spriteMap = new HashMap<String, Sprite>();
	static Sprite currentSprite;
	static Level level;
	static Player PLAYER1;
	
	static void logic(double delta) {
		
	}
	
	static void input() {
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
			translate_x -= speed;
		} 
		else if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {					
			if(translate_x + speed > 0)
				translate_x = 0;
			else
				translate_x += speed;
		}
		
		while(Keyboard.next()) {
			if(Keyboard.getEventKeyState()) {
				switch(Keyboard.getEventKey()) {
				case Keyboard.KEY_ESCAPE:
					translate_x = 0;
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
