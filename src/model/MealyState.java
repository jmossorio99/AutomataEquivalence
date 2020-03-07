package model;

import java.util.ArrayList;

public class MealyState {

	private boolean isInitialState = false;
	private String name;
	private final ArrayList<String> outputs = new ArrayList<>();
	private final ArrayList<MealyState> transitions = new ArrayList<>();

	public MealyState(final String name) {
		this.name = name;
	}

	public void addOutput(final String output) {
		outputs.add(output);
	}

	public void addTransition(final MealyState transition) {
		transitions.add(transition);
	}

	public String getName() {
		return name;
	}

	public MealyState getTransition(final int index) {
		return transitions.get(index);
	}

	public ArrayList<MealyState> getTransitions() {
		return transitions;
	}

	public void setIsInitialState(final boolean isInitialState) {
		this.isInitialState = isInitialState;
	}

	public void setName(final String name) {
		this.name = name;
	}

}
