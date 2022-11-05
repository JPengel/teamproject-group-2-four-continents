package edu.pacific.comp55.starter;
import acm.program.*;
import acm.graphics.*;
//comment
class PauseMenu  extends GraphicsProgram {
	public static final int PROGRAM_WIDTH = 1920; 
	public static final int PROGRAM_HEIGHT = 1080;
	private GImage backGround= new GImage("src/main/resources/Background.png");
	private GImage exit = new GImage("src/main/resources/Exit.png",985,686);
	private GImage continuE = new GImage("src/main/resources/Continue.png",580,376);
	private GImage retry = new GImage("src/main/resources/Retry.png",580,686);
	private boolean returN;
	TimerMode timerModePause;
	NoWasteMode noWasteModePause;
	
	public PauseMenu() {
		
	}
	public PauseMenu(TimerMode timerMode) {
		timerModePause = new TimerMode();
	}
	public PauseMenu(NoWasteMode noWasteMode) {
		noWasteModePause = new NoWasteMode();
	}
	public void drawMenu() {
		//System.out.println("javier was here");
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

