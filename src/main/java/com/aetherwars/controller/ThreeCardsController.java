package com.aetherwars.controller;

import com.aetherwars.model.Board;
import com.aetherwars.model.Card;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.util.List;

public class ThreeCardsController {
    @FXML
    private HBox threeCards;

    private List<Card> threeCardsList;
    private Board board;
    public void render(Board board) {
        this.board = board;
        threeCards.setVisible(true);
        this.threeCardsList = board.getCurrentPlayerTopDeck();
        System.out.print("lesgo\n");
        for (int i = 0; i < 3; i++) {
            ImageView img = new ImageView(String.valueOf(getClass().getResource("/com/aetherwars/card/image/error-icon.png")));
            try {
                img = new ImageView(String.valueOf(getClass().getResource("/com/aetherwars/" + threeCardsList.get(i).getImagePath())));
            } catch (Exception e) {
                System.out.println("Error loading image");
            }
            System.out.print("lesgo" + i + "\n");
            img.setFitHeight(180);
            img.setFitWidth(180);
            Label manaCost = new Label("MANA " + threeCardsList.get(i).getManaCost());
            manaCost.setFont(new Font("System", 24));
            manaCost.setStyle("-fx-font-weight: bold");
            manaCost.setTextAlignment(TextAlignment.CENTER);
            manaCost.setPadding(new Insets(20, 0, 20, 0));
            Label spec = new Label(threeCardsList.get(i).toSpecString());
            spec.setFont(new Font("System", 24));
            spec.setStyle("-fx-font-weight: bold");
            spec.setTextAlignment(TextAlignment.CENTER);
            System.out.print("lesgo" + i + "2\n");
            VBox choice = (VBox) threeCards.getChildren().get(i);
            System.out.print(choice);
            choice.getChildren().add(img);
            choice.getChildren().add(manaCost);
            choice.getChildren().add(spec);
            choice.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
            choice.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            System.out.print("lesgo" + i + "3\n");
        }
    }

    public void chooseThreeCards(MouseEvent mouseEvent) {
        VBox choice = (VBox) mouseEvent.getSource();
        int index = threeCards.getChildren().indexOf(choice);
        board.addToCurrentPlayerHand(threeCardsList.remove(index));
        board.returnToCurrentPlayerDeck(threeCardsList);
        for (Node child : threeCards.getChildren()) {
            ((VBox) child).getChildren().clear();
        }
        threeCards.setVisible(false);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/aetherwars/fxml/InGame.fxml"));
        InGameController inGameController = loader.getController();

        inGameController.setBoard(board);
        inGameController.renderBoard();
    }
}
