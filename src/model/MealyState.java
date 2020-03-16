package model;

import java.util.ArrayList;

public class MealyState {

	private boolean isInitialState = false;
	private String machineSignature = "";
	private String name;
	private final ArrayList<String> outputs = new ArrayList<>();
	private final ArrayList<MealyState> transitions = new ArrayList<>();

	/**
	 * Class constructor
	 *
	 * @param name:             the name of the state
	 * @param machineSignature: this will have the value M1 if this states belongs
	 *                          to the first machine and M2 otherwise
	 */
	public MealyState(final String name, final String machineSignature) {
		this.name = name;
		this.machineSignature = machineSignature;
	}

	/**
	 * This method adds an output to the list of outputs of this state
	 *
	 * @param output: the String containing the value of the output to add
	 */
	public void addOutput(final String output) {
		outputs.add(output);
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
	public void addTransition(final MealyState transition) {
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
	 * gets a certain output given the index
	 *
	 * @param index: index of the output
	 * @return the output
	 */
	public String getOutput(final int index) {
		return outputs.get(index);
	}

	/**
	 *
	 * @return the complete list of outputs
	 */
	public ArrayList<String> getOutputs() {
		return outputs;
	}

	/**
	 * returns a specific transition
	 *
	 * @param index: the index of the transition
	 * @return the transition
	 */
	public MealyState getTransition(final int index) {
		return transitions.get(index);
	}

	/**
	 *
	 * @return the complete list of transitions
	 */
	public ArrayList<MealyState> getTransitions() {
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
	 * sets the name of the state to the parameter
	 *
	 * @param name: new name
	 */
	public void setName(final String name) {
		this.name = name;
	}

}
