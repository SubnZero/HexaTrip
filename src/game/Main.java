package game;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.Display;

/**
 * H e x a T r i p
 * ===============
 * 
 * @author Jason Schühlein
 * @version INDEV 0.2 15/1/2013
 *
 */

public class Main {
	
	private static enum GameState {
		INTRO, MAIN, GAME, GAMEOVER, END;
	}
	
	private GameState State = GameState.GAME;	// TODO: Start at INTRO
	
	
	public Main() {
		Boot.setUpDisplay();
		Boot.setUpOGL();
		Boot.setUpAGL();
		Boot.setUpEntities();
		Boot.setUpLevel(); //TODO: loadLevel in MainMenu
		Boot.setUpTimer();
		
		while(!Display.isCloseRequested()) {
			glClear(GL_COLOR_BUFFER_BIT);
			double delta = Boot.getDelta();
			
			glPushMatrix();
			
			switch(State) {
			case INTRO:
				break;
				
			case MAIN:
				break;
				
			case GAME:
				Game.logic(delta);
				Game.input();
				Game.draw();
				break;
				
			case GAMEOVER:
				break;
				
			case END:
				break;
			}
				
			glPopMatrix();
			
			Display.update();
			Display.sync(60);
		}
		Boot.cleanUp();
		System.exit(0);
	}

	public static void main(String[] args) {
		new Main();
	}

}
