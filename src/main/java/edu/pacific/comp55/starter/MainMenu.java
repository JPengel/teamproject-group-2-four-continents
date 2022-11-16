package edu.pacific.comp55.starter;
import acm.program.*;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.colorchooser.ColorSelectionModel;

import acm.graphics.*;
public class MainMenu extends GraphicsPane{
	public static final int WINDOW_WIDTH = 1920/2;
	public static final int WINDOW_HEIGHT = 1080/2;
	private GImage gif, instruction, pizzaWithTitle, help, quit, noWasteModeButton, timerModeButton, backArrow;
	private TimerMode timerMode;
	private NoWasteMode noWasteMode;
	private MainApplication Mapp;
	private static final double scaleSize = 0.5;
	GRect exit = new GRect(55,42,45,45);
	ArrayList<GObject> pic;
	
	public MainMenu(MainApplication a) {
		super();
		Mapp = a;
		drawMainMenu();
	}

	public void drawMainMenu(){
		// TODO Set up the menu screen
		pic = new ArrayList<GObject>();
		gif = new GImage("src/main/resources/backgroundMainMenu.png");
		gif.scale(scaleSize);
		pizzaWithTitle = new GImage("src/main/resources/Pizza.png",960*scaleSize,100*scaleSize);
		pizzaWithTitle.scale(scaleSize);
		noWasteModeButton = new GImage("src/main/resources/Nowaste.png",100*scaleSize,100*scaleSize);
		noWasteModeButton.scale(scaleSize);
		timerModeButton = new GImage("src/main/resources/Timer.png" , 100*scaleSize, 410*scaleSize);
		timerModeButton.scale(scaleSize);
		quit = new GImage("src/main/resources/Quit.png", 505*scaleSize, 720*scaleSize);
		quit.scale(scaleSize);
		help = new GImage ("src/main/resources/Help.png", 100*scaleSize, 720*scaleSize);
		help.scale(scaleSize);
		exit.setVisible(false);
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
		instruction.scale(scaleSize);
		Mapp.add(instruction);
		Mapp.add(exit);
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
		GObject obj = Mapp.getElementAt(e.getX(),e.getY());
		if(obj == help) {
			help();
		}
		else if(obj == exit) {
			Mapp.remove(instruction);
			Mapp.remove(exit);
		}
		else if(obj == timerModeButton) {
			timerMode = new TimerMode(this, Mapp);
			Mapp.switchToScreen(timerMode);
			
		}
		else if(obj == noWasteModeButton) {
			noWasteMode = new NoWasteMode(this, Mapp);
			Mapp.switchToScreen(noWasteMode);
		}
		else if(obj == quit) {
			System.exit(0);
		}
	}

	@Override
	public void showContents() {
		// TODO Auto-generated method stub
		for(GObject e : pic) {
			//e.scale(scaleSize);
			e.sendForward();
			Mapp.add(e);
		}
		
		timerMode = null;
		noWasteMode = null;
	}

	@Override
	public void hideContents() {
		// TODO Auto-generated method stub
		for(GObject e : pic) {
			Mapp.remove(e);
		}
		
	}
	
	

}
