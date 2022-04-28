package com.aetherwars;


import com.aetherwars.model.Board;
import com.aetherwars.model.Card;
import com.aetherwars.spells.Spell;
import com.aetherwars.model.Character;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
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
    List<Card> threeCards;
    private Board board;
    private boolean isPlanning;
    private Card planHandCard;

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
    @FXML
    private Label deckCount;
    @FXML
    private Label playerManaCount;
    @FXML
    private Label manaCount;
    @FXML
    private Rectangle p1Frame;
    @FXML
    private Rectangle p2Frame;
    @FXML
    private VBox hand1;
    @FXML
    private VBox hand2;
    @FXML
    private VBox hand3;
    @FXML
    private VBox hand4;
    @FXML
    private VBox hand5;
    @FXML
    private VBox windowBox;
    @FXML
    private HBox threeCardsView;
    @FXML
    private ImageView hoverCardImage;
    @FXML
    private Label hoverCardName;
    @FXML
    private Label hoverCardDescription;
    @FXML
    private Label hoverCardAtk;
    @FXML
    private Label hoverCardHp;
    @FXML
    private Label hoverCardLvl;
    @FXML
    private Label hoverCardExp;
    @FXML
    private Label hoverCardType;

    public InGameController() {
        Platform.runLater(() -> {
            board = new Board(characters, spells);
            renderBoard();
            initRender();
            renderThreeCards();
        } );
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }

    public void setSpells(List<Spell> spells) {
        this.spells = spells;
    }

    public void switchToMain(ActionEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/aetherwars/MainMenu.fxml"));
        Parent mainMenu = fxmlLoader.load();
        MainMenuController controller = fxmlLoader.getController();
        controller.setCharacters(characters);
        controller.setSpells(spells);
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(mainMenu);
        stage.setScene(scene);
        stage.show();
    }

    public void initRender() {
        hoverCardName.setText(" ");
        hoverCardDescription.setText(" ");
        hoverCardAtk.setText(" ");
        hoverCardHp.setText(" ");
        hoverCardLvl.setText(" ");
        hoverCardExp.setText(" ");
        hoverCardType.setText(" ");
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
            if (board.getCurrentPlayerHand().size() < 5) {
                renderThreeCards();
            }
        }
        renderBoard();
    }

    public void renderBoard() {
        labelTurn.setText(Integer.toString(board.getTurnCounter()));
        deckCount.setText(Integer.toString(board.getCurrentPlayerDeckCount()));
        playerManaCount.setText(Integer.toString(board.getCurrentPlayerMana()));
        manaCount.setText(Integer.toString(board.getManaCounter()));
        if (board.getWhoseTurn().equals("P1")) {
            p2Frame.setStroke(Paint.valueOf("#000000"));
            p2Frame.setStrokeWidth(1);
            p1Frame.setStroke(Paint.valueOf("#71e356"));
            p1Frame.setStrokeWidth(5);
        } else if (board.getWhoseTurn().equals("P2")) {
            p1Frame.setStroke(Paint.valueOf("#000000"));
            p1Frame.setStrokeWidth(1);
            p2Frame.setStroke(Paint.valueOf("#71e356"));
            p2Frame.setStrokeWidth(5);
        }
        renderCardsInHand();
    }

    public void renderCardsInHand() {
        hand1.getChildren().clear();
        hand2.getChildren().clear();
        hand3.getChildren().clear();
        hand4.getChildren().clear();
        hand5.getChildren().clear();

        for (int i = 0; i < board.getCurrentPlayerHand().size(); i++) {
            ImageView img = new ImageView(String.valueOf(getClass().getResource("card/image/error-icon.png")));
            try {
                img = new ImageView(String.valueOf(getClass().getResource(board.getCurrentPlayerHand().get(i).getImagePath())));
            } catch (Exception e) {
                System.out.println("Error loading image");
            }
            img.setFitHeight(82);
            img.setFitWidth(82);
            Label manaCost = new Label("MANA " + Integer.toString(board.getCurrentPlayerHand().get(i).getManaCost()));
            manaCost.setFont(new Font("System", 13));
            manaCost.setStyle("-fx-font-weight: bold");
            manaCost.setTextAlignment(TextAlignment.CENTER);
            manaCost.setPadding(new Insets(20, 0, 20, 0));
            Label spec = new Label(board.getCurrentPlayerHand().get(i).toSpecString());
            spec.setFont(new Font("System", 13));
            spec.setStyle("-fx-font-weight: bold");
            spec.setTextAlignment(TextAlignment.CENTER);
            if (i == 0) {
                hand1.getChildren().add(img);
                hand1.getChildren().add(manaCost);
                hand1.getChildren().add(spec);
                hand1.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            } else if (i == 1) {
                hand2.getChildren().add(img);
                hand2.getChildren().add(manaCost);
                hand2.getChildren().add(spec);
                hand2.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            } else if (i == 2) {
                hand3.getChildren().add(img);
                hand3.getChildren().add(manaCost);
                hand3.getChildren().add(spec);
                hand3.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            } else if (i == 3) {
                hand4.getChildren().add(img);
                hand4.getChildren().add(manaCost);
                hand4.getChildren().add(spec);
                hand4.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            } else if (i == 4) {
                hand5.getChildren().add(img);
                hand5.getChildren().add(manaCost);
                hand5.getChildren().add(spec);
                hand5.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            }
        }
    }

    public void renderThreeCards() {
        threeCardsView.setVisible(true);
        windowBox.setOpacity(0.2);
        threeCards = board.getCurrentPlayerTopDeck();
        for (int i = 0; i < 3; i++) {
            ImageView img = new ImageView(String.valueOf(getClass().getResource("card/image/error-icon.png")));
            try {
                img = new ImageView(String.valueOf(getClass().getResource(threeCards.get(i).getImagePath())));
            } catch (Exception e) {
                System.out.println("Error loading image");
            }
            img.setFitHeight(180);
            img.setFitWidth(180);
            Label manaCost = new Label("MANA " + Integer.toString(threeCards.get(i).getManaCost()));
            manaCost.setFont(new Font("System", 24));
            manaCost.setStyle("-fx-font-weight: bold");
            manaCost.setTextAlignment(TextAlignment.CENTER);
            manaCost.setPadding(new Insets(20, 0, 20, 0));
            Label spec = new Label(threeCards.get(i).toSpecString());
            spec.setFont(new Font("System", 24));
            spec.setStyle("-fx-font-weight: bold");
            spec.setTextAlignment(TextAlignment.CENTER);

            VBox choice = (VBox) threeCardsView.getChildren().get(i);
            choice.getChildren().add(img);
            choice.getChildren().add(manaCost);
            choice.getChildren().add(spec);
            choice.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
            choice.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        }
    }

    public void chooseThreeCards(Event e) {
        VBox choice = (VBox) e.getSource();
        int index = threeCardsView.getChildren().indexOf(choice);
        board.addToCurrentPlayerHand(threeCards.remove(index));
        board.returnToCurrentPlayerDeck(threeCards);
        for (Node child : threeCardsView.getChildren()) {
            ((VBox) child).getChildren().clear();
        }
        threeCardsView.setVisible(false);
        windowBox.setOpacity(1);
        renderBoard();
    }

    private int getHandCardIndex(VBox handCard) {
        int index = 0;
        if (handCard.equals(hand1)) {
            index = 0;
        } else if (handCard.equals(hand2)) {
            index = 1;
        } else if (handCard.equals(hand3)) {
            index = 2;
        } else if (handCard.equals(hand4)) {
            index = 3;
        } else if (handCard.equals(hand5)) {
            index = 4;
        }

        return index;
    }

    public void renderHoveredCardHand(Event e) {
        VBox handCardBox = (VBox) e.getSource();
        handCardBox.setOpacity(0.6);
        int index = getHandCardIndex(handCardBox);
        try {
            planHandCard = board.getCurrentPlayerHand().get(index);
        } catch (Exception e1) {
            return;
        }
        hoverCardImage.setImage(new Image(String.valueOf(getClass().getResource(planHandCard.getImagePath()))));
        hoverCardName.setText(planHandCard.getName());
        hoverCardDescription.setText(planHandCard.getDescription());
        if (planHandCard.getCardType().equals("Character")) {
            hoverCardAtk.setText("ATK: " + ((Character) planHandCard).getAttack());
            hoverCardHp.setText("HP: " + ((Character) planHandCard).getHealth());
            hoverCardLvl.setText(" ");
            hoverCardExp.setText(" ");
            hoverCardType.setText("Type: " + ((Character)planHandCard).getType());
        } else {
            hoverCardAtk.setText((planHandCard.toSpecString()));
            hoverCardHp.setText(" ");
            hoverCardLvl.setText(" ");
            hoverCardExp.setText(" ");
            hoverCardType.setText("Type: " + ((Spell)planHandCard).getType());
        }
    }

    public void unrenderHoveredCard(Event e) {
        VBox handCardBox = (VBox) e.getSource();
        handCardBox.setOpacity(1);;
        hoverCardImage.setImage(null);
        hoverCardName.setText("");
        hoverCardDescription.setText("");
        hoverCardAtk.setText("");
        hoverCardHp.setText("");
        hoverCardLvl.setText("");
        hoverCardExp.setText("");
        hoverCardType.setText("");
    }

    public void handlePlanning(Event e) {
        if (board.getPhase().equals("PLAN")) {
            VBox handCardBox = (VBox) e.getSource();
            int index = getHandCardIndex(handCardBox);
            try {
                planHandCard = board.getCurrentPlayerHand().get(index);
            } catch (Exception e1) {
                return;
            }

        }
    }
}
