package Code.Window;


import Code.*;
import Code.Gameboard.Gameboard;
import Code.Gameboard.MilitaryToken;
import Code.Gameboard.Shield;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;

import java.util.Optional;

import static Code.Main.pane;
import static Code.Window.Welcome.*;
import static Code.Set.*;
import static Code.Data.progressList;

import static Code.Main.mainPath;

public class Play {

    private static ImageView woodBackground = new ImageView("file:" + mainPath + "/src/Picture/woodBackground.jpg");
    public static boolean isPlaying = false;
    public static Gameboard gameboard;
    public static Shield shield;
    public static MilitaryToken left5;
    public static MilitaryToken left2;
    public static MilitaryToken right2;
    public static MilitaryToken right5;


    public static void play() {
        pane.getChildren().clear();
        isPlaying = true;
        Set.backgroundPicture(woodBackground, "");
        exitButton();
        Set.gameboard();
        money();
        Music.playBackgroundMusic();
    }

    private static void printProgress() {
        String progressDirectory = "file:" + mainPath + "/src/Picture/Progress/";
        for (int i = 0; i < progressList.size(); i++) {
            String path = progressList.get(i);
            ImageView imageView = new ImageView( progressDirectory + path);
            Button progressButton = new Button();
            button(progressButton, imageView, 10*i, 50, 8, 8, 3);
            progressButton.setOnAction(e -> {
                Code.Music.playSoundEffect(0, settingList.get(2));
                confirmation(progressButton);
            });
        }
    }

    private static void money() {
        String moneyPlayer1 = "7";
        String moneyPlayer2 = "7";
        text(moneyPlayer1, 0, 40, 10, 5, false);
        text(moneyPlayer2, 0, 50, 10, 5, false);
        ImageView moneyScore = new ImageView("file:" + mainPath + "/src/Picture/Icones/Money.png");
        image(moneyScore, 2.5, 44.5, 4, 6);
    }

    private static void exitButton() {
        ImageView homePicture = new ImageView("file:" + mainPath + "/src/Picture/Icones/Home.png");
        Button homeButton = new Button();
        button(homeButton, homePicture, 3, 5, 3, 5,0);
        homeButton.setOnAction(e -> {
            Music.playSoundEffect(0, settingList.get(2));
            quitGame();
        });
    }

    private static void confirmation(Button button) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert(alert, "Carte", "Etes-vous sûr de vouloir prendre cette carte?", "Card");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Music.playSoundEffect(0, settingList.get(2));
            Transition.translateTransition(button, 1, 0, 200);
            button.setDisable(true);
        }
    }

    private static void quitGame() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert(alert, "Quitter ?", "Etes-vous sûr de vouloir quitter la partie?", "Home");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Music.playSoundEffect(0, settingList.get(2));
            Music.stopBackgroundMusic();
            isPlaying = false;
            pane.getChildren().clear();
            welcome();
        }
    }

}
