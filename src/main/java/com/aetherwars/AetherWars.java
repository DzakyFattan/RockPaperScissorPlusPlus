package com.aetherwars;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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

  @Override
  public void start(Stage primaryStage) throws IOException {
    Parent mainMenu = FXMLLoader.load(getClass().getResource("/com/aetherwars/MainMenu.fxml"));
    String css = this.getClass().getResource("application.css").toExternalForm();

    Scene mainMenuScene = new Scene(mainMenu);
    mainMenuScene.getStylesheets().add(css);

    primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/aetherwars/icon.png"))));
    primaryStage.setTitle("Minecraft: Aether Wars");

    primaryStage.setMinWidth(960);
    primaryStage.setMinHeight(540);
    primaryStage.setMaxWidth(1920);
    primaryStage.setMaxHeight(1080);
    primaryStage.setScene(mainMenuScene);
    primaryStage.show();

    try {
      this.loadCards();
    } catch (Exception e) {
      System.out.println("Error!!\n");
    }

  }

  public static void main(String[] args) {
    launch(args);
  }
}
