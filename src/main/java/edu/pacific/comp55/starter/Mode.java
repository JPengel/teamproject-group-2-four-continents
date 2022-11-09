package edu.pacific.comp55.starter;
import acm.program.*;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.*;
import acm.graphics.*;

public class Mode extends GraphicsProgram{
	public static final int WINDOWS_WIDTH = 1920;
	public static final int WINDOWS_HEIGHT = 1080;
	
	protected ArrayList<Topping> objList;
	protected GImage pauseButton;
	protected GLabel bothScores;
	protected PauseMenu PMenu;
	protected GameOver gameOver;
	protected int baconSliced, cheeseSliced, eggSliced;
	protected boolean inPause;
	protected Topping topping;
	protected Timer Timer;
	protected int scoreCounter;
	protected GLine comboLine;
	protected int comboCounter = 1;
	protected MainMenu MMenu;
	
	public Mode() {
		
	}
	
	public Mode(MainMenu m) {
		MMenu = m;
	}
	
	public void init() {
		setSize(WINDOWS_WIDTH, WINDOWS_HEIGHT);
	}
	
	public void run() {
		System.out.println("Hello");
	}
	
	public void drawBoard() {
		//TODO Calls all other draw functions.
	}
	
	public void drawPauseButton() {
		//TODO Inserts Image of PauseButton.
	}
	
	public void drawScore() {
		//TODO Draws the current score and highscore.
	}
	
	public void pausePressed() {
		//TODO Creates new instance of PauseMenu and makes inPause true.
	}
	
	public Topping getObject(int x, int y) {
		return null;
		//TODO Returns whatever topping is at the x and y coordinate.
	}
	
	public boolean cutObject(Topping a) {
		return false;
		//TODO Return true if topping.isCut() returns true.
	}
	
	public void resetAll() {
		//TODO returns all variables back to their original forms.
	}
	
	public void generateObject() {
		//TODO creates a new object.
	}
	
	public void exceedHighScore() {
		//TODO if scoreCounter is greater than or equal to highscore then 
		// make scoreCounter the new high score.
	}
	
	public void fallenOffScreen(Topping t) {
		//TODO If topping fell off, then delete it from Array List
	}
	
	public void deleteTopping(Topping t) {
		//TODO Deletes Image of topping from screen.
	}
	
	public boolean knifeSharpened() {
		return false;
		//TODO Returns true if knife is sharpened
	}
	
	public boolean inLine(Topping t) {
		return false;
		//TODO Retrun true if Topping is in comboLine.
	}
	
	public void stopTimer() {
		//TODO Stops timer.
	}
	public void startTimer() {
		//TODO Starts timer again.
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		
	}
	
	public static void main (String[] args) {
		new Mode().start();
	}
}
