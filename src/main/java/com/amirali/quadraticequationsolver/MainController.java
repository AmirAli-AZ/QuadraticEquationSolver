package com.amirali.quadraticequationsolver;

import javafx.beans.binding.Bindings;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private TextField textFieldA;

    @FXML
    private TextField textFieldB;

    @FXML
    private TextField textFieldC;

    @FXML
    private Button calculateButton;

    @FXML
    private Label equation;

    private final PseudoClass error = PseudoClass.getPseudoClass("error");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textFieldA.pseudoClassStateChanged(error, true);
        textFieldB.pseudoClassStateChanged(error, true);
        textFieldC.pseudoClassStateChanged(error, true);

        textFieldA.textProperty().addListener((observable, oldValue, newValue) -> textFieldA.pseudoClassStateChanged(error, !isNumber(newValue)));
        textFieldB.textProperty().addListener((observable, oldValue, newValue) -> textFieldB.pseudoClassStateChanged(error, !isNumber(newValue)));
        textFieldC.textProperty().addListener((observable, oldValue, newValue) -> textFieldC.pseudoClassStateChanged(error, !isNumber(newValue)));

        calculateButton.disableProperty().bind(Bindings.createBooleanBinding(() -> {
            return (!isNumber(textFieldA.getText()) || !isNumber(textFieldB.getText()) || !isNumber(textFieldC.getText()));
        }, textFieldA.textProperty(), textFieldB.textProperty(), textFieldC.textProperty()));
    }

    public void calculate(ActionEvent actionEvent) {
        final var a = Double.parseDouble(textFieldA.getText());
        final var b = Double.parseDouble(textFieldB.getText());
        final var c = Double.parseDouble(textFieldC.getText());

        final var delta = Math.pow(b, 2) - (4 * a * c);

        if (delta < 0) {
            new Alert(Alert.AlertType.ERROR, "This equation is not solvable!").show();
            return;
        }

        equation.setText(a + "x^2+" + b + "x+" + c + "=0");

        if (delta == 0) {
            final var result = -b/(2 * a);
            new Alert(Alert.AlertType.INFORMATION, "x is " + result).show();
            return;
        }

        final var result1 = (-b + Math.sqrt(delta)) / (2 * a);
        final var result2 = (-b - Math.sqrt(delta)) / (2 * a);

        new Alert(
                Alert.AlertType.INFORMATION,
                "x1 is " + result1 + "\n" +
                        "x2 is " + result2
        ).show();
    }

    public boolean isNumber(String string) {
        if (string == null || string.isEmpty())
            return false;

        try {
            Double.parseDouble(string);
            return true;
        }catch (NumberFormatException e) {
            return false;
        }
    }
}
