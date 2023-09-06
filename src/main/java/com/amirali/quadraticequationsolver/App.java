package com.amirali.quadraticequationsolver;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("QuadraticEquationSolver");
        final var loader = new FXMLLoader(getClass().getResource("main.fxml"));
        final var scene = new Scene(loader.load(), 600, 400);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
