import java.util.Random;

public class Board {
 
	private int[][] board;
	private int size;
	private Random rnd = new Random();
	
	/* default constructor for board */
	public Board() {
		board = new int[4][4];
		size = 0;
		populateOne();
		populateOne();
	}
	
	/*
	 * returns a String representation of the 2D array board
	 * each row should be in its own line
	 * 
	 * Example:
	 * { {1, 2, 3}, {4, 5, 6} } ->
	 * 1 2 3
	 * 4 5 6
	 * 
	 * use the String formatter to pad the numbers with leading 0s so that the print out does not become jagged
	 * 
	 * Example:
	 * String str = String.format("%04d", 9); -> 0009
	 */
	public String toString() {
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				System.out.print(String.format("%04d", board[row][col]) + " ");
			}
			System.out.println();
		}
		
		return "";
	}
	
	/*
	 * set one of the empty spaces (at random) to a 2 or 4
	 * an empty spot is defined to be a 0 element
	 * must use the Random class object rnd
	 * 
	 * Example:
	 * int randomNumber = rnd.nextInt(10); -> returns a number in the range [0 10)
	 */
	public void populateOne() {
		int oneTen = (int) (Math.random()*10+1);
		int twoFour;
		if (oneTen == 1) {
			twoFour = 4;
		} else {
			twoFour = 2;
		}
		
		boolean emptyTileFound = false;
		while (!emptyTileFound && size < 16) {
			int row = rnd.nextInt(4)+0;
			int col = rnd.nextInt(4)+0;
			if (board[row][col] == 0) {
				emptyTileFound = true;
				board[row][col] = twoFour;
			}
		}

		size++;
	}
	
	/* sets every element on board to 0 */
	public void eraseBoard() {
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				board[row][col] = 0;
			}
		}
	}

	/*
	 * given an array of integers, slide all non-zero elements to the right
	 * 
	 * Example:
	 * [0 2 0 2] -> [0 0 2 2]
	 * [2 8 0 2] -> [0 2 8 2]
	 * [4 0 0 0] -> [0 0 0 4]
	 */
	public void slideRight(int[] row) {
		int zeroCount = 0;
		for (int i = 0; i < row.length; i++) {
			if (row[i] == 0) {
				zeroCount++;
			}
		}
		int[] noZeroes = new int[row.length - zeroCount];
		int noZeroesIndex = 0;
		for (int i = 0; i < row.length; i++) {
			if (row[i] != 0) {
				noZeroes[noZeroesIndex] = row[i];
				noZeroesIndex++;
			}
		}
		for (int i = 0; i < zeroCount; i++) {
			row[i] = 0;
		}
		for (int i = zeroCount; i < row.length; i++) {
			row[i] = noZeroes[i - zeroCount];
		}
	}

	/*
	 * move the numbers as far to the right as they can go
	 * the numbers are trying to move to the right-most empty spaces
	 */
	public void slideRight() {
		for (int row = 0; row < board.length; row++) {
			slideRight(board[row]);
		}
	}

	/*
	 * given an array of integers, slide all non-zero elements to the left
	 * 
	 * Example:
	 * [0 2 0 2] -> [2 2 0 0]
	 * [2 0 0 2] -> [2 2 0 0]
	 */
	public void slideLeft(int[] row) {
		int zeroCount = 0;
		for (int i = 0; i < row.length; i++) {
			if (row[i] == 0) {
				zeroCount++;
			}
		}
		int[] noZeroes = new int[row.length - zeroCount];
		int noZeroesIndex = 0;
		for (int i = 0; i < row.length; i++) {
			if (row[i] != 0) {
				noZeroes[noZeroesIndex] = row[i];
				noZeroesIndex++;
			}
		}
		for (int i = 0; i < noZeroes.length; i++) {
			row[i] = noZeroes[i];
		}
		for (int i = noZeroes.length; i < row.length; i++) {
			row[i] = 0;
		}
	}

	/* slide all the numbers to the left so that all of the empty spaces are on the right side */
	public void slideLeft() {
		for (int i = 0; i < board.length; i++) {
			slideLeft(board[i]);
		}
	}

	/* given a 2D array and a column number, return a 1D array representing the elements in the given column number */
	public int[] getCol(int[][] data, int col) {
		int[] column = new int[data.length];
		for (int row = 0; row < data.length; row++) {
			column[row] = data[row][col];
		}
		return column;
	}

	/* given an array of integers, slide all non-zero elements to the top */
	public void slideUp(int[] arr) {
		slideLeft(arr);
	}

	/* slide all elements in the board towards the top */
	public void slideUp() {
		for (int col = 0; col < board[0].length; col++) {
			int[] column = getCol(board, col);
			slideUp(column);
			for (int row = 0; row < board.length; row++) {
				board[row][col] = column[row];
			}
		}
	}
	/* given an array of integers, slide all non-zero elements to the bottom */
	public void slideDown(int[] arr) {
		slideRight(arr);
	}

	/* slide all the numbers down so that any empty space is at the top */
	public void slideDown() {
		for (int i = 0; i < board[0].length; i++) {
			int[] column = getCol(board, i);
			slideDown(column);
			for (int j = 0; j < board.length; j++) {
				board[j][i] = column[j];
			}
		}
	}

	/*
	 * given the 2D array, board, combineRight will take adjacent numbers that are the same and combine them (add them)
	 * after adding them together, one of the numbers is zeroed out
	 * for example, if row 0 contained [0 0 4 4], a call to combineRight will produce [0 0 0 8]
	 * if row 1 contained [2 2 2 2], a call to combineRight will produce [0 4 0 4]
	 * notice that the left element is zeroed out
	 */
	public void combineRight() {
		for (int row = 0; row < board.length; row++) {
			for (int col = board[row].length - 1; col > 0; col--) {
				if (board[row][col] == board[row][col - 1]) {
					board[row][col] *= 2;
					board[row][col - 1] = 0;
					col--;
					size--;
				}
			}
		}
	}

	/* same behavior as combineRight but the right element is zeroed out when two elements are combined */
	public void combineLeft() {
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length - 1; col++) {
				if (board[row][col] == board[row][col + 1]) {
					board[row][col] *= 2;
					board[row][col + 1] = 0;
					col++;
					size--;
				}
			}
		}
	}
	
	/* same behavior as combineRight but the bottom element is zeroed out when two elements are combined */
	public void combineUp() {
		for (int col = 0; col < board[0].length; col++) {
			for (int row = 0; row < board.length - 1; row++) {
				if (board[row][col] == board[row + 1][col]) {
					board[row][col] *= 2;
					board[row + 1][col] = 0;
					row++;
					size--;
				}
			}
		}
	}

	/* same behavior as combineRight but the top element is zeroed out when two elements are combined */
	public void combineDown() {
		for (int col = 0; col < board[0].length; col++) {
			for (int row = board.length - 1; row > 0; row--) {
				if (board[row][col] == board[row - 1][col]) {
					board[row][col] *= 2;
					board[row - 1][col] = 0;
					row--;
					size--;
				}
			}
		}
	}
	
	/* 
	 * slide left, combine left, and slide left again
	 * call the populateOne method if the board changed after the move
	 */
	public void left() {
		int[][] beforeMove = new int[4][4];
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				beforeMove[row][col] = board[row][col];
			}
		}
		slideLeft();
		combineLeft();
		slideLeft();
		int[][] afterMove = new int[4][4];
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				afterMove[row][col] = board[row][col];
			}
		}
		boolean boardChanged = false;
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				if (beforeMove[row][col] != afterMove[row][col]) {
					boardChanged = true;
				}
			}
		}
		if (boardChanged) {
			populateOne();
		}
	}
	
	/* 
	 * slide right, combine right, and slide right again
	 * call the populateOne method if the board changed after the move
	 */
	public void right() {
		int[][] beforeMove = new int[4][4];
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				beforeMove[row][col] = board[row][col];
			}
		}
		slideRight();
		combineRight();
		slideRight();
		int[][] afterMove = new int[4][4];
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				afterMove[row][col] = board[row][col];
			}
		}
		boolean boardChanged = false;
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				if (beforeMove[row][col] != afterMove[row][col]) {
					boardChanged = true;
				}
			}
		}
		if (boardChanged) {
			populateOne();
		}
	}
	
	/* 
	 * slide up, combine up, and slide up again
	 * call the populateOne method if the board changed after the move
	 */
	public void up() {
		int[][] beforeMove = new int[4][4];
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				beforeMove[row][col] = board[row][col];
			}
		}
		slideUp();
		combineUp();
		slideUp();
		int[][] afterMove = new int[4][4];
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				afterMove[row][col] = board[row][col];
			}
		}
		boolean boardChanged = false;
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				if (beforeMove[row][col] != afterMove[row][col]) {
					boardChanged = true;
				}
			}
		}
		if (boardChanged) {
			populateOne();
		}
	}
	
	/* 
	 * slide down, combine down, and slide down again
	 * call the populateOne method if the board changed after the move
	 */
	public void down() {
		int[][] beforeMove = new int[4][4];
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				beforeMove[row][col] = board[row][col];
			}
		}
		slideDown();
		combineDown();
		slideDown();
		int[][] afterMove = new int[4][4];
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				afterMove[row][col] = board[row][col];
			}
		}
		boolean boardChanged = false;
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				if (beforeMove[row][col] != afterMove[row][col]) {
					boardChanged = true;
				}
			}
		}
		if (boardChanged) {
			populateOne();
		}
	}
	
	/* 
	 * check the board to see if any possible moves can be made
	 * if any element in the array is 0, then the game is not over
	 * if any two elements in the array are matching left to right, the game is not over
	 * if any two elements in the array are matching up and down, the game is not over
	 * otherwise, the game is over
	 */
	public boolean gameOver() {
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				if (board[row][col] == 0) {
					return false;
				}
			}
		}
		
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length - 1; col++) {
				if (board[row][col] == board[row][col + 1]) {
					return false;
				}
			}
		}
		for (int col = 0; col < board[0].length; col++) {
			for (int row = 0; row < board.length - 1; row++) {
				if (board[row][col] == board[row + 1][col]) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	/* return the board as a 2D array */
	public int[][] getBoard() {
		return board;
	}

	/* populate with a given 2D array */
	public void populate(int[][] arr) {
		for (int r = 0; r < arr.length; r++) {
			for (int c = 0; c < arr[r].length; c++) {
				board[r][c] = arr[r][c];
			}
		}
	}
	
	public static int nextMove(int[][] b) {
		int matchesLR = 0;
		int matchesUD = 0;
		boolean ableToMoveLeft = false;
		boolean ableToMoveRight = false;
		boolean ableToMoveUp = false;
		boolean ableToMoveDown = false;
		int numPossibleMoves = 0;
		
		// count nonzero matching elements in each row
		for (int row = 0; row < b.length; row++) {
			for (int col = 0; col < b[row].length - 1; col++) {
				if (b[row][col] == b[row][col + 1] && b[row][col] != 0) {
					matchesLR++;
				}
			}
		}
		
		// count nonzero matching elements in each column
		for (int col = 0; col < b[0].length; col++) {
			for (int row = 0; row < b.length - 1; row++) {
				if (b[row][col] == b[row + 1][col] && b[row][col] != 0) {
					matchesUD++;
				}
			}
		}
		
		// determine whether or not a left move is possible
		for (int row = 0; row < b.length; row++) {
			int minIndexZero = 3;
			int maxIndexNonzero = 0;
			for (int col = b[row].length - 1; col >= 0; col--) {
				if (b[row][col] == 0) {
					minIndexZero = col;
				}
			}
			for (int col = 0; col < b[row].length; col++) {
				if (b[row][col] != 0) {
					maxIndexNonzero = col;
				}
			}
			if (minIndexZero < maxIndexNonzero || matchesLR > 0) {
				ableToMoveLeft = true;
			}
		}
		
		// determine whether or not a right move is possible
		for (int row = 0; row < b.length; row++) { // 0 0 0 0 max0 = 3 min!0 = 3
			int maxIndexZero = 0;
			int minIndexNonzero = 3;
			for (int col = 0; col < b[row].length; col++) {
				if (b[row][col] == 0) {
					maxIndexZero = col;
				}
			}
			for (int col = b[row].length - 1; col >= 0; col--) {
				if (b[row][col] != 0) {
					minIndexNonzero = col;
				}
			}
			if (minIndexNonzero < maxIndexZero || matchesLR > 0) {
				ableToMoveRight = true;
			}
		}
		
		// determine whether or not an up move is possible
		for (int col = 0; col < b[0].length; col++) {
			int minIndexZero = 3;
			int maxIndexNonzero = 0;
			for (int row = b.length - 1; row >= 0; row--) {
				if (b[row][col] == 0) {
					minIndexZero = row;
				}
			}
			for (int row = 0; row < b.length; row++) {
				if (b[row][col] != 0) {
					maxIndexNonzero = row;
				}
			}
			if (minIndexZero < maxIndexNonzero || matchesUD > 0) {
				ableToMoveUp = true;
			}
		}
		
		// determine whether or not a down move is possible
		for (int col = 0; col < b[0].length; col++) {
			int maxIndexZero = 0;
			int minIndexNonzero = 3;
			for (int row = 0; row < b.length; row++) {
				if (b[row][col] == 0) {
					maxIndexZero = row;
				}
			}
			for (int row = b.length - 1; row >= 0; row--) {
				if (b[row][col] != 0) {
					minIndexNonzero = row;
				}
			}
			if (minIndexNonzero < maxIndexZero || matchesUD > 0) {
				ableToMoveDown = true;
			}
		}
		
		// count the number of possible moves
		if (ableToMoveLeft) numPossibleMoves++;
		if (ableToMoveRight) numPossibleMoves++;
		if (ableToMoveUp) numPossibleMoves++;
		if (ableToMoveDown) numPossibleMoves++;
		
		// return the only possible move if applicable
		if (numPossibleMoves == 1) {
			if (ableToMoveLeft) return 0;
			if (ableToMoveRight) return 1;
			if (ableToMoveUp) return 2;
			if (ableToMoveDown) return 3;
		}
		
		// return the best move out of 2 possible moves if applicable
		if (numPossibleMoves == 2) {
			boolean leftRight = false;
			boolean upDown = false;
			if (ableToMoveLeft) leftRight = true;
			if (ableToMoveRight) leftRight = true;
			if (ableToMoveUp) upDown = true;
			if (ableToMoveDown) upDown = true;
			if (leftRight && !upDown) {
				return 1;
			}
			if (!leftRight && upDown) {
				return 3;
			}
			if (leftRight && upDown) {
				boolean left = false;
				boolean right = false;
				boolean up = false;
				boolean down = false;
				if (ableToMoveLeft) left = true;
				if (ableToMoveRight) right = true;
				if (ableToMoveUp) up = true;
				if (ableToMoveDown) down = true;
				if (matchesLR >= matchesUD) {
					if (left) {
						return 0;
					} else {
						return 1;
					}
				} else {
					if (up) {
						return 2;
					} else {
						return 3;
					}
				}
			}
		}
		
		// return the best move out of 3 possible moves if applicable
		if (numPossibleMoves == 3) {
			boolean left = false;
			boolean right = false;
			boolean up = false;
			boolean down = false;
			if (ableToMoveLeft) left = true;
			if (ableToMoveRight) right = true;
			if (ableToMoveUp) up = true;
			if (ableToMoveDown) down = true;
			if (matchesLR >= matchesUD) {
				if (left && right) {
					return 1;
				}
				if (left && !right) {
					return 0;
				} else {
					return 1;
				}
			} else {
				if (up && down) {
					return 3;
				}
				if (up && !down) {
					return 2;
				} else {
					return 3;
				}
			}
		}
		
		// determine the best move if every move is possible
		if (numPossibleMoves == 4) {
			if (matchesLR >= matchesUD) {
				return 1;
			} else {
				return 3;
			}
		}
		
		return -1;
	}

}
