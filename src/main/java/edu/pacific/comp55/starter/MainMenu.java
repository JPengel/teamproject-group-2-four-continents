package edu.pacific.comp55.starter;
import acm.program.*;

import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.*;
public class MainMenu extends GraphicsProgram{
	public static final int WINDOW_WIDTH = 1920;
	public static final int WINDOW_HEIGHT = 1080;
	private GImage gif, instruction, backArrow, piizaWithTitle, help, quit, noWasteModeButton, timerModeButton;
	private TimerMode timerMode;
	private NoWasteMode noWasteMode;
	
	public void init() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	}
	
	public void run() {
		System.out.println("Hello");
		drawMainMenu();
	}
	
	public void drawMainMenu(){
		// TODO Set up the menu screen
		GImage backGround = new GImage("mainmenu/background.png");
		GImage pizza = new GImage("mainmenu/pizza.png",880,100);
		//pizza.setBounds(960, 100, 880, 880);
		pizza.sendForward();
		add(backGround);
		//add(pizza);
		
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
