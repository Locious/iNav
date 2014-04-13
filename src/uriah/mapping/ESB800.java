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
public class ESB800 {

	Dijkstra di = new Dijkstra();

	PointOfInterest custodian = new PointOfInterest("custodian");
	PointOfInterest maintenance = new PointOfInterest("maintenance");

	PointOfInterest bathroom0 = new PointOfInterest("womans");
	PointOfInterest bathroom1 = new PointOfInterest("men");

	PointOfInterest stairs0 = new PointOfInterest("stairs");
	PointOfInterest Stairs1 = new PointOfInterest("stairs");

	PointOfInterest elevators0 = new PointOfInterest("elevator");
	PointOfInterest elevators1 = new PointOfInterest("elevator");
	PointOfInterest elevators2 = new PointOfInterest("elevator");
	PointOfInterest elevators3 = new PointOfInterest("elevator");
	PointOfInterest elevatorsMaintenance = new PointOfInterest(
			"elevator maintenance");

	// intersection
	PointOfInterest i0 = new PointOfInterest("intersection 0");
	PointOfInterest i1 = new PointOfInterest("intersection 1");
	PointOfInterest i2 = new PointOfInterest("intersection 2");
	PointOfInterest i3 = new PointOfInterest("intersection 3");
	PointOfInterest i4 = new PointOfInterest("intersection 4");
	PointOfInterest i5 = new PointOfInterest("intersection 5");

	// classrooms
	// 800
	PointOfInterest class801A = new PointOfInterest("801");
	PointOfInterest class801B = new PointOfInterest("801");

	// 810
	PointOfInterest class813 = new PointOfInterest("813");
	PointOfInterest class816 = new PointOfInterest("816");
	PointOfInterest class817 = new PointOfInterest("817");
	PointOfInterest class819 = new PointOfInterest("819");

	// 820
	PointOfInterest class821 = new PointOfInterest("821");
	PointOfInterest class823 = new PointOfInterest("823");
	PointOfInterest class825 = new PointOfInterest("825");
	PointOfInterest class827 = new PointOfInterest("827");
	PointOfInterest class829 = new PointOfInterest("829");

	// 830
	PointOfInterest class831 = new PointOfInterest("831");
	PointOfInterest class833 = new PointOfInterest("833");
	PointOfInterest class835 = new PointOfInterest("835");
	PointOfInterest class837 = new PointOfInterest("837");

	// 841
	PointOfInterest class841 = new PointOfInterest("841");
	PointOfInterest class841A = new PointOfInterest("841A");
	PointOfInterest class841B = new PointOfInterest("841B");
	PointOfInterest class841C = new PointOfInterest("841C");

	// 840
	PointOfInterest class848 = new PointOfInterest("848");
	PointOfInterest class849A = new PointOfInterest("849");
	PointOfInterest class849B = new PointOfInterest("849");

	// 850
	PointOfInterest class851 = new PointOfInterest("851");

	PointOfInterest[] vertices = { custodian, maintenance, bathroom0,
			bathroom1, stairs0, Stairs1, elevators0, elevators1, elevators2,
			elevators3, elevatorsMaintenance, i0, i1, i2, i3, i4, i5,
			class801A, class801B, class813, class816, class817, class819,
			class821, class823, class825, class827, class829, class831,
			class833, class835, class837, class841, class841A, class841B,
			class841C, class848, class849A, class849B, class851 };

	List<PointOfInterest> path = null;

	PointOfInterest start = null;
	PointOfInterest end = null;

