package game;

/*	
 *   Gamestate Game Class
 */

import static org.lwjgl.opengl.GL11.*;
import static game.Main.MenuState.*;
import static game.Main.mState;
import static game.Boot.WINDOW_DIMENSION;

import java.util.HashMap;
import java.util.Map;

import org.lwjgl.input.Keyboard;

import entities.Level;
import entities.Player;
import entities.Sprite;

public class Game {
	private static double translate_x = 0f;
	private static double speed = 0.16f;
	static int spritesheet;
	static Map<String, Sprite> spriteMap = new HashMap<String, Sprite>();
	static Sprite currentSprite;
	static Level level;
	static Player PLAYER1;
	
	static void logic(double delta) {
		// TODO put Game Logic Stuff here
	}
	
	static void input(double delta) {
		if(mState == NONE) {
			
			if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
				
				if( WINDOW_DIMENSION[0] / 2 < PLAYER1.getX() &&  PLAYER1.getX() < level.getHighestX() - WINDOW_DIMENSION[0] / 2) 
					PLAYER1.setCenter(true);
				else
					PLAYER1.setCenter(false);
				
				if (!PLAYER1.inCenter()) {						// move Player
					double xold = PLAYER1.getX();				
					PLAYER1.setX( xold + speed * delta );
				} 
				else if (PLAYER1.inCenter()){					// move BG but still change PlayerX
					double xold = PLAYER1.getX();
					PLAYER1.setX( xold + speed * delta );
					translate_x -= speed * delta;
				}
				
				if ( PLAYER1.getX() > level.getHighestX() -32 && !PLAYER1.inCenter()){	// level width limit: highestX - 32
					PLAYER1.setX( level.getHighestX() -32 );
				}
				
			} 
			else if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {		
				
				if( WINDOW_DIMENSION[0] / 2 < PLAYER1.getX() &&  PLAYER1.getX() < level.getHighestX() - WINDOW_DIMENSION[0] / 2) 
					PLAYER1.setCenter(true);
				else
					PLAYER1.setCenter(false);
				
				if (!PLAYER1.inCenter()) {
					double xold = PLAYER1.getX();
					PLAYER1.setX( xold - speed * delta );
				} 
				else if (PLAYER1.inCenter()){
					double xold = PLAYER1.getX();
					PLAYER1.setX( xold - speed * delta );
					translate_x += speed * delta;
				}
				
				if ( PLAYER1.getX() < 32 && !PLAYER1.inCenter()){	// left width limit: 0
					PLAYER1.setX( 32 );
				}
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
		
		glTranslated(translate_x, 0, 0);
		level.draw();
		PLAYER1.draw(spriteMap);
		
	}
	
}
