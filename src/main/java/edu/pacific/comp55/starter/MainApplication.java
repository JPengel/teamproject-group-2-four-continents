package edu.pacific.comp55.starter;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

public class MainApplication extends GraphicsApplication {
	public static final int WINDOW_WIDTH = 1920/2;
	public static final int WINDOW_HEIGHT = 1080/2;
	public static final String MUSIC_FOLDER = "sounds";
	private static final String SOUND_FILES =  "background.mp3" ;
	
	public static Font customFont;
	

	private SomePane somePane;
	private MenuPane menu;
	private MainMenu MMenu;
	private int count;
	
	public static void registerCustomFont() {
		try {
			customFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/RuddyBlack.ttf")).deriveFont(50f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(customFont);
		} catch (IOException e) {
			e.printStackTrace();
		} catch(FontFormatException e) {
			e.printStackTrace();
		}
	}

	public void init() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		registerCustomFont();
	}

	public void run() {
		System.out.println("Hello, world!");
		somePane = new SomePane(this);
		menu = new MenuPane(this);
		MMenu = new MainMenu(this);
		setupInteractions();
		switchToMainMenu();
		playRandomSound();
	}

	
	public void switchToMainMenu() {
		switchToScreen(MMenu);
	}
	public void switchToMenu() {
		playRandomSound();
		count++;
		switchToScreen(menu);
	}
	
	public void switchToPause(GraphicsPane pauseScreen) {
		pauseScreen.showContents();
		curScreen = pauseScreen;
	}

	public void switchToSome() {
		playRandomSound();
		switchToScreen(somePane);
	}

	private void playRandomSound() {
		AudioPlayer audio = AudioPlayer.getInstance();
		audio.playSound(MUSIC_FOLDER, SOUND_FILES, true);
	}
	
	public static void main(String[] args) {
		new MainApplication().start();
	}
}
