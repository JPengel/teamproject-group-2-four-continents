package edu.pacific.comp55.starter;
import acm.program.*;

import java.awt.event.MouseEvent;

import acm.graphics.*;

class PauseMenu  extends GraphicsPane {
	public static final int PROGRAM_WIDTH = 1920/2; 
	public static final int PROGRAM_HEIGHT = 1080/2;
	private GImage backGround= new GImage("src/main/resources/Background.png");
	private GImage exit = new GImage("src/main/resources/Exit.png",985/2,686/2);
	private GImage continuE = new GImage("src/main/resources/Continue.png",580/2,376/2);
	private GImage retry = new GImage("src/main/resources/Retry.png",580/2,686/2);
	private MainApplication Mapp;
	private boolean returN;
	private int flick;
	TimerMode timerModePause;
	NoWasteMode noWasteModePause;
	MainMenu mainMenu;
	
	
	public PauseMenu() {}
	public PauseMenu(Mode mode, MainApplication a) {
		if (mode instanceof NoWasteMode) {
			noWasteModePause = (NoWasteMode) mode;
			Mapp = a;
			flick = 1;
		} else {
			timerModePause = (TimerMode) mode;
			Mapp = a;
			flick = 2;
		}
		
		drawMenu();
	}
	
	public void drawMenu() {
	backGround.scale(.5);
	
	exit.scale(.5);
	
	continuE.scale(.5);
	
	retry.scale(.5);
	
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	GObject obj = Mapp.getElementAt(e.getX(),e.getY());
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
		if (flick == 1) {
			noWasteModePause.startTimer();
			hideContents();
			//Mapp.switchToScreen(noWasteModePause);
		}
		else if (flick == 2) {
			timerModePause.startTimer();
			hideContents();
			//Mapp.switchToScreen(timerModePause);
		}
		
	}
	
	public void retry() {
		hideContents();
		if (flick == 1) {
			noWasteModePause.resetAll();
			//Mapp.switchToScreen(noWasteModePause);
		}
		else if (flick == 2) {
			timerModePause.resetAll();
			///Mapp.switchToScreen(timerModePause);
		}
	}
	
	public void exit() {
		hideContents();
		if (flick == 1) {
			noWasteModePause.stopTimer();
			noWasteModePause.hideContents(); // adding this
			noWasteModePause.returnToMenu();
			
		}
		else if (flick == 2 ) {
			timerModePause.stopTimer();
			timerModePause.hideContents(); //adding this
			timerModePause.returnToMenu();
			
		}
	}

	public void init() {
		
	}
	
	@Override
	public void showContents() {     
		// TODO Auto-generated method stub
		Mapp.add(backGround);
		Mapp.add(exit);
		Mapp.add(continuE);
		Mapp.add(retry);
		if (flick == 1) {
			noWasteModePause.paused = true;
		} else if (flick == 2) {
			timerModePause.paused = true;
		}
		
	}
	@Override
	public void hideContents() {
		// TODO Auto-generated method stub
		Mapp.remove(backGround);
		Mapp.remove(retry);
		Mapp.remove(exit);
		Mapp.remove(continuE);
		if (flick == 1) {
			noWasteModePause.paused = false;
		} else if (flick == 2) {
			timerModePause.paused = false;
		}
	}
	public static void main(String[] args) {
	//new PauseMenu().start(); 
	}
	
}