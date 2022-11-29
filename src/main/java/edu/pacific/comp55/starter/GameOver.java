package edu.pacific.comp55.starter;
import acm.program.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.awt.Font;
import java.io.IOException;

import org.apache.commons.math3.analysis.function.Sqrt;

import acm.util.*;
import javafx.util.Pair;
import acm.graphics.*;

//comment
class GameOver extends GraphicsPane{
	public static final int PROGRAM_WIDTH = 1920/2, PROGRAM_HEIGHT =1080/2, CRUST_SIZE = 80;
	public static final String FONT = "Arial-Bold-50";
	
	GImage backGround = new GImage("src/main/resources/backgroundMainMenu.png");
	GImage blankPizza  = new GImage("src/main/resources/Pizzablank.png",960/2,100/2);
	GImage cuttingBoard = new GImage("src/main/resources/The_end.png",100/2,100/2);
	GImage quit = new GImage("src/main/resources/QuitgameOver.png",505/2,720/2);
	GImage retry = new GImage("src/main/resources/RetryGameOver.png",100/2, 720/2);
	GImage pizza_bacon = new GImage("src/main/resources/bacon_pizza.png");
	GImage pizza_cheese = new GImage ("src/main/resources/cheese_pizza.png");
	GImage pizza_egg = new GImage("src/main/resources/egg_pizza.png");
	GImage bacon = new GImage("src/main/resources/bacon.png",270/2,380/2);
	GImage cheese = new GImage("src/main/resources/cheese.png",495/2,380/2);
	GImage egg = new GImage("src/main/resources/egg.png",725/2,380/2);
	private int baconCount, cheeseCount, eggCount, scoreCount, flick;
	GLabel baconC; 
	GLabel cheeseC;  
	GLabel eggC;
	GLabel totalScore;
	private MainApplication Mapp;
	MainMenu menu; 
	TimerMode timerModeGameOver;
	NoWasteMode noWasteModeGameOver;
	ArrayList<GImage> images = new ArrayList<GImage>();
	private RandomGenerator rand = new RandomGenerator();
	Font Noto;
	
	public GameOver() {}
	
	public GameOver(Mode mode, MainApplication a, int bacon, int cheese, int eggs) {
//		try {
//		Noto = Font.createFont(Font.TRUETYPE_FONT, new File("NotoColorEmoji-Regular.ttf")).deriveFont(30f);
//		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//		ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("NotoColorEmoji-Regular.ttf")));
//		}
//		catch(IOException | FontFormatException e) {
//			
//		}
		if (mode instanceof NoWasteMode) {
			noWasteModeGameOver = (NoWasteMode) mode;
			flick = 1;
		} else {
			timerModeGameOver = (TimerMode) mode;
			flick = 2;
		}
		Mapp = a;
		baconCount = bacon;
		baconC = new GLabel(String.valueOf(baconCount),600/2, 260/2);
		eggCount = eggs;
		eggC = new GLabel(String.valueOf(eggCount),600/2, 430/2);
		cheeseCount = cheese;
		cheeseC = new GLabel(String.valueOf(cheeseCount), 600/2, 610/2);
		totalScore = new GLabel(String.valueOf(mode.scoreCounter));
		if(mode.scoreCounter <10) {
			totalScore.setLocation(250/2, 580/2);
		} else if (mode.scoreCounter <99 && mode.scoreCounter > 10) {
			totalScore.setLocation(200/2, 580/2);
		}
		else {
			totalScore.setLocation(150/2, 580/2);
		}
		drawGameOver();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		GObject obj = Mapp.getElementAt(e.getX(),e.getY()); // why do we need the gapp before? 
		if (obj == retry) {
			retry();
		} else if (obj == quit) {
			quit();		
		}
	}
	
	public void drawGameOver() {
		backGround.scale(.5);
		cuttingBoard.scale(.5);
		quit.scale(.5);
		retry.scale(.5);
		blankPizza.scale(.5);
		baconC.setFont(FONT);
		cheeseC.setFont(FONT);
		eggC.setFont(FONT);
		totalScore.setFont(FONT);
		totalScore.setColor(Color.red);
		egg.scale(.5);
		bacon.scale(.5);
		cheese.scale(.5);
	}
	
