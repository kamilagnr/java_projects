package tictactoe;
import java.util.*;

public class Main {
	public static void printTheGrid(char[][] grid) {
		System.out.println("---------");
		for (char[] row : grid) {
			System.out.print("| ");
			for (char el : row) {
				el = el == 0 ? ' ' : el;
				System.out.print(el + " ");
			}
			System.out.println("|");
		}
		System.out.println("---------");
	}

	public static char[][] play(Scanner sc, char[][] grid, char player) {
		int x = -1;
		int y = -1;
		boolean available = false;
		while (!available) {
			System.out.print("Enter the coordinates: ");
			String scannedCoordinates = scanCoordinates(sc);
			if (scannedCoordinates == null)
				continue;
			x = Character.getNumericValue(scannedCoordinates.charAt(0));
			y = Character.getNumericValue(scannedCoordinates.charAt(1));
			if (grid[x-1][y-1] == 88 || grid[x-1][y-1] == 79) {
				System.out.println("This cell is occupied! Choose another one!");
			} else
				available = true;
		}
		grid[x-1][y-1] = player;
		return grid;
	}
	public static boolean calculate(char[][] grid) {
		boolean end = true;
		boolean wx = false, wo = false;
		for (int i = 0; i < 3; i++) {
			int row = 0;
			int column = grid[0][i] + grid[1][i] + grid[2][i];
			for (int j = 0; j < 3; j++) {
				row += grid[i][j];
			}
			if(row / 3 == 88 || column / 3 == 88)
				wx = true;
			if(row / 3 == 79 || column / 3 == 79)
				wo = true;
//			System.out.println("i = "+i+": row = "+row+", column = "+column);
		}
		int dsum1 = grid[0][0] + grid[1][1] + grid[2][2];
		int dsum2 = grid[0][2] + grid[1][1] + grid[2][0];
		if(dsum1 / 3 == 88 || dsum2 / 3 == 88)
			wx = true;
		if(dsum1 / 3 == 79 || dsum2 / 3 == 79)
			wo = true;

		if (wx) {
			System.out.println("X wins");
			end = false;
		} else if (wo) {
			System.out.println("O wins");
			end = false;
		}
		return end;
	}
	public static void TicTacToe() {
		Scanner sc = new Scanner(System.in);
		char[][] grid = new char[3][3];
		boolean status;
		char player = 'X';
		int numOfMoves = 0;
		printTheGrid(grid);
		do {
			grid = play(sc, grid, player);
			printTheGrid(grid);
			status = calculate(grid);
			player = (numOfMoves % 2 == 0) ? 'O' : 'X';
			numOfMoves++;
		} while (status && numOfMoves < 9);
		if(status)
			System.out.println("Draw");
	}
	public static String scanCoordinates(Scanner sc) {
		String input = sc.nextLine().replaceAll(" ", "");
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) < 47 || input.charAt(i) > 57) {
				System.out.println("You should enter numbers!");
				return null;
			} else if (input.charAt(i) < 49 || input.charAt(i) > 51) {
				System.out.println("Coordinates should be from 1 to 3!");
				return null;
			}
		}
		return input;
	}

    public static void main(String[] args) {
        // write your code here
		TicTacToe();
    }
}
