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
	
	//VARIABLES
	public static final int WINDOWS_WIDTH = 1920/2, WINDOWS_HEIGHT = 1080/2;
	private static RandomGenerator probability = new RandomGenerator(), toppingChooser = new RandomGenerator(), hazardChooser = new RandomGenerator(), upgradeChooser = new RandomGenerator(), toppingToss = new RandomGenerator();
	private static double comboEntryX, comboEntryY, comboPrevX, comboPrevY, comboNewX = 0, comboNewY = 0, lineSlope = 0, lineB = 0, coordinateWaiter = 0;
	protected int baconSliced = 0, cheeseSliced = 0, eggSliced = 0, scoreCounter = 0, comboCounter = 1, timer = 15, splashCounter, pineappleLabelCounter, clockCounter, sharpCounter, sharpLabelCounter = 5, tossCounter = 0, earlierCounter = 0, crossCounter = 0, wasteCount = 0, noWasteTossSpeed = 100;

	protected ArrayList <Topping> toppingArray = new ArrayList<Topping>();
	protected GImage pauseButton, wall, pineappleLabel = new GImage("src/main/resources/Pineapple_Label.png"), clockLabel = new GImage("src/main/resources/Clock_Label.png"), sharpLabel = new GImage("src/main/resources/Sharp_Knife_5.png", 525/2,75/2), gif = new GImage("src/main/resources/gifMainMenu.gif");// comboLabel = new GImage("");
	protected GLabel bothScores, score = new GLabel(String.valueOf(scoreCounter), 100/2,200/2), comboLabel = new GLabel("Combo\n + 2");
	protected GImage splash1 = new GImage("src/main/resources/Blob 1.png", 120/2, 110/2), splash2 = new GImage("src/main/resources/Blob 2.png", 980/2,  60/2), splash3 = new GImage("src/main/resources/Blob 3.png", 200/2, 400/2), splash4 = new GImage("src/main/resources/Blob 4.png", 1000/2, 500/2);
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
 		gif.scale(1.6);
 		MMenu = m;
 		Mapp = x;
 		drawBoard();
 		Timer = new Timer(110,this);
 		Timer.setInitialDelay(1800);
 		paused = false;
 		System.out.println("Mode Constructor");
 	}
	
	//LINE MAKER
	public void calculateLineFunction() {
		if(comboLine != null) {
			Mapp.remove(comboLine);
		}
		lineSlope = (comboEntryY - comboPrevY) / (comboEntryX - comboPrevX);
		lineB = -lineSlope * comboEntryX + comboEntryY;
		System.out.println("FUNCTION: y = " + lineSlope + "x + " + lineB); //4TPs
		drawLine();
	}
	
	public void drawLine() {
		double x1 = 0, y1, x2 = WINDOWS_WIDTH, y2;
		y1 = lineB;
		y2 =  x2 * lineSlope + lineB;
		comboLine = new GLine(x1, y1, x2, y2);
		comboLine.setLineWidth(50);
		comboLine.setVisible(false); //VISIBILITY OF THE LINE
		Mapp.add(comboLine);
		comboLine.sendToBack();
		
	}
		
	//COMBO
	public void combo(MouseEvent e) {
		if(lineSlope != 0 && lineB != 0) {
			if(wasCombo(e)) {
				comboCounter++;
				comboLabel.setLocation(e.getX(), e.getY());
				Mapp.add(comboLabel);
				System.out.println("COMBO: " + comboCounter); //4TPs
				if(onRock) {
					scoreCounter += 2;
				} else {
					scoreCounter++;
				}
			} else {
				lineSlope = 0;
				lineB = 0;
				Mapp.remove(comboLabel);
				calculateLineFunction();
				comboCounter = 1;
			}
		} else {
			calculateLineFunction();
		}
	}
		
	public boolean wasCombo(MouseEvent e) {  
		if (comboLine != null) {
			comboLine.sendToFront();
		}
		boolean isLine = (Mapp.getElementAt(e.getX(), e.getY()) == comboLine);
		if( comboLine != null) {
			comboLine.sendToBack();
		}
		return isLine;
	}
	
	//MOUSE LISTENERS
	@Override
	public void mouseDragged(MouseEvent e) {
		//CUTTING CODE SHOULD GO HERE MARK OBJECT AS CUT, UPDATE 4 POINTS ENTRY X Y & LATER X Y
		comboPrevX = comboNewX;
		comboPrevY = comboNewY;
		if(coordinateWaiter == 5) {
			comboNewX = e.getX();
			comboNewY = e.getY();
			coordinateWaiter = 0;
		}
		coordinateWaiter++;
		if(toppingArray != null) {
			for(Topping i : toppingArray) {
				if(!i.isCut()) {
														// CHECK X								 <- | ->									// CHECK Y
					if(e.getX() < (i.getCurX() + i.getImage().getWidth()) && e.getX() > i.getCurX() && e.getY() < (i.getCurY() + i.getImage().getHeight()) && e.getY() > i.getCurY()) {
						i.cutTopping();
						checkForEffects(i);
						if(i.getType() == ToppingType.CHEESE || i.getType() == ToppingType.BACON || i.getType() == ToppingType.EGG) {
							comboEntryX = e.getX();
							comboEntryY = e.getY();
							System.out.println("PrevX: " + comboPrevX + ", PrevY: " + comboPrevY + "  |  EntryX: " + comboEntryX + " EntryY: " + comboEntryY); //4TPs
							combo(e);
						}
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
			PMenu = new PauseMenu(this, Mapp, MMenu);
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
				stopTimer();
				callGameOver();
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
				scoreCounter += 2;
			} else if (i.getType() == ToppingType.CHEESE) {
				cheeseSliced++;
				scoreCounter += 2;
			} else if (i.getType() == ToppingType.EGG) {
				eggSliced++;
				scoreCounter += 2;
			}
		} else {
			if (i.getType() == ToppingType.BACON) {
				baconSliced++;
				scoreCounter ++;
			} else if (i.getType() == ToppingType.CHEESE) {
				cheeseSliced++;
				scoreCounter ++;
			} else if (i.getType() == ToppingType.EGG) {
				eggSliced++;			
				scoreCounter ++;
			}
		}
		score.setLabel(String.valueOf(scoreCounter));
	}
	
	private void clockLabel() {
		Mapp.add(clockLabel);
		clockCounter = 0;		
	}

	private void addSplashImage() {
		Mapp.add(splash1);
		Mapp.add(splash2);
		Mapp.add(splash3);
		Mapp.add(splash4);
		splashCounter = 0;
		
	}

	private void startRockTimer() {
		onRock = true;
		Mapp.remove(sharpLabel);
		//sharpLabel.setImage("src/main/resources/Sharp_Knife_5.png");
		sharpLabel = new GImage("src/main/resources/Sharp_Knife_5.png", 525/2, 75/2);
		sharpLabel.scale(0.5);

		Mapp.add(sharpLabel);
	}
	
	public void callGameOver() { // not sure where to call gameOver
		//check which mode it is to do XXX
		gameOver = new GameOver(this, Mapp, baconSliced, cheeseSliced, eggSliced);
		Mapp.switchToScreen(gameOver);
	}
	
	//OBJECT GENERATOR
	public void generateObject() {
		if(!Timer.isRunning()) { return; }
		//System.out.println("---TOPPING ADDED---");;
		int chance = probability.nextInt(1, 100);
		if(chance < 71) { //Toppings 70% chance
			toppingArray.add(new Topping(ToppingType.values()[toppingChooser.nextInt(0,2)], Mapp));
		} else if (chance > 70 && chance < 86) { //Hazards 15% chance
			toppingArray.add(new Topping(ToppingType.values()[hazardChooser.nextInt(3,4)], Mapp));
		} else { //Upgrades 15% chance
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
		if(!isTimerMode) {
			int ratio = toppingToss.nextInt(1, noWasteTossSpeed);
			if(tossCounter > ratio) {
				generateObject();
				tossCounter = 0;
			}
			if(tossCounter % 13 == 0 && noWasteTossSpeed > 1) {
				noWasteTossSpeed--;
				System.out.println(String.valueOf(noWasteTossSpeed));
			}
		} else {
			if(tossCounter > 5) {
				generateObject();
				tossCounter = 0;
			}
		}
		tossCounter++;
	}
	
	//RUN OBJECTS 
	public void runToppings() {
		for (int i = toppingArray.size() - 1 ; i >= 0 ; i--) {
		    toppingArray.get(i).moveTopping();
		    if (!toppingArray.get(i).shouldMove()) {
		    	deleteTopping(toppingArray.get(i));
		    }
		}
		checkAllCountDowns();
	}
	
	private void checkAllCountDowns() {
		//TOMATO SPLASH
		if (splashCounter > 5*(1000/110)) {
			removeSplashes();
			splashCounter = 0;
		} else {
			sendSplashes2Front();
		}
		splashCounter ++;
		
		//PINEAPPLE
		if(isTimerMode) {
			if (pineappleLabelCounter > 3*(1000/110)) {
				Mapp.remove(pineappleLabel);
				pineappleLabelCounter = 0;
			}
			pineappleLabelCounter++;
		} else {
			pineappleLabel.sendToFront();
		}
		
		//CLOCK
		if (clockCounter > 3*(1000/110)) {
			Mapp.remove(clockLabel);
			clockCounter = 0;
		} else {
			clockLabel.sendToFront();
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
					sharpLabel.sendToFront();
					sharpCounter = 0;
				}
			}
			sharpCounter ++;
		}
	}

	private void sendSplashes2Front() {
		splash1.sendToFront();
		splash2.sendToFront();
		splash3.sendToFront();
		splash4.sendToFront();
	}

	// ---------
	public void exceedHighScore() {
		//TODO if scoreCounter is greater than or equal to highscore then 
		// make scoreCounter the new high score.
	}
	
	public void deleteTopping(Topping t) {
		if(!isTimerMode && !t.isCut() && t.getType() != ToppingType.CAN && t.getType() != ToppingType.PINEAPPLE && t.getType() != ToppingType.ROCK) {
			wasteCount++;
		}
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
	
	public void removeLabels() {
		removeSplashes();
		Mapp.remove(pineappleLabel);
		Mapp.remove(clockLabel);
		Mapp.remove(sharpLabel);
		Mapp.remove(comboLabel);
	}
	
	
	private void removeSplashes() {
		Mapp.remove(splash1);
		Mapp.remove(splash2);
		Mapp.remove(splash3);
		Mapp.remove(splash4);
	}

	//GRAPHICS
	@Override
 	public void showContents() {
		showWall();
		showTopContents();
 	}
	
	public void showWall() {
		Mapp.add(wall);
	}
	
	public void showTopContents() {
 		Mapp.add(pauseButton);
 		Mapp.add(score);
 		Mapp.add(gif);
	}

	@Override
	public void hideContents() {
		Mapp.remove(wall);
		Mapp.remove(pauseButton);
		Mapp.remove(score);
		if(comboLine != null) {
			Mapp.remove(comboLine);
		}
		removeLabels();
		if(toppingArray != null) {
			for(Topping t: toppingArray) {
				Mapp.remove(t.getImage());
			}
		}
	}
	
	public void drawBoard() {
		wall = new GImage("BackgroundWall.png");
		wall.scale(0.5);
		scaleSplash();
		pineappleLabel.scale(0.2);
		clockLabel.scale(0.2);
		pauseButton = new GImage("Pause button.png",1695/2,810/2);
		pauseButton.scale(0.5);
		sharpLabel.scale(0.5);
		score.setColor(Color.ORANGE);
		score.setFont("Arial-Bold-65");
		comboLabel.setColor(Color.ORANGE);
		comboLabel.setFont("Arial-Bold-35");
	}
	
	private void scaleSplash() {
		splash1.scale(0.5);
		splash2.scale(0.5);
		splash3.scale(0.5);
		splash4.scale(0.5);
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
 		if(gif != null) {
 			Mapp.remove(gif);
 			gif = null;

 		}
 	}
}
