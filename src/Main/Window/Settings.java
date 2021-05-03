package Main.Window;

import Main.*;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;

import static Main.Main.pane;
import static Main.Main.mainPath;
import static Main.Window.Welcome.settingList;
import static Main.Window.Welcome.themeMusic;
import static Main.Window.Welcome.welcomeDuel;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class Settings {

    private static ResponsiveImageView background;

    public static void settings() {
        pane.getChildren().clear();
        //Set.backgroundPicture(sevenWonders,"welcomePicture");
        background = new ResponsiveImageView(new ImageView("file:" + mainPath + "/src/Picture/7Wonders.png"), "welcomePicture");
        ResponsiveButton home = new ResponsiveButton( new ImageView("file:" + mainPath + "/src/Picture/Icones/Home.png"), 3, 5, 3, 4.8,"ICON");
        ResponsiveButton save = new ResponsiveButton(null, 40, 85, 20, 7.5, "SAVE");
        ResponsiveTextField settings = new ResponsiveTextField("Paramètres", 37.5, 0, 25, 10, true);
        ResponsiveSlider opacity = new ResponsiveSlider(0, 100, settingList.get(0), "Opacité", 33.3333, 25, 33.3333, 10);
        ResponsiveSlider mainVolume = new ResponsiveSlider(0, 0.5, settingList.get(1), "Volume principal", 33.3333, 37.5, 33.3333, 10);
        ResponsiveSlider soundEffects = new ResponsiveSlider(0, 1.0, settingList.get(2), "Effets sonores", 33.3333, 50, 33.3333, 10);
        //Slider opacitySlider = new Slider(0, 100.0, settingList.get(0));
        //Slider mainVolumeSlider = new Slider(0, 0.5, settingList.get(1));
        //Slider soundEffectsSlider = new Slider(0, 1.0, settingList.get(2));
        //Set.text("Paramètres", 37.5, 0, 25, 10, true);
        //Set.slider(opacitySlider, "Opacité", 33.3333, 25, 33.3333, 10, false);
        //Set.slider(mainVolumeSlider, "Volume principal", 33.3333, 37.5, 33.3333, 10, false);
        //Set.slider(soundEffectsSlider, "Effets sonores", 33.3333, 50, 33.3333, 10, false);
        home.setOnAction(homeEvent(home));
        save.setOnAction(saveEvent(opacity, mainVolume, soundEffects));
    }

    @Contract(pure = true)
    private static EventHandler homeEvent(ResponsiveButton button) {
        EventHandler eventHandler = event -> {
            Music.playSoundEffect("ClickButton");
            button.clear();
            pane.getChildren().clear();
            Welcome.welcome();
        };
        return eventHandler;
    }

    @Contract(pure = true)
    private static EventHandler saveEvent(ResponsiveSlider opacity, ResponsiveSlider mainVolume, ResponsiveSlider soundEffects) {
        EventHandler eventHandler = event -> {
            Music.playSoundEffect("ClickButton");
            double opacityValue = opacity.getValue()/200.0 + 0.50;
            double volumeValue = mainVolume.getValue();
            double soundEffectsValue = soundEffects.getValue();
            welcomeDuel.setStyle("-fx-opacity: " + opacityValue + ";");
            background.getImageView().setStyle("-fx-opacity: " + opacityValue + ";");
            settingList.set(0, opacity.getValue());
            settingList.set(1, volumeValue);
            settingList.set(2, soundEffectsValue);
            System.out.println(opacity.getValue() + " " + volumeValue + " " +soundEffectsValue);
            themeMusic.setVolume(volumeValue);
            Music.playThemeMusic(themeMusic);
        };
        return eventHandler;
    }

}
