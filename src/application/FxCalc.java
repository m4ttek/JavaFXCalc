package application;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Calculation;
import model.CalculationError;
import model.CalculatorBean;
import model.ChangeSingCalculation;
import model.ClearCalculation;
import model.DefaultCalculation;
import model.EnterCalculation;
import model.PercentageCalulcation;
import model.SimpleMathematicalCalculation;
import model.SquareRootCalculation;

public class FxCalc extends Application {
	
	/*
	 * Propertiesy okna.
	 */
	private static final String WINDOW_TITLE = "FXcalc by Mateusz Kamiński";
	private static final int MIN_SIZE = 200;
	private static final int MAX_SIZE = 600;
	
	/**
	 * Dostępne operacje kalkulatora
	 */
	private static final List<Calculation> CALCULATION_TYPES = Arrays.asList(new ClearCalculation(),
			new PercentageCalulcation(), new EnterCalculation(), new DefaultCalculation(),
			new ChangeSingCalculation(), new SquareRootCalculation(),  new SimpleMathematicalCalculation());
	
	/**
	 * Rozmiar czcionki.
	 */
	private final DoubleProperty fontSize = new SimpleDoubleProperty(15);
	private final DoubleProperty resultFontSize = new SimpleDoubleProperty(15);
	
	/**
	 * Stan kalkulatora.
	 */
	private final CalculatorBean calculatorBean = new CalculatorBean();
	
	@FXML
	private TextField display;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("fxcalc2.fxml"));
			Scene scene = new Scene(root, 400, 400);
			scene.getStylesheets().add(getClass().getResource("fxscene.css").toExternalForm());
			primaryStage.setScene(scene);
			setStageProperties(primaryStage);
			bindFontSizeChange(root, scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void click(Event event) {
		ObservableList<Node> children = ((Button) event.getSource()).getParent().getChildrenUnmodifiable();
		String mnemonic = ((Button) event.getSource()).getText();
		
		try {
			for (Calculation calculation : CALCULATION_TYPES) {
				// szukamy dopóki nie znajdziemy odpowiedniego typu operacji
				String toShow = calculation.calculate(calculatorBean, mnemonic);
				if (toShow != null) {
					display.setText(toShow);
					break;
				}
			}
		} catch (CalculationError e) {
			calculatorBean.setBlocked(true);
		}	
		if (calculatorBean.isBlocked()) {
			display.setText("ERR");
			for (Node child : children) {
				if (child.getStyleClass().contains("calc_button") && !Objects.equals(child.getId(), "c_button")) {
					child.setDisable(true);
				}
			}
		} else {
			for (Node child : children) {
				child.setDisable(false);
			}
		}
	}

	private void setStageProperties(Stage stage) {
		stage.setMinHeight(MIN_SIZE);
		stage.setMinWidth(MIN_SIZE);
		stage.setMaxHeight(MAX_SIZE);
		stage.setMaxWidth(MAX_SIZE);
		stage.setFullScreen(false);
		stage.setTitle(WINDOW_TITLE);
	}

	private void bindFontSizeChange(Parent root, Scene scene) {
		fontSize.bind(scene.widthProperty().add(scene.heightProperty()).divide(50));
		resultFontSize.bind(scene.widthProperty().add(scene.heightProperty()).divide(40));
		for (Node child : root.getChildrenUnmodifiable()) {
			if (child instanceof Button) {
				child.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString(), ";"));
			} else {
				child.styleProperty().bind(Bindings.concat("-fx-font-size: ", resultFontSize.asString(), ";"));
			}
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
