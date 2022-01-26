
public class Activity456Tester {

	public static void main(String[] args) {
		// create a Board object
		Board b = new Board();
		
		// populate the board with some values
		b.populateOne();
		b.populateOne();
		b.populateOne();
		b.populateOne();
		b.populateOne();
		b.populateOne();
		b.populateOne();
		b.populateOne();
		b.populateOne();
		b.populateOne();
		
		// print the board
		System.out.println(b);
		
		// combine and slide left
		b.slideLeft();
		b.combineLeft();
		b.slideLeft();
		System.out.println(b);
		
		// combine and slide up
		b.slideUp();
		b.combineUp();
		b.slideUp();
		System.out.println(b);
		
		// create a Board object
		Board c = new Board();
		
		// populate the board with some values
		c.populateOne();
		c.populateOne();
		c.populateOne();
		c.populateOne();
		c.populateOne();
		c.populateOne();
		c.populateOne();
		c.populateOne();
		c.populateOne();
		c.populateOne();
		
		// print the board
		System.out.println(c);
		
		// combine and slide right
		c.slideRight();
		c.combineRight();
		c.slideRight();
		System.out.println(c);
		
		// combine and slide down
		c.slideDown();
		c.combineDown();
		c.slideDown();
		System.out.println(c);
	}
	
}
