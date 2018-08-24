package swen221.tetris.tetromino;

import swen221.tetris.logic.Rectangle;

/**
 * Represents a tetromino which can only perform one rotation operation.
 *
 * @author David J. Pearce
 * @author Marco Servetto
 *
 */
public class StickyTetromino implements Tetromino {

	int count;
	Tetromino tetromino;
	
	public StickyTetromino(int count, Tetromino tetromino) {
	this.count = count;
	this.tetromino = tetromino;
	}

	@Override
	/**
	 * Get the color of this tetromino.
	 * @return color
	 */
	public Color getColor() {
		if (count == 0) {
			return Color.DARK_GRAY;
		}
		else return tetromino.getColor();
	}

	/**
	 * Get the orientation of this tetromino.
	 * @return orientation
	 */
	public Orientation getOrientation() {
		return tetromino.getOrientation();
	}

	/**
	 * Get the within of this tetromino.
	 * @return within
	 */
	public boolean isWithin(int x, int y) {
		return tetromino.isWithin(x, y);
	}

	/**
	 * Get the orientation of this tetromino.
	 * @return orientation
	 */
	public Rectangle getBoundingBox() {
		return tetromino.getBoundingBox();
	}

	/**
	 * Get the rotate of this tetromino.
	 * @return rotate
	 */
	public Tetromino rotate(int steps) {
		int r = 0;
		if (steps < count) {
			r = steps;
			count = count - steps;
		}
		else {
			r = count;
			count = 0;
		}
		
		if (tetromino instanceof I_Tetromino) {
			return new StickyTetromino(count, new I_Tetromino(getOrientation(), getColor()).rotate(r));
		}
		else if (tetromino instanceof J_Tetromino) {
			return new StickyTetromino(count, new J_Tetromino(getOrientation(), getColor()).rotate(r));
		}
		else if (tetromino instanceof L_Tetromino) {
			return new StickyTetromino(count, new L_Tetromino(getOrientation(), getColor()).rotate(r));
		}
		else if (tetromino instanceof O_Tetromino) {
			return new StickyTetromino(count, new O_Tetromino(getColor()).rotate(r));
		}
		else if (tetromino instanceof S_Tetromino) {
			return new StickyTetromino(count, new S_Tetromino(getOrientation(), getColor()).rotate(r));
		}
		else if (tetromino instanceof T_Tetromino) {
			return new StickyTetromino(count, new T_Tetromino(getOrientation(), getColor()).rotate(r));
		}
		else {
			return new StickyTetromino(count, new Z_Tetromino(getOrientation(), getColor()).rotate(r));
		}
	}
}
