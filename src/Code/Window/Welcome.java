package Code.Window;

import java.io.*;
import java.util.*;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.event.EventHandler;
import javafx.util.Duration;

import Code.Music;
import Code.Set;
import static Code.Main.mainPath;
import static Code.Main.pane;

public class Welcome {

    public static ArrayList<Double> settingList = new ArrayList<>();
    static ImageView welcomeDuel = new ImageView("file:" + mainPath + "/src/Picture/Welcome.jpg");
    static MediaPlayer themeMusic = new MediaPlayer(new Media(new File(mainPath + "/src/Music/Theme/NealAcree_Nightsong.mp3").toURI().toString()));
    //private static ImageView welcome7Wonders = new ImageView("file:" + mainPath + "/src/Picture/Welcome.png");

    public static void welcome() {
        pane.getChildren().clear();
        Music.playThemeMusic(themeMusic, settingList.get(1));
        ImageView settingsPicture = new ImageView("file:" + mainPath + "/src/Picture/Icones/Settings.png");
        Button startButton = new Button();
        Button settingsButton = new Button();
        Set.backgroundPicture(welcomeDuel, "");
        Set.button(startButton, null, 80, 80, 15, 7.5, 1);
        Set.button(settingsButton, settingsPicture, 3, 4.8, 3, 5,0);
        startButton.setOnAction(e -> {
            Music.playSoundEffect(1, settingList.get(2));
            Music.endOfBackgroundMusic = false;
            themeMusic.stop();
            //welcomeDuel.setId("welcomePicture");
            startButton.setDisable(true);
            settingsButton.setDisable(true);
            settingsButton.setVisible(false);
            EventHandler<ActionEvent> event = event1 -> {
                startButton.setVisible(false);
                Play.play();
            };
            playEventOnTimelineFinished(2, event);
        });
        settingsButton.setOnAction(e -> {
            Music.playSoundEffect(0, settingList.get(2));
            //welcomeDuel.setId("welcomePicture");
            startButton.setDisable(true);
            startButton.setVisible(false);
            settingsButton.setDisable(true);
            settingsButton.setVisible(false);
            Settings.settings();
        });
    }

    private static void playEventOnTimelineFinished(double time, EventHandler<ActionEvent> event) {
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(time)));
        timeline.play();
        timeline.setOnFinished(event);
    }

    /*public void printWonders() {
        for (String path : wonderList) {
            ImageView imageView = new ImageView("file:" + mainPath + "/src/Picture/Wonders/" + path);
            imageView.setFitWidth(100.0);
            imageView.setPreserveRatio(true);
            pane.getChildren().add(imageView);
        }
    }*/

}
