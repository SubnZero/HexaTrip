package entities;

/*	
 *   Simple Checkpoint Entitiy
 */

public interface Checkpoint {
	public void draw();
	public void update( double delta );
	
	public void setLocation( double x, double y );
	public void setX( double x );
	public void setY( double y );
	
	public double getX();
	public double getY();
}
