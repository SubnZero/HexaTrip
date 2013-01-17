package game;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.Display;

/**
 * H e x a T r i p
 * ===============
 * 
 * @author Jason Schühlein
 * @version INDEV 0.3 16.1.2013
 *
 */

public class Main {
	
	public static enum GameState {
		INTRO, MAIN, GAME, END;
	}
	
	public static enum MenuState {
		NONE, MAIN_MENU, GAME_START, GAME_PAUSED, GAME_END;
	}
	
	private static GameState gState = GameState.GAME;	//TODO Start at INTRO
	static MenuState mState = MenuState.NONE;
	
	public Main() {
		Boot.setUpDisplay();
		Boot.setUpOGL();
		Boot.setUpAGL();
		Boot.setUpSpriteSheets();
		Boot.setUpStates();
		Boot.setUpLevel(); //TODO loadLevel in MainMenu
		Boot.setUpTimer();
		
		while(!Display.isCloseRequested()) {
			glClear(GL_COLOR_BUFFER_BIT);
			double delta = Boot.getDelta();
			
			glPushMatrix();
			
			switch(gState) {
			case INTRO:
				break;
				
			case MAIN:
				break;
				
			case GAME:
				Game.input();
				Game.logic(delta);
				Game.draw();
				Menu.draw(mState);
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
