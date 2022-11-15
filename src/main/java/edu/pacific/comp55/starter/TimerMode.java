package edu.pacific.comp55.starter;
import acm.program.*;
import acm.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.*;
import acm.graphics.*;

public class TimerMode extends Mode{
	int timer = 60;
	GLabel timerDisplay;
	int highScore;
	
	public TimerMode() {
		super();
		drawTimer();
		paused = false;
	}
	
	public TimerMode(MainMenu m, MainApplication x) {
		super(m,x);
		drawTimer();
	}
	
	public void drawTimer() {
		timerDisplay = new GLabel(String.valueOf(timer), 1200, 50);
		timerDisplay.setColor(Color.ORANGE);
		timerDisplay.setFont("Arial-Bold-50");
	}
	
	@Override 
	public boolean cutObject(Topping a) {
		return false;
		//TODO Return true if topping.isCut() returns true, and check for hazards specific to mode.
	}
	
	
	public void GameOver() { // not sure where to call gameOver
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
		if(paused) {
			PMenu.mouseClicked(e);
		}
		else if(x == temp_Exit) {
			System.out.println("hi");
			Gapp.switchToScreen(MMenu);
		}
		else if(x == pauseButton) {
			System.out.println("Open Pause");
			PMenu = new PauseMenu(this, Gapp);
			PMenu.showContents();
		}
	}	
	
	 public void actionPerformed(ActionEvent e) {
		 generateObject();
	    for (Topping t: objList) {
	    		t.moveTopping();
	    		fallenOffScreen(t);
	   	}
	    timer--;	
	    timerDisplay.setLabel(String.valueOf(timer));
	 }
	@Override
	public void showContents() {
//		Gapp.add(wall);
//		Gapp.add(pauseButton);
//		Gapp.add(temp_Exit);
//		Gapp.add(timerDisplay);
//		startTimer();
		super.showContents();
		Gapp.add(timerDisplay);
		PMenu = null;
		startTimer();
		paused = true;
	}

	@Override
	public void hideContents() {
//		Gapp.remove(wall);
//		Gapp.remove(pauseButton);
//		Gapp.remove(temp_Exit);
//		Gapp.remove(timerDisplay);
		super.hideContents();
		Gapp.remove(timerDisplay);
		paused = false;
	}
}
