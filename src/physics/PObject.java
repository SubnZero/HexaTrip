package physics;

import java.util.List;
import java.util.Map;

public class PObject {
	protected Vec2 VecRes;
	protected double x, y;
	protected List<Vec2> VecList;
	
	public PObject(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void update(double delta) {
		this.x += delta * VecRes.x;
		this.y += delta * VecRes.y;
	}

	public void addVec(Vec2 vec2) {
		VecList.add(vec2);
	}

	public Vec2 getVecRes() {
		VecRes.reset();
		for (Vec2 vec : VecList) {
			VecRes.x += vec.x;
			VecRes.x += vec.y;
		}
		return VecRes;
	}

}
