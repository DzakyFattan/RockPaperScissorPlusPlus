package com.aetherwars;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToInGame(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/aetherwars/InGame.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToMain(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/aetherwars/MainMenu.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void StartGame(ActionEvent e) {
        System.out.println("StartGame!!");
    }

    public void ExitGame(ActionEvent e) {
        System.out.println("Exited");
    }
}
