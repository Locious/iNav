package uriah.mapping;

class Path {
	public final PointOfInterest destination;
	public final double distance;
	public double direction;

	public Path(PointOfInterest destination, double distance, double direction) {
		this.destination = destination;
		this.distance = distance;
		this.direction = direction;
	}
	
}
