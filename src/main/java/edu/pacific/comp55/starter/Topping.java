package edu.pacific.comp55.starter;

import acm.program.*;
import acm.graphics.*;
import java.util.random.RandomGenerator;

public class Topping extends GraphicsPane{
	//VARIABLES
	private  double startX, vertexX, vertexY;
	//					    ↘ CHANGE SCALE ↙ not the screen dimensions
	private  final double SCALE = 0.5, startY = 0, WIDTH = 1920 * SCALE, HEIGHT = 1080 * SCALE;
	private  double curX, curY, hParab, aParab, kParab;
	private  MainApplication Mapp;
	private  RandomGenerator gFlip, gStartXLeft, gStartXRight, gVertexX, gVertexY;
	private  ToppingType type;
	
	private  boolean isCut;
	private  int flick = 0;
	private  GImage image, cutImage;
	public  final String IMG_FILE_PATH = "src/main/resources/", IMG_EXTENSION = ".png";
	private  double moveSpeed = 5; //Changes the pace of the game
	
	//CONSTRUCTORS
	Topping(ToppingType type,MainApplication s){
		this.type = type;
		generateCoordinates();
		Mapp = s;
		createPath();
		createImage();
	}
	Topping(ToppingType type, int moveSpeed, MainApplication s){
		this.type = type;
		Mapp = s;
		generateCoordinates();
		createPath();
		createImage();
		this.moveSpeed = moveSpeed;
	}
	
	//COORDINATE GENERATORS
	 void generateStartX() {
		gFlip = RandomGenerator.getDefault();
		int flip = gFlip.nextInt(0, 2);
		if(flip == 1) {
			gStartXLeft = RandomGenerator.getDefault();
			startX = gStartXLeft.nextDouble(200*SCALE, 600*SCALE);
		} else {
			gStartXRight = RandomGenerator.getDefault();
			startX = gStartXRight.nextDouble(1320*SCALE, 1720*SCALE);
		}
//		System.out.println("Starting X: " + startX); //4TPs
	}
	 void generateVertexX() {
		gVertexX = RandomGenerator.getDefault();
		vertexX = gVertexX.nextDouble(760*SCALE, 1160*SCALE);
//		System.out.println("Vertex X: " + vertexX); //4TPs
	}
	 void generateVertexY() {
		gVertexY = RandomGenerator.getDefault();
		vertexY = gVertexY.nextDouble(580*SCALE, 980*SCALE);
//		System.out.println("Vertex Y: " + vertexY); //4TPs
	}
	 void generateCoordinates() {
		generateStartX();
		generateVertexX();
		generateVertexY();
	}
	
	//PARABOLA MAKER
	 void createPath() {
		curX = startX;
		curY = startY*SCALE;
		hParab = vertexX;
		kParab = vertexY;
		aParab = (curY/Math.pow((curX-hParab),2))-(kParab/Math.pow((curX-hParab),2));
//		System.out.println("FUNCTION: y = " + aParab + " * ( x - " + hParab + ")^2 + " + kParab); //4TPs
	}
	
	//CREATE IMAGE 
	void createImage() {
		image = new GImage(IMG_FILE_PATH + type.toString() + IMG_EXTENSION);
		image.setLocation(curX, HEIGHT- curY);
//		System.out.println(IMG_FILE_PATH + type.toString() + IMG_EXTENSION); //4TPs
//		System.out.println(image); //4TPs
		Mapp.add(image); 
	}
	
	//INCREMENT LOCATION
	void incrementLocation() {
		if (startX > vertexX) {
			curX -= moveSpeed;
		} else {
			curX += moveSpeed;
		}
		curY = aParab * Math.pow((curX - hParab), 2) + kParab;
	}
	
	//UPDATE IMAGE WITH LOCATION
	 void moveTopping() {
		if(shouldMove()) {
			if(isCut) {
				curY -= moveSpeed;
				cutImage.setLocation(curX, HEIGHT - curY);
			} else {
				incrementLocation();
				image.setLocation(curX, HEIGHT - curY);
			}
		} else {
//			System.out.println("TOPPING SHOULDN'T BE MOVING"); //4TPs
		}
	}
	
	public boolean shouldMove() {
		if(curY < -1 || curX < 0 - image.getWidth() || curX > WIDTH) {
			if(isCut()) {
				Mapp.remove(cutImage);
			} else {
				Mapp.remove(image);
			}
			return false;
		} else {
			return true;
		}
	}
		
	//GETTERS AND SETTERS
	public boolean isCut() {
		return isCut;
	}
	public void cutTopping() {
		AudioPlayer.getInstance().playSound("sounds", "slice.mp3");
		isCut = true;
		cutImage = new GImage(IMG_FILE_PATH + type.toString() + "_cut" + IMG_EXTENSION);
		cutImage.setLocation(curX, curY);
		Mapp.remove(image);
		Mapp.add(cutImage); 
	}
	public double getCurY() {
		return curY;
	}
	public double getCurX() {
		return curX;
	}
	public void setMoveSpeed(int moveSpeed) {
		this.moveSpeed = moveSpeed;
	}
	public  double getHParab() {
		return hParab;
	}
	public  double getAParab() {
		return aParab;
	}
	public  double getKParab() {
		return kParab;
	}
	public  GImage getImage() {
		return image;
	}
	public ToppingType getType() {
		return type;
	}
	
	//TEST GROUND
	public  void main(String[] args) {
		type = ToppingType.BACON;
		createImage();
		parabolaTester();
	}
	
	//PARABOLA TESTER
	public  void parabolaTester() {
		generateCoordinates();
		createPath();
		moveSpeed = 1;
		isCut = false;
		System.out.println("|   x   |   Y   |");
		for(int i = 0; i < 11; i++) {
			moveTopping();
			System.out.println("|"+ curX + " | " + curY);
		}
		isCut = true;
		System.out.println("\nSetting isCut TRUE\n|   x   |   Y   |");
		for(int i = 0; i < 11; i++) {
			moveTopping();
			System.out.println("|"+ curX + " | " + curY);
		}
	}
	@Override
	public void showContents() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void hideContents() {
		// TODO Auto-generated method stub
		
	}
}