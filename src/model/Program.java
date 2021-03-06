package model;

import java.util.ArrayList;
import java.util.HashMap;

import controller.MainWindowController;
import exception.NoInitialStateException;

public class Program {

	public final static String M1_SIGNATURE = "M1";
	public final static String M2_SIGNATURE = "M2";
	private MealyMachine mealyMachine1;
	private MealyMachine mealyMachine2;
	private MooreMachine mooreMachine1;
	private MooreMachine mooreMachine2;
	private SumMealyMachine sumMealyMachine;
	private SumMooreMachine sumMooreMachine;
	private String type;

	/**
	 * Program Class constructor
	 *
	 * @param isMealy: boolean indicating whether the program will be run with Mealy
	 *                 or Moore machines
	 */
	public Program(final boolean isMealy) {
		if (isMealy) {
			type = MainWindowController.MEALY;
		} else {
			type = MainWindowController.MOORE;
		}
	}

	/**
	 * This method calculates the direct sum of the two Mealy Machines
	 */
	private void calculateSumMealyMachine() {
		mealyMachine1.deleteUnreachableStates();
		mealyMachine2.deleteUnreachableStates();
		final ArrayList<MealyState> sumStates = mealyMachine1.getStates();
		final MealyState initialM1 = mealyMachine1.getInitialState();
		final MealyState initialM2 = mealyMachine2.getInitialState();
		sumStates.addAll(mealyMachine2.getStates());
		sumMealyMachine = new SumMealyMachine(sumStates, null, mealyMachine1.getInputs(), initialM1, initialM2);

	}

	/**
	 * This method calculates the direct sum of the two Moore Machines
	 */
	private void calculateSumMooreMachine() {
		mooreMachine1.deleteUnreachableStates();
		mooreMachine2.deleteUnreachableStates();
		final ArrayList<MooreState> sumStates = mooreMachine1.getStates();
		final MooreState initialM1 = mooreMachine1.getInitialState();
		final MooreState initialM2 = mooreMachine2.getInitialState();
		sumStates.addAll(mooreMachine2.getStates());
		sumMooreMachine = new SumMooreMachine(sumStates, null, mooreMachine1.getInputs(), initialM1, initialM2);
	}

	/**
	 * This method starts the process of finding whether the machines are equivalent
	 *
	 * @return a boolean indicating whether the machines are equivalent
	 */
	public boolean findEquivalenceMealy() {
		calculateSumMealyMachine();
		final boolean output = sumMealyMachine.findEquivalence();
		return output;
	}

	/**
	 * This method starts the process of finding whether the machines are equivalent
	 *
	 * @return a boolean indicating whether the machines are equivalent
	 */
	public boolean findEquivalenceMoore() {
		calculateSumMooreMachine();
		final boolean output = sumMooreMachine.findEquivalence();
		return output;
	}

