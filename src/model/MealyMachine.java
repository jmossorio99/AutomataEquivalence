package model;

import java.util.ArrayList;

public class MealyMachine {

	protected final MealyState initialState;
	protected final ArrayList<String> inputs;
	protected ArrayList<MealyState> states;

	public MealyMachine(final ArrayList<MealyState> states, final MealyState initialState,
			final ArrayList<String> inputs) {
		this.initialState = initialState;
		this.states = states;
		this.inputs = inputs;
	}

	public void deleteUnreachableStates() {
		if (initialState == null) {
			return;
		}
		boolean done = false;
		final ArrayList<MealyState> R = new ArrayList<>();
		R.add(initialState);
		while (!done) {
			boolean addedOne = false;
			final ArrayList<MealyState> M = new ArrayList<>();
			for (final MealyState state : R) {
				for (final MealyState transition : state.getTransitions()) {
					M.add(transition);
				}
			}
			for (final MealyState mealyState : M) {
				if (!R.contains(mealyState)) {
					R.add(mealyState);
					addedOne = true;
				}
			}
			if (!addedOne) {
				done = true;
			}
		}
		states = R;

	}

	public MealyState getInitialState() {
		return initialState;
	}

	public String getInput(final int index) {
		return inputs.get(index);
	}

	public ArrayList<String> getInputs() {
		return inputs;
	}

	protected MealyState getState(final String stateName) {
		for (final MealyState state : states) {
			if (state.getName().equals(stateName)) {
				return state;
			}
		}
		return null;
	}

	public String getStateOutputs(final MealyState q) {
		String outputs = "";
		for (final String element : q.getOutputs()) {
			outputs += element;
		}
		return outputs;
	}

	public ArrayList<MealyState> getStates() {
		return states;
	}

}
