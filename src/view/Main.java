/**
 * @author Jose Ossorio A00130524
 */

package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(final String[] args) {

		launch();

	}

	@Override
	public void start(final Stage primaryStage) throws Exception {

		final FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/MainWindowView.fxml"));
		final Parent root = loader.load();
		final Scene mainScene = new Scene(root);
		primaryStage.setScene(mainScene);
		primaryStage.setTitle("Automata Equivalence");
		primaryStage.show();

	}

}
