package com.aetherwars;

import com.aetherwars.model.Character;
import com.aetherwars.spells.*;
import com.aetherwars.util.CSVReader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AetherWars extends Application {
    private static final String CHARACTER_CSV_FILE_PATH = "card/data/character.csv";
    private static final String SPELLMORPH_CSV_FILE_PATH = "card/data/spell_morph.csv";
    private static final String SPELLPOTION_CSV_FILE_PATH = "card/data/spell_ptn.csv";
    private static final String SPELLSWAP_CSV_FILE_PATH = "card/data/spell_swap.csv";
    private static final String SPELLLEVEL_CSV_FILE_PATH = "card/data/spell_level.csv";
    private final List<Character> characters;
    private final List<Spell> spells;

    public AetherWars() {
        characters = new ArrayList<>();
        spells = new ArrayList<>();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void loadCards() throws IOException, URISyntaxException {
        File characterCSVFile = new File(getClass().getResource(CHARACTER_CSV_FILE_PATH).toURI());
        CSVReader characterReader = new CSVReader(characterCSVFile, "\t");
        characterReader.setSkipHeader(true);
        List<String[]> characterRows = characterReader.read();
        for (String[] chara : characterRows) {
            Character c = new Character(chara);
            System.out.println(c);
            characters.add(c);
        }
        System.out.println("Characters loaded");

        File spellMorphCSVFile = new File(getClass().getResource(SPELLMORPH_CSV_FILE_PATH).toURI());
        CSVReader spellMorphReader = new CSVReader(spellMorphCSVFile, "\t");
        spellMorphReader.setSkipHeader(true);
        List<String[]> spellMorphRows = spellMorphReader.read();
        for (String[] spell : spellMorphRows) {
            MorphSpell s = new MorphSpell(spell);
            System.out.println(s);
            spells.add(s);
        }

        File spellPotionCSVFile = new File(getClass().getResource(SPELLPOTION_CSV_FILE_PATH).toURI());
        CSVReader spellPotionReader = new CSVReader(spellPotionCSVFile, "\t");
        spellPotionReader.setSkipHeader(true);
        List<String[]> spellPotionRows = spellPotionReader.read();
        for (String[] spell : spellPotionRows) {
            PotionSpell s = new PotionSpell(spell);
            System.out.println(s);
            spells.add(s);
        }

        File spellSwapCSVFile = new File(getClass().getResource(SPELLSWAP_CSV_FILE_PATH).toURI());
        CSVReader spellSwapReader = new CSVReader(spellSwapCSVFile, "\t");
        spellSwapReader.setSkipHeader(true);
        List<String[]> spellSwapRows = spellSwapReader.read();
        for (String[] spell : spellSwapRows) {
            SwapSpell s = new SwapSpell(spell);
            System.out.println(s);
            spells.add(s);
        }

        File spellLevelCSVFile = new File(getClass().getResource(SPELLLEVEL_CSV_FILE_PATH).toURI());
        CSVReader spellLevelReader = new CSVReader(spellLevelCSVFile, "\t");
        spellLevelReader.setSkipHeader(true);
        List<String[]> spellLevelRows = spellLevelReader.read();
        for (String[] spell : spellLevelRows) {
            LevelSpell s = new LevelSpell(spell);
            System.out.println(s);
            spells.add(s);
        }

        System.out.println("Spells loaded");
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/aetherwars/MainMenu.fxml"));
        Parent mainMenu = fxmlLoader.load();
        String css = this.getClass().getResource("application.css").toExternalForm();

        try {
            this.loadCards();
        } catch (Exception e) {
            System.out.println("Error!!\n");
            e.printStackTrace();
        }

        MainMenuController controller = fxmlLoader.getController();
        controller.setCharacters(characters);
        controller.setSpells(spells);

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

    }
}
