package Code.Page;

import java.io.*;
import java.util.*;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.event.EventHandler;
import javafx.util.Duration;

import static Code.Main.pane;

public class Welcome {

    public static String mainPath = System.getProperty("user.dir");
    public static ArrayList<String> progressList = new ArrayList<>();
    public static ArrayList<Double> settingList = new ArrayList<>();
    static ImageView welcomeDuel = new ImageView("file:" + mainPath + "/src/Picture/Welcome.jpg");
    static MediaPlayer themeMusic = new MediaPlayer(new Media(new File(mainPath + "/src/Music/Theme/NealAcree_Nightsong.mp3").toURI().toString()));
    //private static ImageView welcome7Wonders = new ImageView("file:" + mainPath + "/src/Picture/Welcome.png");

    public static void welcomePage() {
        Code.Music.playThemeMusic(themeMusic, settingList.get(1));
        ImageView welcomePicture = welcomeDuel;
        ImageView settingsPicture = new ImageView("file:" + mainPath + "/src/Picture/Icones/Settings.png");
        Button startButton = new Button();
        Button settingsButton = new Button();
        Code.Set.image(welcomePicture);
        Code.Set.button(startButton, null, 80, 80, 15, 7.5, 1);
        Code.Set.button(settingsButton, settingsPicture, 3, 4.8, 3, 5,0);
        startButton.setOnAction(e -> {
            Code.Music.playSoundEffect(1, settingList.get(2));
            Code.Music.endOfBackgroundMusic = false;
            themeMusic.stop();
            welcomePicture.setId("welcomePicture");
            startButton.setDisable(true);
            settingsButton.setDisable(true);
            settingsButton.setVisible(false);
            EventHandler<ActionEvent> event = event1 -> {
                startButton.setVisible(false);
                exitButton();
                printProgress();
            };
            playEventOnTimelineFinished(2, event);
        });
        settingsButton.setOnAction(e -> {
            Code.Music.playSoundEffect(0, settingList.get(2));
            welcomePicture.setId("welcomePicture");
            startButton.setDisable(true);
            startButton.setVisible(false);
            settingsButton.setDisable(true);
            settingsButton.setVisible(false);
            Settings.settingsPage();
        });
    }



    private static void exitButton() {
        ImageView homePicture = new ImageView("file:" + mainPath + "/src/Picture/Icones/Home.png");
        Button homeButton = new Button();
        Code.Set.button(homeButton, homePicture, 3, 5, 3, 5,0);
        homeButton.setOnAction(e -> {
            Code.Music.playSoundEffect(0, settingList.get(2));
            quitGame();
        });
    }

    /*public void printWonders() {
        for (String path : wonderList) {
            ImageView imageView = new ImageView("file:" + mainPath + "/src/Picture/Wonders/" + path);
            imageView.setFitWidth(100.0);
            imageView.setPreserveRatio(true);
            pane.getChildren().add(imageView);
        }
    }*/

    private static void printProgress() {
        String progressDirectory = "file:" + mainPath + "/src/Picture/Progress/";
        for (int i = 0; i < progressList.size(); i++) {
            String path = progressList.get(i);
            ImageView imageView = new ImageView( progressDirectory + path);
            Button progressButton = new Button();
            Code.Set.button(progressButton, imageView, 10*i, 50, 8, 8, 3);
            progressButton.setOnAction(e -> {
                Code.Music.playSoundEffect(0, settingList.get(2));
                confirmation(progressButton);
            });
        }
        Code.Music.playBackgroundMusic();
    }

    private static void confirmation(Button button) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        Code.Set.alert(alert, "Carte", "Etes-vous sûr de vouloir prendre cette carte?", "Card");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Code.Music.playSoundEffect(0, settingList.get(2));
            translateAnimation(button, 1);
            button.setDisable(true);
        }
    }

    private static void translateAnimation(Node node, double time) {
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.seconds(time));
        translateTransition.setNode(node);
        translateTransition.setByY(200);
        translateTransition.play();
    }

    private static void quitGame() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        Code.Set.alert(alert, "Quitter ?", "Etes-vous sûr de vouloir quitter la partie?", "Home");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Code.Music.playSoundEffect(0, settingList.get(2));
            Code.Music.stopBackgroundMusic();
            pane.getChildren().clear();
            welcomePage();
        }
    }

    private static void playEventOnTimelineFinished(double time, EventHandler<ActionEvent> event) {
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(time)));
        timeline.play();
        timeline.setOnFinished(event);
    }

}
