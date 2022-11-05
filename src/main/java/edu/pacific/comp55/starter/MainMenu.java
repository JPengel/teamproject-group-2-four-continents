package edu.pacific.comp55.starter;
import acm.program.*;

import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.*;
public class MainMenu extends GraphicsProgram{
	public static final int WINDOW_WIDTH = 1900;
	public static final int WINDOW_HEIGHT = 750;
	private GImage gif, instruction, backArrow, pizzaWithTitle, help, quit, noWasteModeButton, timerModeButton;
	private TimerMode timerMode;
	private NoWasteMode noWasteMode;
	private static final double scaleSize= 0.6;
	
	public void init() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	}
	
	public void run() {
		System.out.println("Hello");
		drawMainMenu();
		}
	
	public void drawMainMenu(){
		// TODO Set up the menu screen
		gif = new GImage("mainmenu/background.png");
		pizzaWithTitle = new GImage("mainmenu/pizza.png",700,100);
		noWasteModeButton = new GImage("mainmenu/Nowaste.png",100,100);
		timerModeButton = new GImage("mainmenu/Timer.png" , 100, 300);
		quit = new GImage("mainmenu/Quit.png", 100, 500);
		help = new GImage ("mainmenu/Help.png", 350, 500);
		gif.scale(0.68);
		pizzaWithTitle.scale(scaleSize);
		noWasteModeButton.scale(scaleSize);
		timerModeButton.scale(scaleSize);
		quit.scale(scaleSize);
		help.scale(scaleSize);
		pizzaWithTitle.sendForward();
		add(gif);
		add(pizzaWithTitle);
		add(noWasteModeButton);
		add(timerModeButton);
		add(quit);
		add(help);
	}
	
	public void startingAnimation() {
		// TODO Start the animation
	}
	
	public void help() {
		// TODO It should show up the help gImage
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
	public void mousePressed(MouseEvent e) {
		
	}
	
	public static void main (String[] args) {
		new MainMenu().start();
	}

}
