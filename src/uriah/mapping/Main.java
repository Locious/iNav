package uriah.mapping;

public class Main {
	public static void main(String[] args) {
		
		String start = "elevators";
		String end = "802";
		ESB8thfloor test= new ESB8thfloor();
		System.out.println(test.getDirections(start, end));
		
	}


}
