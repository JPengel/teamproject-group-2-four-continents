package edu.pacific.comp55.starter;
import java.lang.String;
//testtete
public enum ToppingType {
	BACON, CHEESE, EGG, //TOMATO, // add toppings on this line
	CAN, PINEAPPLE, CLOCK, ROCK; //add hazards/updates on this line
	
	 public String toString() {
		switch(this) {
		//toppings
			case BACON: return "bacon";
			case CHEESE: return "cheese";
			case EGG: return "egg";
			//case TOMATO: return "tomato";
			
		// hazards/updates
			case CAN: return "can";
			case PINEAPPLE: return "pineapple";
			case CLOCK: return "clock";
			case ROCK: return "rock";
		}
		return "n/a";
	}
	 
	 public static String soundFileName[] = new String[] {"slice.mp3", "slice.mp3", "EggCrack.mp3"};
	 public static int sliceCount[] = new int[] {0,0,0};

}
