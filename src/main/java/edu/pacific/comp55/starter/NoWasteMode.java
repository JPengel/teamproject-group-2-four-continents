package edu.pacific.comp55.starter;
import acm.program.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.*;
import acm.graphics.*;

public class NoWasteMode extends Mode{
	GPoint counter = new GPoint(830,50);
	GImage counterX = new GImage("lives3.png");
	int highScore;
	String filePath = "lives";
	String fileName = ".png";
	static int count = 0;
	
	
	public NoWasteMode(MainMenu m, MainApplication ma) {
		super(m, ma);
		isTimerMode = false;
		drawXCounter();
		System.out.println("NoWaste Constructor");
	}
	
	public void drawXCounter() {
		counterX.setImage(filePath + wasteCount + fileName);
		System.out.println("XCounter Drawn");
	}
	
	
	public void importHighScore() {
		//TODO Copies high score of specific mode from text file.
	}
	
	public void exportHighScore(int score) {
		//TODO Copies high score of mode to text file.
	}
	
	@Override
	public void resetAll() {
		super.resetAll();
		wasteCount = 0;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("No Waste Mouce Clicked");
		GObject x = Mapp.getElementAt(e.getX(), e.getY());
		if(paused) {
			PMenu.mouseClicked(e);
			System.out.println("send to pause");
		} else if(x == pauseButton) {
			System.out.println("Open Pause");
			stopTimer();
			PMenu = new PauseMenu(this, Mapp, MMenu);
			PMenu.showContents();
		}
		generateObject();

	}
	
	@Override
	public void showContents() {
		super.showContents();
		Mapp.add(counterX);
		PMenu = null;
		startTimer();
	}

	@Override
	public void hideContents() {
		super.hideContents();
		Mapp.remove(counterX);
	}
	 public void actionPerformed(ActionEvent e) {
		 	System.out.println("start timer");
			super.actionPerformed(e);
			tossToppings();
			runToppings();
			drawXCounter();
			if(wasteCount == 3) {
				stopTimer();
				callGameOver();
			}
	 }
	
}