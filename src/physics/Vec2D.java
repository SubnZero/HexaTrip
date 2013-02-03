package physics;

public class Vec2D {
	protected double x, y;
	
	public Vec2D( double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public void reset() {
		this.x = 0;
		this.y = 0;
	}
}