	public void drawPizza() {
		Mapp.add(blankPizza);
		addToppings();
	}

	
	private void addToppings() {
		int sum = baconCount + cheeseCount + eggCount;
		int i = 0;
		while(sum > 0) {
			int x = rand.nextInt(1, sum);	
			Pair<Double, Double> coords = generatePlace();
			if(x <= baconCount) {
				baconCount--;
				GImage bacon = new GImage("src/main/resources/bacon_pizza.png");
				bacon.setLocation(coords.getKey(), coords.getValue());
				Mapp.add(bacon);
				images.add(bacon);
			} else if (x > baconCount && x <= cheeseCount + baconCount) {
				cheeseCount--;
				GImage cheese = new GImage("src/main/resources/cheese_pizza.png");
				cheese.setLocation(coords.getKey(), coords.getValue());
				Mapp.add(cheese);
				images.add(cheese);
			} else {
				eggCount--;
				GImage egg = new GImage("src/main/resources/egg_pizza.png");
				egg.setLocation(coords.getKey(), coords.getValue());
				Mapp.add(egg);
				images.add(egg);
			}
			sum = baconCount + cheeseCount + eggCount;
			i++;
			//System.out.println("SUM " + i + ": " + sum);
		}
	}
	
	private Pair<Double, Double> generatePlace() {
		Double x = rand.nextDouble(blankPizza.getX(), (blankPizza.getX()+ blankPizza.getWidth()));
		Double y = rand.nextDouble(blankPizza.getY(), (blankPizza.getY()+ blankPizza.getHeight()));
		//System.out.println("----New Object---");
		while (!insidePizza(x, y)) {
			x = rand.nextDouble(blankPizza.getX(),blankPizza.getX()+ blankPizza.getWidth());
			y = rand.nextDouble(blankPizza.getY(),blankPizza.getY()+ blankPizza.getHeight());
		}
		return new Pair<>(x,y);
	}

	private boolean insidePizza(double x, double y) {
		double radius = blankPizza.getWidth()/2;
		//System.out.println("Distance from Radius: " + distanceFromRadius(x,y) + "   | x: " + x + "   y: " + y);
		if((radius - CRUST_SIZE) > distanceFromRadius(x, y)) {
			//System.out.println(radius > distanceFromRadius(x, y));
			return true;
		} else {
			return false;
		}
	}

	private double distanceFromRadius(double x, double y) {
		double centerX = blankPizza.getX() + blankPizza.getWidth() / 2 - 35;
		double centerY = blankPizza.getY() + blankPizza.getHeight() / 2 - 35;
		//System.out.println("Center X: " + centerX + "   Center Y: " + centerY);
		double a = centerX - x, b = centerY - y;
		return Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
	}
	
	
	@Override
	public void showContents() {
		Mapp.add(backGround);
		Mapp.add(cuttingBoard);
		Mapp.add(quit);
		Mapp.add(retry);
		Mapp.add(baconC);
		Mapp.add(cheeseC);
		Mapp.add(eggC);
		Mapp.add(totalScore);
		drawPizza();
		
	}

	@Override
	public void hideContents() {
		// TODO Auto-generated method stub
		Mapp.remove(backGround);
		Mapp.remove(cuttingBoard);
		Mapp.remove(quit);
		Mapp.remove(retry);
		Mapp.remove(blankPizza);
		Mapp.remove(baconC);
		Mapp.remove(cheeseC);
		Mapp.remove(eggC);
		Mapp.remove(totalScore);
		Mapp.remove(bacon);
		Mapp.remove(cheese);
		Mapp.remove(egg);
		for(int i = 0; i < images.size(); i++) {
			Mapp.remove(images.get(i));
		}
	}
	
	public void retry() {
		Mapp.removeAll();
		if (flick == 1) {
			noWasteModeGameOver = new NoWasteMode(menu,Mapp);
			Mapp.switchToScreen(noWasteModeGameOver);
		}
		else if (flick == 2) {
			timerModeGameOver = new TimerMode(menu,Mapp);
			Mapp.switchToScreen(timerModeGameOver);
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
		
	}

