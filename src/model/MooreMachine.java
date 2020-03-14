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

}
