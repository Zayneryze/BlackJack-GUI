package model;

import java.awt.Color;
import java.util.Random;


public class ClearCellGame extends Game  {
	private int maxRows;
	private int maxCols;
	private int strategy;
	private Random random;
	private int score;
	

	public ClearCellGame(int maxRows, int maxCols, Random random, int strategy) {
		super(maxRows, maxCols);
		this.random = random;
		this.strategy = strategy;
		this.score = 0;
		board = new BoardCell [maxRows][maxCols];
		for (int row = 0; row < maxRows; row++ ) {
			for(int col =0; col< maxCols; col++) {
				board[row][col] = BoardCell.EMPTY;			}
		}
		
	}
	
	
	

	@Override
	public boolean isGameOver() {
	    int lastRowIndex = board.length - 1;
	    for (int colIndex = 0; colIndex < board[lastRowIndex].length; colIndex++) {
	        if (board[lastRowIndex][colIndex] != BoardCell.EMPTY) {
	            return true;
	        }
	    }
	    return false;
	}


	@Override
	public int getScore() {
		return score;
	}

	public void nextAnimationStep() {
	    // Check if the game has ended
	    if (isGameOver()) {
	        return;
	    }

	    int lastRowIndex = board.length - 1;

	    // Check if the last row is empty
	    boolean isLastRowEmpty = true;
	    for (int col = 0; col < board[lastRowIndex].length; col++) {
	        if (board[lastRowIndex][col] != BoardCell.EMPTY) {
	            isLastRowEmpty = false;
	            break;
	        }
	    }

	    // If the last row is empty, move non-empty rows downward and set the top row to a new, random row
	    if (isLastRowEmpty) {
	        // Move non-empty rows downward
	        for (int row = lastRowIndex - 1; row >= 0; row--) {
	            board[row + 1] = board[row].clone();
	        }

	        // Generate a new random row
	        BoardCell[] newRow = generateRandomRow();

	        // Set the top row to the new random row
	        board[0] = newRow.clone();
	    }
	}


	private BoardCell[] generateRandomRow() {
	    BoardCell[] newRow = new BoardCell[board[0].length];
	    for (int col = 0; col < newRow.length; col++) {
	        newRow[col] = BoardCell.getNonEmptyRandomBoardCell(random);
	    }
	    return newRow;
	}






	@Override
	public void processCell(int rowIndex, int colIndex) {
	    Color target = board[rowIndex][colIndex].getColor();

	    processCellHelper(rowIndex, colIndex, target, 1, 0); // Horizontal
	    processCellHelper(rowIndex, colIndex, target, -1, 0); // Horizontal
	    processCellHelper(rowIndex, colIndex, target, 0, 1); // Vertical
	    processCellHelper(rowIndex, colIndex, target, 0, -1); // Vertical
	    processCellHelper(rowIndex, colIndex, target, 1, 1); // Forward Slash
	    processCellHelper(rowIndex, colIndex, target, -1, -1); // Forward Slash
	    processCellHelper(rowIndex, colIndex, target, 1, -1); // Back Slash
	    processCellHelper(rowIndex, colIndex, target, -1, 1); // Back Slash

	    this.setBoardCell(rowIndex, colIndex, BoardCell.EMPTY);
	    score++;
	    processCellShiftHelper();
	}

	public void processCellHelper(int rowIndex, int colIndex, Color target, int rowIncrement, int colIncrement) {
	    boolean side1 = false;
	    boolean side2 = false;

	    int row = rowIndex + rowIncrement;
	    int col = colIndex + colIncrement;

	    while (row >= 0 && row < board.length && col >= 0 && col < board[row].length) {
	        if (!side1 && board[row][col].getColor() == target) {
	            board[row][col] = BoardCell.EMPTY;
	            score++;
	        } else {
	            side1 = true;
	        }

	        row += rowIncrement;
	        col += colIncrement;
	    }

	    row = rowIndex - rowIncrement;
	    col = colIndex - colIncrement;

	    while (row >= 0 && row < board.length && col >= 0 && col < board[row].length) {
	        if (!side2 && board[row][col].getColor() == target) {
	            board[row][col] = BoardCell.EMPTY;
	            score++;
	        } else {
	            side2 = true;
	        }

	        row -= rowIncrement;
	        col -= colIncrement;
	    }
	}

	public void processCellShiftHelper() {
	    boolean blank;

	    for (int i = 0; i < board.length - 1; i++) {
	        blank = true;
	        for (int j = 0; j < board[i].length; j++) {
	            if (board[i][j].getColor() != Color.WHITE) {
	                j = board[i].length + 1;
	                blank = false;
	            }
	        }
	        if (blank) {
	            for (int j = 0; j < board[i].length; j++) {
	                this.setBoardCell(i, j, this.getBoardCell(i + 1, j));
	                this.setBoardCell(i + 1, j, BoardCell.EMPTY);
	            }
	        }
	    }
	}

	
	}
	


	

	


