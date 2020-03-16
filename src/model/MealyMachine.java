package model;

import java.util.ArrayList;

public class MealyMachine {

	protected final MealyState initialState;
	protected final ArrayList<String> inputs;
	protected ArrayList<MealyState> states;

	/**
	 * constructor of the class
	 *
	 * @param states:       an ArrayList of states, which are the states of this
	 *                      machine.
	 * @param initialState: the initial state of the machine
	 * @param inputs:       the list of inputs that the machine will take
	 */
	public MealyMachine(final ArrayList<MealyState> states, final MealyState initialState,
			final ArrayList<String> inputs) {
		this.initialState = initialState;
		this.states = states;
		this.inputs = inputs;
	}

	/**
	 * This method deletes all the unreachable states of this machine
	 */
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

	/**
	 * @return returns the initial state of the machine
	 */
	public MealyState getInitialState() {
		return initialState;
	}

	/**
	 * returns a specific input given an index
	 *
	 * @param index: the index of the input
	 * @return the string containing the input value
	 */
	public String getInput(final int index) {
		return inputs.get(index);
	}

	/**
	 *
	 * @return the complete list of inputs of this machine
	 */
	public ArrayList<String> getInputs() {
		return inputs;
	}

	/**
	 * Returns a state given its name
	 *
	 * @param stateName: the name of the state to return
	 * @return the state that matches the name. It will return null if no state
	 *         matches the given name.
	 */
	protected MealyState getState(final String stateName) {
		for (final MealyState state : states) {
			if (state.getName().equals(stateName)) {
				return state;
			}
		}
		return null;
	}

	/**
	 * Returns a string containing the outputs of a specific state concatenated.
	 *
	 * @param q
	 * @return
	 */
	public String getStateOutputs(final MealyState q) {
		String outputs = "";
		for (final String element : q.getOutputs()) {
			outputs += element;
		}
		return outputs;
	}

	/**
	 *
	 * @return the complete list of states
	 */
	public ArrayList<MealyState> getStates() {
		return states;
	}

}
