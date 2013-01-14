package game;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.ARBTextureRectangle.GL_TEXTURE_RECTANGLE_ARB;

import org.lwjgl.*;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import entities.AbstractLevel;

public class Boot {

	private static final int[] WINDOW_DIMENSION = { 640, 480 };
	private static final String WINDOW_TITLE = ". H e x a T r i p .";
	private static long lastFrame;
	
	
	static void setUpDisplay() {
		try {
			Display.setDisplayMode(new DisplayMode(WINDOW_DIMENSION[0], WINDOW_DIMENSION[1]));
			Display.setTitle(WINDOW_TITLE);
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}
	
	static void setUpOGL() {
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, WINDOW_DIMENSION[0], 0, WINDOW_DIMENSION[1], -1, 1);  //just a mark
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_RECTANGLE_ARB);	// TODO: Add Spritesheet
	}
	
	static void setUpAGL() {
		// TODO: Init AGL Code
	}
	
	static void setUpEntities() {
		// TODO: Init Entities Code
	}
	
	static void setUpLevel() {
		Game.level = new AbstractLevel("res/level1.xml");	// TODO: loadLevel in MainMenu
		Game.level.load();
	}
	
	static long getTime() {
		return ( Sys.getTime() * 1000 ) / Sys.getTimerResolution();
	}
	
	static void setUpTimer() {
		lastFrame = getTime();
	}
	
	static double getDelta() {
		double delta = getTime() - lastFrame;
		lastFrame = getTime();
		return delta;
	}
	
	static void cleanUp() {
		Display.destroy();
	}

}
