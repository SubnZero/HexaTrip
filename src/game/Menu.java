package game;

import static org.lwjgl.opengl.GL11.*;
import static game.Boot.WINDOW_DIMENSION;

import game.Main.MenuState;

public class Menu {
	
	public static void draw(MenuState mState) {
		switch(mState) {
		case GAME_END:
			glPopMatrix();
			glMatrixMode(GL_PROJECTION);
			
			glRectd(50, 50, WINDOW_DIMENSION[0] -50, WINDOW_DIMENSION[1] -50);
			// TODO Put Game Over Screen here
			
			glMatrixMode(GL_MODELVIEW);
			glPushMatrix();
			break;
			
		case GAME_PAUSED:
			glPopMatrix();
			glMatrixMode(GL_PROJECTION);
			glColor4f(1f, 1f, 1f, 0.8f);
			glRectd(50, 50, WINDOW_DIMENSION[0] -50, WINDOW_DIMENSION[1] -50);
			glColor4f(1f, 1f, 1f, 1f);
			// TODO Put Game Paused Screen here
			
			glMatrixMode(GL_MODELVIEW);
			glPushMatrix();
			break;
			
		case GAME_START:
			glPopMatrix();
			glMatrixMode(GL_PROJECTION);
			
			glRectd(50, 50, WINDOW_DIMENSION[0] -50, WINDOW_DIMENSION[1] -50);
			// TODO Put Game Start Screen here
			
			glMatrixMode(GL_MODELVIEW);
			glPushMatrix();
			break;
			
		case MAIN_MENU:
			glPopMatrix();
			glMatrixMode(GL_PROJECTION);
			
			glRectd(50, 50, WINDOW_DIMENSION[0] -50, WINDOW_DIMENSION[1] -50);
			// TODO Put Main Menu Screen here
			
			glMatrixMode(GL_MODELVIEW);
			glPushMatrix();
			break;
			
		case NONE:
			break;		
		}
		
	}
	
}
