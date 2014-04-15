package support;


public class Position {

	private float x;
	private float y;
	private float z;
	private float azimuth;
	
	public float getX() { return x; }
	public float getY() { return y; }
	public float getZ() { return z; }
	public float getAzimuth() { return azimuth; }
	
	public Position(float x, float y,float z,float azimuth) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.azimuth = azimuth;
	}
	
	public void set(float newx, float newy,float newz, float newAzimuth) {
		x = newx;
		y = newy;
		z = newz;
		azimuth = newAzimuth;
	}
	
	public void offset(float dx, float dy,float dz,float dAzimuth) {
		x += dx;
		y += dy;
		z += dz;
		azimuth -= dAzimuth;
	}
	
	public float distance() {
		return (float)Math.sqrt(Math.pow(x, 2) + Math.pow(y,2));
	}
	
	public float distanceFrom(Position other) {
		return (float)Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2));
	}
}
