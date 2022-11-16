package edu.pacific.comp55.starter;
import acm.program.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.*;
import acm.graphics.*;

public class NoWasteMode extends Mode{
	int wasteCount = 0;
	GPoint counter = new GPoint(830,50);
	GImage counterX;
	int highScore;
	String filePath = "lives";
	String source = ".png";
	static int count = 0;
	
	
	public NoWasteMode(MainMenu m, MainApplication ma) {
		super(m, ma);
		drawXCounter();
	}
	public void drawXCounter() {
		//TODO Inserts the Image of counterX.
		if (count == 0) {
			filePath += "0";
			filePath += source;
			counterX = new GImage(filePath);
			counterX.setLocation(counter);       
		} else if(count == 1) {
			filePath += "1";
			filePath += source;
			counterX = new GImage(filePath);
		} else if(count == 2) {
			filePath+= "2";
			filePath += source;
			counterX = new GImage(filePath);
		} else {
			filePath += "3";
			filePath += source;
			counterX = new GImage(filePath);
			stopTimer();
			GameOver(); // im calling game over is here instead of creating an isGameOver();
		}
		Mapp.add(counterX);
		filePath = "lives";
	}
	
	public void checkForFall(Topping t) {
		if(super.fallenOffScreen(t)){
			count++;
			//drawXCounter();
		}
		//TODO Overrides fallenOffScreen so that in addition
		//to deleting it from the ArrayList, it also adds to the wasCount.
		
	}
	
	public void GameOver() { 
		gameOver = new GameOver(this, Mapp, baconSliced, cheeseSliced, eggSliced);
		Mapp.switchToScreen(gameOver);
	}
	
	public void importHighScore() {
		//TODO Copies high score of specific mode from text file.
	}
	
	public void exportHighScore(int score) {
		//TODO Copies high score of mode to text file.
	}
	
	@Override
	public void resetAll() {
		PMenu = null;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("hi");
		GObject x = Mapp.getElementAt(e.getX(), e.getY());
		if(x == temp_Exit) {
			Mapp.switchToScreen(MMenu);
		}
		else if(x == pauseButton) {
			System.out.println("Open Pause");
			stopTimer();
			PMenu = new PauseMenu(this, Mapp);
			Mapp.switchToPause(PMenu);
		}
		generateObject();

	}
	
	@Override
	public void showContents() {
		startTimer();
		super.showContents();
		//Mapp.add(counterX);
		PMenu = null;
	}

	@Override
	public void hideContents() {
		super.hideContents();
		Mapp.remove(counterX);
	}
	 public void actionPerformed(ActionEvent e) {
		 for(Topping t : objList) {
			 t.moveTopping();
			//checkForFall(t);
		 }
	 }
	
}