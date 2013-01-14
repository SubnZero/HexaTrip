package entities;

public interface Level {
	public void draw();
	public void load();
	
	public String getTitle();
	public String getAuthor();
	public String getVersion();
	
	public int getHexagonAmount();
	public int getCheckpointAmount();
	public double getGravity();
	public boolean isFinish();
}
