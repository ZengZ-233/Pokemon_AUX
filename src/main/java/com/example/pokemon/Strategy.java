package com.example.pokemon;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

public class Strategy {

    @FXML
    private void goBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("tool.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

//    private void switchScene(ActionEvent event, String fxmlFile) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
//        Parent root = loader.load();
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        stage.setScene(new Scene(root));
//        stage.show();
//    }
    private void switchScene(ActionEvent event, String fxmlFile) throws IOException {
        URL resource = getClass().getResource("/com/example/pokemon/place/" + fxmlFile);
        if (resource == null) {
            throw new FileNotFoundException("FXML file not found: " + fxmlFile);
        }
        FXMLLoader loader = new FXMLLoader(resource);
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void shenao(ActionEvent event) throws IOException {
        switchScene(event, "shenao.fxml");
    }

    @FXML
    private void hezhong(ActionEvent event) throws IOException {
        switchScene(event, "hezhong.fxml");
    }

    @FXML
    private void guandu(ActionEvent event) throws IOException {
        switchScene(event, "guandu.fxml");
    }

    @FXML
    private void fengyuan(ActionEvent event) throws IOException {
        switchScene(event, "fengyuan.fxml");
    }
}
