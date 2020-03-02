package model;

import java.util.ArrayList;

public class MealyMachine {

	private final MealyState initialState;
	private final ArrayList<MealyState> states;

	public MealyMachine(final ArrayList<MealyState> states, final MealyState initialState) {
		this.initialState = initialState;
		this.states = states;
	}

}
