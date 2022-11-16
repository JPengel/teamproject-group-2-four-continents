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
		System.out.println("Timer Constructor");
	}
	
	public TimerMode(MainMenu m, MainApplication ma) {
		super(m, ma);
		drawTimer();
		System.out.println("Timer Constructor");
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
		System.out.println("Timer Mouse Clicked");
		GObject x = Mapp.getElementAt(e.getX(), e.getY());
		if(paused) {
			paused = false;
			PMenu.mouseClicked(e);
			System.out.println("Pause back");
		}
		else if(x == temp_Exit) {
			System.out.println("Exit");
			Mapp.switchToScreen(MMenu);
		}
		else if(x == pauseButton) {
			System.out.println("Open Pause");
			PMenu = new PauseMenu(this, Mapp);
			PMenu.showContents();
			paused = true;
			stopTimer();
		}
	}
	
	 public void actionPerformed(ActionEvent e) {
		 generateObject();
	    for (Topping t: objList) {
//	    		t.moveTopping();
//	    		fallenOffScreen(t);
	    	t.createImage();
	   	}
	    timer--;	
	    timerDisplay.setLabel(String.valueOf(timer));
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
