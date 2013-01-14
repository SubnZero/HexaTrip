package game;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Keyboard;
// GAMESTATE: GAME

import entities.Level;


public class Game {
	private static float translate_x = 0f;
	private static float speed = 2.5f;
	static Level level;
	
	static void logic(double delta) {
		
	}
	
	static void input() {
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {			// TODO: Level width limitation
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
		
		Game.level.draw();
	}
	
}
