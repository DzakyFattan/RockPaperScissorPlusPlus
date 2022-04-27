package com.aetherwars;


import com.aetherwars.model.Board;
import com.aetherwars.spells.Spell;
import com.aetherwars.model.Character;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.List;
import java.util.Objects;

public class InGameController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private List<Character> characters;
    private List<Spell> spells;
    private Board board;

    @FXML
    private Label labelTurn;
    @FXML
    private Rectangle drawPhaseIndicator;
    @FXML
    private Rectangle planPhaseIndicator;
    @FXML
    private Rectangle attackPhaseIndicator;
    @FXML
    private Rectangle endPhaseIndicator;

    public InGameController() {
        Platform.runLater(() -> {
            board = new Board(characters, spells);
            labelTurn.setText(Integer.toString(board.getTurnCounter()));
        } );
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }

    public void setSpells(List<Spell> spells) {
        this.spells = spells;
    }

    public void switchToMain(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/aetherwars/MainMenu.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void nextPhaseButtonClick(ActionEvent e) {
        if (board.getPhase().equals("DRAW")) {
            board.nextPhase();
            drawPhaseIndicator.setFill(Paint.valueOf("#eaeaea"));
            planPhaseIndicator.setFill(Paint.valueOf("#ffa21f"));
        } else if (board.getPhase().equals("PLAN")) {
            board.nextPhase();
            planPhaseIndicator.setFill(Paint.valueOf("#eaeaea"));
            attackPhaseIndicator.setFill(Paint.valueOf("#ffa21f"));
        } else if (board.getPhase().equals("ATTACK")) {
            board.nextPhase();
            attackPhaseIndicator.setFill(Paint.valueOf("#eaeaea"));
            endPhaseIndicator.setFill(Paint.valueOf("#ffa21f"));
        } else if (board.getPhase().equals("END")) {
            board.nextPhase();
            endPhaseIndicator.setFill(Paint.valueOf("#eaeaea"));
            drawPhaseIndicator.setFill(Paint.valueOf("#ffa21f"));
            labelTurn.setText(Integer.toString(board.getTurnCounter()));
        }
    }


}
