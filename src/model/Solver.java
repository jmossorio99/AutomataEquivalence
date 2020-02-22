package model;

public class Solver {

	private MealyAutomaton mealyAutomaton = null;
	private MooreAutomaton mooreAutomaton = null;

	public Solver(final MealyAutomaton mealyAutomaton) {
		this.mealyAutomaton = mealyAutomaton;
	}

	public Solver(final MooreAutomaton mooreAutomaton) {
		this.mooreAutomaton = mooreAutomaton;
	}

}
