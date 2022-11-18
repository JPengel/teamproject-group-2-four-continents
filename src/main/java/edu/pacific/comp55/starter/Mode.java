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
//koba
public class Mode extends GraphicsPane implements ActionListener{
	
	//VARIABLES
	public static final int WINDOWS_WIDTH = 1920/2, WINDOWS_HEIGHT = 1080/2;
	private static RandomGenerator probability = new RandomGenerator(), toppingChooser = new RandomGenerator(), hazardChooser = new RandomGenerator(), upgradeChooser = new RandomGenerator();
	private static double comboEntryX, comboEntryY, comboLaterX, comboLaterY, lineSlope = 0, lineB = 0;
	protected int baconSliced, cheeseSliced, eggSliced, scoreCounter = 0, comboCounter = 1, timer = 60, splashCounter, pineappleLabelCounter, clockCounter, sharpCounter, sharpLabelCounter = 5, tossCounter = 0;
	protected ArrayList <Topping> toppingArray = new ArrayList<Topping>();
	protected GImage pauseButton, button, wall, splash = new GImage("src/main/resources/Splash.png"), pineappleLabel = new GImage("src/main/resources/Pineapple_Label.png"), clockLabel = new GImage("src/main/resources/Clock_Label.png"), sharpLabel = new GImage("src/main/resources/Sharp_Knife_5.png", 525/2,75/2);
	protected GLabel bothScores;
	protected PauseMenu PMenu;
	protected GameOver gameOver;
	protected Timer Timer;
	protected GLine comboLine;
	protected MainMenu MMenu;
	protected MainApplication Mapp;
	protected boolean paused, isTimerMode, onRock = false;

	
	//CONSTRUCTORS
	public Mode() {
		drawBoard();
		paused = false;
		System.out.println("Mode Constructor");
	}
	
	public Mode(MainMenu m, MainApplication x) {
		super();
		MMenu = m;
		Mapp = x;
		drawBoard();
		Timer = new Timer(110,this);
		paused = false;
		System.out.println("Mode Constructor");
	}
	
	
	//LINE MAKER
	static public void calculateLineFunction() {
		lineSlope = (comboLaterY - comboEntryY) / (comboLaterX - comboEntryX);
		lineB = -lineSlope * comboEntryX + comboEntryY;
		System.out.println("FUNCTION: y = " + lineSlope + "x + " + lineB); //4TPs
	}
	
	public void drawLine() {
		double x1 = 0, y1, x2 = WINDOWS_WIDTH, y2;
		y1 = lineB;
		y2 =  x2 * lineSlope + lineB;
		comboLine = new GLine(x1, y1, x2, y2);
		comboLine.setLineWidth(50);
		System.out.println("x1: " + x1 + ", y1: " + y1 + "  |  x2: " + x2 + " y2: " + y2); //4TPs
		Mapp.add(comboLine);
	}
		
	//COMBO
	public void combo(Topping t, MouseEvent e) {
		if(lineSlope != 0 && lineB != 0) {
			if(wasCombo(t, e)) {
				comboCounter++;
			} else {
				lineSlope = 0;
				lineB = 0;
				calculateLineFunction();
				comboCounter = 1;
			}
		} else {
			calculateLineFunction();
		}
	}
		
	public boolean wasCombo(Topping t, MouseEvent e) {  
		comboLine.sendToFront();
		return (Mapp.getElementAt(e.getX(), e.getY()) == comboLine);
	}
	
