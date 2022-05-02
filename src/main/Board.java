package main;

import java.util.List;

public class Board {
	private class Dimension {
		private int line;
		private int column;

		Dimension(int line, int column) {
			this.line = line;
			this.column = column;
		}
	}

	private static final List<Character> VALID_CHAR = List.of('1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', ' ');
	private char[][] board;

	Board(int size, String initialBoard) {
		if (initialBoard == null) {
			throw new IllegalArgumentException("Initial board is null");
		}

		char[] initialBoardArray = initialBoard.toCharArray();

		if (initialBoardArray.length != (size * size)) {
			throw new IllegalArgumentException("Initial board not equals to length");
		}

		board = new char[size][size];
		boolean isBlankFound = false;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				char boardChar = initialBoardArray[(i * size) + j];
				if (VALID_CHAR.contains(boardChar)) {
					board[i][j] = boardChar;
					if (boardChar == ' ') {
						isBlankFound = true;
					}
				} else {
					throw new IllegalArgumentException("Initial board contains invalid characters");
				}
			}
		}

		if (!isBlankFound) {
			throw new IllegalArgumentException("Blank location is not found");
		}
	}

	Board(Board b) {

	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (char[] aBoard : board) {
			sb.append(aBoard);
			sb.append("\n");
		}
		return sb.toString();
	}
}
