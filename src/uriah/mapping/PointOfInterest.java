package uriah.mapping;

public class PointOfInterest implements Comparable<PointOfInterest> {
	
	public final String name;
	public Path[] adjacencies;
	public double minDistance = Double.POSITIVE_INFINITY;
	public PointOfInterest previous;

	public PointOfInterest(String argName) {
		this.name = argName;
	}

	public String toString() {
		return name;
	}

	public int compareTo(PointOfInterest other) {
		return Double.compare(minDistance, other.minDistance);
	}
}
