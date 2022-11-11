package edu.pacific.comp55.starter;
import acm.program.*;

import java.awt.event.MouseEvent;

import acm.graphics.*;
//comment
class PauseMenu  extends GraphicsProgram {
public static final int PROGRAM_WIDTH = 1920/2; 
public static final int PROGRAM_HEIGHT = 1080/2;
private GImage backGround= new GImage("src/main/resources/Background.png");
private GImage exit = new GImage("src/main/resources/Exit.png",985/2,686/2);
private GImage continuE = new GImage("src/main/resources/Continue.png",580/2,376/2);
private GImage retry = new GImage("src/main/resources/Retry.png",580/2,686/2);
private boolean returN;
private int flick;
TimerMode timerModePause;
NoWasteMode noWasteModePause;
MainMenu mainMenu;
public PauseMenu() {}
public PauseMenu(Mode mode, MainMenu mainMenu) {
if (mode instanceof NoWasteMode) {
noWasteModePause = (NoWasteMode) mode;
flick = 1;
} else {
timerModePause = (TimerMode) mode;
flick = 2;
}
this.mainMenu = mainMenu;
drawMenu();
}

public void drawMenu() {
backGround.scale(.5);
mainMenu.add(backGround);
exit.scale(.5);
mainMenu.add(exit);
continuE.scale(.5);
mainMenu.add(continuE);
retry.scale(.5);
mainMenu.add(retry);
}

/*@Override
public void mouseClicked(MouseEvent e) {
GObject obj = getElementAt(e.getX(),e.getY());
if (obj == continuE) {
continueGame();
}
else if (obj == retry) {
hidePause();
retry();
}
else if (obj == exit) {
hidePause();
exit();
}
}*/
public void continueGame() {
hidePause();
if (flick == 1) {
noWasteModePause.startTimer();
}
else if (flick == 2) {
timerModePause.startTimer();
}
}
public void retry() {
if (flick == 1) {
noWasteModePause.resetAll();
}
else if (flick == 2) {
timerModePause.resetAll();
}
}
public void exit() {
noWasteModePause.returnToMenu();
}
private void hidePause() {
mainMenu.remove(backGround);
mainMenu.remove(retry);
mainMenu.remove(exit);
mainMenu.remove(continuE);
}
public void init() {
// setSize(PROGRAM_WIDTH,PROGRAM_HEIGHT);
}
public void run() {
drawMenu();
addMouseListeners();
}
public static void main(String[] args) {
//new PauseMenu().start(); 
}
}