package edu.pacific.comp55.starter;
import acm.program.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import org.apache.commons.math3.analysis.function.Sqrt;

import acm.util.*;
import javafx.util.Pair;
import acm.graphics.*;

//comment
class GameOver extends GraphicsPane{
	public static final int PROGRAM_WIDTH = 1920/2; 
	public static final int PROGRAM_HEIGHT =1080/2;
	public static final String FONT = "Arial-Bold-70";
	
	GImage backGround = new GImage("src/main/resources/backgroundMainMenu.png");
	GImage blankPizza  = new GImage("src/main/resources/Pizzablank.png",960/2,100/2);
	GImage cuttingBoard = new GImage("src/main/resources/The_end.png",100/2,100/2);
	GImage quit = new GImage("src/main/resources/QuitgameOver.png",505/2,720/2);
	GImage retry = new GImage("src/main/resources/RetryGameOver.png",100/2, 720/2);
	GImage pizza_bacon = new GImage("src/main/resources/bacon_pizza.png");
	GImage pizza_cheese = new GImage ("src/main/resources/cheese_pizza.png");
	GImage pizza_egg = new GImage("src/main/resources/egg_pizza.png");
	private int baconCount, cheeseCount, eggCount, scoreCount, flick;
	GLabel baconC = new GLabel("" + baconCount,172/2, 470/2);
	GLabel cheeseC = new GLabel("" + cheeseCount, 407/2,470/2);
	GLabel eggC = new GLabel("" + eggCount, 627/2,470/2);
	GLabel totalScore = new GLabel("" + scoreCount, 407/2,648/2);
	private GraphicsApplication Gapp;
	TimerMode timerModeGameOver;
	NoWasteMode noWasteModeGameOver;
	ArrayList<GImage> images;
	private RandomGenerator rand = new RandomGenerator();
	
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
		baconC.setFont(FONT);
		cheeseC.setFont(FONT);
		eggC.setFont(FONT);
		totalScore.setFont("Arial-Bold-100");
		totalScore.setColor(Color.red);
		if(scoreCount > 9) {
		totalScore.setLocation(355/2,648/2);
		}
		
		
	}
	
	public void drawPizza() {
		//TODO Uses the variables to draw a pizza with the toppings.
		Gapp.add(blankPizza);
		addToppings();
	}

	
	private void addToppings() {
		int sum = baconCount + cheeseCount + eggCount;
		for(int i = 0; i < sum; i++) {
			int x = rand.nextInt(1, sum);	
			Pair<Double, Double> coords = generatePlace();
			if(x <= baconCount) {
				baconCount--;
				GImage bacon = new GImage("src/main/resources/bacon_pizza.png");
				bacon.setLocation(coords.getKey(), coords.getValue());
				Gapp.add(bacon);
				images.add(bacon);
			} else if (x > baconCount && x <= cheeseCount + baconCount) {
				cheeseCount--;
				GImage cheese = new GImage("src/main/resources/cheese_pizza.png");
				cheese.setLocation(coords.getKey(), coords.getValue());
				Gapp.add(cheese);
				images.add(cheese);
			} else {
				eggCount--;
				GImage egg = new GImage("src/main/resources/egg_pizza.png");
				egg.setLocation(coords.getKey(), coords.getValue());
				Gapp.add(egg);
				images.add(egg);
			}
		}
	}
	
	private Pair<Double, Double> generatePlace() {
		Double x = rand.nextDouble(blankPizza.getX(),blankPizza.getX()+ blankPizza.getWidth());
		Double y = rand.nextDouble(blankPizza.getY(),blankPizza.getY()+ blankPizza.getHeight());
		Pair <Double, Double> coords = new Pair<>(x,y);
		while (!insidePizza(x, y)) {
			x = rand.nextDouble(blankPizza.getX(),blankPizza.getX()+ blankPizza.getWidth());
			y = rand.nextDouble(blankPizza.getY(),blankPizza.getY()+ blankPizza.getHeight());
		}
		return coords;
	}

	private boolean insidePizza(double x, double y) {
		double radius = blankPizza.getWidth()/2;
		if(radius > distanceFromRadius(x, y)) {
			return true;
		} else {
			return false;
		}
	}

	private double distanceFromRadius(double x, double y) {
		double centerX = (blankPizza.getX()+ blankPizza.getWidth())/2;
		double centerY = (blankPizza.getY()+ blankPizza.getHeight())/2;
		double a = centerX - x, b = centerY - y;
		return Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
	}
	
	
	@Override
	public void showContents() {
		// TODO Auto-generated method stub
		Gapp.add(backGround);
		Gapp.add(cuttingBoard);
		Gapp.add(quit);
		Gapp.add(retry);
		Gapp.add(baconC);
		Gapp.add(cheeseC);
		Gapp.add(eggC);
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
		Gapp.remove(blankPizza);
		Gapp.remove(baconC);
		Gapp.remove(cheeseC);
		Gapp.remove(eggC);
		Gapp.remove(totalScore);
		for(int i = 0; i < images.size(); i++) {
			Gapp.remove(images.get(i));
		}
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
