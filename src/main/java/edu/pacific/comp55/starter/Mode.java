package edu.pacific.comp55.starter;
import acm.program.*;
import acm.util.RandomGenerator;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.*;
import acm.graphics.*;

public class Mode extends GraphicsProgram{
	public static final int WINDOWS_WIDTH = 1920;
	public static final int WINDOWS_HEIGHT = 1080;
	private static RandomGenerator probability, toppingChooser, hazardChooser, upgradeChooser;
	protected ArrayList<Topping> objList;
	protected GImage pauseButton;
	protected GLabel bothScores;
	protected PauseMenu PMenu;
	protected GameOver gameOver;
	protected int baconSliced, cheeseSliced, eggSliced, scoreCounter, comboCounter = 1;
	protected ArrayList <Topping> toppingArray = new ArrayList<Topping>();
	protected Timer Timer;
	protected GLine comboLine;
	protected MainMenu MMenu;
	private static boolean isTimerMode;
	private static double comboEntryX, comboEntryY, comboLaterX, comboLaterY, lineSlope = 0, lineB = 0;
	
	public Mode(MainMenu m) {
		MMenu = m;
	}
	
	public void init() {
		setSize(WINDOWS_WIDTH, WINDOWS_HEIGHT);
	}
	
	public void run() {
		System.out.println("Hello");
	}
	
	public void drawBoard() {
		//TODO Calls all other draw functions.
	}
	
	public void drawPauseButton() {
		//TODO Inserts Image of PauseButton.
	}
	
	public void drawScore() {
		//TODO Draws the current score and highscore.
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
	
	public void generateObject() {
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
	}
	
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
	
	public void stopTimer() {
		//TODO Stops timer.
	}
	public void startTimer() {
		//TODO Starts timer again.
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(getElementAt(e.getX(), e.getY()) == pauseButton) {
			PMenu = new PauseMenu();
		}
	}
	public static void main (String[] args, MainMenu m) {
		new Mode(m).start();
	}
}
