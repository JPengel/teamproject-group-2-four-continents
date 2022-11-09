package edu.pacific.comp55.starter;
import acm.program.*;

import java.awt.event.MouseEvent;

import acm.graphics.*;

class GameOver extends GraphicsProgram{
	public static final int PROGRAM_WIDTH = 1920/2; 
	public static final int PROGRAM_HEIGHT =1080/2;
	
	GImage backGround = new GImage("src/main/resources/backgroundMainMenu.png");
	GImage blankPizza  = new GImage("src/main/resources/Pizzablank.png",960/2,100/2);
	GImage cuttingBoard = new GImage("src/main/resources/The_end.png",100/2,100/2);
	GImage quit = new GImage("src/main/resources/QuitgameOver.png",505/2,720/2);
	GImage retry = new GImage("src/main/resources/RetryGameOver.png",100/2, 720/2);
	int baconCount;
	int cheeseCount;
	int eggCount; 
	int scoreCount;
	private int flick;
	TimerMode timerModePause;
	NoWasteMode noWasteModePause;
	
	public GameOver() {}
	
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
		backGround.scale(.5);
		cuttingBoard.scale(.5);
		quit.scale(.5);
		retry.scale(.5);
		add(backGround);
		add(cuttingBoard);
		add(quit);
		add(retry);
		drawPizza();
		
	}
	
	public void drawPizza() {
		//TODO Uses the variables to draw a pizza with the toppings.
		blankPizza.scale(.5);
		add(blankPizza);
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
	
	public static void main(String[] args) {
		new GameOver().start();
	}
}
