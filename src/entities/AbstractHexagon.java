package entities;

/*	
 *   Hexagon Class
 */

import static org.lwjgl.opengl.GL11.*;

public class AbstractHexagon implements Hexagon {
	protected double x, y, width, height;
	protected HexagonType type;

	public AbstractHexagon( double x, double y, double width, double height, HexagonType type ) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.type = type;
	}

	@Override
	public void draw() {
		glBegin(GL_LINE_LOOP);
		
		switch(this.type) {
		case HEX_DEFAULT:
			glVertex2d(this.x, this.y);
			glVertex2d(this.x + 30, this.y - 0.5 * this.height);
			glVertex2d(this.x + this.width - 30, this.y - 0.5 * this.height);
			glVertex2d(this.x + this.width, this.y);
			glVertex2d(this.x + this.width - 30, this.y + 0.5 * this.height);
			glVertex2d(this.x + 30, this.y + 0.5 * this.height);
			break;
			
		case HEX_RAMP:
			glVertex2d(this.x, this.y - 0.25 * this.height);
			glVertex2d(this.x + 30, this.y - 0.5 * this.height);
			glVertex2d(this.x + this.width - 30, this.y - 0.5 * this.height);
			glVertex2d(this.x + this.width, this.y + 0.25 * this.height);
			glVertex2d(this.x + this.width - 30, this.y + 0.5 * this.height);
			glVertex2d(this.x + 30, this.y + 0.5 * this.height);
		case HEX_NONE:
			break;
		default:
			System.err.println("invalid HexagonType in AbstractHexagon.draw()");
			System.exit(0);
			break;
		}
		
		glEnd();
	}

	@Override
	public void update(double delta) {}

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
	public void setY(double y) {
		this.y = y;
	}

	@Override
	public void setWidth(double width) {
		this.width = width;
	}

	@Override
	public void setHeight(double height) {
		this.height = height;
	}

	@Override
	public double getX() {
		return this.x;
	}

	@Override
	public double getY() {
		return this.y;
	}

	@Override
	public double getWidth() {
		return this.width;
	}

	@Override
	public double getHeight() {
		return this.height;
	}
	
	public HexagonType getType() {
		return this.type;
	}

}
