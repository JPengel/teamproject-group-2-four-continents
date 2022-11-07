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
	
	public GameOver(int bacon, int cheese,int eggs) {
		baconCount = bacon;
		cheeseCount = cheese;
		eggCount = eggs;
	}
	public GameOver(TimerMode t, int bacon, int cheese,int eggs) {
		
	}
	
	public GameOver(NoWasteMode w, int bacon, int cheese,int eggs) {
		
	}
	
	public void drawGameOver() {
		
	}
	
	public void drawPizza() {
		
	}
	
	public void retry() {
		
	}
	
	public void quit() {
		
	}
	public void run() {
		drawGameOver();
		addMouseListeners();
	}
}
