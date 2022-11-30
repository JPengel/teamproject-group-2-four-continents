package edu.pacific.comp55.starter;
import acm.program.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.*;
import acm.graphics.*;
import java.io.*;
import java.util.Scanner;

public class NoWasteMode extends Mode{
	GPoint counter = new GPoint(830,50);
	GImage counterX = new GImage("lives3.png",750,20);
	int highScore;
	String filePath = "lives";
	String fileName = ".png";
	String oldLine;
	static int lives = 3;
	
	
	public NoWasteMode(MainMenu m, MainApplication ma) {
		super(m, ma);
		isTimerMode = false;
		counterX.scale(0.5);
		drawXCounter();
		importHighScore();
		oldLine = "NoWaste: " + String.valueOf(highScore);
		System.out.println("NoWaste Constructor");
	}
	
	public void drawXCounter() {
		counterX.setImage(filePath + (lives - wasteCount) + fileName);
	}
	
	
	public void importHighScore() {
		//TODO Copies high score of specific mode from text file.
	}
	
	public void exportHighScore() {
		//TODO Copies high score of mode to text file.
		String newLine = "NoWaste: " + String.valueOf(highScore);
		try {
			Scanner HighScore = new Scanner(new File("src/main/resources/HighScore.txt"));
			StringBuffer buffer = new StringBuffer();
			
			while(HighScore.hasNextLine()) {
				buffer.append(HighScore.nextLine() + System.lineSeparator());
			}
			String scObj = buffer.toString();
			HighScore.close();
			scObj = scObj.replaceAll(oldLine, newLine);
			FileWriter writer = new FileWriter("src/main/resources/HighScore.txt");
			writer.append(scObj);
			writer.close();
	
		}catch (IOException e) {
            e.printStackTrace();
        }
		
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
	public void mouseDragged(MouseEvent e) {
		super.mouseDragged(e);
		if(scoreCounter >= highScore) {
			highScore = scoreCounter;
		}
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
			super.actionPerformed(e);
			tossToppings();
			runToppings();
			drawXCounter();
			if(wasteCount == 3) {
				stopTimer();
				exportHighScore();
				callGameOver();
			}
	 }
}