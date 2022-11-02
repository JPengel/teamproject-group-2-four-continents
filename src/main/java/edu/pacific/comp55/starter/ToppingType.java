package edu.pacific.comp55.starter;
import java.lang.String;

public enum ToppingType {
	BACON, CHEESE, EGG, CAN, PINEAPPLE, CLOCK, ROCK;
	
	 public String toString() {
		switch(this) {
			case BACON: return "bacon";
			case CHEESE: return "cheese";
			case EGG: return "egg";
			case CAN: return "can";
			case PINEAPPLE: return "pineapple";
			case CLOCK: return "clock";
			case ROCK: return "rock";
		}
		return "n/a";
	}

}
