package physics;

import org.lwjgl.Sys;

public class TempVec2D {
	protected double x, y;
	protected double duration;
	protected long triggered;
	protected long time;
	
	public TempVec2D( double x, double y, long duration) {
		this.x = x;
		this.y = y;
		this.duration = duration;
		triggered = (( Sys.getTime() * 1000 ) / Sys.getTimerResolution());
	}
	
	public void reset() {
		this.x = 0;
		this.y = 0;
	}

	public double getX() {
		time = ( Sys.getTime() * 1000 ) / Sys.getTimerResolution();
		if ( time > triggered + duration )
			return 0;
		else
			return x;
	}

	public double getY() {
		time = ( Sys.getTime() * 1000 ) / Sys.getTimerResolution();
		if ( time > triggered + duration )
			return 0;
		else
			return y;
	}
	
}
