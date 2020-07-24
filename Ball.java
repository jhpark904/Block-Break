package edu.nyu.cs.jhp489;

/**
 * Ball class to be used in the BlockBreak game
 * @author jhp489
 *
 */
public class Ball {
	private BlockBreak blockBreak;	
	private int x, y;		// x and y position
	
	// speed in x and y direction
	private int speedX = 5;		//speed in x direction
	private int speedY = 5;		//speed in y direction
	private int ballDiameter = 110;		// diameter of the ball
	private int newX = BlockBreak.appMargin;		// newX position to be used for animation
	private int newY = BlockBreak.appMargin;		//newY position to be used for animation
	
	private static int numContact = 0;		// counts how many contact all the balls has made with the blocks
	
	/**
	 * getter for speedX
	 * @return speedX
	 */
	public int getSpeedX() {
		return this.speedX;
	}
	
	/**
	 * setter for speedX
	 * @param newSpeedX
	 */
	public void setSpeedX(int newSpeedX) {
		this.speedX = newSpeedX;
	}
	
	/**
	 * getter for speedY
	 * @return speedY
	 */
	public int getSpeedY() {
		return this.speedY;
	}
	
	/**
	 * setter for speedY
	 * @param newSpeedY
	 */
	public void setSpeedY(int newSpeedY) {
		this.speedY = newSpeedY;
	}
	
	/**
	 * getter for the numContact
	 * @return numContact
	 */
	public int getNumContact() {
		return Ball.numContact;
	}
	
	/**
	 * setter for numContact
	 * @param newNumContact
	 */
	public void setNumContact(int newNumContact) {
		Ball.numContact = newNumContact;
	}
	
	/**
	 * constructor of the ball class
	 * @param x - x position
	 * @param y - y position
	 * @param blockBreak - blockBreak class
	 */
	public Ball(int x, int y, BlockBreak blockBreak) {
		this.blockBreak = blockBreak;
		this.x = x;
		this.y = y;
	}
	
	/**
	 * constructor for the ball with different speed
	 * @param x
	 * @param y
	 * @param blockBreak
	 * @param speedX
	 * @param speedY
	 */
	public Ball(int x, int y, BlockBreak blockBreak, int speedX, int speedY) {
		this.blockBreak = blockBreak;
		this.x = x;
		this.y = y;
		this.speedX = speedX;
		this.speedY = speedY;
	}
	
	/**
	 * constructor for the ball with different size
	 * @param x
	 * @param y
	 * @param blockBreak
	 * @param ballDiameter
	 */
	public Ball(int x, int y, BlockBreak blockBreak, int ballDiameter) {
		this.blockBreak = blockBreak;
		this.x = x;
		this.y = y;
		this.ballDiameter = ballDiameter;
	}
	
	/**
	 * draw the green  non-main ball
	 */
	public void draw() {
		this.blockBreak.fill(0, 128, 0);
		this.blockBreak.ellipse(this.x, this.y, ballDiameter, ballDiameter);
	}
	
	/**
	 * draw the yellow (main) ball
	 */
	public void drawMain() {
		this.blockBreak.fill(255, 255, 0);
		this.blockBreak.ellipse(this.x, this.y, ballDiameter, ballDiameter);
	}
	
	/**
	 * to set the ball position at once
	 * @param x - new x position
	 * @param y - new y position
	 */
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * to check if the ball contacted the bar
	 * @param ball
	 * @param bar
	 * @return boolean - if it contacted or not
	 */
	public static boolean isContact(Ball ball, Bar bar) {
		boolean isContact = false;
		boolean barcontact1 = ball.x + ball.ballDiameter >= bar.getX() && ball.x <= bar.getX() + bar.getBarWidth();
		boolean barcontact2 = ball.y + ball.ballDiameter / 2 >= bar.getY() && ball.y < bar.getY();
		if (barcontact1 && barcontact2) {
			isContact = true;
		}
		return isContact;
	}
	
	/**
	 * to check if the ball contacted the block
	 * @param ball
	 * @param block
	 * @return boolean - if it contacted or not
	 */
	public static boolean isContact(Ball ball, Block block) {
		boolean isContact = false;
		boolean blockContact1 = ball.x + ball.ballDiameter >= block.getX() && ball.x <= block.getX() + block.getWidth();
		boolean blockContact2 = ball.y >= block.getY() - ball.ballDiameter / 2 && ball.y <= block.getY() + ball.ballDiameter;
		if (blockContact1 && blockContact2 && !block.getIsDestroyed()) {
			isContact = true;
			Ball.numContact++;
		}
		return isContact;
	}
		
	/**
	 * to check if the main ball falls out of the window
	 * @param ball
	 * @return boolean - if the game is over or not
	 */
	public static boolean endGame(Ball ball) {
		if (ball.y > ball.blockBreak.height) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * to check if all the blocks are destroyed
	 * @param ball
	 * @param numContact
	 * @return boolean - if the game is over or not
	 */
	public static boolean endGame(Ball ball, int numContact) {
		if (numContact == ball.blockBreak.getBlocks().size() + 1) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * to move the ball and make it not fall out of window (except bottom)
	 */
	public void move() {
		
		if (newX > this.blockBreak.width - BlockBreak.appMargin) this.speedX *= -1;
		else if (newX < BlockBreak.appMargin) this.speedX *= -1;
		if (newY < BlockBreak.appMargin) this.speedY *= -1;
		
		newX = this.x + this.speedX;
		newY = this.y + this.speedY;
		
		this.setPosition(newX, newY);
	}
	


}
