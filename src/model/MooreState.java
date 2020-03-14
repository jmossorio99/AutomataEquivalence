package model;

import java.util.ArrayList;

public class MooreState {

	private boolean isInitialState = false;
	private String machineSignature = "";
	private String name;
	private String output = "";
	private final ArrayList<MooreState> transitions = new ArrayList<>();

	public MooreState(final String name, final String machineSignature) {
		setName(name);
		this.machineSignature = machineSignature;
	}

	public void addTransition(final MooreState transition) {
		transitions.add(transition);
	}

	public String getMachineSignature() {
		return machineSignature;
	}

	public String getName() {
		return name;
	}

	public String getOutput() {
		return output;
	}

	public MooreState getTransition(final int index) {
		return transitions.get(index);
	}

	public ArrayList<MooreState> getTransitions() {
		return transitions;
	}

	public void setIsInitialState(final boolean isInitialState) {
		this.isInitialState = isInitialState;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setOutput(final String output) {
		this.output = output;
	}

}
