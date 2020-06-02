package Code;

import javafx.scene.media.Media;

import java.io.File;
import java.util.ArrayList;

import static Code.Main.mainPath;
import static Code.Window.Welcome.settingList;

public class Data {

    public static ArrayList<String> progressList = new ArrayList<>();
    private static ArrayList<String> wonderList = new ArrayList<>();
    static ArrayList<Media> musicList = new ArrayList<>();

    private static void addProgress() {
        String progressDirectory = mainPath + "/src/Picture/Progress";
        addData(progressList, progressDirectory);
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
        addWonders();
        addProgress();
        addSettings();
        addMusics();
    }

}
