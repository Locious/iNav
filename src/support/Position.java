package support;


public class Position {

	private float x;
	private float y;
	private float z;
	
	public float getX() { return x; }
	public float getY() { return y; }
	public float getZ() { return z; }
	
	public Position(float x, float y,float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public void set(float newx, float newy,float newz) {
		x = newx;
		y = newy;
		z = newz;
	}
	
	public void offset(float dx, float dy,float dz) {
		x += dx;
		y += dy;
		z += dz;
	}
	
	public float distance() {
		return (float)Math.sqrt(Math.pow(x, 2) + Math.pow(y,2));
	}
	
	public float distanceFrom(Position other) {
		return (float)Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2));
	}
}
