package edu.pacific.comp55.starter;
import acm.program.*;
import acm.util.RandomGenerator;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.*;
import acm.graphics.*;

public class Mode extends GraphicsPane{
	public static final int WINDOWS_WIDTH = 1920;
	public static final int WINDOWS_HEIGHT = 1080;
	private static RandomGenerator probability, toppingChooser, hazardChooser, upgradeChooser;
	protected ArrayList<Topping> objList;
	protected GImage pauseButton;
	protected GImage temp_Exit;
	protected GLabel bothScores;
	protected PauseMenu PMenu;
	protected GameOver gameOver;
	protected int baconSliced, cheeseSliced, eggSliced, scoreCounter, comboCounter = 1;
	protected ArrayList <Topping> toppingArray = new ArrayList<Topping>();
	protected Timer Timer;
	protected GLine comboLine;
	protected GImage wall;
	protected MainMenu MMenu;
	protected GraphicsApplication Gapp;
	private static boolean isTimerMode;
	private static double comboEntryX, comboEntryY, comboLaterX, comboLaterY, lineSlope = 0, lineB = 0;
	
	public Mode() {
		drawBoard();
	}
	
	public Mode(MainMenu m, GraphicsApplication x) {
		super();
		MMenu = m;
		Gapp = x;
		drawBoard();
	}
	
	
	public void drawBoard() {
		//TODO Calls all other draw functions.
		wall = new GImage("wall.jpg");
		wall.setSize(WINDOWS_WIDTH, WINDOWS_HEIGHT);
		
		pauseButton = new GImage("pauseButton.png",950,550);
		temp_Exit = new GImage("Exit.png");
		
	}
	
	public MainMenu getMMenu() {
		return MMenu;
	}
	
	
	
	public Topping getObject(int x, int y) {
		return null;
		//TODO Returns whatever topping is at the x and y coordinate.
	}
	
	public boolean cutObject(Topping a) {
		return false;
		//TODO Return true if topping.isCut() returns true.
	}
	
	public void resetAll() {
		//TODO returns all variables back to their original forms.
	}
	
	/*public void generateObject() {
		//TODO creates a new object.			
		int chance = probability.nextInt(1, 100);
		if(chance < 81) { //Toppings 80% chance
			objList.add(new Topping(ToppingType.values()[toppingChooser.nextInt(0,2)]));
		} else if (chance > 80 && chance < 91) { //Hazards 10% chance
			objList.add(new Topping(ToppingType.values()[hazardChooser.nextInt(3,4)]));
		} else { //Upgrades 10% chance
			if(isTimerMode) { 
				objList.add(new Topping(ToppingType.values()[upgradeChooser.nextInt(5,6)]));
			} else {
				if(chance % 2 == 0) {
					objList.add(new Topping(ToppingType.ROCK));
				}
			}
		}
	}*/
	
	public void exceedHighScore() {
		//TODO if scoreCounter is greater than or equal to highscore then 
		// make scoreCounter the new high score.
	}
	
	public void fallenOffScreen(Topping t) {
		//TODO If topping fell off, then delete it from Array List
	}
	
	public void deleteTopping(Topping t) {
		//TODO Deletes Image of topping from screen.
	}
	
	public boolean knifeSharpened() {
		return false;
		//TODO Returns true if knife is sharpened
	}
	
	public boolean inLine(Topping t) {
		return false;
		//TODO Retrun true if Topping is in comboLine.
	}
	//TODO: delete this
	public void stopTimer() {
		//TODO Stops timer.
	}
	public void startTimer() {
		//TODO Start TImer and set PMenu to null
		PMenu = null;
	}
	
	public void setPauseToNull() {
		PMenu = null;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("Hi");
		GObject x = Gapp.getElementAt(e.getX(), e.getY());
		System.out.println(x.toString());
		
		if(x == temp_Exit) {
			System.out.println("hi");
			Gapp.switchToScreen(MMenu);
		}
		else if(x == pauseButton) {
			System.out.println("Open Pause");
			PMenu = new PauseMenu(this, Gapp);
			Gapp.switchToPause(PMenu);
		}
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void showContents() {
		Gapp.add(wall);
		Gapp.add(pauseButton);
		Gapp.add(temp_Exit);
	}

	@Override
	public void hideContents() {
		Gapp.remove(wall);
		Gapp.remove(pauseButton);
	}

//	@Override
//	public void mouseClicked(MouseEvent e) {
//		GObject x = getElementAt(e.getX(), e.getY());
//		System.out.println(x);
//		if(x == pauseButton) {
//			PMenu = new PauseMenu();
//		}
//	}
	
}
