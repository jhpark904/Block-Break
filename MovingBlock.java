package edu.nyu.cs.jhp489;

/**
 * moving block class that inherits the block class
 * @author jhp489
 */
public class MovingBlock extends Block {
	private int newX = BlockBreak.appMargin;		// to control movement in x axis

	private int speed = 5;

	/**
	 * constructor of the moving block
	 * @param x - x position
	 * @param y - y position
	 * @param blockBreak
	 */
	public MovingBlock(int x, int y, BlockBreak blockBreak) {
		super(x, y, blockBreak);
	}
	
	/**
	 * to make the moving blocks to move
	 */
	public void move() {		
		
		// don't make it fall out of the window
		if (newX > this.blockBreak.width - BlockBreak.appMargin - this.getWidth()) this.speed *= -1;
		else if (newX < BlockBreak.appMargin) this.speed *= -1;
		
		this.setX(newX);
		newX = this.getX() + this.speed;
		}

}