package edu.pacific.comp55.starter;
import acm.program.*;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.*;
import acm.graphics.*;

public class NoWasteMode extends Mode{
	int wasteCount;
	GImage counterX;
	int highScore;
	
	public void init() {
		setSize(WINDOWS_WIDTH, WINDOWS_HEIGHT);
	}
	
	public void run() {
		System.out.println("Hello");
	}
	
	public void drawXCounter() {
		//TODO Inserts the Image of counterX.
	}
	
	@Override
	public void fallenOffScreen(Topping t) {
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

