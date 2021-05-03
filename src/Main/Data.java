package Main;

import Main.Gameboard.ProgressToken;
import Main.Gameboard.ProgressTokenList;
import javafx.scene.media.Media;

import java.io.File;
import java.util.ArrayList;

import static Main.Main.mainPath;
import static Main.Main.pane;
import static Main.Window.Play.gameboard;
import static Main.Window.Play.mainProgressTokenList;
import static Main.Window.Welcome.settingList;

public class Data {

    public static ArrayList<String> wonderList = new ArrayList<>();
    static ArrayList<Media> musicList = new ArrayList<>();

    public static void addProgressToken() {
        String progressTokenDirectory = mainPath + "/src/Picture/ProgressToken";
        File folder = new File(progressTokenDirectory);
        File[] fileList = folder.listFiles();
        if (fileList != null) {
            mainProgressTokenList = new ProgressTokenList();
            for (File file : fileList) {
                String fileName = file.getName().substring(0, file.getName().length()-4);
                ProgressToken progressToken = new ProgressToken(fileName);
                progressToken.setLocation("GAMEBOARD");
                mainProgressTokenList.add(progressToken);
            }
        }
    }

    private static void addWonders() {
        String wondersDirectory = mainPath + "/src/Picture/Wonders";
        addData(wonderList, wondersDirectory);
    }

    private static void addMusics() {
        String backgroundMusicDirectory = mainPath + "/src/Music/Background/";
        File backgroundMusicFile = new File(backgroundMusicDirectory);
        File[] backgroundMusicList = backgroundMusicFile.listFiles();
        if (backgroundMusicList != null) {
            for (File music : backgroundMusicList) {
                Media musicMedia = new Media(music.toURI().toString());
                musicList.add(musicMedia);
            }
        }
    }

    private static void addData(ArrayList<String> dataList, String path) {
        File folder = new File(path);
        File[] fileList = folder.listFiles();
        if (fileList != null) {
            for (File file : fileList) {
                String fileName = file.getName();
                dataList.add(fileName);
            }
        }
    }

    private static void addSettings() {
        settingList.add(50.0); //Opacité
        settingList.add(0.25); //Volume principal
        settingList.add(0.5); //Effets sonores
        settingList.add(50.0); //Police paramètres
        settingList.add(100.0); //Police sliders
    }

    public static void add() {
        addSettings();
        addMusics();
    }

}
