package com.example.pokemon.place;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

public class Shenao {
    @FXML
    private void goBack(ActionEvent event) throws IOException {
        URL fxmlUrl = getClass().getResource("/com/example/pokemon/strategy.fxml");
        if (fxmlUrl == null) {
            throw new FileNotFoundException("FXML file not found: /com/example/pokemon/strategy.fxml");
        }
        FXMLLoader loader = new FXMLLoader(fxmlUrl);
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

}
