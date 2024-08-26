package com.example.pokemon;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;

import java.io.IOException;

public class HelloController {
    @FXML
    private void onHelloButtonClick(ActionEvent event) throws IOException {
        // 加载 tool.fxml 文件
        FXMLLoader loader = new FXMLLoader(getClass().getResource("tool.fxml"));
        Parent root = loader.load();

        // 获取当前窗口的 stage
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // 设置新场景
        stage.setScene(new Scene(root));
        stage.setTitle("Tool Page");
        stage.show();
    }
}
