package entities;

import java.util.Map;

/*	
 *   Simple Player Entitiy
 */

public interface Player {
	public void draw(Map<String, Sprite> spriteMap);
	
	public void setLocation( double x, double y );
	public void setX( double x );
	public void setY( double y );
	
	public double getX();
	public double getY();
	public String getName();
	
	public boolean hasFinished(Level level);

	public void setCenter(boolean inCenter);
	public boolean inCenter();
}
