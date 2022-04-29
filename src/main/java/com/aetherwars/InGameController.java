package com.aetherwars;


import com.aetherwars.battle.Battle;
import com.aetherwars.model.Board;
import com.aetherwars.model.Card;
import com.aetherwars.slot.CardOnField;
import com.aetherwars.spells.*;
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
import javafx.scene.control.Button;
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
import java.util.Map;
import java.util.Objects;

public class InGameController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private List<Character> characters;
    private List<Spell> spells;
    private List<Card> threeCards;
    private Board board;
    private boolean isPlanning;
    private boolean isAttacking;
    private Card planHandCard;
    private Card planFieldCard;
    private int planHandCardIndex;
    private int planFieldCardIndex;
    private CardOnField attackingCard;
    private CardOnField defendingCard;
    private int attackerIndex;
    private int defenderIndex;

    @FXML private Label labelTurn;
    @FXML private Label labelMiddle;
    @FXML private Rectangle drawPhaseIndicator;
    @FXML private Rectangle planPhaseIndicator;
    @FXML private Rectangle attackPhaseIndicator;
    @FXML private Rectangle endPhaseIndicator;
    @FXML private Label deckCount;
    @FXML private Label playerManaCount;
    @FXML private Label manaCount;
    @FXML private Rectangle p1Frame;
    @FXML private Rectangle p2Frame;
    @FXML private VBox hand1;
    @FXML private VBox hand2;
    @FXML private VBox hand3;
    @FXML private VBox hand4;
    @FXML private VBox hand5;
    @FXML private VBox windowBox;
    @FXML private HBox threeCardsView;
    @FXML private ImageView hoverCardImage;
    @FXML private Label hoverCardName;
    @FXML private Label hoverCardDescription;
    @FXML private Label hoverCardAtk;
    @FXML private Label hoverCardHp;
    @FXML private Label hoverCardLvl;
    @FXML private Label hoverCardExp;
    @FXML private Label hoverCardType;
    @FXML private Pane p1FieldPane;
    @FXML private Pane p2FieldPane;
    @FXML private Button addExpButton;
    @FXML private Button deleteButton;
    @FXML private Button homeButton;
    @FXML private Rectangle rectRightHealth;
    @FXML private Rectangle rectLeftHealth;
    @FXML private Label p1Health;
    @FXML private Label p2Health;
    @FXML private Label winLabel;

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
        addExpButton.setVisible(false);
        deleteButton.setVisible(false);
        hand1.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        hand2.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        hand3.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        hand4.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        hand5.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
    }

    public void nextPhaseButtonClick(ActionEvent e) {
        if (board.getPhase().equals("DRAW")) {
            board.nextPhase();
            drawPhaseIndicator.setFill(Paint.valueOf("#eaeaea"));
            planPhaseIndicator.setFill(Paint.valueOf("#ffa21f"));
            addExpButton.setVisible(true);
            deleteButton.setVisible(true);
        } else if (board.getPhase().equals("PLAN")) {
            board.nextPhase();
            planPhaseIndicator.setFill(Paint.valueOf("#eaeaea"));
            attackPhaseIndicator.setFill(Paint.valueOf("#ffa21f"));
            resetHandBackgrounds();
            resetFieldBackgrounds();
            addExpButton.setVisible(false);
            deleteButton.setVisible(false);
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
        double healthP1 = board.getP1().getHealth() / 80.0;
        double healthP2 = board.getP2().getHealth() / 80.0;
        p1Health.setText(Integer.toString(board.getP1().getHealth()));
        p2Health.setText(Integer.toString(board.getP2().getHealth()));
        rectLeftHealth.scaleXProperty().set(healthP1);
        rectLeftHealth.translateXProperty().set(-250.0 + (healthP1 * 500.0 * 0.5));
        rectRightHealth.scaleXProperty().set(healthP2);
        rectRightHealth.translateXProperty().set(250.0 - (healthP2 * 500.0 * 0.5));
        deckCount.setText(Integer.toString(board.getCurrentPlayerDeckCount()));
        playerManaCount.setText(Integer.toString(board.getCurrentPlayerMana()));
        manaCount.setText(Integer.toString(board.getManaCounter()));
        if (board.getWhoseTurn().equals("P1")) {
            labelMiddle.setText("<- Steve's Turn   ");
            p2Frame.setStroke(Paint.valueOf("#000000"));
            p2Frame.setStrokeWidth(1);
            p1Frame.setStroke(Paint.valueOf("#71e356"));
            p1Frame.setStrokeWidth(5);
        } else if (board.getWhoseTurn().equals("P2")) {
            labelMiddle.setText("    Alex's Turn ->");
            p1Frame.setStroke(Paint.valueOf("#000000"));
            p1Frame.setStrokeWidth(1);
            p2Frame.setStroke(Paint.valueOf("#71e356"));
            p2Frame.setStrokeWidth(5);
        }
        renderCardsInHand();
        renderField();
        if (board.checkWinner().equals("P1")) {
            winLabel.setVisible(true);
            winLabel.setText("Steve Wins!");
            homeButton.setVisible(true);
            windowBox.setOpacity(0.1);
            windowBox.setDisable(true);
        } else if (board.checkWinner().equals("P2")) {
            winLabel.setVisible(true);
            winLabel.setText("Alex Wins!");
            homeButton.setVisible(true);
            windowBox.setOpacity(0.1);
            windowBox.setDisable(true);
        }
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
                System.out.println("Error loading image: " + board.getCurrentPlayerHand().get(i).getImagePath());
            }
            img.setFitHeight(82);
            img.setFitWidth(82);
            Label manaCost = null;
            if (board.getCurrentPlayerHand().get(i) instanceof LevelSpell) {
                manaCost = new Label("MANA=LVL/2");
            } else {
                manaCost = new Label("MANA " + Integer.toString(board.getCurrentPlayerHand().get(i).getManaCost()));
            }
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
            } else if (i == 1) {
                hand2.getChildren().add(img);
                hand2.getChildren().add(manaCost);
                hand2.getChildren().add(spec);
            } else if (i == 2) {
                hand3.getChildren().add(img);
                hand3.getChildren().add(manaCost);
                hand3.getChildren().add(spec);
            } else if (i == 3) {
                hand4.getChildren().add(img);
                hand4.getChildren().add(manaCost);
                hand4.getChildren().add(spec);
            } else if (i == 4) {
                hand5.getChildren().add(img);
                hand5.getChildren().add(manaCost);
                hand5.getChildren().add(spec);
            }
        }
    }

    public void clearField(Pane fieldPane) {
        for (Node child : fieldPane.getChildren()) {
            if (child.getClass().equals(Pane.class)) {
                Pane pane = (Pane) child;
                ((ImageView)pane.getChildren().get(2)).setImage(null);
                ((Label)pane.getChildren().get(1)).setText("");
                ((Label)pane.getChildren().get(3)).setText("");
                ((Label)pane.getChildren().get(4)).setText("");
            }
        }
    }

    public void renderCardOnField (Pane pane, CardOnField card) {;
        ((ImageView)pane.getChildren().get(2)).setImage(new Image(String.valueOf(getClass().getResource("card/image/error-icon.png"))));
        try {
            ((ImageView)pane.getChildren().get(2)).setImage(new Image(String.valueOf(getClass().getResource(card.getImagePath()))));
        } catch (Exception e) {
            System.out.println("Error loading image");
        }
        ((Label)pane.getChildren().get(1)).setText("HP:" + card.getHealth());
        ((Label)pane.getChildren().get(3)).setText("ATK:" + card.getAttack());
        ((Label)pane.getChildren().get(4)).setText(card.getExp() + "/" + card.getCurrentExpReq() + "[" + card.getLevel() + "]");
        if (!card.getStatus()) {
            ((Rectangle)pane.getChildren().get(0)).setFill(Color.MAROON);
        } else if (pane.getParent().getId().equals(p1FieldPane.getId())) {
            ((Rectangle) pane.getChildren().get(0)).setFill(Paint.valueOf("#7dc0ff"));
        } else if (pane.getParent().getId().equals(p2FieldPane.getId())) {
            ((Rectangle) pane.getChildren().get(0)).setFill(Paint.valueOf("#ff8383"));
        }
    }

    public void renderField() {
        clearField(p1FieldPane);
        clearField(p2FieldPane);
        for (Map.Entry<Integer, CardOnField> entry : board.getPlayerField("P1").entrySet()) {
            renderCardOnField((Pane) p1FieldPane.getChildren().get(entry.getKey() + 2), entry.getValue());
        }

        for (Map.Entry<Integer, CardOnField> entry : board.getPlayerField("P2").entrySet()) {
            renderCardOnField((Pane) p2FieldPane.getChildren().get(entry.getKey() + 2), entry.getValue());
        }
    }

    public void renderThreeCards() {
        threeCardsView.setVisible(true);
        windowBox.setOpacity(0.2);
        threeCards = board.getCurrentPlayerTopDeck();
        for (int i = 0; i < threeCards.size(); i++) {
            ImageView img = new ImageView(String.valueOf(getClass().getResource("card/image/error-icon.png")));
            try {
                img = new ImageView(String.valueOf(getClass().getResource(threeCards.get(i).getImagePath())));
            } catch (Exception e) {
                System.out.println("Error loading image: " + threeCards.get(i).getImagePath());
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

    private int getFieldCardIndex(Pane fieldCard) {
        return board.getWhoseTurn().equals("P1") ? p1FieldPane.getChildren().indexOf(fieldCard) : p2FieldPane.getChildren().indexOf(fieldCard);
    }

    private int getOpponentFieldCardIndex(Pane fieldCard) {
        return board.getWhoseTurn().equals("P2") ? p1FieldPane.getChildren().indexOf(fieldCard) : p2FieldPane.getChildren().indexOf(fieldCard);
    }

    public void renderHoveredCardHand(Event e) {
        VBox handCardBox = (VBox) e.getSource();
        handCardBox.setOpacity(0.6);
        int index = getHandCardIndex(handCardBox);
        Card hoverHandCard;
        try {
            hoverHandCard = board.getCurrentPlayerHand().get(index);
        } catch (Exception e1) {
            return;
        }
        hoverCardImage.setImage(new Image(String.valueOf(getClass().getResource(hoverHandCard.getImagePath()))));
        hoverCardName.setText(hoverHandCard.getName());
        hoverCardDescription.setText(hoverHandCard.getDescription());
        if (hoverHandCard.getCardType().equals("Character")) {
            hoverCardAtk.setText("ATK: " + ((Character) hoverHandCard).getAttack());
            hoverCardHp.setText("HP: " + ((Character) hoverHandCard).getHealth());
            hoverCardLvl.setText(" ");
            hoverCardExp.setText(" ");
            hoverCardType.setText("Type: " + ((Character) hoverHandCard).getType());
        } else {
            hoverCardAtk.setText((hoverHandCard.toSpecString()));
            hoverCardHp.setText("Duration: " + ((Spell) hoverHandCard).getDuration());
            hoverCardLvl.setText(" ");
            hoverCardExp.setText(" ");
            hoverCardType.setText("Type: " + ((Spell) hoverHandCard).getType());
        }
    }

    public void renderHoveredCardField(Event e) {
        Pane fieldCard = (Pane) e.getSource();
        fieldCard.setOpacity(0.6);
        boolean isP1field = p1FieldPane.getChildren().contains(fieldCard);
        boolean isP2field = p2FieldPane.getChildren().contains(fieldCard);
        int index = isP1field ? p1FieldPane.getChildren().indexOf(fieldCard) : p2FieldPane.getChildren().indexOf(fieldCard);
        CardOnField hoverFieldCard;
        if (board.getPlayerField("P1").containsKey(index - 2) && isP1field) {
            hoverFieldCard = board.getPlayerField("P1").get(index - 2);
        } else if (board.getPlayerField("P2").containsKey(index - 2) && isP2field) {
            hoverFieldCard = board.getPlayerField("P2").get(index - 2);
        } else {
            return;
        }
        hoverCardImage.setImage(new Image(String.valueOf(getClass().getResource(hoverFieldCard.getImagePath()))));
        hoverCardName.setText(hoverFieldCard.getName());
        hoverCardDescription.setText(hoverFieldCard.getDescription());
        hoverCardAtk.setText("ATK: " + hoverFieldCard.getAttack());
        hoverCardHp.setText("HP: " + hoverFieldCard.getHealth());
        hoverCardLvl.setText("LVL: " + hoverFieldCard.getLevel());
        hoverCardExp.setText("EXP: " + hoverFieldCard.getExp());
        hoverCardType.setText("Type: " + hoverFieldCard.getType());
    }

    public void unrenderHoveredCard(Event e) {
        if (e.getSource() instanceof VBox) {
            VBox handCardBox = (VBox) e.getSource();
            handCardBox.setOpacity(1);
        } else if (e.getSource() instanceof Pane) {
            Pane fieldCard = (Pane) e.getSource();
            fieldCard.setOpacity(1);
        }
        hoverCardImage.setImage(null);
        hoverCardName.setText("");
        hoverCardDescription.setText("");
        hoverCardAtk.setText("");
        hoverCardHp.setText("");
        hoverCardLvl.setText("");
        hoverCardExp.setText("");
        hoverCardType.setText("");
    }

    private void resetHandBackgrounds() {
        hand1.setBackground(null);
        hand2.setBackground(null);
        hand3.setBackground(null);
        hand4.setBackground(null);
        hand5.setBackground(null);
        isPlanning = false;
        planHandCard = null;
    }

    private void resetFieldBackgrounds() {
        for (Node node : p1FieldPane.getChildren()) {
            if (node instanceof Pane) {
                Pane pane = (Pane) node;
                if (!((Rectangle)pane.getChildren().get(0)).getFill().equals(Color.MAROON)) {
                    ((Rectangle) pane.getChildren().get(0)).setFill(Paint.valueOf("#7dc0ff"));
                }
            }
        }
        for (Node node : p2FieldPane.getChildren()) {
            if (node instanceof Pane) {
                Pane pane = (Pane) node;
                if (!((Rectangle)pane.getChildren().get(0)).getFill().equals(Color.MAROON)) {
                    ((Rectangle) pane.getChildren().get(0)).setFill(Paint.valueOf("#ff8383"));
                }
            }
        }
        isPlanning = false;
        planFieldCard = null;
    }

    public void onHandCardClicked(Event e) {
        handlePlanning(e);
    }

    public void onFieldCardClicked(Event e) {
        if (board.getPhase().equals("PLAN")) {
            handlePlanning(e);
            placePlannedCardOnField(e);
        } else if (board.getPhase().equals("ATTACK")) {
            handleBattle(e);
        }
    }

    public void handlePlanning(Event e) {
        if (board.getPhase().equals("PLAN")) {
            if (e.getSource() instanceof VBox) {
                resetFieldBackgrounds();
                resetHandBackgrounds();
                VBox handCardBox = (VBox) e.getSource();
                int index = getHandCardIndex(handCardBox);
                try {
                    planFieldCard = null;
                    planHandCard = board.getCurrentPlayerHand().get(index);
                } catch (Exception e1) {
                    return;
                }
                handCardBox.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
                isPlanning = true;
                planHandCardIndex = index;
            } else if (e.getSource() instanceof Pane && !isPlanning) {
                resetFieldBackgrounds();
                resetHandBackgrounds();
                Pane fieldCardPane = (Pane) e.getSource();
                int index = getFieldCardIndex(fieldCardPane) - 2;
                if (board.getCurrentPlayerField().containsKey(index)) {
                    planHandCard = null;
                    planFieldCard = board.getCurrentPlayerField().get(index);
                } else {
                    return;
                }
                ((Rectangle)fieldCardPane.getChildren().get(0)).setFill(Color.LIGHTGREEN);
                isPlanning = true;
                planFieldCardIndex = index;
            }
        }
    }

    public void placePlannedCardOnField(Event e) {
        if (board.getPhase().equals("PLAN") && isPlanning && planHandCard != null) {
            if (planHandCard.getManaCost() > board.getCurrentPlayerMana()) {
                planHandCard = null;
                isPlanning = false;
                resetHandBackgrounds();
                return;
            }
            Node fieldCardBox = (Node) e.getSource();
            Pane fieldPane = (Pane) fieldCardBox.getParent();

            int index = fieldPane.getChildren().indexOf(fieldCardBox) - 2;

            if (planHandCard.getCardType().equals("Character")) {
                if (fieldPane.equals(p1FieldPane) && board.getWhoseTurn().equals("P2")) {
                    isPlanning = false;
                    return;
                } else if (fieldPane.equals(p2FieldPane) && board.getWhoseTurn().equals("P1")) {
                    isPlanning = false;
                    return;
                }
                if (board.getCurrentPlayerField().containsKey(index)) {
                    isPlanning = false;
                    return;
                }
                CardOnField cardOnField = new CardOnField((Character) planHandCard);
                board.addToCurrentPlayerField(index, cardOnField);
                board.reduceCurrentPlayerMana(planHandCard.getManaCost());
                board.removeFromCurrentPlayerHand(planHandCardIndex);
            } else if (planHandCard.getCardType().equals("Spell")) {
                if (board.getCurrentPlayerField().containsKey(index) || board.getCurrentOpponentField().containsKey(index)) {
                    CardOnField cardOnField = board.getCurrentPlayerField().get(index);
                    String playerTarget = "";
                    if (planHandCard instanceof LevelSpell) {
                        System.out.println(LevelSpell.getLvlSpellManaCost(cardOnField.getLevel()));
                        ((LevelSpell) planHandCard).setManaCost(LevelSpell.getLvlSpellManaCost(cardOnField.getLevel()));
                        if (planHandCard.getManaCost() > board.getCurrentPlayerMana()) {
                            planHandCard = null;
                            isPlanning = false;
                            resetHandBackgrounds();
                            return;
                        }
                    }
                    ((LevelSpell) planHandCard).setManaCost(LevelSpell.getLvlSpellManaCost(cardOnField.getLevel()));
                    if (fieldPane.equals(p1FieldPane)) {
                        playerTarget = "P1";
                        cardOnField = board.getPlayerField("P1").get(index);
                    } else if (fieldPane.equals(p2FieldPane)) {
                        playerTarget = "P2";
                        cardOnField = board.getPlayerField("P2").get(index);
                    }
                    if (planHandCard instanceof PotionSpell) {
                        cardOnField.applyPotionSpell((PotionSpell) planHandCard);
                    } else if (planHandCard instanceof LevelSpell) {
                        cardOnField.applyLevelSpell((LevelSpell) planHandCard);
                    } else if (planHandCard instanceof MorphSpell) {
                        board.applyMorphSpell(playerTarget, index, (MorphSpell) planHandCard);
                    } else if (planHandCard instanceof SwapSpell) {
                        cardOnField.applySwapSpell((SwapSpell) planHandCard);
                    }
                    board.reduceCurrentPlayerMana(planHandCard.getManaCost());
                    board.removeFromCurrentPlayerHand(planHandCardIndex);
                    board.checkForDeathsOnField();
                }
            }
            resetHandBackgrounds();
            renderBoard();
            isPlanning = false;
        }
    }

    public void onAddExpButtonClick() {
        if (board.getPhase().equals("PLAN") && isPlanning && planFieldCard != null && board.getCurrentPlayerMana() > 0) {
            ((CardOnField)planFieldCard).addExp(1);
            ((CardOnField)planFieldCard).levelUp();
            board.reduceCurrentPlayerMana(1);
            isPlanning = false;
            resetFieldBackgrounds();
            renderBoard();
        }
    }

    public void onDeleteButtonClick() {
        if (board.getPhase().equals("PLAN") && isPlanning && planFieldCard != null) {
            board.getCurrentPlayerField().remove(planFieldCardIndex);
            isPlanning = false;
            resetFieldBackgrounds();
            renderBoard();
        } else if (board.getPhase().equals("PLAN") && isPlanning && planHandCard != null) {
            board.removeFromCurrentPlayerHand(planHandCardIndex);
            isPlanning = false;
            resetHandBackgrounds();
            renderBoard();
        }
    }

    public void handleBattle(Event e) {
        if (board.getPhase().equals("ATTACK")) {
            Node fieldCard = (Node) e.getSource();
            Pane fieldPane = (Pane) fieldCard.getParent();
            if (!isAttacking) {
                resetFieldBackgrounds();
                if (fieldPane.equals(p1FieldPane) && board.getWhoseTurn().equals("P2")) {
                    return;
                } else if (fieldPane.equals(p2FieldPane) && board.getWhoseTurn().equals("P1")) {
                    return;
                }
                attackerIndex = getFieldCardIndex((Pane)fieldCard) - 2;
                if (!board.getCurrentPlayerField().containsKey(attackerIndex)) {
                    return;
                }
                attackingCard = board.getCurrentPlayerField().get(attackerIndex);
                if (!attackingCard.getStatus()) {
                    return;
                }
                ((Rectangle)((Pane)fieldCard).getChildren().get(0)).setFill(Color.LIGHTGREEN);
                isAttacking = true;
            } else {
                if (fieldCard instanceof ImageView && board.getCurrentOpponentField().isEmpty()) {
                    board.reduceCurrentOpponentHealth(attackingCard.getAttack());
                    isAttacking = false;
                    attackingCard.setStatus(false);
                    resetFieldBackgrounds();
                    renderBoard();
                } else {
                    defenderIndex = getOpponentFieldCardIndex((Pane)fieldCard) - 2;
                    if (!board.getCurrentOpponentField().containsKey(defenderIndex)) {
                        return;
                    }
                    defendingCard = board.getCurrentOpponentField().get(defenderIndex);
                    Battle battle = new Battle(board);
                    battle.characterAttacked(attackerIndex, defenderIndex);
                    battle.runCachedActions();
                    if (!board.getCurrentOpponentField().containsKey(defenderIndex)) {
                        attackingCard.addExp(defendingCard.getLevel());
                        attackingCard.levelUp();
                    }
                    attackingCard.setStatus(false);
                    isAttacking = false;
                    board.checkForDeathsOnField();
                    resetFieldBackgrounds();
                    renderBoard();
                    //TODO: add battle
                }
            }
        }
    }

    public void debugMe() {
        System.out.println("Hey");
    }
}
