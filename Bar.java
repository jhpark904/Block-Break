package edu.nyu.cs.jhp489;

/**
 * Bar class to be used in the blockBreak game
 * @author jhp489
 *
 */
public class Bar {
	// to get some features of blockBreak
	private BlockBreak blockBreak;
	
	private int x, y;		// x and y position
	// speed
	private int speedX = 15;		
	private int speedY = 15;
	//bar size
	private int barWidth = 300;
	private int barHeight = 20;
	
	/**
	 * getter for barHeight
	 * @return barHeight
	 */
	public int getBarHeight() {
		return this.barHeight;
	}
	
	/**
	 * getter for barWidth
	 * @return barWidth
	 */
	public int getBarWidth() {
		return this.barWidth;
	}
	
	/**
	 * getter for x
	 * @return x
	 */
	public int getX() {
		return this.x;
	}
	
	/**
	 * getter for y
	 * @return y
	 */
	public int getY() {
		return this.y;
	}
	
	/**
	 * constructor for the bar class
	 * @param x - 
	 * @param y
	 * @param blockBreak
	 */
	public Bar(int x, int y, BlockBreak blockBreak) {
		this.x = x - this.barWidth / 2;
		this.y = y;
		this.blockBreak = blockBreak;
	}
	
	/**
	 *  to draw a blue bar
	 */
	public void draw() {
		this.blockBreak.fill(0, 0, 255);
		this.blockBreak.rect(this.x, this.y, this.barWidth, this.barHeight);
	}
	
	/**
	 * to set the position of the bar
	 * @param x
	 * @param y
	 */
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * to move the bar down
	 */
	public void goDown() {
		int newY = this.y + this.speedY;
		
		if (newY > this.blockBreak.height - BlockBreak.appMargin) newY = this.blockBreak.height - BlockBreak.appMargin;
		
		this.setPosition(this.x, newY);
	}
	
	/**
	 * move the bar up
	 */
	public void goUp() {
		int newY = this.y - this.speedY;
		
		if (newY < this.blockBreak.height / 2 ) newY = this.blockBreak.height / 2;
		
		this.setPosition(this.x, newY);

	}
	
	/**
	 * move the bar right
	 */
	public void goRight() {
		int newX = this.x + this.speedX;
		
		if (newX > this.blockBreak.width - this.barWidth - BlockBreak.appMargin) newX = this.blockBreak.width - this.barWidth - BlockBreak.appMargin;
		
		this.setPosition(newX, this.y);
	}
	
	/**
	 * move the bar left
	 */
	public void goLeft() {
		int newX = this.x - this.speedX;
		
		if (newX < BlockBreak.appMargin) newX = BlockBreak.appMargin;
		
		this.setPosition(newX, this.y);

	}
}
