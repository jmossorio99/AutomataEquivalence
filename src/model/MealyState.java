package model;

import java.util.ArrayList;

public class MealyState {

	private boolean isInitialState = false;
	private String machineSignature = "";
	private String name;
	private final ArrayList<String> outputs = new ArrayList<>();
	private final ArrayList<MealyState> transitions = new ArrayList<>();

	public MealyState(final String name, final String machineSignature) {
		this.name = name;
		this.machineSignature = machineSignature;
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

	public String getOutput(final int index) {
		return outputs.get(index);
	}

	public ArrayList<String> getOutputs() {
		return outputs;
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

	public String getMachineSignature() {
		return machineSignature;
	}

}
