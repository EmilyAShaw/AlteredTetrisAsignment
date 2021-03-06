package swen221.tetris.tetromino;

import swen221.tetris.logic.Rectangle;

/**
 * Represents a tetromino which has not yet been placed on the board. That is, a
 * tetromino which is "in flight" and has a center position on the board. Once a
 * tetrmino has been placed, it loses the concept of a center position as this
 * no longer makes sense.
 *
 * @author David J. Pearce
 * @author Marco Servetto
 *
 */
public class ActiveTetromino implements Tetromino {
	/**
	 * The tetromino underlying this active tetromino. This determins the shape,
	 * color and orientation of this tetromino.
	 */
	private final Tetromino tetromino;

	/**
	 * Column coordinate of the center of the Tetromino
	 */
	protected final int 	x;

	/**
	 * Row coordinate of the center of the Tetromino
	 */
	protected final int y;

	public ActiveTetromino(int x, int y, Tetromino tetromino) {
		if (tetromino == null) {
			throw new IllegalArgumentException("invalid tetromino!");
		}
		this.x = x;
		this.y = y;
		this.tetromino = tetromino;
	}

	@Override
	public Color getColor() {
		return tetromino.getColor();
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public Orientation getOrientation() {
		return tetromino.getOrientation();
	}

	@Override
	public boolean isWithin(int x, int y) {
		int dx = x - this.x;
		int dy = y - this.y;
		//
		switch (tetromino.getOrientation()) {
		case NORTH:
			return tetromino.isWithin(dx, dy);
		case SOUTH:
			return tetromino.isWithin(-dx, -dy);
		case EAST:
			return tetromino.isWithin(-dy, dx);
		case WEST:
		default:
			return tetromino.isWithin(dy, -dx);
		}
	}
	
	/**
	 * Check whether a given coordinate on the board is within this tetromino.
	 *  That is, whether or not it is one of the locations making up this tetromino.
	 *   This method is used, for example, when drawing a tetrmoino. 
	 *   That is, we first acquire the bounding box for the tetromino and then employ 
	 *   this method to determine which cells are actually within the tetromino.
	 *    This method can also be used to check whether a given tetromino overlaps with another, etc.
	 *    recursive method
	 * @param x 
	 * @param y
	 * 
	 */
	public boolean isWithin(int x, int y, int steps) {
		Orientation o = tetromino.getOrientation().rotate(steps);
		int dx = x - this.x;
		int dy = y - this.y;
		//
		switch (o) {
		case NORTH:
			return tetromino.isWithin(dx, dy);
		case SOUTH:
			return tetromino.isWithin(-dx, -dy);
		case EAST:
			return tetromino.isWithin(-dy, dx);
		case WEST:
		default:
			return tetromino.isWithin(dy, -dx);
		}
	}

	public Tetromino getTetromino() {
		return tetromino;
	}

	/**
	 * 
	 * Represents a rectangle of cells on the Tetris board.
	 * 
	 */
	public Rectangle getBoundingBox() {
		Rectangle box = tetromino.getBoundingBox();
		switch (tetromino.getOrientation()) {
		case WEST:
			box = box.rotateClockwise();
		case SOUTH:
			box = box.rotateClockwise();
		case EAST:
			box = box.rotateClockwise();
		}
		return box.translate(x, y);
	}
	
	public Rectangle getBoundingBox(int steps) {
		Rectangle box = tetromino.getBoundingBox();
		Orientation o = tetromino.getOrientation().rotate(steps);
		switch (o) {
		case WEST:
			box = box.rotateClockwise();
		case SOUTH:
			box = box.rotateClockwise();
		case EAST:
			box = box.rotateClockwise();
		}
		return box.translate(x, y);
	}

	/**
	 * Move this Tetromino by a given amount in the x and/or y direction.
	 *
	 * @param dx
	 *            The amount to move in the x direction.
	 * @param dy
	 *            The amount to move in the y direction.
	 * @return
	 */
	public ActiveTetromino translate(int dx, int dy) {
		return new ActiveTetromino(x + dx, y + dy, tetromino);
	}

	@Override
	public ActiveTetromino rotate(int steps) {
		return new ActiveTetromino(x, y, tetromino.rotate(steps));
	}

	@Override
	public String toString() {
		return tetromino.toString();
	}
}
