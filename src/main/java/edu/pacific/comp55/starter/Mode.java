package edu.pacific.comp55.starter;
import acm.program.*;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.*;
import acm.graphics.*;

public class Mode extends GraphicsProgram{
	public static final int WINDOWS_WIDTH = 1920;
	public static final int WINDOWS_HEIGHT = 1080;
	
	private ArrayList<Topping> objList;
	private GImage pauseButton;
	private GLabel bothScores;
	private PauseMenu PMenu;
	private GameOver gameOver;
	private int baconSliced, cheeseSliced, eggSliced;
	private boolean inPause;
	private Topping topping;
	private Timer Timer;
	private int scoreCounter;
	private GLine comboLine;
	private int comboCounter = 1;
	private boolean timeStop = false;
	
	public void init() {
		setSize(WINDOWS_WIDTH, WINDOWS_HEIGHT);
	}
	
	public void run() {
		System.out.println("Hello");
	}
	
}
