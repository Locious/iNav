package uriah.mapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra {

	public Dijkstra() {
		// empty constructor
	}

	public void setStart(PointOfInterest start) {
		start.minDistance = 0;
		PriorityQueue<PointOfInterest> vertexQueue = new PriorityQueue<PointOfInterest>();
		vertexQueue.add(start);

		while (!vertexQueue.isEmpty()) {
			PointOfInterest poi = vertexQueue.poll();

			for (Path p : poi.adjacencies) {
				PointOfInterest v = p.destination;
				double weight = p.distance;
				double distanceThroughU = poi.minDistance + weight;
				if (distanceThroughU < v.minDistance) {
					vertexQueue.remove(v);
					v.minDistance = distanceThroughU;
					v.previous = poi;
					vertexQueue.add(v);
				}
			}
		}
	}

	public List<PointOfInterest> findEnd(PointOfInterest target) {
		List<PointOfInterest> path = new ArrayList<PointOfInterest>();
		for (PointOfInterest vertex = target; vertex != null; vertex = vertex.previous) {
			path.add(vertex);
		}
		Collections.reverse(path);
		return path;
	}

}