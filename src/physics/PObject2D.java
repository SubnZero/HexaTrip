package physics;

import java.util.ArrayList;
import java.util.List;

public class PObject2D {
	double mass = 0;
	double v[] = {0, 0};
	double dv[] = {0, 0};
	double a[] = {0, 0};
	double da[] = {0, 0};
	protected double pos[] = {0, 0};
	protected long timetriggered = 0;
	protected Vec2D VecRes = new Vec2D(0,0);
	protected List<Vec2D> vecList = new ArrayList<Vec2D>();
	protected List<TempVec2D> tempVecList = new ArrayList<TempVec2D>();
	
	public PObject2D(double x, double y, double mass) {
		this.pos[0] = x;
		this.pos[1] = y;
		this.mass = mass;
	}

	public void addVec(Vec2D vec) {
		vecList.add(vec);
	}
	
	public void addTempVec( TempVec2D tempvec) {
		tempVecList.add(tempvec);
	}
	
	public Vec2D getVecRes() {
		VecRes.reset();
		
		for (Vec2D vec : vecList) {
			VecRes.x += vec.x;
			VecRes.y += vec.y;
		}
		
		for (TempVec2D tempvec : tempVecList) {
			double tempvecX = tempvec.getX();
			double tempvecY = tempvec.getY();
			
			if (tempvecX == 0 && tempvecY == 0) {
				tempVecList.remove(tempvec);
			} 
			else {
				VecRes.x += tempvecX;
				VecRes.y += tempvecY;
			}
		}
		
		return VecRes;
	}
}
