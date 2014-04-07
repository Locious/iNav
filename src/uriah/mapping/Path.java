package uriah.mapping;

class Path {
	public final PointOfInterest destination;
	public final double distance;
	public String direction;

	public Path(PointOfInterest destination, double distance, String direction) {
		this.destination = destination;
		this.distance = distance;
		this.direction = direction;
	}
	
}
