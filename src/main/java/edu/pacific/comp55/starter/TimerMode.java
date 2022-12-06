package edu.pacific.comp55.starter;
import acm.program.*;
import acm.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import acm.graphics.*;

public class TimerMode extends Mode{
	private int count = 0;
	GLabel timerDisplay;
	GLabel highScoreDisplay;
	int highScore;
	String oldLine;
	
	public TimerMode() {
		super();
		isTimerMode = true;
		//drawTimer();
		oldLine = "Timer: " + String.valueOf(highScore);
		System.out.println("Timer Constructor");
	}
	
	public TimerMode(MainMenu m, MainApplication ma) {
		super(m, ma);
		isTimerMode = true;
		try {
			importHighScore();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		oldLine = "Timer: " + String.valueOf(highScore);
		highScoreDisplay = new GLabel("High Score: " + String.valueOf(highScore), 100/2, 900/2);
		System.out.println("Timer Constructor");
	}
	
	
	public void drawTimer() {
		timerDisplay = new GLabel(String.valueOf(timer), 860, 100);
		timerDisplay.setColor(Color.decode("#ffdaa7"));
		timerDisplay.setFont("Arial-Bold-65");
		highScoreDisplay.setColor(Color.decode("#ffdaa7"));
		highScoreDisplay.setFont("Arial-Bold-40");
	}
	

	public void importHighScore() throws FileNotFoundException {
		//TODO Copies high score of specific mode from text file.
		try {
			File file = new File("src/main/resources/HighScore.txt");
			Scanner myReader = new Scanner(file);
			myReader.nextLine();
			String highestScore = myReader.nextLine();
			highestScore = highestScore.replaceAll("[^0-9]","");
			highScore = Integer.parseInt(highestScore);
			System.out.println(highScore);
			myReader.close();
		} catch(FileNotFoundException e) {
			System.out.println("An error occured");
		}
		
		
	}
	
	public void exportHighScore() {
		//TODO Copies high score of mode to text file.
		String newLine = "Timer: " + String.valueOf(highScore);
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
			System.out.println("High Score updated." + highScore);
			writer.close();
	
		}catch (IOException e) {
            e.printStackTrace();
        }
		
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
		//System.out.println("TimerMode action performed");
		super.actionPerformed(e);
		gameClock();
		if(iterationCount == 5) {
			tossToppings();
			iterationCount=0;
		}
		runToppings();
		iterationCount++;
	}


	public void gameClock() {
		if(count > 1000/20){
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
	public void mouseDragged(MouseEvent e) {
		super.mouseDragged(e);
		if(scoreCounter >= highScore) {
			highScore = scoreCounter;
			highScoreDisplay.setLabel("High Score: " + String.valueOf(highScore));
		}
	}
	
	@Override
	public void callGameOver() {
		super.callGameOver();
		exportHighScore();
	}
	
	@Override
	public void showContents() {
		drawTimer();
		super.showContents();
		Mapp.add(timerDisplay);
		Mapp.add(highScoreDisplay);
		super.showTopContents();
		startTimer();
		PMenu = null;
//		startTimer();
	}

	@Override
	public void hideContents() {
		super.Timer.stop();
		System.out.println("Hide!");
		super.hideContents();
		Mapp.remove(timerDisplay);
		Mapp.removeAll();
	}
}
