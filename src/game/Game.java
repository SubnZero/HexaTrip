package game;

import static org.lwjgl.opengl.GL11.*;


import org.lwjgl.input.Keyboard;

// GAMESTATE: GAME

public class Game {
	private static float translate_x = 0f;
	private static float speed = 2.5f;
	
	static void logic(double delta) {
		
	}
	
	static void input() {
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
			translate_x -= speed;
		} else if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
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
		
		glBegin(GL_QUADS);
		glVertex2i(400, 400); // Upper-left
		glVertex2i(450, 400); // Upper-right
		glVertex2i(450, 450); // Bottom-right
		glVertex2i(400, 450); // Bottom-left
		glEnd();
	}
	
}
