package edu.nyu.cs.jhp489;

import processing.core.*;
import java.util.ArrayList;

/**
 * A simple game using processing
 * You can move up, down, left, right with the keypad
 * You have to hit all the blocks and break them
 * You only have to save the yellow ball
 * @author jhp489
 */
public class BlockBreak extends PApplet {
	
	public final static int appMargin = 40;
	
	// classes used
	private Ball ball;
	private Ball slowBall;
	private Ball smallBall;
	
	private Bar bar;
	private MovingBlock movingBlock;
	private ArrayList<Block> blocks = new ArrayList<Block>(); // ArrayList to save the blocks
	
	private int blockSpacing = 50; // space between blocks
	private int numBlocks = 4; // number of blocks (none-moving)
	
	/**
	 * main method to control the flow of the program
	 * @param args
	 */
	public static void main(String[] args) {
		PApplet.main("edu.nyu.cs.jhp489.BlockBreak");
		
	}
	
	/**
	 * a getter for the ArrayList saving the blocks
	 * @return ArrayList with blocks
	 */
	public ArrayList<Block> getBlocks() {
		return this.blocks;
	}
	
	/**
	 * to set window size
	 */
	public void settings() {
		this.size(800, 600);
	}
	
	/**
	 * to set up the objects used in the program
	 */
	public void setup() {
		this.ball = new Ball(BlockBreak.appMargin, this.height / 2, this); 			// start around left-middle
		this.smallBall = new Ball(BlockBreak.appMargin, this.height / 2, this, 50);
		this.slowBall = new Ball(BlockBreak.appMargin, this.height / 2, this, 3, 3);
		
		this.bar = new Bar(this.width / 2, this.height - BlockBreak.appMargin, this);			 // start around bottom-middle
		
		int x = appMargin + 140; 			// x position of first block
		int y = appMargin + blockSpacing; 			// y position of first block
		
		// set up the blocks according to the number
		for (int i=0; i < numBlocks; i++) {
			Block block = new Block(x, y, this);
			blocks.add(block);
			x += block.getWidth() + this.blockSpacing;
			if (i == 0) {
				this.movingBlock = new MovingBlock(appMargin, y - block.getHeight() - this.blockSpacing, this); 			// create the moving block only once
			}
		}
	}
	
	/**
	 * to create animation
	 */
	public void draw() {
		this.background(0, 0, 0); // background is black
		
		// if the game is over print out appropriate text and don't draw anything else
		if (Ball.endGame(ball)) {
			this.fill(0, 255, 0);
			this.textSize(100);
			this.text("You lose", this.width / 4, this.height / 2);
		} else if (Ball.endGame(this.ball, this.ball.getNumContact())) {
			this.fill(0, 255, 0);
			this.textSize(100);
			this.text("You win", this.width / 4, this.height / 2);
		}
		
		// if the game is not over draw stuff
		else {
		this.ball.move();
		this.ball.drawMain();
		this.smallBall.move();
		this.smallBall.draw();

		this.slowBall.move();
		this.slowBall.draw();

		
		this.movingBlock.move();
		this.movingBlock.draw();
		
		this.bar.draw();
		
		for (Block block : blocks) {
			block.draw();
			// if the Ball contacts the block the block is destroyed and the ball bounces off
			if (Ball.isContact(this.ball, block)) {
				this.ball.setSpeedX(this.ball.getSpeedX() * -1);
				this.ball.setSpeedY(this.ball.getSpeedY() * -1);
				block.destroy();
			}
			if (Ball.isContact(this.smallBall, block)) {
				this.smallBall.setSpeedX(this.smallBall.getSpeedX() * -1);
				this.smallBall.setSpeedY(this.smallBall.getSpeedY() * -1);
				block.destroy();
			}
			if (Ball.isContact(this.slowBall, block)) {
				this.slowBall.setSpeedX(this.slowBall.getSpeedX() * -1);
				this.slowBall.setSpeedY(this.slowBall.getSpeedY() * -1);
				block.destroy();
			}
		}
			// if the Ball contacts the bar the ball bounces back
		if (Ball.isContact(this.ball, this.bar)) {
			this.ball.setSpeedX(this.ball.getSpeedX() * -1);
			this.ball.setSpeedY(this.ball.getSpeedY() * -1); 
		}
		
		if (Ball.isContact(this.smallBall, this.bar)) {
			this.smallBall.setSpeedX(this.smallBall.getSpeedX() * -1);
			this.smallBall.setSpeedY(this.smallBall.getSpeedY() * -1); 
		}
		
		if (Ball.isContact(this.slowBall, this.bar)) {
			this.slowBall.setSpeedX(this.slowBall.getSpeedX() * -1);
			this.slowBall.setSpeedY(this.slowBall.getSpeedY() * -1); 
		}
		
			// contact with the moving block is dealt the same way with the regular block contact
		if (Ball.isContact(this.ball, this.movingBlock)) {
			this.ball.setSpeedX(this.ball.getSpeedX() * -1);
			this.ball.setSpeedY(this.ball.getSpeedY() * -1);
			this.movingBlock.destroy();
		}
		if (Ball.isContact(this.smallBall, this.movingBlock)) {
			this.smallBall.setSpeedX(this.smallBall.getSpeedX() * -1);
			this.smallBall.setSpeedY(this.smallBall.getSpeedY() * -1);
			this.movingBlock.destroy();
		}
		
		if (Ball.isContact(this.slowBall, this.movingBlock)) {
			this.slowBall.setSpeedX(this.slowBall.getSpeedX() * -1);
			this.slowBall.setSpeedY(this.slowBall.getSpeedY() * -1);
			this.movingBlock.destroy();
		}
		
		}
	}
		
	
	/**
	 * to create interaction with the user
	 * a user can move the bar with the keypad
	 */
	public void keyPressed() {
		// get user input
		switch (this.keyCode) {
			case PConstants.LEFT:
				this.bar.goLeft(); //go left
				break;
			case PConstants.RIGHT:
				this.bar.goRight(); //go right
				break;
			case PConstants.UP:
				this.bar.goUp(); //go up
				break;
			case PConstants.DOWN:
				this.bar.goDown(); //go down
				break;
			}
		}
	}

