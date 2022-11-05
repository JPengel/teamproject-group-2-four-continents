package edu.pacific.comp55.starter;
import acm.program.*;

import java.awt.event.MouseEvent;

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
	private int flick;
	TimerMode timerModePause;
	NoWasteMode noWasteModePause;
	
	public PauseMenu() {
		
	}
	public PauseMenu(TimerMode timerMode) {
		timerModePause = timerMode;
		flick = 2;
	}
	public PauseMenu(NoWasteMode noWasteMode) {
		noWasteModePause = noWasteMode;
		flick = 1;
	}
	public void drawMenu() {
		//System.out.println("javier was here");
		add(backGround);
		add(exit);
		add(continuE);
		add(retry);
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		GObject obj = getElementAt(e.getX(),e.getY());
		if(obj == continuE) {
			clear();
			if (flick == 1) {
				noWasteModePause.startTimer();
			}
			else if (flick == 2) {
				timerModePause.startTimer();
			}
		}
	}
	public void init() {
		 setSize(PROGRAM_WIDTH,PROGRAM_HEIGHT);
	}
	public void run() {
		drawMenu();
		addMouseListeners();
	}
	public static void main(String[] args) {
		new PauseMenu().start(); 
		
	}
}

