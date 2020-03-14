package model;

import java.util.ArrayList;

public class MooreMachine {

	protected final MooreState initialState;
	protected final ArrayList<String> inputs;
	protected ArrayList<MooreState> states;

	public MooreMachine(final ArrayList<MooreState> states, final MooreState initialState,
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

	public MooreState getInitialState() {
		return initialState;
	}

	public String getInput(final int index) {
		return inputs.get(index);
	}

	public ArrayList<String> getInputs() {
		return inputs;
	}

	protected MooreState getState(final String stateName) {
		for (final MooreState state : states) {
			if (state.getName().equals(stateName)) {
				return state;
			}
		}
		return null;
	}

	public String getStateOutput(final MooreState q) {
		return q.getOutput();
	}

	public ArrayList<MooreState> getStates() {
		return states;
	}

}
