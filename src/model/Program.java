package model;

import java.util.ArrayList;

import controller.MainWindowController;

public class Program {

	private MealyMachine mealyMachine1;
	private MealyMachine mealyMachine2;
	private MealyMachine sumMealyMachine;
	private String type;

	public Program(final boolean isMealy) {
		if (isMealy) {
			type = MainWindowController.MEALY;
		} else {
			type = MainWindowController.MOORE;
		}
	}

	public void initializeM1(final ArrayList<String> transitions, final ArrayList<String> inputs) {

	}

	public void initializeM2(final ArrayList<String> transitions, final ArrayList<String> inputs) {

	}

}
