package Code.Window;

import Code.Set;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;

import static Code.Main.pane;
import static Code.Main.mainPath;
import static Code.Window.Welcome.settingList;
import static Code.Window.Welcome.themeMusic;
import static Code.Window.Welcome.welcomeDuel;
import Code.Music;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class Settings {

    private static ImageView sevenWonders = new ImageView("file:" + mainPath + "/src/Picture/7Wonders.png");

    public static void settings() {
        pane.getChildren().clear();
        Button homeButton = new Button();
        Button saveButton = new Button();
        Slider opacitySlider = new Slider(0, 100.0, settingList.get(0));
        Slider mainVolumeSlider = new Slider(0, 0.5, settingList.get(1));
        Slider soundEffectsSlider = new Slider(0, 1.0, settingList.get(2));
        Set.backgroundPicture(sevenWonders,"welcomePicture");
        Set.button(homeButton, new ImageView("file:" + mainPath + "/src/Picture/Icones/Home.png"), 3, 5, 3, 4.8,0);
        Set.button(saveButton, null, 40, 85, 20, 7.5, 2);
        Set.text("Paramètres", 37.5, 0, 25, 10, true);
        Set.slider(opacitySlider, "Opacité", 33.3333, 25, 33.3333, 10, false);
        Set.slider(mainVolumeSlider, "Volume principal", 33.3333, 37.5, 33.3333, 10, false);
        Set.slider(soundEffectsSlider, "Effets sonores", 33.3333, 50, 33.3333, 10, false);
        homeButton.setOnAction(e -> {
            homeButtonAction(homeButton);
        });
        saveButton.setOnAction(e -> {
            saveButtonAction(opacitySlider, mainVolumeSlider, soundEffectsSlider);
        });
    }

    @Contract(pure = true)
    private static void homeButtonAction(Button button) {
        Music.playSoundEffect(0, settingList.get(2));
        clearButton(button);
        pane.getChildren().clear();
        Welcome.welcome();
    }

    @Contract(pure = true)
    private static void saveButtonAction(@NotNull Slider opacity, @NotNull Slider mainVolume, @NotNull Slider soundEffects) {
        Music.playSoundEffect(0, settingList.get(2));
        double opacityValue = opacity.getValue()/200.0 + 0.50;
        double volumeValue = mainVolume.getValue();
        double soundEffectsValue = soundEffects.getValue();
        welcomeDuel.setStyle("-fx-opacity: " + opacityValue + ";");
        sevenWonders.setStyle("-fx-opacity: " + opacityValue + ";");
        Music.playThemeMusic(themeMusic, volumeValue);
        settingList.set(0, opacity.getValue());
        settingList.set(1, volumeValue);
        settingList.set(2, soundEffectsValue);
    }

    private static void clearButton(@NotNull Button button) {
        button.setDisable(true);
        button.setVisible(false);
    }

}
