package edu.pacific.comp55.starter;
import acm.program.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.Timer;
import javax.swing.colorchooser.ColorSelectionModel;

import acm.graphics.*;
public class MainMenu extends GraphicsPane implements ActionListener{
	public static final int WINDOW_WIDTH = 1920/2;
	public static final int WINDOW_HEIGHT = 1080/2;
	private GImage gif, instruction, pizzaWithTitle, help, quit, noWasteModeButton, timerModeButton, backArrow,backGround;
	private TimerMode timerMode;
	private NoWasteMode noWasteMode;
	private MainApplication Mapp;
	private static final double scaleSize = 0.5;
	GRect exit = new GRect(55,42,45,45);
	ArrayList<GImage> button;
	private Timer timer = new Timer(0,this);
    private int flank; 
	
	public MainMenu(MainApplication a) {
		super();
		Mapp = a;
	}

	public void drawMainMenu(){
		// TODO Set up the menu screen
		button = new ArrayList<GImage>();
		backGround = new GImage("src/main/resources/backgroundMainMenu.png");
		backGround.scale(scaleSize);
		pizzaWithTitle = new GImage("src/main/resources/Pizza.png",960*scaleSize,100*scaleSize);
		pizzaWithTitle.scale(scaleSize);
		noWasteModeButton = new GImage("src/main/resources/Nowaste.png",100*scaleSize,100*scaleSize);
		noWasteModeButton.scale(scaleSize);
		timerModeButton = new GImage("src/main/resources/Timer.png" , 100*scaleSize, 410*scaleSize);
		timerModeButton.scale(scaleSize);
		quit = new GImage("src/main/resources/Quit.png", 505*scaleSize, 720*scaleSize);
		quit.scale(scaleSize);
		help = new GImage ("src/main/resources/Help.png", 100*scaleSize, 720*scaleSize);
		gif = new GImage("src/main/resources/gifMainMenu.gif");
		gif.scale(scaleSize);
		help.scale(scaleSize);
		exit.setVisible(false);
		button.add(noWasteModeButton);
		button.add(timerModeButton);
		button.add(quit);
		button.add(help);
		
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
			flank = 2;
			timer.start();
			
		}
		else if(obj == noWasteModeButton) {
			noWasteMode = new NoWasteMode(this, Mapp);
			flank = 1;
			timer.start();
		}
		else if(obj == quit) {
			System.exit(0);
		}
	}
	
	public Mode getMode() {
		if(flank == 2) {
			return timerMode;
		}
		else {
			return noWasteMode;
		}
	}

	@Override
	public void showContents() {
		// TODO Auto-generated method stub
		drawMainMenu();
		Mapp.add(backGround);
		for(GImage e : button) {
			Mapp.add(e);
			System.out.println(e);
		}
		Mapp.add(pizzaWithTitle);
		timerMode = null;
		noWasteMode = null;
	}

	@Override
	public void hideContents() {
		// TODO Auto-generated method stub
		for(GImage e : button) {
			Mapp.remove(e);
			}
		Mapp.remove(pizzaWithTitle);
		Mapp.remove(backGround);
		Mapp.remove(gif);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		for(GImage i : button) {
			i.move(-12, 0);
		}
		pizzaWithTitle.move(12, 0);
		if(pizzaWithTitle.getX() >= 960) {
			System.out.println("move stp");
			
			if(flank == 1) {
				timer.stop();
				Mapp.switchToScreen(noWasteMode);	
			} else {
				timer.stop();
				Mapp.switchToScreen(timerMode);	
			}
			
		}
	}
}
