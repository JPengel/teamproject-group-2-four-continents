package edu.pacific.comp55.starter;
import acm.program.*;

import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.*;
public class MainMenu extends GraphicsProgram{
	public static final int WINDOW_WIDTH = 1920;
	public static final int WINDOW_HEIGHT = 1080;
	private GImage gif, instruction, backArrow, piizaWithTitle, help, quit, noWasteMode, timmerMode;
	
	public void init() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	}
	public void run() {
		System.out.println("Hello");
	}
	public static void main (String[] args) {
		new MainMenu().start();
	}

}
