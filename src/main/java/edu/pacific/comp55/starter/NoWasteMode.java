package edu.pacific.comp55.starter;
import acm.program.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.*;
import acm.graphics.*;

public class NoWasteMode extends Mode{
	int wasteCount = 0;
	GPoint counter = new GPoint(1100,50);
	GImage counterX;
	int highScore;
	String filePath = "lives";
	String source = ".png";
	static int count = 0;
	
	
	public NoWasteMode(MainMenu m, GraphicsApplication x) {
		super(m, x);
		drawXCounter();
	}
	public void drawXCounter() {
		//TODO Inserts the Image of counterX.
		if (count == 0) {
			filePath += "0";
			filePath += source;
			counterX = new GImage(filePath);
			counterX.setLocation(counter);       
			
		}
		else if(count == 1) {
			filePath += "1";
			filePath += source;
			counterX = new GImage(filePath);
			

		}
		else if(count == 2) {
			filePath+= "2";
			filePath += source;
			counterX = new GImage(filePath);
		}
		else {
			filePath += "3";
			filePath += source;
			counterX = new GImage(filePath);
			stopTimer();
			GameOver(); // im calling game over is here instead of creating an isGameOver();
			
		}
		Gapp.add(counterX);
		filePath = "lives";
	}
	
	public void incrementTheCounter(Topping t) {
		if(super.fallenOffScreen(t)){
		count += 1;
		drawXCounter();
		}
		//TODO Overrides fallenOffScreen so that in addition
		//to deleting it from the ArrayList, it also adds to the wasCount.
		
	}
	
	public void GameOver() { 
		//TODO Calls new instance of GameOver
		gameOver = new GameOver(this, Gapp, baconSliced, cheeseSliced, eggSliced);
		Gapp.switchToScreen(gameOver);
	}
	
	public void importHighScore() {
		//TODO Copies high score of specific mode from text file.
	}
	
	public void exportHighScore(int score) {
		//TODO Copies high score of mode to text file.
	}
	
	@Override
	public void resetAll() {
		//TODO Reset all parameters to their original forms.
		PMenu = null;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("Hi");
		GObject x = Gapp.getElementAt(e.getX(), e.getY());
		if(x == temp_Exit) {
			System.out.println("hi");
			Gapp.switchToScreen(MMenu);
		}
		else if(x == pauseButton) {
			System.out.println("Open Pause");
			stopTimer();
			PMenu = new PauseMenu(this, Gapp);
			Gapp.switchToPause(PMenu);
		}
	}
	
	@Override
	public void showContents() {
//		button.scale(3);
//		Gapp.add(wall);
//		Gapp.add(pauseButton);
//		Gapp.add(temp_Exit);
//		Gapp.add(counterX);
//		Gapp.add(button);
//		PMenu = null;
//		startTimer();
		super.showContents();
		Gapp.add(counterX);
		PMenu = null;
	}

	@Override
	public void hideContents() {
//		Gapp.remove(wall);
//		Gapp.remove(pauseButton);
//		Gapp.remove(temp_Exit);
//		Gapp.remove(counterX);
		super.hideContents();
		Gapp.add(counterX);
	}
	 public void actionPerformed(ActionEvent e) {
		 generateObject();
		 for(Topping t : objList) {
			 t.moveTopping();
			 incrementTheCounter(t);
		 }
		 
	 }
	
}