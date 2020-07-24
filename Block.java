package edu.nyu.cs.jhp489;

public class Block {
	
	protected BlockBreak blockBreak;
	// position
	private int x, y;
	
	// size
	private int height = 40;
	private int width = 100;
	
	// to check if the block should be gone
	private boolean isDestroyed = false;
	
	/**
	 * constructor for the block
	 * @param x
	 * @param y
	 * @param blockBreak
	 */
	public Block(int x, int y, BlockBreak blockBreak) {
		this.x = x - this.width / 2;
		this.y = y;
		this.blockBreak = blockBreak;
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
	 * setter for x
	 * @param newX
	 */
	public void setX(int newX) {
		this.x = newX;
	}
	
	/**
	 * getter for isDestroyed
	 * @return boolean - if the block is destroyed or not
	 */
	public boolean getIsDestroyed() {
		return this.isDestroyed;
	}
	
	/**
	 * getter for the height
	 * @return height
	 */
	public int getHeight() {
		return this.height;
	}
	
	/**
	 * getter for the width
	 * @return width
	 */
	public int getWidth() {
		return this.width;
	}
	
	/**
	 * to draw a brown block
	 */
	public void draw() {
		// only draw when the block is not destroyed
		if(!this.isDestroyed) {
			this.blockBreak.fill(165, 42, 42);
			this.blockBreak.rect(x, y, width, height);
		}
	}
	
	/**
	 * to change the block's status to be destroyed when it is
	 */
	public void destroy() {
		this.isDestroyed = true;
	}
	
	

}
