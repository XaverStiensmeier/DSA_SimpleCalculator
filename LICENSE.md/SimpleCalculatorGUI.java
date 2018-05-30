package dsaFunctions;

import java.util.Optional;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;

public class SimpleCalculatorGUI extends Application {
	private Button zButtonHelp;
	private Button zButtonGroupThrowProbability;
	private Button zButtonThrowProbability;
	private Button zButtonGetQualityProbability;
	private Button zButtonSetN;
	private SimpleCalculator myCalculator = new SimpleCalculator();

	public static void main(String args[]) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("DSA - Calculator");

		// buttons initializing
		zButtonHelp = new Button();
		zButtonGroupThrowProbability = new Button();
		zButtonThrowProbability = new Button();
		zButtonGetQualityProbability = new Button();
		zButtonSetN = new Button();

		// buttons setText
		zButtonHelp.setText("Help");
		zButtonGroupThrowProbability.setText("Sammelpelproben");
		zButtonThrowProbability.setText("Einzel Proben");
		zButtonGetQualityProbability.setText("Einzel Proben mit Qualitätsstufe");
		zButtonSetN.setText("Setze 'N'. Wobei N Anzahl der Test-Würfe ist");
		;

		// This will set actionListener
		this.zButtonHelp.setOnAction((event) -> {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText(
					"Das Programm arbeitet nicht per Formel sondern per Brute-Force. Daher ist eine große Versuchsgröße N notwendig."
							+ "\nEs werden also N Versuche ausgewertet. Standart ist eine Millionen (pro Wurf)"
							+ "\nBei leerer Eingabe wird immer 0 angenommen."
							+ "\nErklärung der einzelnen Buttons:" 
							+ "\n\nEinzel Proben... Gibt die Wahrscheinlichkeit eine einzelne Probe zu bestehen"
							+ "\n\nQualitätsstufen... Gibt die Wahrscheinlichkeit eine Probe mit einer bestimmten QS oder höher zu bestehen"
							+ "\n\nSammelproben... Gibt die Wahrscheinlichkeit eine Sammelprobe mit gesammt-QS X oder höher zu bestehen"
							+ "\n\nSetze N... Lege dieses N fest"
							+ "\n\nHelp - Das was du gedrückt hast. Hilfe.");

			alert.showAndWait();
		});
		;

		this.zButtonGetQualityProbability.setOnAction((event) -> {
			double lReturn = this.myCalculator.getQualityLevelProbability(
					getValue("Zu erreichende QS (1-6)"), getValue("Eigenschaft 1"),
					getValue("Eigenschaft 2"), getValue("Eigenschaft 3"), getValue("Fähigkeitswert"),
					getValue("Modifikator"));
			showText("" + lReturn);
		});
		;

		this.zButtonGroupThrowProbability.setOnAction((event) -> {
			double lReturn = this.myCalculator.getGroupThrowProbability(getValue("Wie viele Proben (z.b. 7)"),
					getValue("Zu erreichende gesammt QS (z.b. 10)"), getValue("Eigenschaft 1"),
					getValue("Eigenschaft 2"), getValue("Eigenschaft 3"), getValue("Fähigkeitswert"),
					getValue("Modifikator"));
			showText("" + lReturn);
		});
		;

		this.zButtonThrowProbability.setOnAction((event) -> {
			double lReturn = this.myCalculator.getThrowProbability(getValue("Eigenschaft 1"), getValue("Eigenschaft 2"),
					getValue("Eigenschaft 3"), getValue("Fähigkeitswert"), getValue("Modifikator"));
			showText("" + lReturn);
		});
		;

		this.zButtonSetN.setOnAction((event) -> {
			TextInputDialog dialog = new TextInputDialog("1000000");
			dialog.setTitle("Setze N");
			dialog.setHeaderText(null);

			dialog.setContentText("Gebe einen neuen Wert für n (es sollte ein 10000 < Wert < 1000000000 sein)");
			dialog.initStyle(StageStyle.UTILITY);

			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()) {
				int x = Integer.parseInt(result.get());
				if(x == 42) {
					showText("Herzlichen Glückwunsch, Bruder im Glauben.\nIch habe den Wert nicht geändert. 42 ist zwar sehr schön aber zu klein!");
				}
				else {
				this.myCalculator.setN(x);
				showText("Fertig");
				}
			}
		});
		;

		// setPosition
		zButtonHelp.setLayoutX(0);
		zButtonHelp.setLayoutY(120);
		zButtonHelp.setPrefWidth(300);
		zButtonHelp.setPrefHeight(30);

		zButtonGetQualityProbability.setLayoutX(0);
		zButtonGetQualityProbability.setLayoutY(30);
		zButtonGetQualityProbability.setPrefWidth(300);
		zButtonGetQualityProbability.setPrefHeight(30);

		zButtonGroupThrowProbability.setLayoutX(0);
		zButtonGroupThrowProbability.setLayoutY(60);
		zButtonGroupThrowProbability.setPrefWidth(300);
		zButtonGroupThrowProbability.setPrefHeight(30);

		zButtonThrowProbability.setLayoutX(0);
		zButtonThrowProbability.setLayoutY(0);
		zButtonThrowProbability.setPrefWidth(300);
		zButtonThrowProbability.setPrefHeight(30);

		zButtonSetN.setLayoutX(0);
		zButtonSetN.setLayoutY(90);
		zButtonSetN.setPrefWidth(300);
		zButtonSetN.setPrefHeight(30);

		// do rest
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Heyja");
		alert.setHeaderText(null);
		alert.setContentText(
				"Ich hoffe das Programm gefällt dir. Falls du irgendwelche Rückfragen oder Verbesserungsvorschläge hast, kannst du dich gerne bei mir melden."
						+ "\nxaverstiensmeier@gmx.de\n\nVersion 1.00\n©Xaver Stiensmeier");

		alert.showAndWait();
		
		
		Group layout = new Group();
		layout.getChildren().addAll(this.zButtonHelp, this.zButtonGetQualityProbability,
				this.zButtonGroupThrowProbability, this.zButtonThrowProbability, this.zButtonSetN);

		Scene scene = new Scene(layout, 300, 150);
		primaryStage.setScene(scene);
		primaryStage.show();
		

	}

	public void showText(String pText) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(null);
		alert.setHeaderText(null);
		alert.setContentText(pText);
		alert.showAndWait();
	}

	public int getValue(String pText) {
		TextInputDialog dialog = new TextInputDialog("");
		dialog.setContentText(pText);
		dialog.setHeaderText(null);
		dialog.setTitle(null);
		dialog.initStyle(StageStyle.UTILITY);

		Optional<String> result = dialog.showAndWait();
		if (result.isPresent() && !result.get().equals("")) {
			return Integer.parseInt(result.get());
		}
		return 0;
	}
}
