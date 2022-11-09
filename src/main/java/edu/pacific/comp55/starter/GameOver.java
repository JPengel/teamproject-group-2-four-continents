package edu.pacific.comp55.starter;
import acm.program.*;

import java.awt.event.MouseEvent;

import acm.graphics.*;

class GameOver extends GraphicsProgram{
	public static final int PROGRAM_WIDTH = 1920/2; 
	public static final int PROGRAM_HEIGHT =1080/2;
	
	Gimage backGround = new GImage("src/main/resources/backgroundMainMenu.png");
	GImage blankPizza; // = new GImage();
	GImage cuttingBoard; //= new GImage();
	GImage quit;// = new GImage();
	GImage retry;// = newGimage();
	int baconCount;
	int cheeseCount;
	int eggCount; 
	int scoreCount;
	private int flick;
	TimerMode timerModePause;
	NoWasteMode noWasteModePause;
	
	public GameOver() {}
	
	public GameOver(int mode, int bacon, int cheese,int eggs) {
		flick = mode;
		baconCount = bacon;
		cheeseCount = cheese;
		eggCount = eggs;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		GObject obj = getElementAt(e.getX(),e.getY());
		if (obj == retry) {
			retry();
		}
		else if (obj == quit) {
			quit();		
		}
	}
	
	public void drawGameOver() {
		//TODO Draws the game over menu, would also call the drawPizza() function.
		drawPizza();
		
	}
	
	public void drawPizza() {
		//TODO Uses the variables to draw a pizza with the toppings.
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
	
	public void quit() {
		clear();
		noWasteModePause.returnToMenu();
	}

		
	public void run() {
		drawGameOver();
		addMouseListeners();
	}
	
	public void init() {
		setSize(PROGRAM_WIDTH,PROGRAM_HEIGHT);
	}
	
	public static void main(String[] args, int bacon, int cheese, int eggs) {
		new GameOver().start();
	}
}
