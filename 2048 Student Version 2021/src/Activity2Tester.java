
public class Activity2Tester {

	public static void main(String[] args) {
		// create a Board object
		Board b = new Board();
		System.out.println(b);
		
		// call the populateOne method and print out the board each time
		// Does it fill a random location? How about if the board is almost full? Does it still work?
		for (int i = 0; i < 14; i++) {
			b.populateOne();
			System.out.println(b);
		}
		
		// call the populateOne method one more time to make sure that the array does not change as it is full
		b.populateOne();
		System.out.println(b);
	}

}
