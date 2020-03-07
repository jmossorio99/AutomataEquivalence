package model;

import java.util.ArrayList;
import java.util.HashMap;

import controller.MainWindowController;
import exception.NoInitialStateException;

public class Program {

	private MealyMachine mealyMachine1;
	private MealyMachine mealyMachine2;
	private SumMealyMachine sumMealyMachine;
	private String type;

	public Program(final boolean isMealy) {
		if (isMealy) {
			type = MainWindowController.MEALY;
		} else {
			type = MainWindowController.MOORE;
		}
	}

	private void calculateSumMealyMachine() {
		mealyMachine1.deleteUnreachableStates();
		mealyMachine2.deleteUnreachableStates();
		final ArrayList<MealyState> sumStates = mealyMachine1.getStates();
		sumStates.addAll(mealyMachine2.getStates());
		sumMealyMachine = new SumMealyMachine(sumStates, null, mealyMachine1.getInputs());

	}

	public String findEquivalence() {
		calculateSumMealyMachine();
		final String output = sumMealyMachine.findEquivalence();
		return output;
	}

	public void initializeMealy1(final ArrayList<String> transitionsAndOutputs, final ArrayList<String> inputs,
			final ArrayList<String> states) throws NoInitialStateException {

		final ArrayList<String> transitions = new ArrayList<>();
		final ArrayList<String> outputs = new ArrayList<>();
		for (final String transitionAndOutput : transitionsAndOutputs) {
			final String[] transitionAndOutputData = transitionAndOutput.split(",");
			transitions.add(transitionAndOutputData[0]);
			outputs.add(transitionAndOutputData[1]);
		}
		final ArrayList<MealyState> mealyStates = new ArrayList<>();
		final HashMap<String, MealyState> stateMap = new HashMap<>();
		for (final String state : states) {
			final MealyState newState = new MealyState(state);
			stateMap.put(state, newState);
			mealyStates.add(newState);
		}
		final int inputNum = inputs.size();
		int transitionIndex = 0;
		final boolean isInitialState = true;
		MealyState initialState = null;

		for (final String state : states) {
			final MealyState currentState = stateMap.get(state);
			for (int j = 0; j < inputNum; j++) {
				currentState.addTransition(stateMap.get(transitions.get(transitionIndex)));
				currentState.addOutput(outputs.get(transitionIndex));
				transitionIndex++;
			}
			if (isInitialState) {
				initialState = currentState;
				currentState.setIsInitialState(true);
			}
		}
		if (initialState != null) {
			mealyMachine1 = new MealyMachine(mealyStates, initialState, inputs);
		} else {
			throw new NoInitialStateException("Exception: No initial state");
		}

	}

	public void initializeMealy2(final ArrayList<String> transitionsAndOutputs, final ArrayList<String> inputs,
			final ArrayList<String> states) throws NoInitialStateException {

		final ArrayList<String> transitions = new ArrayList<>();
		final ArrayList<String> outputs = new ArrayList<>();
		for (final String transitionAndOutput : transitionsAndOutputs) {
			final String[] transitionAndOutputData = transitionAndOutput.split(",");
			transitions.add(transitionAndOutputData[0]);
			outputs.add(transitionAndOutputData[1]);
		}
		final ArrayList<MealyState> mealyStates = new ArrayList<>();
		final HashMap<String, MealyState> stateMap = new HashMap<>();
		for (final String state : states) {
			final MealyState newState = new MealyState(state);
			stateMap.put(state, newState);
			mealyStates.add(newState);
		}
		final int inputNum = inputs.size();
		int transitionIndex = 0;
		final boolean isInitialState = true;
		MealyState initialState = null;

		for (final String state : states) {
			final MealyState currentState = stateMap.get(state);
			for (int j = 0; j < inputNum; j++) {
				currentState.addTransition(stateMap.get(transitions.get(transitionIndex)));
				currentState.addOutput(outputs.get(transitionIndex));
				transitionIndex++;
			}
			if (isInitialState) {
				initialState = currentState;
				currentState.setIsInitialState(true);
			}
		}
		if (initialState != null) {
			mealyMachine2 = new MealyMachine(mealyStates, initialState, inputs);
		} else {
			throw new NoInitialStateException("Exception: No initial state");
		}

	}

	public void initializeMoore1(final ArrayList<String> transitions, final ArrayList<String> inputs,
			final ArrayList<String> states, final ArrayList<String> outputs) throws NoInitialStateException {

	}

	public void initializeMoore2(final ArrayList<String> transitions, final ArrayList<String> inputs,
			final ArrayList<String> states, final ArrayList<String> outputs) throws NoInitialStateException {

	}

}
