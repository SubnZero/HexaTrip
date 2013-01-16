package entities;

import java.util.Map;

/*	
 *   Simple Player Entitiy
 */

public interface Player {
	public void draw(Map<String, Sprite> spriteMap);
	public void update( double delta );
	
	public void setLocation( double x, double y );
	public void setX( double x );
	public void setDX( double dx );
	public void setY( double y );
	public void setDY( double dy );
	
	public double getX();
	public double getDX();
	public double getY();
	public double getDY();
	public String getName();
	
	public boolean hasFinished(Level level);
}
