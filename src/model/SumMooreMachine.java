package model;

import java.util.ArrayList;

/**
 * This class is the direct sum of the two Moore Machines.
 *
 */
public class SumMooreMachine extends MooreMachine {

	private final MooreState initialM1;
	private final MooreState initialM2;

	/**
	 * constructor of the class
	 *
	 * @param states:       an ArrayList of states, which are the states of this
	 *                      machine.
	 * @param initialState: the initial state of the machine
	 * @param inputs:       the list of inputs that the machine will take
	 * @param initialM1:    the initial state of M1
	 * @param initialM2:    the initial state of M2
	 */
	public SumMooreMachine(final ArrayList<MooreState> states, final MooreState initialState,
			final ArrayList<String> inputs, final MooreState initialM1, final MooreState initialM2) {
		super(states, initialState, inputs);
		this.initialM1 = initialM1;
		this.initialM2 = initialM2;
		int index = 0;
		for (final MooreState state : states) {
			state.setName("q" + index);
			index++;
		}
	}

	/**
	 * Check whether two given partitions are equivalent
	 *
	 * @param p1:         first partition
	 * @param partitionK: second partition
	 * @return a boolean indicating if they are the same or not; TRUE or FALSE
	 */
	private boolean checkPartitionsEquivalence(final ArrayList<ArrayList<MooreState>> p1,
			final ArrayList<ArrayList<MooreState>> partitionK) {

		if (p1.size() == partitionK.size()) {
			for (int i = 0; i < p1.size(); i++) {
				if (!p1.get(i).equals(partitionK.get(i))) {
					return false;
				}
			}
			return true;
		}

		return false;
	}

	/**
	 * This method evaluates the last partition to check if it fulfills all the
	 * requirements that indicate that indeed the machines are equivalent
	 *
	 * @param pf: the partition to evaluate
	 * @return a boolean indicating whether the partition fulfills all requirements:
	 *         every equivalence class has a state from each machine and the initial
	 *         states of each machine are in the same equivalence class.
	 */
	public boolean evaluateFinalPartition(final ArrayList<ArrayList<MooreState>> pf) {
		boolean initialsOk = false;
		boolean fromBothMachines = true;
		for (final ArrayList<MooreState> eqClass : pf) {
			if (eqClass.contains(initialM1) && eqClass.contains(initialM2)) {
				System.out.println(1);
				initialsOk = true;
				break;
			}
		}
		for (final ArrayList<MooreState> eqClass : pf) {
			boolean tempM1 = false;
			boolean tempM2 = false;
			for (final MooreState state : eqClass) {
				if (state.getMachineSignature().equals(Program.M1_SIGNATURE)) {
					tempM1 = true;
				}
				if (state.getMachineSignature().equals(Program.M2_SIGNATURE)) {
					tempM2 = true;
				}
			}
			if (!tempM1 || !tempM2) {
				System.out.println(2);
				fromBothMachines = false;
				break;
			}
		}
		return initialsOk && fromBothMachines;
	}

	/**
	 * This method is the trigger for the whole process of determining if the
	 * machines are equivalent
	 *
	 * @return a boolean indicating if the machines that make this direct sum are
	 *         equivalent or not
	 */
	public boolean findEquivalence() {
		final ArrayList<ArrayList<MooreState>> p1 = findP1();
		final ArrayList<ArrayList<MooreState>> pf = findPf(p1);
		return evaluateFinalPartition(pf);
	}

	/**
	 * finds the first partition of the states of the machine
	 *
	 * @return the first partition
	 */
	public ArrayList<ArrayList<MooreState>> findP1() {
		final ArrayList<ArrayList<MooreState>> p1 = new ArrayList<>();
		final ArrayList<String> outs = new ArrayList<>();
		for (int i = 0; i < states.size(); i++) {
			final ArrayList<MooreState> temp = new ArrayList<>();
			final MooreState currentState = states.get(i);
			final String currentOutput = getStateOutput(currentState);
			// System.out.println(currentOutput);
			if (!outs.contains(currentOutput)) {
				outs.add(currentOutput);
				temp.add(currentState);
				for (int j = i + 1; j < states.size(); j++) {
					final MooreState nextState = states.get(j);
					if (currentOutput.equals(getStateOutput(nextState))) {
						temp.add(nextState);
					}
				}
			}
			if (temp.size() > 0) {
				p1.add(temp);
			}
		}
		String test = "{";
		for (final ArrayList<MooreState> element : p1) {
			test += "{";
			for (final MooreState element2 : element) {
				test += element2.getName();
			}
			test += "}";
		}
		test += "}";
		System.out.println(test);
		return p1;
	}

	/**
	 * finds the last partition, recursively
	 *
	 * @param p1: the first partition of the states of the machine.
	 * @return the last partition.
	 */
	private ArrayList<ArrayList<MooreState>> findPf(final ArrayList<ArrayList<MooreState>> p1) {

		final ArrayList<ArrayList<MooreState>> partitionK = new ArrayList<>();
		for (final ArrayList<MooreState> eqClass : p1) {
			final ArrayList<MooreState> newEqClass = new ArrayList<>();
			final ArrayList<MooreState> newEqClass2 = new ArrayList<>();
			final MooreState currentState = eqClass.get(0);
			final ArrayList<MooreState> currentStateTransitions = currentState.getTransitions();
			newEqClass.add(currentState);
			for (int i = 1; i < eqClass.size(); i++) {
				boolean areEquivalent = true;
				final ArrayList<MooreState> nextStateTransitions = eqClass.get(i).getTransitions();
				for (int j = 0; j < nextStateTransitions.size(); j++) {
					if (!statesAreEquivalent(p1, currentStateTransitions.get(j), nextStateTransitions.get(j))) {
						areEquivalent = false;
						break;
					}
				}
				if (areEquivalent) {
					newEqClass.add(eqClass.get(i));
				} else {
					newEqClass2.add(eqClass.get(i));
				}
			}
			partitionK.add(newEqClass);
			if (!newEqClass2.isEmpty()) {
				partitionK.add(newEqClass2);
			}
		}
		String test = "{";
		for (final ArrayList<MooreState> element : partitionK) {
			test += "{";
			for (final MooreState element2 : element) {
				test += element2.getName();
			}
			test += "}";
		}
		test += "}";
		System.out.println(test);
		if (checkPartitionsEquivalence(p1, partitionK)) {
			return p1;
		} else {
			return findPf(partitionK);
		}

	}

	/**
	 * This method evaluates whether two states in an equivalence class have their
	 * successors in the same equivalence class of the previous partition
	 *
	 * @param partition: the partition in which the two states are
	 * @param state1:    the first state
	 * @param state2:    the second state
	 * @return a boolean indicating whether they fulfill the conditions or not
	 */
	private boolean statesAreEquivalent(final ArrayList<ArrayList<MooreState>> partition, final MooreState state1,
			final MooreState state2) {

		for (final ArrayList<MooreState> eqClass : partition) {
			if (eqClass.contains(state1) && eqClass.contains(state2)) {
				return true;
			}
		}
		return false;

	}

}
