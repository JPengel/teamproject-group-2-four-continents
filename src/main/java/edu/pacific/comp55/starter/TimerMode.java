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
		drawTimer();
		
	}
	
	public TimerMode(MainMenu m) {
		super(m);
		drawBoard();
		drawTimer();
	}
	
	public void run() {
		drawBoard();
		drawTimer();
	}
	
	public void drawTimer() {
		timerDisplay = new GLabel(timer2, 1200, 50);
		timerDisplay.setColor(Color.ORANGE);
		timerDisplay.setFont("Arial-Bold-50");
		MMenu.add(timerDisplay);
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
	
//	@Override
//	public void mouseClicked(MouseEvent e) {
//		GObject x = getElementAt(e.getX(), e.getY());
//		if(x == pauseButton) {
//			System.out.println("hi");
//			removeAll();
//			PMenu = new PauseMenu(MMenu);
//		}
//	}
	
	public void returnToMenu() {
		//TODO Has Main Menu call isTimeOver()
	}
	
	public static void main (String[] args) {
		new TimerMode().start();
	}
}
