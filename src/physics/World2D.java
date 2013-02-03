package physics;

import java.util.HashMap;
import java.util.Map;

public class World2D {
	protected double gravity = 9.81;		// 9.81 m/s²
	protected double resolution = 64;		// 64px = 1m
	protected double pobj_ds[] = {0, 0};
	protected Vec2D vecRes;
	protected Map<String, PObject2D> pobjList = new HashMap<String, PObject2D>();

	public World2D(double gravity, double resolution) {
		this.gravity = gravity;
		this.resolution = resolution;
	}
	
	public void addPObj( PObject2D pobj, String name ) {
		pobjList.put(name, pobj);
	}

	public void update( double delta ) {
		delta /= 1000;		// = delta in seconds
		for ( PObject2D pobj : pobjList.values() ) {
			vecRes = pobj.getVecRes();
			
			pobj.da[0] = vecRes.x / pobj.mass;
			pobj.da[1] = (vecRes.y - gravity) / pobj.mass;

			pobj.a[0] += pobj.da[0];
			pobj.a[1] += pobj.da[1];

			pobj.dv[0] = pobj.a[0] * delta;
			pobj.dv[1] = pobj.a[1] * delta;

			pobj.v[0] += pobj.dv[0];
			pobj.v[1] += pobj.dv[1];

			pobj_ds[0] = pobj.v[0] * delta;
			pobj_ds[1] = pobj.v[1] * delta;

			pobj.pos[0] += pobj_ds[0] * resolution;
			pobj.pos[1] += pobj_ds[1] * resolution;
			
		}
	}
}
