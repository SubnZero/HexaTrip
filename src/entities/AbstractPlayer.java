package entities;

public class AbstractPlayer implements Player {
	protected String name;
	protected double x , y, dx, dy;

	public AbstractPlayer(String name, double x, double y) {
		this.name = name;
		this.x = x;
		this.y = y;
		this.dx = 0;
		this.dy = 0;
	}

	@Override
	public void draw() {
		// TODO Draw Player
	}

	@Override
	public void update(double delta) {
		this.x += this.dx * delta;
		this.y += this.dy * delta;
	}

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
	public void setDX(double dx) {
		this.dx = dx;
	}

	@Override
	public void setY(double y) {
		this.y = y;
	}

	@Override
	public void setDY(double dy) {
		this.dy = dy;
	}

	@Override
	public double getX() {
		return this.x;
	}

	@Override
	public double getDX() {
		return this.dx;
	}

	@Override
	public double getY() {
		return this.y;
	}

	@Override
	public double getDY() {
		return this.dy;
	}

	@Override
	public boolean hasFinished(Level level) {
		if(level.getXFinish() >= this.x && level.getYFinish()+10 >= this.y && level.getYFinish()-10 <= this.y)
			return true;
		else
			return false;
	}

}
