package application;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DollarsToPounds extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage stage) {
		
		Button exchangeButton = new Button();
		exchangeButton.setText("Exchange");
		TextField userInput = new TextField();
		Label inputValue = new Label("Input value: $");
		Label result = new Label();
		
		exchangeButton.setOnAction(event -> {
			String poundString = userInput.getCharacters().toString();
			try {
				double pounds = Double.parseDouble(poundString);
				double exchangeResult = exchange(pounds);
				result.setText(String.format("%.2f", exchangeResult));
			} catch (NumberFormatException e) {
				Alert a = new Alert(Alert.AlertType.ERROR);
				a.setTitle("Error");
				a.setHeaderText("Invalid Dollar Amount");
				a.setContentText("Please only use digits.");
				a.showAndWait();
				
			}
		});
		
		HBox input = new HBox();
		input.setAlignment(Pos.CENTER);
		input.getChildren().addAll(inputValue, userInput);
		
		VBox root = new VBox();
		root.setAlignment(Pos.CENTER);
		root.setSpacing(10);
		root.getChildren().addAll(input, exchangeButton, result);
		
		Scene scene = new Scene(root, 400, 400);
		stage.setTitle("Dollars To Pounds");
		stage.setScene(scene);
		stage.show();
	}
	private double exchange(double value) {
		double converted = 0;
		converted = value * 0.81;
		return converted;
	}
}
