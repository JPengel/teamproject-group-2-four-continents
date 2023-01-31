package edu.pacific.comp55.starter;
import java.lang.String;
public enum ToppingType {
	BACON, CHEESE, EGG, TOMATO, // if adding new ingredients to the game add toppings name on this line
	CAN, PINEAPPLE, CLOCK, ROCK; //if adding new hazards/updates to the game add hazards/updates name on this line
	
	
	 public String toString() {
		switch(this) {
		//toppings
			case BACON: return "bacon";
			case CHEESE: return "cheese";
			case EGG: return "egg";
			case TOMATO: return "tomato";
	 //if adding new ingredients to the game add toppings name below on this line	
			
			
			
			
	 //if adding new hazards/updates to the game add hazards/updates name below on this line
			//hazards/updates
			case CAN: return "can";
			case PINEAPPLE: return "pineapple";
			case CLOCK: return "clock";
			case ROCK: return "rock";
		}
		return "n/a";
	}
	 
	 /* This method receives a integer as input and matches the input with the ingredients names based
	  	on their enum position */
	 
	 public static ToppingType TypeName(int i) {
		 //"n" case matches with ingredient on "n" enum position and returns it
		 switch (i) {
		 case 0: 
			return BACON;
		 case 1:
			 return CHEESE;
		 case 2: 
			 return EGG;
		 case 3:
			 return TOMATO;
	 //if adding new ingredients to the game add one more case and return the name of the new ingredient 
		
		 
		 
		 //if case is not matched, by default return rock
		 default:
			return ROCK;
		 }
	 }
	 // if adding new ingredients to the game add one more sound file name to this array, follow the enum order above
	 public static String soundFileName[] = new String[] {"slice.mp3", "slice.mp3", "EggCrack.mp3", "slice.mp3"};
	 
	 public static int sliceCount[];
	 
	 /* This method simply initializes the array thats keeps track of how many times each ingredient
	   	type was sliced based on the ingredients enum position */
	 
	 public static void initArray() {
		 //create new array that has same size as the sound file array
		 sliceCount = new int[soundFileName.length];
		 for(int i = 0; i < soundFileName.length; i++) {
			 //initialize each element of the array to zero
			 sliceCount[i] = 0;
		 }
	 }
}