	/**
	 * This method initializes the first Mealy Machine
	 *
	 * @param transitionsAndOutputs: ArrayList of strings containing in each entry
	 *                               the state to which the current state goes to
	 *                               with a certain input comma the output it gives.
	 * @param inputs:                the list of inputs the machine will take.
	 * @param states:                the names of the states of the machine.
	 * @throws NoInitialStateException: in case the initial state is null.
	 */
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
			final MealyState newState = new MealyState(state, M1_SIGNATURE);
			stateMap.put(state, newState);
			mealyStates.add(newState);
		}
		final int inputNum = inputs.size();
		int transitionIndex = 0;
		boolean isInitialState = true;
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
				isInitialState = false;
			}
		}
		if (initialState != null) {
			mealyMachine1 = new MealyMachine(mealyStates, initialState, inputs);
		} else {
			throw new NoInitialStateException("Exception: No initial state");
		}

	}

	/**
	 * This method initializes the second Mealy Machine
	 *
	 * @param transitionsAndOutputs: ArrayList of strings containing in each entry
	 *                               the state to which the current state goes to
	 *                               with a certain input comma the output it gives.
	 * @param inputs:                the list of inputs the machine will take.
	 * @param states:                the names of the states of the machine.
	 * @throws NoInitialStateException: in case the initial state is null.
	 */
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
			final MealyState newState = new MealyState(state, M2_SIGNATURE);
			stateMap.put(state, newState);
			mealyStates.add(newState);
		}
		final int inputNum = inputs.size();
		int transitionIndex = 0;
		boolean isInitialState = true;
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
				isInitialState = false;
			}
		}
		if (initialState != null) {
			mealyMachine2 = new MealyMachine(mealyStates, initialState, inputs);
		} else {
			throw new NoInitialStateException("Exception: No initial state");
		}

	}

	/**
	 * This method initializes the first Moore Machine
	 *
	 * @param transitions: ArrayList of strings containing in each entry the state
	 *                     to which the current state goes to with a certain input.
	 * @param inputs:      the list of inputs the machine will take.
	 * @param states:      the names of the states of the machine.
	 * @param outputs:     this is the list of outputs the states will give
	 * @throws NoInitialStateException: in case the initial state is null.
	 */
	public void initializeMoore1(final ArrayList<String> transitions, final ArrayList<String> inputs,
			final ArrayList<String> states, final ArrayList<String> outputs) throws NoInitialStateException {

		final ArrayList<MooreState> mooreStates = new ArrayList<>();
		final HashMap<String, MooreState> stateMap = new HashMap<>();
		for (final String state : states) {
			final MooreState newState = new MooreState(state, M1_SIGNATURE);
			stateMap.put(state, newState);
			mooreStates.add(newState);
		}

		final int inputNum = inputs.size();
		int transitionIndex = 0;
		int stateIndex = 0;
		boolean isInitialState = true;
		MooreState initialState = null;

		for (final String state : states) {
			final MooreState currentState = stateMap.get(state);
			for (int j = 0; j < inputNum; j++) {
				currentState.addTransition(stateMap.get(transitions.get(transitionIndex)));
				transitionIndex++;
			}
			if (isInitialState) {
				initialState = currentState;
				currentState.setIsInitialState(true);
				isInitialState = false;
			}
			currentState.setOutput(outputs.get(stateIndex));
			stateIndex++;
		}
		if (initialState != null) {
			mooreMachine1 = new MooreMachine(mooreStates, initialState, inputs);
		} else {
			throw new NoInitialStateException("Exception: No initial state");
		}

	}

	/**
	 * This method initializes the second Moore Machine
	 *
	 * @param transitions: ArrayList of strings containing in each entry the state
	 *                     to which the current state goes to with a certain input.
	 * @param inputs:      the list of inputs the machine will take.
	 * @param states:      the names of the states of the machine.
	 * @param outputs:     this is the list of outputs the states will give
	 * @throws NoInitialStateException: in case the initial state is null.
	 */
	public void initializeMoore2(final ArrayList<String> transitions, final ArrayList<String> inputs,
			final ArrayList<String> states, final ArrayList<String> outputs) throws NoInitialStateException {

		final ArrayList<MooreState> mooreStates = new ArrayList<>();
		final HashMap<String, MooreState> stateMap = new HashMap<>();
		for (final String state : states) {
			final MooreState newState = new MooreState(state, M2_SIGNATURE);
			stateMap.put(state, newState);
			mooreStates.add(newState);
		}

		final int inputNum = inputs.size();
		int transitionIndex = 0;
		int stateIndex = 0;
		boolean isInitialState = true;
		MooreState initialState = null;

		for (final String state : states) {
			final MooreState currentState = stateMap.get(state);
			for (int j = 0; j < inputNum; j++) {
				currentState.addTransition(stateMap.get(transitions.get(transitionIndex)));
				transitionIndex++;
			}
			if (isInitialState) {
				initialState = currentState;
				currentState.setIsInitialState(true);
				isInitialState = false;
			}
			currentState.setOutput(outputs.get(stateIndex));
			stateIndex++;
		}
		if (initialState != null) {
			mooreMachine2 = new MooreMachine(mooreStates, initialState, inputs);
		} else {
			throw new NoInitialStateException("Exception: No initial state");
		}

	}

}
