package edu.pacific.comp55.starter;
import acm.program.*;
import acm.util.*;

import javax.swing.Timer;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.*;
import acm.graphics.*;

public class Mode extends GraphicsPane implements ActionListener{
	public static final int WINDOWS_WIDTH = 1920;
	public static final int WINDOWS_HEIGHT = 1080;
	private static RandomGenerator probability = new RandomGenerator(), toppingChooser = new RandomGenerator(), hazardChooser = new RandomGenerator(), upgradeChooser = new RandomGenerator();
	protected ArrayList<Topping> objList = new ArrayList<Topping>();
	protected GImage pauseButton, button;
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
	protected MainApplication Gapp;
	private static boolean isTimerMode;
	private static double comboEntryX, comboEntryY, comboLaterX, comboLaterY, lineSlope = 0, lineB = 0;
	protected boolean paused;
	
	public Mode() {
		drawBoard();
		paused = false;
		System.out.println("Mode Constructor");
	}
	
	public Mode(MainMenu m, MainApplication x) {
		super();
		MMenu = m;
		Gapp = x;
		drawBoard();
		Timer = new Timer(1000,this);
		paused = false;
		System.out.println("Mode Constructor");
	}
	
	
	public void drawBoard() {
		//TODO Calls all other draw functions.
		wall = new GImage("wall.jpg");
		wall.setSize(WINDOWS_WIDTH, WINDOWS_HEIGHT);
		pauseButton = new GImage("pauseButton.png",950,550);
		temp_Exit = new GImage("Exit.png");
		button = new GImage("lives0.png", 500, 500);
		
		
	}
	
	public MainMenu getMMenu() {
		return MMenu;
	}
	
	
	
	public Topping getObject(int x, int y) {
		GObject currTopping = Gapp.getElementAt(x,y); 
		for(Topping t : objList) {
			if (t.getCurX()*-1 == currTopping.getX() && t.getCurY()*-1 == currTopping.getY() && t.getImage() == currTopping) {
				return t;
			}
		}
		return null;
		//TODO Returns whatever topping is at the x and y coordinate.
	}
	
	public boolean cutObject(Topping a) {
		if(a.isCut() == false) {
			a.setCut();
			return true;
		}
		else {return false;}
		//TODO Return true if topping.isCut() returns true.
	}
	
	public void resetAll() {
		//TODO returns all variables back to their original forms.
	}
	
	public void generateObject() {
		//TODO creates a new object.			
		int chance = probability.nextInt(1, 100);
		if(chance < 81) { //Toppings 80% chance
			objList.add(new Topping(ToppingType.values()[toppingChooser.nextInt(0,2)], Gapp));
		} else if (chance > 80 && chance < 91) { //Hazards 10% chance
			objList.add(new Topping(ToppingType.values()[hazardChooser.nextInt(3,4)], Gapp));
		} else { //Upgrades 10% chance
			if(isTimerMode) { 
				objList.add(new Topping(ToppingType.values()[upgradeChooser.nextInt(5,6)],Gapp));
			} else {
				if(chance % 2 == 0) {
					objList.add(new Topping(ToppingType.ROCK, Gapp));
				}
			}
		}
	}
	
	public void exceedHighScore() {
		//TODO if scoreCounter is greater than or equal to highscore then 
		// make scoreCounter the new high score.
	}
	//TODO Discuss WIth LINH AND KIBA TOMORROW ABOUT ERROR
	public boolean fallenOffScreen(Topping t) {
		//TODO If topping fell off, then delete it from Array List
		if(t.shouldMove() == false) {
			deleteTopping(t);
			return true;
		}
		return false;
	}
	
	public void deleteTopping(Topping t) {
		//TODO Deletes Image of topping from screen.
		Gapp.remove(t.getImage());
		objList.remove(t);
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
		Timer.start();
	}
	
	public void setPauseToNull() {
//		PMenu = null;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("Hi");
		GObject x = Gapp.getElementAt(e.getX(), e.getY());
		System.out.println(x.toString());
		
		if (paused) {
			paused = false;
			PMenu.mouseClicked(e);
		}
		else if(x == temp_Exit) {
			
			Gapp.switchToScreen(MMenu);
		}
		else if(x == pauseButton) {
			System.out.println("Open Pause");
			Timer.stop();
			PMenu = new PauseMenu(this, Gapp);
			PMenu.showContents();
		}
		else if(x == button) {
			
			gameOver = new GameOver(this, Gapp,10,10,10);
			Gapp.switchToScreen(gameOver);
			System.out.println("hi");
			paused = true;
		}
		
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		if(getObject(e.getX(),e.getY()) != null) {
			cutObject(getObject(e.getX(),e.getY()));
		}
	}

	@Override
	public void showContents() {
		Gapp.add(wall);
		Gapp.add(pauseButton);
		Gapp.add(temp_Exit);
		Gapp.add(button);
	}

	@Override
	public void hideContents() {
		Gapp.remove(wall);
		Gapp.remove(pauseButton);
		Gapp.remove(button);
	}
	
	public void returnToMenu() {
		//TODO Has Main Menu call isTimeOver()
		PMenu = null;
		gameOver = null;
		Gapp.switchToScreen(MMenu);
	}
	
    @Override
    public void actionPerformed(ActionEvent e) {
    	generateObject();
    	for (Topping t: objList) {
    		t.moveTopping();
    		fallenOffScreen(t);
    	}
    }
	
}
