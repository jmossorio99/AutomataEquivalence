package model;

import java.util.ArrayList;

public class MooreState {

	private boolean isInitialState = false;
	private String machineSignature = "";
	private String name;
	private String output = "";
	private final ArrayList<MooreState> transitions = new ArrayList<>();

	/**
	 * Class constructor
	 *
	 * @param name:             the name of the state
	 * @param machineSignature: this will have the value M1 if this states belongs
	 *                          to the first machine and M2 otherwise
	 */
	public MooreState(final String name, final String machineSignature) {
		setName(name);
		this.machineSignature = machineSignature;
	}

	/**
	 * Adds a transition to the state; a transition is just the name of the state we
	 * will transition to
	 *
	 * @param transition: the state to which we will move if we get an input. These
	 *                    transitions are in the same order as the inputs, which
	 *                    means that if you have inputs 1, 0 and transitions A, B it
	 *                    means that this state will go to A with input 1 and will
	 *                    go to B with input 0.
	 */
	public void addTransition(final MooreState transition) {
		transitions.add(transition);
	}

	/**
	 *
	 * @return the machine signature, explained in the constructor of this class.
	 */
	public String getMachineSignature() {
		return machineSignature;
	}

	/**
	 *
	 * @return String containing the name of the state
	 */
	public String getName() {
		return name;
	}

	/**
	 * gets the output of this state
	 *
	 * @return the output
	 */
	public String getOutput() {
		return output;
	}

	/**
	 * returns a specific transition
	 *
	 * @param index: the index of the transition
	 * @return the transition
	 */
	public MooreState getTransition(final int index) {
		return transitions.get(index);
	}

	/**
	 *
	 * @return the complete list of transitions
	 */
	public ArrayList<MooreState> getTransitions() {
		return transitions;
	}

	/**
	 * sets the value of the boolean indicating if this state is the initial state
	 *
	 * @param isInitialState: boolean indicating if this state is an initial state
	 */
	public void setIsInitialState(final boolean isInitialState) {
		this.isInitialState = isInitialState;
	}

	/**
	 * sets the name of the state to the parameter.
	 *
	 * @param name: new name
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * sets the output of this state
	 *
	 * @param output: the String containing the output of this state.
	 */
	public void setOutput(final String output) {
		this.output = output;
	}

}
