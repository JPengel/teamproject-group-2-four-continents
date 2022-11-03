package edu.pacific.comp55.starter;
import acm.program.*;
import acm.graphics.*;
// comments
//comments
class PauseMenu  extends GraphicsProgram {
	public static final int PROGRAM_WIDTH = 1920; // change this
	public static final int PROGRAM_HEIGHT = 1080;// change this
	private GImage backGround;
	private GImage exit;
	private GImage continuE;
	private GImage retry;
	private boolean returN;
	 public void init() {
		 setSize(PROGRAM_WIDTH,PROGRAM_HEIGHT);
	 }
	public void run() {
		
	}
	public static void main(String[] args) {
		new PauseMenu().start();
	}
}

