package com.aetherwars;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private AnchorPane mainMenuPane;
    Stage fxmlstage;

    public void startGame(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/aetherwars/InGame.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void exitGame(ActionEvent e) {
        fxmlstage = (Stage) mainMenuPane.getScene().getWindow();
        System.out.println("Exited");
        fxmlstage.close();
    }
}
