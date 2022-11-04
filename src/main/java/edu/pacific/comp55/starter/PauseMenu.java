package edu.pacific.comp55.starter;
import acm.program.*;
import acm.graphics.*;

class PauseMenu  extends GraphicsProgram {
	public static final int PROGRAM_WIDTH = 1920; 
	public static final int PROGRAM_HEIGHT = 1080;
	private GImage backGround= new GImage("pauseMenuImages/Background.png");
	private GImage exit = new GImage("pauseMenuImages/Exit.png",985,686);
	private GImage continuE = new GImage("pauseMenuImages/Continue.png",580,376);
	private GImage retry = new GImage("pauseMenuImages/Retry.png",580,686);
	private boolean returN;
	TimerMode timerModePause = new TimerMode();
	NoWasteMode noWasteModePause = new NoWasteMode();
	public void drawMenu() {
//		System.out.println("javier was here");
		add(backGround);
		add(exit);
		add(continuE);
		add(retry);
		
	}
	public void init() {
		 setSize(PROGRAM_WIDTH,PROGRAM_HEIGHT);
	}
	public void run() {
		drawMenu();
	}
	public static void main(String[] args) {
		new PauseMenu().start(); 
		
	}
}

