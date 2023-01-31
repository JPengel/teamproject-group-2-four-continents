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

public class Mode extends GraphicsPane implements ActionListener {

	// VARIABLES
	
	public static final int WINDOWS_WIDTH = 1920 / 2, WINDOWS_HEIGHT = 1080 / 2;
	private static RandomGenerator probability = new RandomGenerator(), toppingChooser = new RandomGenerator(),
			hazardChooser = new RandomGenerator(), upgradeChooser = new RandomGenerator(),
			toppingToss = new RandomGenerator();
	private static double comboEntryX, comboEntryY, comboPrevX, comboPrevY, comboNewX = 0, comboNewY = 0, lineSlope = 0,
			lineB = 0, coordinateWaiter = 0;
	protected int baconSliced = 0, cheeseSliced = 0, eggSliced = 0, scoreCounter = 0, comboCounter = 1, timer = 60,
			splashCounter, pineappleLabelCounter, clockCounter, sharpCounter, sharpLabelCounter = 5, tossCounter = 0,
			earlierCounter = 0, crossCounter = 0, wasteCount = 0, noWasteTossSpeed = 100, iterationCount = 0, comboLabelCounter;

	protected ArrayList<Topping> toppingArray = new ArrayList<Topping>();
	protected GImage pauseButton, wall, pineappleLabel = new GImage("src/main/resources/Pineapple_Label.png"),
			clockLabel = new GImage("src/main/resources/Clock_Label.png"),
			sharpLabel = new GImage("src/main/resources/Sharp_Knife_5.png", 525 / 2, 75 / 2),
			gif = new GImage("src/main/resources/gifMainMenu.gif");
	protected GLabel bothScores, score = new GLabel(String.valueOf(scoreCounter), 100 / 2, 200 / 2),
			comboLabel = new GLabel("Combo\n +" + String.valueOf(comboCounter + 1));
	protected GImage splash1 = new GImage("src/main/resources/Blob 1.png", 120 / 2, 110 / 2),
			splash2 = new GImage("src/main/resources/Blob 2.png", 980 / 2, 60 / 2),
			splash3 = new GImage("src/main/resources/Blob 3.png", 200 / 2, 400 / 2),
			splash4 = new GImage("src/main/resources/Blob 4.png", 1000 / 2, 500 / 2);
	protected PauseMenu PMenu;
	protected GameOver gameOver;
	protected Timer Timer, timerGif;
	protected GLine comboLine;
	protected MainMenu MMenu;
	protected MainApplication Mapp;
	protected boolean paused, isTimerMode, onRock = false;

	// CONSTRUCTORS
	public Mode() {
		drawBoard();
		ToppingType.initArray();
		paused = false;
		System.out.println("Mode Constructor");
	}

	public Mode(MainMenu m, MainApplication x) {
		super();
		gif.scale(1.6);
		MMenu = m;
		Mapp = x;
		drawBoard();
		Timer = new Timer(20, this);
		timerGif = new Timer(1400,this);
		Timer.setInitialDelay(600);
		ToppingType.initArray();
		paused = false;
		System.out.println("Mode Constructor");
	}

	// LINE MAKER
	public void calculateLineFunction() {
		if (comboLine != null) {
			Mapp.remove(comboLine);
		}
		lineSlope = (comboEntryY - comboPrevY) / (comboEntryX - comboPrevX);
		lineB = -lineSlope * comboEntryX + comboEntryY;
		System.out.println("FUNCTION: y = " + lineSlope + "x + " + lineB); // 4TPs
		drawLine();
	}

	public void drawLine() {
		double x1 = 0, y1, x2 = WINDOWS_WIDTH, y2;
		y1 = lineB;
		y2 = x2 * lineSlope + lineB;
		comboLine = new GLine(x1, y1, x2, y2);
		comboLine.setLineWidth(50);
		comboLine.setVisible(false); // VISIBILITY OF THE LINE
		Mapp.add(comboLine);
		comboLine.sendToBack();

	}

