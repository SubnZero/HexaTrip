package game;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Keyboard;
// GAMESTATE: GAME

import entities.Level;
import entities.Player;


public class Game {
	private static float translate_x = 0f;
	private static float speed = 2.5f;
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
	}
	
}
