package edu.pacific.comp55.starter;
import acm.program.*;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;

import acm.graphics.*;
public class MainMenu extends GraphicsProgram{
	public static final int WINDOW_WIDTH = 1900;
	public static final int WINDOW_HEIGHT = 750;
	private GImage gif, instruction, pizzaWithTitle, help, quit, noWasteModeButton, timerModeButton, backArrow;
	private TimerMode timerMode;
	private NoWasteMode noWasteMode;
	private static final double scaleSize= 0.6;
	public void init() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	}
	
	public void run() {
		drawMainMenu();
		addMouseListeners();
		}
	
	public void drawMainMenu(){
		// TODO Set up the menu screen
		ArrayList<GImage> pic = new ArrayList<GImage>();
		gif = new GImage("src/main/resources/backgroundMainMenu.png");
		pizzaWithTitle = new GImage("src/main/resources/Pizza.png",700,100);
		noWasteModeButton = new GImage("src/main/resources/Nowaste.png",100,100);
		timerModeButton = new GImage("src/main/resources/Timer.png" , 100, 300);
		quit = new GImage("src/main/resources/Quit.png", 100, 500);
		help = new GImage ("src/main/resources/Help.png", 350, 500);
		pic.add(pizzaWithTitle);
		pic.add(noWasteModeButton);
		pic.add(timerModeButton);
		pic.add(quit);
		pic.add(help);
		gif.scale(0.68);
		add(gif);
		for(GImage e : pic) {
			e.scale(scaleSize);
			e.sendForward();
			add(e);
		}
	}
	
	public void startingAnimation() {
		// TODO Start the animation
	}
	
	public void help() {
		// TODO It should show up the help gImage
		instruction = new GImage("src/main/resources/Instructions.png");
		backArrow = new GImage("src/main/resources/Xbutton.PNG",50,50);
		backArrow.setSize(80, 80);
		backArrow.sendForward();
		add(instruction);
		add(backArrow);
	}
	
	public void quit() {
		// TODO quit the game
	}
	
	public void creatTimer() {
		//TODO Create Timer Mode and check if it is Gamer Over
	}
	
	public boolean isTimerOver(boolean returned) {
		return returned;
	}
	
public void creatWaste() {
		//TODO Create No Waste Mode and check if it is Gamer Over
	}
	
	public boolean isWasteOver(boolean returned) {
		return returned;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		GObject obj = getElementAt(e.getX(),e.getY());
		System.out.println(obj);
		if(obj == help) {
			help();
		}
		else if(obj == backArrow) {
			remove(instruction);
		}
		else if(obj == timerModeButton) {
			removeAll();
			
		}
		else if(obj == noWasteModeButton) {
			removeAll();
		}
	}
	
	public static void main (String[] args) {
		new MainMenu().start();
	}

}
