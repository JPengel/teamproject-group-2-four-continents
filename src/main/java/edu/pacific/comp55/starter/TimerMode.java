package edu.pacific.comp55.starter;
import acm.program.*;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.*;
import acm.graphics.*;

public class TimerMode extends Mode{
	int timer;
	GLabel timerDisplay;
	int highScore;
	
	public TimerMode(MainMenu m) {
		super(m);
	}
	
	public void init() {
		setSize(WINDOWS_WIDTH, WINDOWS_HEIGHT);
	}
	
	public void run() {
		System.out.println("Hello");
	}
	
	public void drawTimer() {
		//TODO Create the display of timer.
	}
	
	@Override 
	public boolean cutObject(Topping a) {
		return false;
		//TODO Return true if topping.isCut() returns true, and check for hazards specific to mode.
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
