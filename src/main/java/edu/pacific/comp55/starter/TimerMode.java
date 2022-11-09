package edu.pacific.comp55.starter;
import acm.program.*;
import acm.util.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.*;
import acm.graphics.*;

public class TimerMode extends Mode{
	int timer = 60;
	GLabel timerDisplay;
	int highScore;
	String timer2 = String.valueOf(timer);
	
	public TimerMode() {
		super();
	}
	
	public TimerMode(MainMenu m) {
		super(m);
	}
	
	public void run() {
		drawBoard();
		drawTimer();
	}
	
	public void drawTimer() {
		timerDisplay = new GLabel(timer2, 1200, 50);
		timerDisplay.setColor(Color.ORANGE);
		timerDisplay.setFont("Arial-Bold-50");
		add(timerDisplay);
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
	
	public static void main (String[] args) {
		new TimerMode().start();
	}
}
