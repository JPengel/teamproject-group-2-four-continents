package edu.pacific.comp55.starter;
import acm.program.*;
import java.awt.*;
import java.awt.event.MouseEvent;

import acm.graphics.*;

class GameOver extends GraphicsPane{
	public static final int PROGRAM_WIDTH = 1920/2; 
	public static final int PROGRAM_HEIGHT =1080/2;
	public static final String FONT = "Arial-Bold-70";
	
	GImage backGround = new GImage("src/main/resources/backgroundMainMenu.png");
	GImage blankPizza  = new GImage("src/main/resources/Pizzablank.png",960/2,100/2);
	GImage cuttingBoard = new GImage("src/main/resources/The_end.png",100/2,100/2);
	GImage quit = new GImage("src/main/resources/QuitgameOver.png",505/2,720/2);
	GImage retry = new GImage("src/main/resources/RetryGameOver.png",100/2, 720/2);
	int baconCount;
	int cheeseCount;
	int eggCount; 
	int scoreCount;
	GLabel bacon = new GLabel("" + baconCount,172/2, 470/2);
	GLabel cheese = new GLabel("" + cheeseCount, 407/2,470/2);
	GLabel egg = new GLabel("" + eggCount, 627/2,470/2);
	GLabel totalScore = new GLabel("" + scoreCount, 407/2,648/2);
	private int flick;
	private GraphicsApplication Gapp;
	TimerMode timerModeGameOver;
	NoWasteMode noWasteModeGameOver;
	
	public GameOver() {}
	
	public GameOver(Mode mode, GraphicsApplication a, int bacon, int cheese,int eggs) {
		if (mode instanceof NoWasteMode) {
			noWasteModeGameOver = (NoWasteMode) mode;
			flick = 1;
		} 
		else {
			timerModeGameOver = (TimerMode) mode;
			flick = 2;
		}
		Gapp = a;
		baconCount = bacon;
		cheeseCount = cheese;
		eggCount = eggs;
		drawGameOver();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		GObject obj = Gapp.getElementAt(e.getX(),e.getY()); // why do we need the gapp before? 
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
		blankPizza.scale(.5);
		bacon.setFont(FONT);
		cheese.setFont(FONT);
		egg.setFont(FONT);
		totalScore.setFont("Arial-Bold-100");
		totalScore.setColor(Color.red);
		if(scoreCount > 9) {
		totalScore.setLocation(355/2,648/2);
		}
		
		
	}
	
	public void drawPizza() {
		//TODO Uses the variables to draw a pizza with the toppings.
		Gapp.add(blankPizza);
		
	}
	
	@Override
	public void showContents() {
		// TODO Auto-generated method stub
		Gapp.add(backGround);
		Gapp.add(cuttingBoard);
		Gapp.add(quit);
		Gapp.add(retry);
		Gapp.add(bacon);
		Gapp.add(cheese);
		Gapp.add(egg);
		Gapp.add(totalScore);
		drawPizza();
		
	}

	@Override
	public void hideContents() {
		// TODO Auto-generated method stub
		Gapp.remove(backGround);
		Gapp.remove(cuttingBoard);
		Gapp.remove(quit);
		Gapp.remove(retry);
	}
	
	public void retry() {
		if (flick == 1) {
			noWasteModeGameOver.resetAll();
		}
		else if (flick == 2) {
			timerModeGameOver.resetAll();
		}
	}
	
	public void quit() {
		if (flick == 1) {
			noWasteModeGameOver.returnToMenu();
		}
		else if (flick == 2) {
			timerModeGameOver.returnToMenu();
		}
	}
	
//	public void run() {
//		drawGameOver();
//		addMouseListeners();
//	}
	
//	public void init() {
//		//setSize(PROGRAM_WIDTH,PROGRAM_HEIGHT);
//	}
	
//	public static void main(String[] args) {
//		new GameOver().start();
//	}

	
}
