package main;

public class Main {
	public static void main(String[] args) {
		Board board = new Board(2, "3213");
		System.out.println(board.toString());

		Board board2 = new Board(4, "123456789AB DEFC");
		System.out.println(board2.toString());

	}
}
