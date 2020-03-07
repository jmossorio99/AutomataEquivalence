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

	public void initializeMealy1(final ArrayList<String> transitions, final ArrayList<String> inputs,
			final ArrayList<String> states) {

	}

	public void initializeMealy2(final ArrayList<String> transitions, final ArrayList<String> inputs,
			final ArrayList<String> states) {

	}

	public void initializeMoore1(final ArrayList<String> transitions, final ArrayList<String> inputs,
			final ArrayList<String> states, final ArrayList<String> outputs) {

	}

	public void initializeMoore2(final ArrayList<String> transitions, final ArrayList<String> inputs,
			final ArrayList<String> states, final ArrayList<String> outputs) {

	}

}
