package com.aetherwars;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import com.aetherwars.model.Character;
import com.aetherwars.util.CSVReader;

public class AetherWars extends Application {
  private static final String CHARACTER_CSV_FILE_PATH = "card/data/character.csv";

  public void loadCards() throws IOException, URISyntaxException {
    File characterCSVFile = new File(getClass().getResource(CHARACTER_CSV_FILE_PATH).toURI());
    CSVReader characterReader = new CSVReader(characterCSVFile, "\t");
    characterReader.setSkipHeader(true);
    List<String[]> characterRows = characterReader.read();
    for (String[] chara : characterRows) {
      Character c = new Character(chara);
      System.out.println(c);
    }
  }

  Button btnStart;

  @Override
  public void start(Stage primaryStage) {
    Text text = new Text();
    text.setText("Loading...");
    text.setX(50);
    text.setY(50);

    Group root = new Group();
    root.getChildren().add(text);

    btnStart = new Button();
    btnStart.setText("Moyai");

    StackPane layout = new StackPane();
    layout.getChildren().add(btnStart);
    Scene scene = new Scene(root, 1280, 720);

    primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icon.png"))));
    primaryStage.setTitle("Minecraft: Aether Wars");

    primaryStage.setMinWidth(960);
    primaryStage.setMinHeight(540);
    primaryStage.setMaxWidth(1920);
    primaryStage.setMaxHeight(1080);
    primaryStage.setScene(scene);
    primaryStage.show();

    try {
      this.loadCards();
      text.setText("Minecraft: Aether Wars!");
    } catch (Exception e) {
      text.setText("Failed to load cards: " + e);
    }
  }

  public static void main(String[] args) {
    launch(args);
  }
}
