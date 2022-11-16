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
	String filePath = "lives0.png";
//	String source = ".png";
	static int count = 0;
	
	
	public NoWasteMode(MainMenu m, MainApplication ma) {
		super(m, ma);
		drawXCounter();
	}
	
	public void drawXCounter() {
			if(count == 0) {
				counterX = new GImage(filePath);
				counterX.setLocation(counter);  
			}
			else if(count == 1) {
				filePath.replace("0", "1");
			}
			else if(count == 2) {
				filePath.replace("1", "2");
			}
			else if(count == 3) {
				stopTimer();
				filePath.replace("2", "3");
				GameOver();
			}
			Mapp.add(counterX);
			filePath = "lives";

		//TODO Inserts the Image of counterX.
//		if (count == 0) {
//			filePath += "0";
//			filePath += source;
//			counterX = new GImage(filePath);
//			counterX.setLocation(counter);       
//			
//		}
//		else if(count == 1) {
//			filePath += "1";
//			filePath += source;
//			counterX = new GImage(filePath);
//			counterX.setLocation(counter); 
//
//		}
//		else if(count == 2) {
//			filePath+= "2";
//			filePath += source;
//			counterX = new GImage(filePath);
//			counterX.setLocation(counter); 
//		}
//		else {
//			filePath += "3";
//			filePath += source;
//			counterX = new GImage(filePath);
//			counterX.setLocation(counter); 
//			stopTimer();
//			GameOver(); // im calling game over is here instead of creating an isGameOver();
//			
//		}
//		Mapp.add(counterX);
//		filePath = "lives";
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
		//TODO Reset all parameters to their original forms.
		PMenu = null;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("No Waste Mouce Clicked");
		GObject x = Mapp.getElementAt(e.getX(), e.getY());
		if(paused) {
			paused = false;
			PMenu.mouseClicked(e);
			System.out.println("send to pause");
		}
		else if(x == temp_Exit) {
			System.out.println("Exit");
			Mapp.switchToScreen(MMenu);
		}
		else if(x == pauseButton) {
			System.out.println("Open Pause");
			stopTimer();
			PMenu = new PauseMenu(this, Mapp);
			PMenu.showContents();
			paused = true;
		}
	}
	
	@Override
	public void showContents() {
		startTimer();
		super.showContents();
		Mapp.add(counterX);
		PMenu = null;
	}

	@Override
	public void hideContents() {
		super.hideContents();
		Mapp.remove(counterX);
	}
	 public void actionPerformed(ActionEvent e) {
		 generateObject();
		 for(Topping t : objList) {
			 t.moveTopping();
			 incrementTheCounter(t);
//			 t.createImage();
		 }
		 
	 }
	
}