	public ESB800() {

		custodian.adjacencies = new Path[] { new Path(i3, 5.7, 158),
				new Path(i4, 7.1, 338) };
		maintenance.adjacencies = new Path[] { new Path(i1, 9.2, 68),
				new Path(i2, 2.2, 248) };

		bathroom0.adjacencies = new Path[] { new Path(i3, 7.7, 158),
				new Path(i4, 5.4, 338) };
		bathroom1.adjacencies = new Path[] { new Path(i0, 7.6, 158),
				new Path(i5, 4.4, 338) };

		stairs0.adjacencies = new Path[] { new Path(i3, 9.7, 158),
				new Path(i4, 3.4, 338) };
		Stairs1.adjacencies = new Path[] { new Path(i0, 4.0, 68),
				new Path(i3, 10.5, 248) };

		elevators0.adjacencies = new Path[] { new Path(i0, 8.5, 68),
				new Path(i3, 6.0, 248) };
		elevators1.adjacencies = new Path[] { new Path(i0, 11.2, 68),
				new Path(i3, 3.4, 248) };
		elevators2.adjacencies = new Path[] { new Path(i0, 8.5, 68),
				new Path(i3, 6.0, 248) };
		elevators3.adjacencies = new Path[] { new Path(i0, 11.2, 68),
				new Path(i3, 3.4, 248) };
		elevatorsMaintenance.adjacencies = new Path[] { new Path(i2, 4.3, 158),
				new Path(i3, 7.1, 338) };

		i0.adjacencies = new Path[] {

		new Path(i3, 14.3, 248),

		new Path(Stairs1, 3.9, 248), new Path(elevators0, 8.4, 248),
				new Path(elevators3, 8.4, 248),
				new Path(elevators2, 11.1, 248),
				new Path(elevators1, 11.1, 248),

				new Path(i1, 8.9, 158),

				new Path(class801A, 1, 158), new Path(class801B, 4.3, 158),
				new Path(class813, 8.9, 158), new Path(i5, 12.4, 338),
				new Path(class851, 7.3, 338), new Path(bathroom1, 7.3, 338),
				new Path(class848, 8.4, 338), new Path(class849B, 12.4, 338)

		};
		i1.adjacencies = new Path[] {

		new Path(i0, 8.9, 338),

		new Path(class813, 1, 338), new Path(class801B, 4.6, 338),
				new Path(class801A, 8.9, 338),

				new Path(i2, 11.2, 248),

				new Path(class817, 5, 248), new Path(class819, 6.6, 248),
				new Path(class816, 6.6, 248), new Path(maintenance, 9.1, 248),
				new Path(class821, 9.8, 248), new Path(class823, 11.2, 248)

		};
		i2.adjacencies = new Path[] {

		new Path(i1, 11.2, 68),

		new Path(class823, 1, 68), new Path(class821, 2.1, 68),
				new Path(maintenance, 4.55, 68), new Path(class816, 4.6, 68),
				new Path(class819, 4.6, 68), new Path(class817, 6.2, 68),

				new Path(i3, 11.2, 338),

				new Path(class823, 1, 338), new Path(class825, .6, 338),
				new Path(class827, 4.19, 338), new Path(class829, 6.69, 338),

		};
		i3.adjacencies = new Path[] { new Path(i0, 14.3, 68),
				new Path(elevators2, 3.3, 68), new Path(elevators1, 3.3, 68),
				new Path(elevators0, 5.9, 68), new Path(elevators3, 5.9, 68),
				new Path(Stairs1, 10.4, 68),

				new Path(i2, 12.8, 158),

				new Path(class829, 4.5, 158), new Path(class827, 7, 158),
				new Path(class825, 10.66, 158),

				new Path(i4, 12.8, 338),

				new Path(class831, 11.2, 338), new Path(class833, 10.3, 338),
				new Path(custodian, 7.1, 338), new Path(bathroom0, 7.4, 338),
				new Path(class835, 7.4, 338), new Path(class837, 8.9, 338),
				new Path(stairs0, 9.57, 338) };
		i4.adjacencies = new Path[] {

		new Path(i3, 12.8, 158),

		new Path(class841, 1, 158), new Path(stairs0, 3.3, 158),
				new Path(class837, 3.9, 158), new Path(class835, 5.25, 158),
				new Path(bathroom0, 5.25, 158), new Path(custodian, 7.1, 158),
				new Path(class833, 10.3, 158), new Path(class831, 11.2, 158),

				new Path(i5, 12.6, 68),

				new Path(class841, 1, 68), new Path(class849A, 4.11, 68),
				new Path(class849B, 12.6, 68)

		};
		i5.adjacencies = new Path[] {

		new Path(i4, 12.6, 248),

		new Path(class849B, 1, 248), new Path(class849A, 8.46, 248),
				new Path(class841, 12.6, 248),

				new Path(i0, 12.4, 158),

				new Path(class849B, 1, 158), new Path(class848, 4, 158),
				new Path(bathroom1, 4.3, 158), new Path(class851, 4.3, 158),
				new Path(class801A, 12.4, 158)

		};

		class801A.adjacencies = new Path[] { new Path(i0, 1, 248), };
		class801B.adjacencies = new Path[] { new Path(i0, 4.4, 338),
				new Path(i1, 4.6, 158) };
		class813.adjacencies = new Path[] { new Path(i2, 1, 248), };
		class816.adjacencies = new Path[] { new Path(i1, 6.7, 68),
				new Path(i2, 4.6, 248) };
		class817.adjacencies = new Path[] { new Path(i1, 5.1, 68),
				new Path(i2, 6.2, 248) };
		class819.adjacencies = new Path[] { new Path(i1, 6.7, 68),
				new Path(i2, 4.6, 248) };
		class821.adjacencies = new Path[] { new Path(i1, 9.8, 68),
				new Path(i2, 1.4, 248) };
		class823.adjacencies = new Path[] { new Path(i2, 1.1, 338) };
		class825.adjacencies = new Path[] { new Path(i2, .6, 158),
				new Path(i3, 10.7, 338) };
		class827.adjacencies = new Path[] { new Path(i2, 4.2, 158),
				new Path(i3, 7.1, 338) };

		class829.adjacencies = new Path[] { new Path(i2, 6.7, 158),
				new Path(i3, 4.6, 338) };

		class831.adjacencies = new Path[] { new Path(i3, 1.7, 158),
				new Path(i4, 11.2, 338) };
		class833.adjacencies = new Path[] { new Path(i3, 2.5, 158),
				new Path(i4, 10.3, 338) };
		class835.adjacencies = new Path[] { new Path(i3, 7.7, 158),
				new Path(i4, 5.4, 338) };
		class837.adjacencies = new Path[] { new Path(i3, 8.9, 158),
				new Path(i4, 3.9, 338) };
		class841.adjacencies = new Path[] { new Path(i4, 1, 158),

		new Path(class841A, 1.57, 338), new Path(class841B, 2.81, 338),
				new Path(class841C, 2.81, 338) };

		class841A.adjacencies = new Path[] { new Path(i4, 1.6, 158) };
		class841B.adjacencies = new Path[] { new Path(i4, 2.9, 158) };
		class841C.adjacencies = new Path[] { new Path(i4, 2.9, 158) };

		class849A.adjacencies = new Path[] { new Path(i4, 4.2, 248),
				new Path(i5, 8.5, 68) };

		class849B.adjacencies = new Path[] { new Path(i5, 1, 158) };
		class848.adjacencies = new Path[] { new Path(i5, 4.0, 338),
				new Path(i0, 8.5, 158) };
		class851.adjacencies = new Path[] { new Path(i5, 4.4, 338),
				new Path(i0, 7.4, 158) };

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

		String r2 = "Path: " + path + " distance " + end.minDistance + "\n";

		displayPath();
		return r1 + r2;

	}

	public String getPath(PointOfInterest start, PointOfInterest end) {

		for (int i = 0;; i++) {
			if (start.adjacencies[i].destination == end) {
				return "go " + start.adjacencies[i].distance + " meters "
						+ start.adjacencies[i].direction + " to "
						+ end.toString();
			}
		}

	}

	public String displayPath() {
		this.path = di.findEnd(end);
		String rValue = "";
		for (int i = 1; i < path.size(); i++) {
			System.out.println(getPath(path.get(i - 1), path.get(i)));

		}
		return rValue;

	}

}
