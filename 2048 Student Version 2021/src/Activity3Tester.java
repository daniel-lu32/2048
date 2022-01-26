
public class Activity3Tester {

	public static void main(String[] args) {
		// create a Board object
		Board b = new Board();
		
		// create some arrays with values you want to slideRight and slideLeft
		int[] sample1 = {2, 2, 0, 4};	// slideRight: 0 2 2 4		slideLeft: 2 2 4 0
		int[] sample2 = {0, 0, 0, 0};	// slideRight: 0 0 0 0		slideLeft: 0 0 0 0
		int[] sample3 = {4, 0, 0, 0};	// slideRight: 0 0 0 4		slideLeft: 4 0 0 0
		int[] sample4 = {4, 0, 0, 2};	// slideRight: 0 0 4 2		slideLeft: 4 2 0 0
		int[] sample5 = {0, 0, 0, 2};	// slideRight: 0 0 0 2		slideLeft: 2 0 0 0
		int[] sample6 = {4, 4, 2, 2};	// slideRight: 4 4 2 2		slideLeft: 4 4 2 2
		int[] sample7 = {2, 0, 2, 4};	// slideRight: 0 2 2 4		slideLeft: 2 2 4 0
		int[] sample8 = {0, 2, 4, 4};	// slideRight: 0 2 4 4		slideLeft: 2 4 4 0
		int[] sample9 = {0, 2, 0, 2};	// slideRight: 0 0 2 2		slideLeft: 2 2 0 0
		int[] sample10 = {0, 0, 4, 0};	// slideRight: 0 0 0 4		slideLeft: 4 0 0 0
		
		// invoke the slideRight method on the arrays
		b.slideRight(sample1);
		b.slideRight(sample2);
		b.slideRight(sample3);
		b.slideRight(sample4);
		b.slideRight(sample5);
		b.slideRight(sample6);
		b.slideRight(sample7);
		b.slideRight(sample8);
		b.slideRight(sample9);
		b.slideRight(sample10);
		
		// print out the arrays to see if slideRight worked
		printSample(sample1);
		printSample(sample2);
		printSample(sample3);
		printSample(sample4);
		printSample(sample5);
		printSample(sample6);
		printSample(sample7);
		printSample(sample8);
		printSample(sample9);
		printSample(sample10);
		
		System.out.println();
		
		// invoke the slideLeft method on the arrays
		b.slideLeft(sample1);
		b.slideLeft(sample2);
		b.slideLeft(sample3);
		b.slideLeft(sample4);
		b.slideLeft(sample5);
		b.slideLeft(sample6);
		b.slideLeft(sample7);
		b.slideLeft(sample8);
		b.slideLeft(sample9);
		b.slideLeft(sample10);
		
		// print out the arrays to see if slideLeft worked
		printSample(sample1);
		printSample(sample2);
		printSample(sample3);
		printSample(sample4);
		printSample(sample5);
		printSample(sample6);
		printSample(sample7);
		printSample(sample8);
		printSample(sample9);
		printSample(sample10);
	}
	
	public static void printSample(int[] sample) {
		for (int i = 0; i < sample.length; i++) {
			System.out.print(sample[i] + " ");
		}
		System.out.println();
	}

}