	//MOUSE LISTENERS
	@Override
	public void mouseDragged(MouseEvent e) {
		//CUTTING CODE SHOULD GO HERE MARK OBJECT AS CUT, UPDATE 4 POINTS ENTRY X Y & LATER X Y
		if(toppingArray != null) {
			for(Topping i : toppingArray) {
				if(!i.isCut()) {
														// CHECK X								 <- | ->									// CHECK Y
					if(e.getX() < (i.getCurX() + i.getImage().getWidth()) && e.getX() > i.getCurX() && e.getY() < (i.getCurY() + i.getImage().getHeight()) && e.getY() > i.getCurY()) {
						i.cutTopping();
						comboEntryX = e.getX();
						comboEntryY = e.getY();
						checkForEffects(i);
						//combo(i, e);
					} 
				}
			}
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		GObject x = Mapp.getElementAt(e.getX(), e.getY());
		System.out.println(x.toString());
		if (paused) {
			paused = false;
			PMenu.mouseClicked(e);
		} else if(x == pauseButton) {
			System.out.println("Open Pause");
			Timer.stop();
			PMenu = new PauseMenu(this, Mapp);
			Mapp.switchToPause(PMenu);
		} 
	}
		
	
	//CUTIING
	public void checkForEffects(Topping i) {
		if (i.getType() == ToppingType.PINEAPPLE) {
			if (isTimerMode) {
				timer -= 10;
				pineappleLabel.setLocation(i.getCurX(), i.getCurY());
				Mapp.add(pineappleLabel);
			} else {
				//callGameOver();
			}
		} else if (i.getType() == ToppingType.CLOCK) {
			clockLabel.setLocation(i.getCurX(), i.getCurY());
			timer += 5;
			clockLabel();
		} else if (i.getType() == ToppingType.ROCK) {
			onRock = true;
			startRockTimer();
		} else if (i.getType() == ToppingType.CAN) {
			addSplashImage();
		}
		if(onRock) {
			if (i.getType() == ToppingType.BACON) {
				baconSliced++;
			} else if (i.getType() == ToppingType.CHEESE) {
				cheeseSliced++;
			} else if (i.getType() == ToppingType.EGG) {
				eggSliced++;
			}
			scoreCounter += 2;
		} else {
			if (i.getType() == ToppingType.BACON) {
				baconSliced++;
			} else if (i.getType() == ToppingType.CHEESE) {
				cheeseSliced++;
			} else if (i.getType() == ToppingType.EGG) {
				eggSliced++;
			}
			scoreCounter ++;
		}
	}
	
	private void clockLabel() {
		Mapp.add(clockLabel);
		clockCounter = 0;		
	}

	private void addSplashImage() {
		Mapp.add(splash);
		splashCounter = 0;
		
	}

	private void startRockTimer() {
		onRock = true;
		sharpLabel.setImage("src/main/resources/Sharp_Knife_5.png");
		Mapp.add(sharpLabel);
	}
	
	public void callGameOver() { // not sure where to call gameOver
		//check which mode it is to do XXX
		gameOver = new GameOver(this, Mapp, baconSliced, cheeseSliced, eggSliced);
		Mapp.switchToScreen(gameOver);
	}
	
	//OBJECT GENERATOR
	public void generateObject() {
		int chance = probability.nextInt(1, 100);
		if(chance < 61) { //Toppings 80% chance
			toppingArray.add(new Topping(ToppingType.values()[toppingChooser.nextInt(0,2)], Mapp));
		} else if (chance > 60 && chance < 81) { //Hazards 10% chance
			toppingArray.add(new Topping(ToppingType.values()[hazardChooser.nextInt(3,4)], Mapp));
		} else { //Upgrades 10% chance
			if(isTimerMode) { 
				if(!onRock) {
					toppingArray.add(new Topping(ToppingType.values()[upgradeChooser.nextInt(5,6)],Mapp));
				} else if(chance % 2 == 0) {
					toppingArray.add(new Topping(ToppingType.CLOCK, Mapp));
				}
			} else {
				if(chance % 2 == 0 && !onRock) {
					toppingArray.add(new Topping(ToppingType.ROCK, Mapp));
				}
			}
		}
	}
	
	public void tossToppings() {
		// add random generator to change toppins appearence
		if(tossCounter > 5) {
			generateObject();
			tossCounter = 0;
		}
		tossCounter++;
	}
	
	//RUN OBJECTS 
	public void runToppings() {
		for (int i = toppingArray.size() - 1 ; i >= 0 ; i--) {
		    toppingArray.get(i).moveTopping();
		    if (!toppingArray.get(i).shouldMove()) {
		    	toppingArray.remove(i);
		    }
		}
		checkAllCountDowns();
	}
	
	private void checkAllCountDowns() {
		//TOMATO SPLASH
		if (splashCounter > 5*(1000/110)) {
			Mapp.remove(splash);
			splashCounter = 0;
		}
		splashCounter ++;
		
		//PINEAPPLE
		if(!isTimerMode) {
			if (pineappleLabelCounter > 3*(1000/110)) {
				Mapp.remove(pineappleLabel);
				pineappleLabelCounter = 0;
			}
			pineappleLabelCounter++;
		}
		
		//CLOCK
		if (clockCounter > 3*(1000/110)) {
			Mapp.remove(clockLabel);
			clockCounter = 0;
		}
		clockCounter ++;
		
		//ROCK
		if(onRock) {
			if(sharpCounter > 1000/110) {
				if (sharpLabelCounter <= 1) {
					Mapp.remove(sharpLabel);
					sharpCounter = 0;
					sharpLabelCounter = 5;
					onRock = false;
				} else {
					sharpLabelCounter --;
					Mapp.remove(sharpLabel);
					String path = "src/main/resources/Sharp_Knife_" + sharpLabelCounter + ".png";
					sharpLabel.setImage(path);
					Mapp.add(sharpLabel);
					sharpCounter = 0;
				}
			}
			sharpCounter ++;
		}
	}

	// ---------
	public void exceedHighScore() {
		//TODO if scoreCounter is greater than or equal to highscore then 
		// make scoreCounter the new high score.
	}
	
	public void deleteTopping(Topping t) {
		Mapp.remove(t.getImage());
		toppingArray.remove(t);
	}
	
	public boolean inLine(Topping t) {
		return false;
		//TODO Retrun true if Topping is in comboLine.
	}

	public void stopTimer() {
		Timer.stop();
	}
	
	public void startTimer() {
		PMenu = null;
		Timer.start();
	}
	
	public void setPauseToNull() {
		PMenu = null;
	}
	
	
	//GRAPHICS
	@Override
	public void showContents() {
		Mapp.add(wall);
		Mapp.add(pauseButton);
	}

	@Override
	public void hideContents() {
		for(Topping t: toppingArray) {
			Mapp.remove(t.getImage());
		}
		Mapp.remove(wall);
		Mapp.remove(pauseButton);
	}
	
	public void drawBoard() {
		wall = new GImage("BackgroundWall.png");
		wall.scale(0.5);
		splash.scale(0.5);
		pineappleLabel.scale(0.2);
		clockLabel.scale(0.2);
		pauseButton = new GImage("Pause button.png",1695/2,810/2);
		pauseButton.scale(0.5);
		sharpLabel.scale(0.5);
	}
	
	public MainMenu getMMenu() {
		return MMenu;
	}
	
	public void resetAll() {
		//TODO returns all variables back to their original forms.
	}
	
	public void returnToMenu() {
		//TODO Has Main Menu call isTimeOver()
		PMenu = null;
		gameOver = null;
		System.out.println("Quit the game");
		Mapp.switchToScreen(MMenu);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
