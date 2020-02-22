package model;

import java.util.ArrayList;
import java.util.HashMap;

public class MealyAutomaton {

	private String name = "";
	private final ArrayList<State> states = new ArrayList<>();
	private final HashMap<State, ArrayList<MealyEntry>> transitions = new HashMap<>();

	public MealyAutomaton(final String name, final String transitionStrings) {

		this.name = name;
		setStates(transitionStrings);

	}

	public String getName() {
		return name;
	}

	private void setStates(final String transitionStrings) {

		State currentState = null;
		final String[] indivTransitions = transitionStrings.split(" "); // This is an array containing all the
																		// individual transitions
		for (final String indivTransition : indivTransitions) {
			final String[] transitionData = indivTransition.split(",");
			final String initialStateName = transitionData[0];
			final String finalStateName = transitionData[1];
			final String outputValue = transitionData[2];
			if ((currentState == null) || !initialStateName.equals(currentState.getName())) {
				currentState = new State(initialStateName);
				states.add(currentState);
				transitions.put(currentState, new ArrayList<MealyEntry>());
				transitions.get(currentState).add(new MealyEntry(finalStateName, outputValue));
			} else {
				transitions.get(currentState).add(new MealyEntry(finalStateName, outputValue));
			}
		}

	}

}
