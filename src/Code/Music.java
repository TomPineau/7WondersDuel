package Code;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import static Code.Page.Welcome.mainPath;
import static Code.Page.Welcome.settingList;

public class Music {

    public static boolean endOfBackgroundMusic;
    private static int lastBackgroundMusicIndex = -1;
    static ArrayList<MediaPlayer> musicList = new ArrayList<>();

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
        waitUntilNewMusic();
    }

    private static void waitUntilNewMusic() {
        double time = randomTime();
        printYellow("Temps avant prochaine chanson : " + time + " secondes");
        EventHandler<ActionEvent> event = e -> {
            if (!endOfBackgroundMusic) {
                playRandomMusic();
            }
        };
        playMusicOnTimelineFinished(time, event);
    }

    private static void playRandomMusic() {
        Random rand = new Random();
        int randomMusic = rand.nextInt(musicList.size());
        while (randomMusic == lastBackgroundMusicIndex) {
            randomMusic = rand.nextInt(musicList.size());
        }
        lastBackgroundMusicIndex = randomMusic;
        MediaPlayer musicPlayed = musicList.get(randomMusic);
        musicPlayed.setVolume(settingList.get(1));
        musicPlayed.play();
        printYellow("Musique jouée : " + musicPlayed.getMedia().getSource().substring(67,musicPlayed.getMedia().getSource().length()-4));
        EventHandler<ActionEvent> event = e -> {
            musicPlayed.stop();
            printYellow("Fin chanson");
            if (!endOfBackgroundMusic) {
                waitUntilNewMusic();
            }
        };
        playMusicOnTimelineFinished(360, event);
    }

    private static void playMusicOnTimelineFinished(double time, EventHandler<ActionEvent> event) {
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(time)));
        timeline.play();
        timeline.setOnFinished(event);
    }

    public static void stopBackgroundMusic() {
        endOfBackgroundMusic = true;
        lastBackgroundMusicIndex = -1;
        for (MediaPlayer mediaPlayer : musicList) {
            mediaPlayer.stop();
        }
    }

    private static double randomTime() {
        Random random = new Random();
        return (double) (random.nextInt(121) + 60);
    }

    private static void printYellow(String string) {
        String ANSI_RESET = "\u001B[0m";
        String ANSI_YELLOW = "\u001B[33m";
        System.out.println(ANSI_YELLOW + string + ANSI_RESET);
    }

}
