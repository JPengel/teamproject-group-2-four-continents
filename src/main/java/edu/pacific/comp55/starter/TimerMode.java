package edu.pacific.comp55.starter;
import acm.program.*;
import acm.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.*;
import acm.graphics.*;

public class TimerMode extends Mode{
	private int count = 0;
	GLabel timerDisplay;
	int highScore;
	
	public TimerMode() {
		super();
		isTimerMode = true;
		drawTimer();
		System.out.println("Timer Constructor");
	}
	
	public TimerMode(MainMenu m, MainApplication ma) {
		super(m, ma);
		isTimerMode = true;
		drawTimer();
		System.out.println("Timer Constructor");
	}
	
	
	public void drawTimer() {
		timerDisplay = new GLabel(String.valueOf(timer), 860, 70);
		timerDisplay.setColor(Color.ORANGE);
		timerDisplay.setFont("Arial-Bold-65");
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
		super.resetAll();
		timer = 60;
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("Timer Mouse Clicked");
		GObject x = Mapp.getElementAt(e.getX(), e.getY());
		if(paused) {
			PMenu.mouseClicked(e);
			System.out.println("Pause back");
		} else if(x == pauseButton) {
			System.out.println("Open Pause");
			PMenu = new PauseMenu(this, Mapp, MMenu);
			PMenu.showContents();
			stopTimer();
		} 
	}
	
	public void actionPerformed(ActionEvent e) {
		System.out.println("start timer");
		super.actionPerformed(e);
		gameClock();
		tossToppings();
		runToppings();
	}


	public void gameClock() {
		if(count > 1000/110){
			timer--;	
			count = 0;
		    timerDisplay.setLabel(String.valueOf(timer));
		}
		count++;
		if(timer <= 0) {
			stopTimer();
			callGameOver();
		}
	}
	
	
	@Override
	public void showContents() {
		super.showContents();
		Mapp.add(timerDisplay);
		PMenu = null;
		startTimer();
	}

	@Override
	public void hideContents() {
		super.hideContents();
		Mapp.remove(timerDisplay);
	}
}
