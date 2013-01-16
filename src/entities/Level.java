package entities;

/*	
 *   Level Entitiy
 */

public interface Level {
	public void draw();
	public void load();
	
	public String getTitle();
	public String getAuthor();
	public String getVersion();
	
	public int getHexagonAmount();
	public int getCheckpointAmount();
	public double getGravity();
	
	public double getXStart();
	public double getYStart();
	public double getXFinish();
	public double getYFinish();
}
