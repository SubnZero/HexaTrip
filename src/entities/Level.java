package entities;

public interface Level {
	public void draw();
	public void load();
	
	public String getTitle();
	public String getAuthor();
	public String getVersion();
	
	public double getWidth();
	public double getHeight();
	
	public int getHexagonAmount();
	public int getCheckpointAmount();
	public double getGravity();
}
