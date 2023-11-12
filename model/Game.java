package model;

import java.awt.Color;

/**
 * This class represents the logic of a game where a board is updated on each
 * step of the game animation. The board can also be updated by selecting a
 * board cell.
 * 
 * 
 */

public abstract class Game {
	protected BoardCell[][] board;
	private int maxRows;
	private int maxCols;
	
	
	
	public Game(int maxRows, int maxCols) {
		this.maxCols = maxCols;
		this.maxRows = maxRows;
		board = new BoardCell [maxRows][maxCols];
		for (int row = 0; row < maxRows; row++ ) {
			for(int col =0; col< maxCols; col++) {
				board[row][col] = BoardCell.EMPTY;			}
		}
	}
	

	public int getMaxRows() {
		return maxRows;
	}

	public int getMaxCols() {
		return maxCols;
	}

	public void setBoardCell(int rowIndex, int colIndex, BoardCell boardCell) {
		this.board[rowIndex][colIndex] = boardCell;
	}

	public BoardCell getBoardCell(int rowIndex, int colIndex) {
		return board[rowIndex][colIndex];
	}

	/**
	 * Initializes row with the specified color.
	 * @param rowIndex
	 * @param cell
	 */
	public void setRowWithColor(int rowIndex, BoardCell cell) {
		for (int i = 0; i < board[rowIndex].length; i++) {
	        board[rowIndex][i] = cell;
	    }
	}
	
	/**
	 * Initializes column with the specified color.
	 * @param colIndex
	 * @param cell
	 */
	public void setColWithColor(int colIndex, BoardCell cell) {
		for(int i =0; i < board.length; i++) {
			board[i][colIndex] = cell;
		}
	}
	
	/**
	 * Initializes the board with the specified color.
	 * @param cell
	 */
	public void setBoardWithColor(BoardCell cell) {
		for (int row = 0; row < board.length; row++) {
	        for (int col = 0; col < board[row].length; col++) {
	            board[row][col] = cell;
	        }
	    }
		
	}	
	
	public abstract boolean isGameOver();

	public abstract int getScore();

	/**
	 * Advances the animation one step.
	 */
	public abstract void nextAnimationStep();

	/**
	 * Adjust the board state according to the current board state and the
	 * selected cell.
	 * 
	 * @param rowIndex
	 * @param colIndex
	 */
	public abstract void processCell(int rowIndex, int colIndex);
}