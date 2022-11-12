package edu.pacific.comp55.starter;
import acm.program.*;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;

import acm.graphics.*;
public class MainMenu extends GraphicsPane{
	public static final int WINDOW_WIDTH = 1900;
	public static final int WINDOW_HEIGHT = 750;
	private GImage gif, instruction, pizzaWithTitle, help, quit, noWasteModeButton, timerModeButton, backArrow;
	private TimerMode timerMode;
	private NoWasteMode noWasteMode;
	private GraphicsApplication Gapp;
	private static final double scaleSize= 0.6;
	ArrayList<GImage> pic;
	
	public MainMenu(GraphicsApplication a) {
		super();
		Gapp = a;
		drawMainMenu();
	}
	
	
	
	public void drawMainMenu(){
		// TODO Set up the menu screen
		pic = new ArrayList<GImage>();
		gif = new GImage("src/main/resources/backgroundMainMenu.png");
		gif.scale(.67, .7);
		pizzaWithTitle = new GImage("src/main/resources/Pizza.png",700,100);
		pizzaWithTitle.scale(scaleSize);
		noWasteModeButton = new GImage("src/main/resources/Nowaste.png",100,100);
		noWasteModeButton.scale(scaleSize);
		timerModeButton = new GImage("src/main/resources/Timer.png" , 100, 300);
		timerModeButton.scale(scaleSize);
		quit = new GImage("src/main/resources/Quit.png", 100, 500);
		quit.scale(scaleSize);
		help = new GImage ("src/main/resources/Help.png", 350, 500);
		help.scale(scaleSize);
		pic.add(gif);
		pic.add(pizzaWithTitle);
		pic.add(noWasteModeButton);
		pic.add(timerModeButton);
		pic.add(quit);
		pic.add(help);
		
		
	}
	
	public void startingAnimation() {
		// TODO Start the animation
	}
	
	public void help() {
		// TODO It should show up the help gImage
		instruction = new GImage("src/main/resources/Instructions.png");
		instruction.scale(.95, .9);
		backArrow = new GImage("src/main/resources/Xbutton.PNG",50,50);
		backArrow.setSize(80, 80);
		backArrow.sendForward();
		Gapp.add(instruction);
		Gapp.add(backArrow);
	}
	
	public void creatTimer() {
		//TODO Create Timer Mode, and MainMenu passes itself as a parameter.
	}
	
	public void timerOver() {
		//TODO Sets timerMode to null;
	}
	
	public void creatWaste() {
		//TODO Create No Waste Mode, and MainMenu passes itself as a parameter.
	}
	
	public void wasteOver() {
		//TODO Sets NoWasteMode to null.
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		GObject obj = Gapp.getElementAt(e.getX(),e.getY());
		if(obj == help) {
			help();
		}
		else if(obj == backArrow) {
			Gapp.remove(instruction);
			Gapp.remove(backArrow);
		}
		else if(obj == timerModeButton) {
			timerMode = new TimerMode(this, Gapp);
			Gapp.switchToScreen(timerMode);
			
		}
		else if(obj == noWasteModeButton) {
			noWasteMode = new NoWasteMode(this, Gapp);
			Gapp.switchToScreen(noWasteMode);
		}
		else if(obj == quit) {
			System.exit(0);
		}
	}

	@Override
	public void showContents() {
		// TODO Auto-generated method stub
		for(GImage e : pic) {
			//e.scale(scaleSize);
			e.sendForward();
			Gapp.add(e);
		}
		
		timerMode = null;
		noWasteMode = null;
	}

	@Override
	public void hideContents() {
		// TODO Auto-generated method stub
		for(GImage e : pic) {
			Gapp.remove(e);
		}
		
	}
	
	

}
