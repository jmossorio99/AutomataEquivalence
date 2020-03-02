package model;

import java.util.ArrayList;

public class MealyState {

	private final String name;
	private final ArrayList<String> transitions;

	public MealyState(final String name, final ArrayList<String> transitions) {
		this.name = name;
		this.transitions = transitions;
	}

	public String getName() {
		return name;
	}

	public ArrayList<String> getTransitions() {
		return transitions;
	}

}
