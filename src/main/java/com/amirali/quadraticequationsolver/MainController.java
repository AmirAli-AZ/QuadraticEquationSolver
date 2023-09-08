package com.amirali.quadraticequationsolver;

import javafx.beans.binding.Bindings;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private TextField textFieldA, textFieldB, textFieldC;

    @FXML
    private Button calculateButton;

    @FXML
    private Text textA, textB, textC;

    private final PseudoClass error = PseudoClass.getPseudoClass("error");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textFieldA.pseudoClassStateChanged(error, true);
        textFieldB.pseudoClassStateChanged(error, true);
        textFieldC.pseudoClassStateChanged(error, true);

        textFieldA.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!isNumber(newValue)) {
                textFieldA.pseudoClassStateChanged(error, true);
                textA.setText("a");
                return;
            }
            textFieldA.pseudoClassStateChanged(error, false);
            textA.setText(newValue);
        });
        textFieldB.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!isNumber(newValue)) {
                textFieldB.pseudoClassStateChanged(error, true);
                textB.setText("+b");
                return;
            }
            textFieldB.pseudoClassStateChanged(error, false);
            textB.setText(Double.parseDouble(newValue) > 0 ? "+" + newValue : newValue);
        });
        textFieldC.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!isNumber(newValue)) {
                textFieldC.pseudoClassStateChanged(error, true);
                textC.setText("+c");
                return;
            }
            textFieldC.pseudoClassStateChanged(error, false);
            textC.setText(Double.parseDouble(newValue) > 0 ? "+" + newValue : newValue);
        });

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
