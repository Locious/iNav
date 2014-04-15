package uriah.mapping;

class Path {
	public final PointOfInterest destination;
	public final double distance;
	public int direction;

	public Path(PointOfInterest destination, double distance, int direction) {
		this.destination = destination;
		this.distance = distance;
		this.direction = direction;
	}
	
}
