package edu.pacific.comp55.starter;
import acm.program.*;

import java.awt.event.MouseEvent;

import acm.graphics.*;

class GameOver extends GraphicsProgram{
	public static final int PROGRAM_WIDTH = 1920/2; 
	public static final int PROGRAM_HEIGHT =1080/2;
	
	GImage blankPizza;
	GImage quit;
	GImage retry;
	int baconCount;
	int cheeseCount;
	int eggCount; 
	int scoreCount;
	private int flick;
	TimerMode timerModePause;
	NoWasteMode noWasteModePause;
	
	public GameOver() {
		baconCount = 0;
		cheeseCount = 0;
		eggCount = 0;
	}
	public GameOver(TimerMode t, int bacon, int cheese,int eggs) {
		timerModePause = t;
		flick = 2;
		baconCount = bacon;
		cheeseCount = cheese;
		eggCount = eggs;
	}
	
	public GameOver(NoWasteMode w, int bacon, int cheese,int eggs) {
		noWasteModePause = w;
		flick = 1;
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
	}
	
	public void drawPizza(int b, int c, int e) {
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
		if (flick == 1) {
			noWasteModePause.returnToMenu();
		}
		else if (flick == 2) {
			timerModePause.returnToMenu();
		}
	}
	
	public void run() {
		drawGameOver();
		addMouseListeners();
	}
	
	public void init() {
		setSize(PROGRAM_WIDTH,PROGRAM_HEIGHT);
	}
	
	public static void main(String[] args) {
		new GameOver().start();
	}
}
