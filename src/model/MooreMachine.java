package model;

import java.util.ArrayList;

public class MooreMachine {

	protected final MooreState initialState;
	protected final ArrayList<String> inputs;
	protected ArrayList<MooreState> states;

	/**
	 * constructor of the class
	 *
	 * @param states:       an ArrayList of states, which are the states of this
	 *                      machine.
	 * @param initialState: the initial state of the machine
	 * @param inputs:       the list of inputs that the machine will take
	 */
	public MooreMachine(final ArrayList<MooreState> states, final MooreState initialState,
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
		final ArrayList<MooreState> R = new ArrayList<>();
		R.add(initialState);
		while (!done) {
			boolean addedOne = false;
			final ArrayList<MooreState> M = new ArrayList<>();
			for (final MooreState state : R) {
				for (final MooreState transition : state.getTransitions()) {
					M.add(transition);
				}
			}
			for (final MooreState mooreState : M) {
				if (!R.contains(mooreState)) {
					R.add(mooreState);
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
	public MooreState getInitialState() {
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
	protected MooreState getState(final String stateName) {
		for (final MooreState state : states) {
			if (state.getName().equals(stateName)) {
				return state;
			}
		}
		return null;
	}

	/**
	 * returns the output of a specific state
	 *
	 * @param q: the state of which we want to obtain the output
	 * @return the output of that state
	 */
	public String getStateOutput(final MooreState q) {
		return q.getOutput();
	}

	/**
	 *
	 * @return the complete list of states
	 */
	public ArrayList<MooreState> getStates() {
		return states;
	}

}
