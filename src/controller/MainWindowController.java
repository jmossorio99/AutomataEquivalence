package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import model.Program;

public class MainWindowController implements Initializable {

	public final static String MEALY = "Mealy";
	public final static String MOORE = "Moore";

	@FXML
	private Button buildMachine;
	private int columnNum;
	private final EventHandler<ActionEvent> comboBoxSelected = new EventHandler<ActionEvent>() {

		@Override
		public void handle(final ActionEvent event) {
			selectedType = machineType.getValue();
		}
	};
	private final EventHandler<ActionEvent> goBtnClicked = new EventHandler<ActionEvent>() {

		@Override
		public void handle(final ActionEvent event) {
			final GridPane m1 = (GridPane) ((Button) event.getSource()).getScene().lookup("#M1");
			final GridPane m2 = (GridPane) ((Button) event.getSource()).getScene().lookup("#M2");
			final ArrayList<String> inputs = new ArrayList<>();
			final ArrayList<String> states = new ArrayList<>();
			final ArrayList<String> m1Transitions = new ArrayList<>();
			final ArrayList<String> m2Transitions = new ArrayList<>();
			final ArrayList<String> outputsMooreM1 = new ArrayList<>();
			final ArrayList<String> outputsMooreM2 = new ArrayList<>();
			for (final String element : inputSymbolList) {
				inputs.add(element);
			}
			for (int i = 0; i < rowNum; i++) {
				for (int j = 0; j < (selectedType.equals(MOORE) ? columnNum + 1 : columnNum); j++) {
					final TextField tf1 = (TextField) m1.lookup("#" + (i + 1) + "," + (j + 1));
					final TextField tf2 = (TextField) m2.lookup("#" + (i + 1) + "," + (j + 1));
					final String tf1Text = tf1.getText();
					final String tf2Text = tf2.getText();
					if (selectedType.equals(MOORE)) {
						if (tf1Text.isEmpty() || tf2Text.isEmpty()) {
							final Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("Missing fields");
							alert.setContentText("Please enter all the fields");
							alert.show();
							return;
						}
					} else {
						if (tf1Text.isEmpty() || tf2Text.isEmpty() || (tf1Text.split(",").length != 2)
								|| (tf2Text.split(",").length != 2)) {
							final Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("Missing fields or wrong input for transitions");
							alert.setContentText("Please enter all the fields with the right input format");
							alert.show();
							return;
						}
					}
					if (j == columnNum) {
						outputsMooreM1.add(tf1Text);
						outputsMooreM2.add(tf1Text);
					}
					m1Transitions.add(tf1Text);
					m2Transitions.add(tf2Text);

				}
				states.add("q" + i);
			}
			if (!selectedType.equals(MOORE)) {
				program.initializeMealy1(m1Transitions, inputs, states);
				program.initializeMealy2(m2Transitions, inputs, states);
			} else {
				program.initializeMoore1(m1Transitions, inputs, states, outputsMooreM1);
				program.initializeMoore2(m2Transitions, inputs, states, outputsMooreM2);
			}
		}
	};
	private String[] inputSymbolList;
	@FXML
	private TextField inputSymbols;
	@FXML
	private ComboBox<String> machineType;
	@FXML
	private TextField numStates;
	private Program program;
	private int rowNum;
	private String selectedType = "";
	private final String[] types = { "Mealy", "Moore" };

	@FXML
	void buildMachineClicked(final ActionEvent event) {

		if ((inputSymbols.getText().equals("")) || (numStates.getText().equals("")) || (selectedType.equals(""))) {
			final Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Missing fields");
			alert.setContentText("Please enter all the fields");
			alert.show();
			return;
		}

		program = selectedType.equals(MOORE) ? new Program(true) : new Program(false);

		inputSymbolList = inputSymbols.getText().length() > 1 ? inputSymbols.getText().split(",")
				: new String[] { inputSymbols.getText() };
		final Stage stage = new Stage();
		final GridPane root = new GridPane();
		final GridPane gridPane1 = new GridPane();
		gridPane1.setPadding(new Insets(20));
		gridPane1.setId("M1");
		columnNum = inputSymbolList.length;
		rowNum = Integer.parseInt(numStates.getText());
		for (int i = 0; i < columnNum; i++) {
			final Label label = new Label(inputSymbolList[i]);
			gridPane1.add(label, i + 1, 0);
			gridPane1.getColumnConstraints().add(new ColumnConstraints(50));
			GridPane.setHalignment(label, HPos.CENTER);
		}
		for (int i = 0; i < rowNum; i++) {
			final Label label = new Label("q" + i);
			gridPane1.add(label, 0, i + 1);
			gridPane1.getRowConstraints().add(new RowConstraints(50));
			GridPane.setHalignment(label, HPos.CENTER);
		}
		for (int i = 0; i < rowNum; i++) {
			for (int j = 0; j < (selectedType.equals(MOORE) ? columnNum + 1 : columnNum); j++) {
				final TextField textField = new TextField();
				textField.setMaxWidth(35);
				textField.setId((i + 1) + "," + (j + 1));
				gridPane1.add(textField, j + 1, i + 1);
				GridPane.setHalignment(textField, HPos.CENTER);
			}

		}
		final GridPane gridPane2 = new GridPane();
		gridPane2.setPadding(new Insets(20));
		gridPane2.setId("M2");
		for (int i = 0; i < columnNum; i++) {
			final Label label = new Label(inputSymbolList[i]);
			gridPane2.add(label, i + 1, 0);
			gridPane2.getColumnConstraints().add(new ColumnConstraints(50));
			GridPane.setHalignment(label, HPos.CENTER);
		}
		for (int i = 0; i < rowNum; i++) {
			final Label label = new Label("q" + i);
			gridPane2.add(label, 0, i + 1);
			gridPane2.getRowConstraints().add(new RowConstraints(50));
			GridPane.setHalignment(label, HPos.CENTER);
		}
		for (int i = 0; i < rowNum; i++) {
			for (int j = 0; j < (selectedType.equals(MOORE) ? columnNum + 1 : columnNum); j++) {
				final TextField textField = new TextField();
				textField.setMaxWidth(35);
				textField.setId((i + 1) + "," + (j + 1));
				gridPane2.add(textField, j + 1, i + 1);
				GridPane.setHalignment(textField, HPos.CENTER);
			}

		}
		root.add(gridPane1, 0, 1);
		root.add(gridPane2, 2, 1);
		final Label m1Title = new Label("M 1");
		final Label m2Title = new Label("M 2");
		root.add(m1Title, 0, 0);
		root.add(m2Title, 2, 0);
		GridPane.setHalignment(m1Title, HPos.CENTER);
		GridPane.setHalignment(m2Title, HPos.CENTER);
		final Button evaluateBtn = new Button("Go");
		evaluateBtn.setOnAction(goBtnClicked);
		root.add(evaluateBtn, 1, 2);
		GridPane.setMargin(evaluateBtn, new Insets(20, 0, 20, 0));
		final String title = selectedType.equals(MOORE) ? "Moore Machine Equivalence" : "Mealy Machine Equivalence";
		stage.setTitle(title);
		stage.setScene(new Scene(root));
		stage.show();

	}

	@Override
	public void initialize(final URL location, final ResourceBundle resources) {
		machineType.setItems(FXCollections.observableArrayList(types));
		machineType.setOnAction(comboBoxSelected);

	}

}
