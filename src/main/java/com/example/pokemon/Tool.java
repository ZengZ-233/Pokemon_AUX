package com.example.pokemon;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;

import java.io.IOException;

public class Tool {

    private void switchScene(ActionEvent event, String fxmlFile) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void openRestrain(ActionEvent event) throws IOException {
        switchScene(event, "restrain.fxml");
    }

    @FXML
    private void openPokedex(ActionEvent event) throws IOException {
        switchScene(event, "pokedex.fxml");
    }

    @FXML
    private void openMaps(ActionEvent event) throws IOException {
        switchScene(event, "maps.fxml");
    }

    @FXML
    private void openStrategy(ActionEvent event) throws IOException {
        switchScene(event, "strategy.fxml");
    }
}
