package edu.pacific.comp55.starter;
import acm.program.*;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.*;
import acm.graphics.*;

public class NoWasteMode extends Mode{
	int wasteCount;
	GPoint counter = new GPoint(850,100);
	GImage counterX;
	int highScore;
	String filePath = "lives";
	String source = "png";
	static int count = 0;
	public void init() {
		setSize(1900,750);
	}
	public void run() {
		drawBoard();
		drawXCounter();
	}
	public NoWasteMode(MainMenu m) {
		super(m);
	}
	public void drawXCounter() {
		//TODO Inserts the Image of counterX.
		if (count == 0) {
			filePath += "1";
			filePath += source;
			counterX = new GImage(filePath);
			counterX.setLocation(counter);
			add(counterX);
		}
		else if(count == 1) {
			filePath += "2";
			filePath += source;
			counterX = new GImage(filePath);
			add(counterX);

		}
		else {
			filePath += "3";
			filePath += source;
			counterX = new GImage(filePath);
			add(counterX);
		}
		filePath = "lives";
	}
	
	@Override
	public void fallenOffScreen(Topping t) {
		count += 1;
		drawXCounter();
		//TODO Overrides fallenOffScreen so that in addition
		//to deleting it from the ArrayList, it also adds to the wasCount.
		
	}
	
	public void isGamerOver() {
		//TODO Calls new instance of GameOver
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
	}
	
	public void returnToMenu() {
		//TODO Has Main Menu call isTimeOver()
	}
}