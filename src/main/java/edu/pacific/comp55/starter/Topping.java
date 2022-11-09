package edu.pacific.comp55.starter;

import acm.program.*;
import acm.graphics.*;
import java.util.random.RandomGenerator;

public class Topping {
	//VARIABLES
	private static int startX, vertexX, vertexY;
	private static final int startY = -1080;
	private static double curX, curY, hParab, aParab, kParab;

	private static RandomGenerator gFlip, gStartXLeft, gStartXRight, gVertexX, gVertexY;
	private static ToppingType type;
	private static boolean isCut;
	private static int flick = 0;
	private static GImage image;
	public static final String IMG_FILE_PATH = "src/main/resources/", IMG_EXTENSION = ".png";
	private static double moveSpeed = 1; //Changes the pace of the game
	
	//CONSTRUCTORS
	Topping(ToppingType type){
		this.type = type;
		generateCoordinates();
		createPath();
		createImage();
	}
	Topping(ToppingType type, int moveSpeed){
		this.type = type;
		generateCoordinates();
		createPath();
		createImage();
		this.moveSpeed = moveSpeed;
	}

	
	//COORDINATE GENERATORS
	static void generateStartX() {
		gFlip = RandomGenerator.getDefault();
		int flip = gFlip.nextInt(0, 1);
		if(flip == 1) {
			gStartXLeft = RandomGenerator.getDefault();
			startX = gStartXLeft.nextInt(-1720, -980);
		} else {
			gStartXRight = RandomGenerator.getDefault();
			startX = gStartXRight.nextInt(-1380, -200);
		}
		System.out.println("Starting X: " + startX); //4TPs
	}
	static void generateVertexX() {
		gVertexX = RandomGenerator.getDefault();
		vertexX = gVertexX.nextInt(-460, 460);
		System.out.println("Vertex X: " + vertexX); //4TPs
	}
	static void generateVertexY() {
		gVertexY = RandomGenerator.getDefault();
		vertexY = gVertexY.nextInt(-500, -100);
		System.out.println("Vertex Y: " + vertexY); //4TPs
	}
	static void generateCoordinates() {
		generateStartX();
		generateVertexX();
		generateVertexY();
	}
	
	//PARABOLA MAKER
	static void createPath() {
		curX = startX;
		curY = startY;
		hParab = vertexX;
		kParab = vertexY;
		aParab = (startY/Math.pow((curX-hParab),2))-(kParab/Math.pow((curX-hParab),2));
		System.out.println("FUNCTION: y = " + aParab + " * ( x - " + hParab + ")^2 + " + kParab); //4TPs
	}
	
	//CREATE IMAGE 
	static void createImage() {
		image = new GImage(IMG_FILE_PATH + type.toString() + IMG_EXTENSION);
		image.setLocation(curX * -1, curY * -1);
		//add(image); <- HAS A PROBLEM FOR SOME REASON
	}
	
	//INCREMENT LOCATION
	static void incrementLocation() {
		if (startX > vertexX) {
			curX -= moveSpeed;
		} else {
			curX += moveSpeed;
		}
		curY = aParab * Math.pow((curX - hParab), 2) + kParab;
	}
	
	//UPDATE IMAGE WITH LOCATION
	static void moveTopping() {
		if(shouldMove()) {
			if(isCut) {
				if(flick < 1) {
					image = new GImage(IMG_FILE_PATH + type.toString() + "_cut" + IMG_EXTENSION);
					flick++;
				}
				curY -= moveSpeed;
			} else {
				incrementLocation();
			}
			//image.setLocation(curX * -1, curY * -1);
		} else {
			System.out.println("TOPPING SHOULDN'T BE MOVING"); //4TPs
		}
	}
	
	public static boolean shouldMove() {
		if(curY > -1080.01) {
			return true;
		} else {
			return false;
		}
	}
		
	//GETTERS AND SETTERS
	public boolean isCut() {
		return isCut;
	}
	public void setCut(boolean isCut) {
		this.isCut = isCut;
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
	public static double getHParab() {
		return hParab;
	}
	public static double getAParab() {
		return aParab;
	}
	public static double getKParab() {
		return kParab;
	}
	
	//TEST GROUND
	public static void main(String[] args) {
		parabolaTester();
	}
	
	//PARABOLA TESTER
	public static void parabolaTester() {
		generateCoordinates();
		createPath();
		moveSpeed = 1;
		isCut = false;
		System.out.println("|   x   |   Y   |");
		for(int i = 0; i < 11; i++) {
			moveTopping();
			System.out.println("|"+ curX + " | " + curY);
		}
		flick = 1;
		isCut = true;
		System.out.println("\nSetting isCut TRUE\n|   x   |   Y   |");
		for(int i = 0; i < 11; i++) {
			moveTopping();
			System.out.println("|"+ curX + " | " + curY);
		}
		
	}
}

