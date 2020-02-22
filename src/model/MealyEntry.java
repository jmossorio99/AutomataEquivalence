package model;

public class MealyEntry {

	private String finalStateName = "";
	private String outputValue = "";

	public MealyEntry(final String finalStateName, final String outputValue) {
		this.finalStateName = finalStateName;
		this.outputValue = outputValue;
	}

	public String getFinalStateName() {
		return finalStateName;
	}

	public String getOutputValue() {
		return outputValue;
	}

}
