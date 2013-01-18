package entities;

/*	
 *   Player Class
 */

import static org.lwjgl.opengl.ARBTextureRectangle.GL_TEXTURE_RECTANGLE_ARB;
import static org.lwjgl.opengl.GL11.*;
import static game.Boot.cleanUp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class AbstractPlayer implements Player {
	protected String name;
	protected double x , y, dx, dy;
	protected int spritesheet;
	protected Texture texPlayer;
	

	public AbstractPlayer(String name, Level level, int spritesheet) {
		this.name = name;
		this.x = level.getXStart();
		this.y = level.getYStart();
		this.spritesheet = spritesheet;
		this.dx = 0;
		this.dy = 0;
		
		try {
			texPlayer = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/Player1.png")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			cleanUp();
		} catch (IOException e) {
			e.printStackTrace();
			cleanUp();
		}
	}

	@Override
	public void draw(Map<String, Sprite> spriteMap) {
		glBindTexture(GL_TEXTURE_RECTANGLE_ARB, spritesheet);
		
		Sprite currentSprite = spriteMap.get("player1");
		
		int x = currentSprite.getX();
        int y = currentSprite.getY();
        int x2 = currentSprite.getX() + currentSprite.getWidth();
        int y2 = currentSprite.getY() + currentSprite.getHeight();
		
		texPlayer.bind();
		
		glBegin(GL_QUADS);
			glTexCoord2f(x2,y2);
			glVertex2d(this.x - 32, this.y);
			glTexCoord2f(x,y2);
			glVertex2d(this.x + 32 , this.y);
			glTexCoord2f(x,y);
			glVertex2d(this.x + 32 , this.y + 128);
			glTexCoord2f(x2,y);

			glVertex2d(this.x - 32, this.y + 128);
		glEnd();
		
		
		glBindTexture(GL_TEXTURE_RECTANGLE_ARB, 0);
	}

	@Override
	public void update(double delta) {
		this.x += this.dx * delta;
		this.y += this.dy * delta;
	}

	@Override
	public void setLocation(double x, double y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void setX(double x) {
		this.x = x;
	}

	@Override
	public void setDX(double dx) {
		this.dx = dx;
	}

	@Override
	public void setY(double y) {
		this.y = y;
	}

	@Override
	public void setDY(double dy) {
		this.dy = dy;
	}

	@Override
	public double getX() {
		return this.x;
	}

	@Override
	public double getDX() {
		return this.dx;
	}

	@Override
	public double getY() {
		return this.y;
	}

	@Override
	public double getDY() {
		return this.dy;
	}

	@Override
	public boolean hasFinished(Level level) {
		if(level.getXFinish() >= this.x && level.getYFinish()+10 >= this.y && level.getYFinish()-10 <= this.y)
			return true;
		else
			return false;
	}

	@Override
	public String getName() {
		return this.name;
	}

}
