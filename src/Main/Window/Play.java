package Main.Window;


import Main.*;
import Main.Gameboard.*;
import Main.Wonder.Wonder;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import org.jetbrains.annotations.Contract;

import java.util.Optional;

import static Main.Main.pane;
import static Main.Window.Welcome.*;
import static Main.Set.*;

import static Main.Main.mainPath;

public class Play {

    private static ImageView woodBackground = new ImageView("file:" + mainPath + "/src/Picture/7W-Duel-Logo.png");
    public static boolean isPlaying = false;
    public static Gameboard gameboard;
    public static Shield shield;
    public static MilitaryToken left5;
    public static MilitaryToken left2;
    public static MilitaryToken right2;
    public static MilitaryToken right5;
    public static ProgressTokenList mainProgressTokenList;
    public static ProgressTokenList progressTokenGameboard;
    public static ProgressTokenList progressTokenDeck;
    public static Player leftPlayer;
    public static Player rightPlayer;


    public static void play() {
        pane.getChildren().clear();
        isPlaying = true;
        //Set.backgroundPicture(woodBackground, "");
        ResponsiveImageView background = new ResponsiveImageView(woodBackground, "");
        Music.playBackgroundMusic();
        exitButton();
        gameboard();
        money();
        leftPlayer = new Player("Tom");
        rightPlayer = new Player("Marie");
        wonders();
    }

    private static void gameboard() {
        String path = "file:" + mainPath + "/src/Picture/MilitaryToken/";
        gameboard = new Gameboard(new ResponsiveImageView(new ImageView("file:" + mainPath + "/src/Picture/Gameboard.png"), 25.0, 0.0, 50.0, 22.5));
        left5 = new MilitaryToken(new ResponsiveImageView(new ImageView(path + "left5.png"), 11,72.5,11,16));
        left2 = new MilitaryToken(new ResponsiveImageView(new ImageView(path + "left2.png"), 25.9,74.25,9.5,13));
        right2 = new MilitaryToken(new ResponsiveImageView(new ImageView(path + "right2.png"), 63.5,74.25,9.5,13));
        right5 = new MilitaryToken(new ResponsiveImageView(new ImageView(path + "right5.png"), 77,72.5,11,16));
        shield = new Shield(new ResponsiveImageView(new ImageView("file:" + mainPath + "/src/Picture/Shield.png"),45.8775,39, 6, 40));
        shield.move(4);
        Data.addProgressToken();
        progressTokenGameboard = new ProgressTokenList();
        progressTokenDeck = new ProgressTokenList();
        progressTokenGameboard.setProgressTokenGameboard(5);
        progressTokenDeck.setProgressTokenDeck();
        mainProgressTokenList.setProgressTokenList();
        progressTokenGameboard.startAnimation(0);
    }

    private static void resetGameboard() {
        mainProgressTokenList = new ProgressTokenList();
        progressTokenGameboard = new ProgressTokenList();
        progressTokenDeck = new ProgressTokenList();
        ProgressTokenList.index = 0;
    }

    private static void wonders() {
        String csv = "Wonders";
        int rowNumber = CSVReader.getRowNumber(csv);
        for (int i = 1; i < rowNumber; i++) {
            String[] row = CSVReader.getRow(csv, i);
            String name = row[0];
            boolean isPlayed = Boolean.parseBoolean(row[1]);
            String location = row[2];
            int stoneCost = Integer.parseInt(row[3]);
            int woodCost = Integer.parseInt(row[4]);
            int brickCost = Integer.parseInt(row[5]);
            int glassCost = Integer.parseInt(row[6]);
            int papyrusCost = Integer.parseInt(row[7]);
            boolean doReplay = Boolean.parseBoolean(row[8]);
            String doRemoveCard = row[9];
            boolean drawProgressToken = Boolean.parseBoolean(row[10]);
            boolean drawCardFromDeck = Boolean.parseBoolean(row[11]);
            int shield = Integer.parseInt(row[12]);
            int moneyGain = Integer.parseInt(row[13]);
            int moneyLost = Integer.parseInt(row[14]);
            boolean stoneGain = Boolean.parseBoolean(row[15]);
            boolean woodGain = Boolean.parseBoolean(row[16]);
            boolean brickGain = Boolean.parseBoolean(row[17]);
            boolean glassGain = Boolean.parseBoolean(row[18]);
            boolean papyrusGain = Boolean.parseBoolean(row[19]);
            int crown = Integer.parseInt(row[20]);
            Wonder wonder = new Wonder(name, isPlayed, location,
                    stoneCost, woodCost, brickCost, glassCost, papyrusCost,
                    doReplay, doRemoveCard, drawProgressToken, drawCardFromDeck,
                    shield, moneyGain, moneyLost,
                    stoneGain, woodGain, brickGain, glassGain, papyrusGain,
                    crown);
        }
    }

    private static void money() {
        String moneyPlayer1 = "7";
        String moneyPlayer2 = "7";
        text(moneyPlayer1, 0, 40, 10, 5, false);
        text(moneyPlayer2, 0, 50, 10, 5, false);
        ImageView moneyScore = new ImageView("file:" + mainPath + "/src/Picture/Icones/Money.png");
        ResponsiveImageView money = new ResponsiveImageView(moneyScore, 2.5, 44.5, 4, 6);
        money.set(money.getX(), money.getY(), money.getWidth(), money.getHeight());
    }

    private static void exitButton() {
        ImageView homePicture = new ImageView("file:" + mainPath + "/src/Picture/Icones/Home.png");
        ResponsiveButton home = new ResponsiveButton(homePicture, 3, 5, 3, 5,"ICON");
        home.setOnAction(homeEvent());
    }

    @Contract(pure = true)
    private static EventHandler homeEvent() {
        EventHandler eventHandler = event -> {
            Music.playSoundEffect("ClickButton");
            quitGame();
        };
        return eventHandler;
    }

    private static void quitGame() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert(alert, "Quitter ?", "Etes-vous sûr de vouloir quitter la partie?", "Home");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Music.playSoundEffect("ClickButton");
            Music.stopBackgroundMusic();
            isPlaying = false;
            resetGameboard();
            welcome();
        }
    }

}
