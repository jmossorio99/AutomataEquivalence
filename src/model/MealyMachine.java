package model;

import java.util.ArrayList;
import java.util.Arrays;

public class MealyMachine {

	private final MealyState initialState;
	private final ArrayList<String> inputs;
	private final ArrayList<MealyState> states;

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
		final ArrayList<MealyState> R = new ArrayList<>(Arrays.asList(initialState));
		while (!done) {
			boolean addedOne = false;
			final ArrayList<MealyState> M = new ArrayList<>();
			for (final MealyState state : R) {
				for (final MealyState transition : state.getTransitions()) {
					if (!R.contains(transition)) {
						M.add(transition);
						addedOne = true;
					}
				}
			}
			R.addAll(M);
			if (!addedOne) {
				done = true;
			}
		}
		for (final MealyState state : states) {
			if (!R.contains(state)) {
				states.remove(state);
			}
		}

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

	private MealyState getState(final String stateName) {
		for (final MealyState state : states) {
			if (state.getName().equals(stateName)) {
				return state;
			}
		}
		return null;
	}

	public ArrayList<MealyState> getStates() {
		return states;
	}

}
