package entities;

/*	
 *   Checkpoint Class
 */

public class AbstractCheckpoint implements Checkpoint {
	protected double x, y;
	
	public AbstractCheckpoint( double x, double y ) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void draw() {
		//TODO: Draw Checkpoint
	}

	@Override
	public void update(double delta) {}

	@Override
	public void setLocation(double x, double y) {
		this.x = x;
		
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
	public double getX() {
		return this.x;
	}

	@Override
	public double getY() {
		return this.y;
	}

}
