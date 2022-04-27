package com.aetherwars;

import com.aetherwars.spells.Spell;
import com.aetherwars.model.Character;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class MainMenuController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    // For passing to InGameController
    private List<Character> characters;
    private List<Spell> spells;

    @FXML
    private AnchorPane mainMenuPane;
    Stage fxmlstage;

    public void startGame(ActionEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/aetherwars/InGame.fxml"));
        Parent root = fxmlLoader.load();
        InGameController controller = fxmlLoader.getController();
        controller.setCharacters(characters);
        controller.setSpells(spells);
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

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }

    public void setSpells(List<Spell> spells) {
        this.spells = spells;
    }
}
