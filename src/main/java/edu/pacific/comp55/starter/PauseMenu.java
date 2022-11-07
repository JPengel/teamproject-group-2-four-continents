package edu.pacific.comp55.starter;
import acm.program.*;

import java.awt.event.MouseEvent;

import acm.graphics.*;
//comment
class PauseMenu  extends GraphicsProgram {
	public static final int PROGRAM_WIDTH = 1920/2; 
	public static final int PROGRAM_HEIGHT = 1080/2;
	private GImage backGround= new GImage("src/main/resources/Background.png");
	private GImage exit = new GImage("src/main/resources/Exit.png",985/2,686/2);
	private GImage continuE = new GImage("src/main/resources/Continue.png",580/2,376/2);
	private GImage retry = new GImage("src/main/resources/Retry.png",580/2,686/2);
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
		backGround.scale(.5);
		add(backGround);
		exit.scale(.5);
		add(exit);
		continuE.scale(.5);
		add(continuE);
		retry.scale(.5);
		add(retry);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		GObject obj = getElementAt(e.getX(),e.getY());
		if (obj == continuE) {
			continueGame();
		}
		else if (obj == retry) {
			retry();
		}
		else if (obj == exit) {
			exit();		
		}
	}
	
	public void continueGame() {
		clear();
		if (flick == 1) {
			noWasteModePause.startTimer();
		}
		else if (flick == 2) {
			timerModePause.startTimer();
		}
	}
	
	public void retry() {
		clear();
		if (flick == 1) {
			noWasteModePause.resetAll();
		}
		else if (flick == 2) {
			timerModePause.resetAll();
		}
	}
	
	public void exit() {
		clear();
		if (flick == 1) {
			noWasteModePause.returnToMenu();
		}
		else if (flick == 2) {
			timerModePause.returnToMenu();
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

