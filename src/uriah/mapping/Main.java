package uriah.mapping;

import com.example.inav.Navigate;

public class Main {
	public static void main(String[] args) {
		
		String start = "841C";
		String end = "813";
		ESB800 test= new ESB800();
		System.out.println(test.getDirections(start, end));
		Navigate.setDirectionText(test.getDirections(start, end));
		
	}


}
