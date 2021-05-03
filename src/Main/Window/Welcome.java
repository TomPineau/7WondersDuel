package Main.Window;

import java.io.*;
import java.util.*;

import Main.*;
import Main.Set;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.event.EventHandler;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import static Main.Main.mainPath;
import static Main.Main.pane;

public class Welcome {

    public static ArrayList<Double> settingList = new ArrayList<>();
    static ImageView welcomeDuel = new ImageView("file:" + mainPath + "/src/Picture/Welcome.jpg");
    static MediaPlayer themeMusic = new MediaPlayer(new Media(new File(mainPath + "/src/Music/Theme/NealAcree_Nightsong.mp3").toURI().toString()));
    //private static ImageView welcome7Wonders = new ImageView("file:" + mainPath + "/src/Picture/Welcome.png");

    public static void welcome() {
        pane.getChildren().clear();
        //Set.backgroundPicture(welcomeDuel, "");
        ResponsiveImageView background = new ResponsiveImageView(welcomeDuel, "");
        Music.playThemeMusic(themeMusic);
        ImageView settingsPicture = new ImageView("file:" + mainPath + "/src/Picture/Icones/Settings.png");
        ResponsiveButton start = new ResponsiveButton(null, 80, 80, 15, 7.5, "START");
        ResponsiveButton settings = new ResponsiveButton(settingsPicture, 3, 4.8, 3, 5, "ICON");
        start.setOnAction(startEvent(start, settings));
        settings.setOnAction(settingsEvent(start, settings));
    }

    @Contract(pure = true)
    private static EventHandler startEvent(@NotNull ResponsiveButton start, @NotNull ResponsiveButton settings) {
        EventHandler eventHandler = event -> {
            Music.playSoundEffect("StartGame");
            themeMusic.stop();
            settings.clear();
            start.setDisable(true);
            EventHandler onClickEvent = event1 -> {
                start.setVisible(false);
                Play.play();
            };
            Event.playEventOnTimelineFinished(onClickEvent, 2);
        };
        return eventHandler;
    }

    @Contract(pure = true)
    private static EventHandler settingsEvent(@NotNull ResponsiveButton start, @NotNull ResponsiveButton settings) {
        EventHandler eventHandler = event -> {
            Music.playSoundEffect("ClickButton");
            start.clear();
            settings.clear();
            Settings.settings();
        };
        return eventHandler;
    }

}
