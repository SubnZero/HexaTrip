package game;

/*	
 *   Booting the Game
 *   Initializing Display, OGL, AGL, Timer
 */

import static org.lwjgl.openal.AL10.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.ARBTextureRectangle.GL_TEXTURE_RECTANGLE_ARB;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.lwjgl.*;
import org.lwjgl.openal.AL;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.WaveData;

import entities.AbstractLevel;
import entities.AbstractPlayer;
import entities.Sprite;
import utilities.ImagingTools;


public class Boot {
	private static final String WINDOW_TITLE = ". H e x a T r i p .";
	private static final String SPRITESHEET_IMAGE_LOCATION = "res/spritesheet.png";
	private static final String SPRITESHEET_XML_LOCATION = "res/spritesheet.xml";
	static final int[] WINDOW_DIMENSION = { 640, 480 };
	
	private static long lastFrame;
	static int albGameStart, alsGameStart;
	
	static void setUpDisplay() {
		try {
			Display.setDisplayMode(new DisplayMode(WINDOW_DIMENSION[0], WINDOW_DIMENSION[1]));
			Display.setTitle(WINDOW_TITLE);
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			cleanUp();
		}
	}
	
	static void setUpOGL() {
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, WINDOW_DIMENSION[0], 0, WINDOW_DIMENSION[1], -1, 1);
		glMatrixMode(GL_MODELVIEW);
	}
	
	static void setUpAGL() {
		try {
			AL.create();
			WaveData data;
			data = WaveData.create(new BufferedInputStream(new FileInputStream("res/Andrey_Avkhimovich_-_Press_Start.wav")));
			albGameStart = alGenBuffers();
			alBufferData(albGameStart, data.format, data.data, data.samplerate);
			data.dispose();
			alsGameStart = alGenSources();
			alSourcei(alsGameStart, AL_BUFFER, albGameStart);
			alSourcei(alsGameStart, AL_LOOPING, AL_TRUE);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			cleanUp();
		} catch (LWJGLException e) {
			e.printStackTrace();
			cleanUp();
		}
		alSourcePlay(alsGameStart);
	}
	
	static void setUpLevel() {
		Game.level = new AbstractLevel("res/level1.xml");	// TODO: loadLevel in MainMenu
		Game.level.load();
		Game.PLAYER1 = new AbstractPlayer("Jason", Game.level, Game.spritesheet);
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
	
	public static void cleanUp() {
		glDeleteTextures(Game.spritesheet);
		alDeleteBuffers(albGameStart);
        alDeleteSources(alsGameStart);
        Display.destroy();
        AL.destroy();
	}
	
	 static void setUpSpriteSheets() {
		 Game.spritesheet = ImagingTools.glLoadLinearPNG(SPRITESHEET_IMAGE_LOCATION);
		 SAXBuilder builder = new SAXBuilder();
		 try {
			 Document document = builder.build(new File(SPRITESHEET_XML_LOCATION));
			 Element root = document.getRootElement();
			 for (Object spriteObject : root.getChildren()) {
				 Element spriteElement = (Element) spriteObject;
				 String name = spriteElement.getAttributeValue("n");
				 int x = spriteElement.getAttribute("x").getIntValue();
				 int y = spriteElement.getAttribute("y").getIntValue();
				 int w = spriteElement.getAttribute("w").getIntValue();
				 int h = spriteElement.getAttribute("h").getIntValue();
				 Sprite sprite = new Sprite(name, x, y, w, h);
				 Game.spriteMap.put(sprite.getName(), sprite);
			 }
		 } catch (JDOMException e) {
			 e.printStackTrace();
			 cleanUp();
		 } catch (IOException e) {
			 System.err.println("ERROR! DATA NOT FOUND");
			 e.printStackTrace();
			 cleanUp();
		 }
	 }
	 
	 static void setUpStates() {
			glEnable(GL_TEXTURE_RECTANGLE_ARB);
			glEnable(GL_CULL_FACE);
			glEnable(GL_BLEND);
			glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
			glCullFace(GL_BACK);
			Game.currentSprite = Game.spriteMap.get("air");
	 }

}
