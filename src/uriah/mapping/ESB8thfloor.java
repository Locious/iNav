package uriah.mapping;

import java.util.List;

// map
// class.800...class.801
// .....|......|
// .....v......v
// 5--------2--------4
// | .......|....... |
// |....... |....... |
// | .......1....... |<-class 802
// | .......|....... |
// | .......|....... |
// 7--------3--------6
// ............^
// ............|
// .......class 803
//
public class ESB8thfloor {

	Dijkstra di = new Dijkstra();

	PointOfInterest elevators = new PointOfInterest("elevators");
	PointOfInterest class0 = new PointOfInterest("800");
	PointOfInterest class1 = new PointOfInterest("801");
	PointOfInterest class2 = new PointOfInterest("802");
	PointOfInterest class3 = new PointOfInterest("803");
	PointOfInterest i2 = new PointOfInterest("intersection 2");
	PointOfInterest i3 = new PointOfInterest("intersection 3");
	PointOfInterest i4 = new PointOfInterest("intersection 4");
	PointOfInterest i5 = new PointOfInterest("intersection 5");
	PointOfInterest i6 = new PointOfInterest("intersection 6");
	PointOfInterest i7 = new PointOfInterest("intersection 7");

	PointOfInterest[] vertices = { elevators, class0, class1, class2, class3,
			i2, i3, i4, i5, i6, i7 };
	List<PointOfInterest> path = null;

	PointOfInterest start = null;
	PointOfInterest end = null;

	public ESB8thfloor() {

		elevators.adjacencies = new Path[] { new Path(i2, 2, "North"),
				new Path(i3, 2, "South") };
		class0.adjacencies = new Path[] { new Path(i2, 3, "East"),
				new Path(i5, 5, "West") };
		class1.adjacencies = new Path[] { new Path(i2, 3, "West"),
				new Path(i4, 6, "East") };
		class2.adjacencies = new Path[] { new Path(i4, 3, "North"),
				new Path(i6, 3, "South") };
		class3.adjacencies = new Path[] { new Path(i3, 3, "East"),
				new Path(i6, 6, "West") };
		i2.adjacencies = new Path[] { new Path(class0, 3, "West"),
				new Path(class1, 3, "East"), new Path(elevators, 3, "South") };
		i3.adjacencies = new Path[] { new Path(class3, 3, "East"),
				new Path(i7, 8, "West"), new Path(elevators, 2, "North") };
		i4.adjacencies = new Path[] { new Path(class1, 6, "West"),
				new Path(class2, 3, "South") };
		i5.adjacencies = new Path[] { new Path(class0, 5, "East"),
				new Path(i7, 5, "South") };
		i6.adjacencies = new Path[] { new Path(class2, 3, "North"),
				new Path(class3, 6, "West") };
		i7.adjacencies = new Path[] { new Path(i5, 5, "North"),
				new Path(i3, 8, "East") };

	}

	public String getDirections(String s, String e) {

		for (PointOfInterest v : vertices) {
			if (v.toString().equals(s)) {
				this.start = v;
			}
			if (v.toString().equals(e)) {
				this.end = v;
			}
		}
		di.setStart(start);
		this.path = di.findEnd(end);
		String r1 = "Distance to " + end + ": " + end.minDistance + "\n";

		String r2 = "Path: " + path + " distance" + end.minDistance + "\n";
		
		
		displayPath();
		return r1 + r2;

	}

	public String getPath(PointOfInterest start, PointOfInterest end) {

		for (int i = 0;; i++) {
			if (start.adjacencies[i].destination == end) {
				return "go " + start.adjacencies[i].distance + " feet "
						+ start.adjacencies[i].direction;
			}
		}

	}

	public String displayPath() {
		this.path = di.findEnd(end);
		String rValue = "";
		for (int i = 1; i < path.size(); i++) {
			System.out.println(getPath(path.get(i-1), path.get(i)));

		}
		return rValue;

	}

}
