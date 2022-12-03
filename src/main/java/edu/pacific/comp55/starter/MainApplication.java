package edu.pacific.comp55.starter;
public class MainApplication extends GraphicsApplication {
	public static final int WINDOW_WIDTH = 1920/2;
	public static final int WINDOW_HEIGHT = 1080/2;
	public static final String MUSIC_FOLDER = "sounds";
	private static final String SOUND_FILES =  "background.mp3" ;

	

	private SomePane somePane;
	private MenuPane menu;
	private MainMenu MMenu;
	private int count;

	public void init() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
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
