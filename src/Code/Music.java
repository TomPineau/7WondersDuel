package Code;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import org.jetbrains.annotations.NotNull;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import java.io.File;
import java.util.Random;

import static Code.Main.mainPath;
import static Code.Window.Welcome.settingList;
import static Code.Data.musicList;

public class Music {

    public static boolean endOfBackgroundMusic;
    private static boolean printMusicMessage = true;
    private static int lastBackgroundMusicIndex = -1;
    private static MediaPlayer currentMusic;
    private static Timeline wait;

    public static void playThemeMusic(@NotNull MediaPlayer mediaPlayer, double volume) {
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setVolume(volume);
        mediaPlayer.play();
    }

    public static void playSoundEffect(int indexSoundEffect, double volume) {
        if (indexSoundEffect == 0) {
            MediaPlayer menuSoundEffect = new MediaPlayer(new Media(new File(mainPath + "/src/Music/SoundEffect/SoundEffect1.mp3").toURI().toString()));
            menuSoundEffect.setVolume(volume);
            menuSoundEffect.play();
        }
        if (indexSoundEffect == 1) {
            MediaPlayer beginGameSoundEffect = new MediaPlayer(new Media(new File(mainPath + "/src/Music/SoundEffect/SoundEffect2.mp3").toURI().toString()));
            beginGameSoundEffect.setVolume(volume);
            beginGameSoundEffect.play();
        }
    }

    public static void playBackgroundMusic() {
        endOfBackgroundMusic = false;
        waitUntilNewMusic();
    }

    private static void waitUntilNewMusic() {
        double time = randomTime(10, 10);
        printMusicMessage("Temps avant prochaine chanson : " + time + " secondes");
        EventHandler playRandomMusic = event -> {
            if (!endOfBackgroundMusic) {
                playRandomMusic();
            }
        };
        playMusicOnTimelineFinished(time, playRandomMusic);
    }

    private static void playRandomMusic() {
        if (currentMusic != null) {
            currentMusic.stop();
        }
        Media musicChosen = chooseRandomMusic();
        currentMusic = new MediaPlayer(musicChosen);
        currentMusic.setVolume(settingList.get(1));
        currentMusic.play();
        printMusicMessage("Musique jouée : " + currentMusic.getMedia().getSource().substring(mainPath.length() + 27, currentMusic.getMedia().getSource().length()-4));
        EventHandler<ActionEvent> waitUntilNewMusic = e -> {
            currentMusic.stop();
            printMusicMessage("Fin chanson");
            if (!endOfBackgroundMusic) {
                waitUntilNewMusic();
            }
        };
        playMusicOnTimelineFinished(360, waitUntilNewMusic);
    }

    private static void playMusicOnTimelineFinished(double time, EventHandler<ActionEvent> event) {
        wait = new Timeline(new KeyFrame(Duration.seconds(time)));
        wait.play();
        wait.setOnFinished(event);
    }

    public static void stopBackgroundMusic() {
        if (currentMusic != null) {
            currentMusic.stop();
        }
        wait.stop();
        endOfBackgroundMusic = true;
        lastBackgroundMusicIndex = -1;
        printMusicMessage("||| Arrêt |||");
    }

    private static Media chooseRandomMusic() {
        Random rand = new Random();
        int randomMusic = rand.nextInt(musicList.size());
        while (randomMusic == lastBackgroundMusicIndex) {
            randomMusic = rand.nextInt(musicList.size());
        }
        lastBackgroundMusicIndex = randomMusic;
        return musicList.get(randomMusic);
    }

    private static double randomTime(double minTime, int averageTime) {
        Random random = new Random();
        return random.nextInt(averageTime) + minTime;
    }

    private static void printMusicMessage(String string) {
        if (printMusicMessage) {
            String ANSI_RESET = "\u001B[0m";
            String ANSI_YELLOW = "\u001B[33m";
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            System.out.println(ANSI_YELLOW + string + " - " + dtf.format(now) + ANSI_RESET);
        }
    }

    private static void printDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
    }

}
