package edu.pacific.comp55.starter;
import acm.program.*;

import java.awt.event.MouseEvent;

import acm.graphics.*;
//comment
class PauseMenu  extends GraphicsPane {
	public static final int PROGRAM_WIDTH = 1920/2; 
	public static final int PROGRAM_HEIGHT = 1080/2;
	private GImage backGround= new GImage("src/main/resources/Background.png");
	private GImage exit = new GImage("src/main/resources/Exit.png",985/2,686/2);
	private GImage continuE = new GImage("src/main/resources/Continue.png",580/2,376/2);
	private GImage retry = new GImage("src/main/resources/Retry.png",580/2,686/2);
	private GraphicsApplication Gapp;
	private boolean returN;
	private int flick;
	TimerMode timerModePause;
	NoWasteMode noWasteModePause;
	MainMenu mainMenu;
	
	
	public PauseMenu() {}
	public PauseMenu(Mode mode, GraphicsApplication a) {
		if (mode instanceof NoWasteMode) {
		noWasteModePause = (NoWasteMode) mode;
			Gapp = a;
			flick = 1;
		} else {
		timerModePause = (TimerMode) mode;
			Gapp = a;
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
	GObject obj = Gapp.getElementAt(e.getX(),e.getY());
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
			Gapp.switchToScreen(noWasteModePause);
		}
		else if (flick == 2) {
			Gapp.switchToScreen(timerModePause);
		}
	}
	
	public void retry() {
		if (flick == 1) {
			noWasteModePause.resetAll();
			Gapp.switchToScreen(noWasteModePause);
		}
		else if (flick == 2) {
			timerModePause.resetAll();
			Gapp.switchToScreen(timerModePause);
		}
	}
	
	public void exit() {
		if (flick == 1) {
			noWasteModePause.returnToMenu(); // shouldnt we call return to menu ? 
		}
		else if (flick == 2 ) {
			timerModePause.returnToMenu();
		}
	}

	public void init() {
		
	}
	
	@Override
	public void showContents() {     // where is it being called
		// TODO Auto-generated method stub
		Gapp.add(backGround);
		Gapp.add(exit);
		Gapp.add(continuE);
		Gapp.add(retry);
	}
	@Override
	public void hideContents() {
		// TODO Auto-generated method stub
		Gapp.remove(backGround);
		Gapp.remove(retry);
		Gapp.remove(exit);
		Gapp.remove(continuE);
		
	}
	public static void main(String[] args) {
	//new PauseMenu().start(); 
	}
	
}