	// COMBO
	public void combo(MouseEvent e) {
		if (lineSlope != 0 && lineB != 0) {
			if (wasCombo(e)) {
				comboCounter++;
				comboLabel.setLocation(e.getX(), e.getY());
				Mapp.add(comboLabel);
				comboLabelCounter = 0;
				System.out.println("COMBO: " + comboCounter); // 4TPs
				if (onRock) {
					scoreCounter += 2;
				} else {
					scoreCounter++;
				}
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

	public boolean wasCombo(MouseEvent e) {
		if (comboLine != null) {
			comboLine.sendToFront();
		}
		boolean isLine = (Mapp.getElementAt(e.getX(), e.getY()) == comboLine);
		if (comboLine != null) {
			comboLine.sendToBack();
		}
		return isLine;
	}

	// MOUSE LISTENERS
	@Override
	public void mouseDragged(MouseEvent e) {
		//if the game is paused return
		if (paused == true) {System.out.println("In Pause");}
		//if not paused
        	//set previous combo x and y coordinates to new combo x and y coordinates
		else {
			comboPrevX = comboNewX;
			comboPrevY = comboNewY;
			//if coordinates counter = 5,  set new combo x and y coordinates to current x and y coordinates of the cursor
			if (coordinateWaiter == 5) {
				comboNewX = e.getX();
				comboNewY = e.getY();
				//set coordinates counter to 0
				coordinateWaiter = 0;
			}
			//increment coordinates counter 
			coordinateWaiter++;
			//if the topping array is not empty iterate through the array
			if (toppingArray != null) {
				for (Topping i : toppingArray) {
					if (!i.isCut()) {
						//check if the cursor's coordinates match with any ingredients that were not cut dimensions/coordinates
		            	//if the coordinates match, cut the topping and perform their effects
						// CHECK X <- | -> // CHECK Y
						if (e.getX() < (i.getCurX() + i.getImage().getWidth()) && e.getX() > i.getCurX()
								&& e.getY() < (i.getCurY() + i.getImage().getHeight()) && e.getY() > i.getCurY()) {
							i.cutTopping();
							checkForEffects(i);
							//if topping type enum position <= topping type count array size
							if (i.getType().ordinal() <= ToppingType.sliceCount.length - 1) {
								//set combo entry x and y to current x and y coordinates of the cursor
					 
								comboEntryX = e.getX();
								comboEntryY = e.getY();
								System.out.println("PrevX: " + comboPrevX + ", PrevY: " + comboPrevY + "  |  EntryX: "
										+ comboEntryX + " EntryY: " + comboEntryY); // 4TPs
								//check for combo
								combo(e);
							}
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
		} else if (x == pauseButton) {
			System.out.println("Open Pause");
			Timer.stop();
			PMenu = new PauseMenu(this, Mapp, MMenu);
			Mapp.switchToPause(PMenu);
		}
	}

	// CUTIING
	public void checkForEffects(Topping i) {
		//if the ingredient cut was pineapple play pineapple sound
		if (i.getType() == ToppingType.PINEAPPLE) {
			AudioPlayer.getInstance().playSound("sounds", "slice.mp3");
			
			//if the current game mode is Timer Mode
			if (isTimerMode) {
				//decrease 10 seconds from the game timer
				timer -= 10;
				//set the pineapple label location and add it to the screen
				pineappleLabel.setLocation(i.getCurX(), i.getCurY());
				Mapp.add(pineappleLabel);
				
			//if not Timer Mode, stop the timer and declare game over
			} else {
				stopTimer();
				callGameOver();
			}
			
		//if the ingredient cut was a clock play clock sound
		} else if (i.getType() == ToppingType.CLOCK) {
			AudioPlayer.getInstance().playSound("sounds", "BreakClock.mp3");
			clockLabel.setLocation(i.getCurX(), i.getCurY());
			//add 5 seconds to the timer and set clock label and add it to the screen
			timer += 5;
			clockLabel();
			
		//if the ingredient cut was a rock
		} else if (i.getType() == ToppingType.ROCK) {
			//play rock sound
			AudioPlayer.getInstance().playSound("sounds", "KnifeSharpened.mp3");
			//start rock timer and set rock label and add it to the screen
			onRock = true;
			startRockTimer();
		
		//if the ingredient cut was a can
		} else if (i.getType() == ToppingType.CAN) {
			// play can sound
			AudioPlayer.getInstance().playSound("sounds", "CanSmash.mp3");
			//add splash images to the screen
			addSplashImage();
		}
		
		else {
			//play audio of topping from topping type audio array
			AudioPlayer.getInstance().playSound("sounds", ToppingType.soundFileName[i.getType().ordinal()]);
			//increment by one the integer counter of ingredients cut on topping type count array
			ToppingType.sliceCount[i.getType().ordinal()] ++;
			//increment the game score counter by 1
			scoreCounter ++;
			//if rock was cut, increment the game score counter by 1
			if(onRock) {
				scoreCounter ++;
				}
		}
		//update game score counter label
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
		// sharpLabel.setImage("src/main/resources/Sharp_Knife_5.png");
		sharpLabel = new GImage("src/main/resources/Sharp_Knife_5.png", 525 / 2, 75 / 2);
		sharpLabel.scale(0.5);

		Mapp.add(sharpLabel);
	}

	public void callGameOver() { // not sure where to call gameOver
		// check which mode it is to do XXX
		gameOver = new GameOver(this, Mapp, MMenu, baconSliced, cheeseSliced, eggSliced);
		Mapp.switchToScreen(gameOver);
	}

	// OBJECT GENERATOR
	public void generateObject() {
		//if timer is not running return nothing
		if (!Timer.isRunning()) {
			return;
		}
		// System.out.println("---TOPPING ADDED---");;
		//randomly picks a number between 1-100
		int chance = probability.nextInt(1, 100);
		//if number is < 71
		if (chance < 71) { // Toppings 70% chance
			//randomly choose between 0-topping type count array and generate an ingredient based on the chosen number and its enum position
			//add topping to the topping array
			toppingArray.add(new Topping(ToppingType.values()[toppingChooser.nextInt(0, ToppingType.sliceCount.length - 1)], Mapp));
		//if number is > 70 and < 86
	    //randomly selects either can or pineapple and add hazard to the topping array
		} else if (chance > 70 && chance < 86) { // Hazards 15% chance
			toppingArray.add(new Topping(ToppingType.values()[hazardChooser.nextInt(ToppingType.CAN.ordinal(),ToppingType.PINEAPPLE.ordinal())], Mapp));
		//if number is > 85
		} else { // Upgrades 15% chance
			// if current game mode = timer Mode
	          	//if no rock was cut
			if (isTimerMode) {
				if (!onRock) {
					//randomly selects either clock or rock and add the upgrade to the topping array
					toppingArray.add(new Topping(ToppingType.values()[upgradeChooser.nextInt(ToppingType.CLOCK.ordinal(),ToppingType.ROCK.ordinal())], Mapp));
				//if rock was cut and chosen number is even, generate a clock and add clock to the topping array
				} else if (chance % 2 == 0) {
					toppingArray.add(new Topping(ToppingType.CLOCK, Mapp));
				}
			//if not in Timer mode and if the chosen number is even and no rock was cut	
			} else {
				if (chance % 2 == 0 && !onRock) {
					// generate a rock and add rock to the topping array
					toppingArray.add(new Topping(ToppingType.ROCK, Mapp));
				}
			}
		}

	}

	public void tossToppings() {
		// add random generator to change toppins appearence
		if (!isTimerMode) {
			int ratio = toppingToss.nextInt(1, noWasteTossSpeed);
			if (tossCounter > ratio) {
				generateObject();
				AudioPlayer.getInstance().playSound("sounds", "tossing.mp3");
				tossCounter = 0;
			}
			if (tossCounter % 13 == 0 && noWasteTossSpeed > 1) {
				noWasteTossSpeed--;
				System.out.println(String.valueOf(noWasteTossSpeed));
			}
		} else {
			if (tossCounter > 5) {
				generateObject();
				AudioPlayer.getInstance().playSound("sounds", "tossing.mp3");
				tossCounter = 0;
			}
		}
		tossCounter++;
	}

	// RUN OBJECTS
	public void runToppings() {
		for (int i = toppingArray.size() - 1; i >= 0; i--) {
			toppingArray.get(i).moveTopping();
			if (!toppingArray.get(i).shouldMove()) {
				deleteTopping(toppingArray.get(i));
			}
		}
		checkAllCountDowns();
	}

	private void checkAllCountDowns() {
		// TOMATO SPLASH
		if (splashCounter > 5 * (1000 / 20)) {
			removeSplashes();
			splashCounter = 0;
		} else {
			sendSplashes2Front();
		}
		splashCounter++;

		// PINEAPPLE
		if (isTimerMode) {
			if (pineappleLabelCounter > 3 * (500 / 20)) {
				Mapp.remove(pineappleLabel);
				pineappleLabelCounter = 0;
			}
			pineappleLabelCounter++;
		} else {
			pineappleLabel.sendToFront();
			sendSplashes2Front();
		}

		// CLOCK
		if (clockCounter > 3 * (500 / 20)) {
			Mapp.remove(clockLabel);
			clockCounter = 0;
		} else {
			clockLabel.sendToFront();
			sendSplashes2Front();
		}
		clockCounter++;

		// ROCK
		if (onRock) {
			if (sharpCounter > 1000 / 20) {
				if (sharpLabelCounter <= 1) {
					Mapp.remove(sharpLabel);
					sharpCounter = 0;
					sharpLabelCounter = 5;
					onRock = false;
				} else {
					sharpLabelCounter--;
					Mapp.remove(sharpLabel);
					String path = "src/main/resources/Sharp_Knife_" + sharpLabelCounter + ".png";
					sharpLabel.setImage(path);
					Mapp.add(sharpLabel);
					sharpLabel.sendToFront();
					sendSplashes2Front();
					sharpCounter = 0;
				}
			}
			sharpCounter++;
		}
		if (comboLabelCounter > 3 * (500/20)) {
			Mapp.remove(comboLabel);
			comboLabelCounter = 0;
		}
		else {
			comboLabel.sendToFront();
			sendSplashes2Front();
		}
		comboLabelCounter ++;
	}

	private void sendSplashes2Front() {
		splash1.sendToFront();
		splash2.sendToFront();
		splash3.sendToFront();
		splash4.sendToFront();
	}

	// ---------
	public void exceedHighScore() {
		// TODO if scoreCounter is greater than or equal to highscore then
		// make scoreCounter the new high score.
	}

	public void deleteTopping(Topping t) {
		if (!isTimerMode && !t.isCut() && t.getType() != ToppingType.CAN && t.getType() != ToppingType.PINEAPPLE
				&& t.getType() != ToppingType.ROCK) {
			wasteCount++;
		}
		Mapp.remove(t.getImage());
		toppingArray.remove(t);
	}

	public boolean inLine(Topping t) {
		return false;
		// TODO Retrun true if Topping is in comboLine.
	}

	public void stopTimer() {
		Timer.stop();
	}

	public void startTimer() {
		PMenu = null;
		Timer.start();
//		timerGif.start();
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

	@Override

 	public void showContents() {
//		showWall();
//		showTopContents();
		Mapp.add(wall);
		Mapp.add(pauseButton);
 		Mapp.add(score);
 	}
	




	public void showTopContents() {
        timerGif.start();
// 		Mapp.add(pauseButton);
// 		Mapp.add(score);
		Mapp.add(gif);
	}

	@Override
	public void hideContents() {
//		Mapp.remove(wall);
		Mapp.remove(pauseButton);
		Mapp.remove(score);
		if (comboLine != null) {
			Mapp.remove(comboLine);
		}
		if (toppingArray != null) {
			for (Topping t : toppingArray) {
				Mapp.remove(t.getImage());
			}
		}
		removeLabels();
		AudioPlayer.getInstance().pauseSound("sounds", "tossing.mp3");
	}

	public void drawBoard() {
		wall = new GImage("BackgroundWall.png");
		wall.scale(0.5);
		scaleSplash();
		pineappleLabel.scale(0.2);
		clockLabel.scale(0.2);
		pauseButton = new GImage("Pause button.png", 1695 / 2, 810 / 2);
		pauseButton.scale(0.5);
		sharpLabel.scale(0.5);
		score.setColor(Color.decode("#ffdaa7"));
		score.setFont(MainApplication.customFont);
		score.scale(1.5);
		comboLabel.setColor(Color.decode("#ffdaa7"));
		comboLabel.setFont(MainApplication.customFont);
		//;comboLabel.scale(.5);  not sure if this is the best way to solve font problem and I also prefer Combo being bigger than rest
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
		// TODO returns all variables back to their original forms.

	}

	public void returnToMenu() {
		// TODO Has Main Menu call isTimeOver()
		PMenu = null;
		gameOver = null;
		System.out.println("Quit the game");
		Mapp.switchToScreen(MMenu);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
//		if (gif != null) {
//			Mapp.remove(gif);
//			gif = null;
//
//		}
		if(source == timerGif) {
			Mapp.remove(gif);
			Mapp.remove(MMenu.getBackGround());
			timerGif.stop();
			gif = null; 
		}
	}
	public GImage getGif() {
		return gif;
	}
}
