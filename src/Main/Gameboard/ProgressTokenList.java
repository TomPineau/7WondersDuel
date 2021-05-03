package Main.Gameboard;

import Main.Event;
import Main.Music;
import Main.Set;
import Main.Transition;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

import static Main.Main.pane;
import static Main.Window.Play.*;

public class ProgressTokenList {

    private static boolean printProgressTokenMessage = false;
    private static boolean playerCanTakeProgressTokenFromGameboard = false;
    public static int index = 0;

    private ArrayList<ProgressToken> progressTokenList;

    public ArrayList<ProgressToken> getProgressTokenList() {
        return this.progressTokenList;
    }

    public void setProgressTokenList(ArrayList<ProgressToken> progressTokenList) {
        this.progressTokenList = progressTokenList;
    }

    public ProgressTokenList() {
        this.progressTokenList = new ArrayList<>();
    }

    public void add(ProgressToken progressToken) {
        this.getProgressTokenList().add(progressToken);
    }

    private int size() {
        return this.getProgressTokenList().size();
    }

    private int indexOf(ProgressToken progressToken) {
        return this.getProgressTokenList().indexOf(progressToken);
    }

    private ProgressToken get(int index) {
        return this.getProgressTokenList().get(index);
    }

    private void remove(int index) {
        this.getProgressTokenList().remove(index);
    }

    private void addAll(@NotNull ProgressTokenList progressTokenList) {
        this.getProgressTokenList().addAll(progressTokenList.getProgressTokenList());
    }

    public void startAnimation(int nb) {
        index = index + nb;
        double time = 3;
        if (index < this.size()) {
            ProgressToken progressToken = this.get(index);
            double sceneWidth = pane.getScene().getWidth()/100;
            double sceneHeight = pane.getScene().getHeight()/100;
            double gameboardXToPixel = gameboard.getX()*sceneWidth;
            double gameboardYToPixel = gameboard.getY()*sceneHeight;
            double gameboardWidthToPixel = gameboard.getWidth()*sceneWidth/100;
            double gameboardHeightToPixel = gameboard.getHeight()*sceneHeight/100;
            double progressTokenWidth = progressToken.getResponsiveButton().getWidth();
            double progressTokenHeight = progressToken.getResponsiveButton().getHeight();
            double progressTokenX = 50 - progressTokenWidth / 2;
            double progressTokenY = 50 - progressTokenHeight / 2;
            double spaceBetweenProgressToken = gameboardWidthToPixel*10;
            double translateByX = gameboardXToPixel + gameboardWidthToPixel*22.125 - (sceneWidth*50 - progressTokenWidth*sceneWidth) + spaceBetweenProgressToken * index;
            double translateByY = gameboardYToPixel + gameboardHeightToPixel*1.125 - (sceneHeight*50 - progressTokenHeight*sceneHeight);
            double newX = progressTokenX + translateByX/sceneWidth;
            double newY = progressTokenY + translateByY/sceneHeight;
            progressToken.set(progressTokenX, progressTokenY, progressTokenWidth, progressTokenHeight);
            Transition.progressTokenTransition(progressToken, time, translateByX, translateByY);
            Event.playEventOnTimelineFinished(progressToken.reset(newX, newY, progressTokenWidth, progressTokenHeight), time);
            Event.playEventOnTimelineFinished(progressTokenGameboard.setAlert(), time);
            waitUntilNewAnimation();
        }
    }

    private void waitUntilNewAnimation() {
        Event.playEventOnTimelineFinished(event -> {
            if (isPlaying) {
                startAnimation(1);
            }
        }, 0.5);
    }

    /*public void setProgressTokenOnGameboard() {
        int i = 0;
        String progressTokenDirectory = "file:" + mainPath + "/src/Picture/ProgressToken/";
        for (ProgressToken progressToken : progressTokenGameboard.getProgressTokenList()) {
            double x = progressToken.getX() + 5*i;
            double y = progressToken.getY();
            double width = progressToken.getWidth();
            double height = progressToken.getHeight();
            progressToken.setX(x);
            progressToken.setY(y);
            progressToken.setWidth(width);
            progressToken.setHeight(height);
            String path = progressTokenDirectory + progressToken.getName() + ".png";
            Set.button(new Button(), new ImageView(path), x, y, width, height, 3);
            i++;
        }
    }*/

    public void setProgressTokenGameboard(int nb) {
        for (int i = 0; i < nb; i++) {
            int randomProgressToken = new Random().nextInt(mainProgressTokenList.size());
            int progressTokenIndex = mainProgressTokenList.indexOf(mainProgressTokenList.get(randomProgressToken));
            ProgressToken progressToken = mainProgressTokenList.get(randomProgressToken);
            progressTokenGameboard.add(progressToken);
            mainProgressTokenList.remove(progressTokenIndex);
        }
        this.printProgressToken();
    }

    public void setProgressTokenDeck() {
        progressTokenDeck.addAll(mainProgressTokenList);
    }

    public void setProgressTokenList() {
        for (int i = mainProgressTokenList.size() - 1; i >= 0; i--) {
            mainProgressTokenList.remove(i);
        }
    }

    @NotNull
    @Contract(pure = true)
    private EventHandler setAlert() {
        return event -> {
            for (ProgressToken progressToken : this.getProgressTokenList()) {
                progressToken.getResponsiveButton().setOnAction(eventButton -> {
                    if (playerCanTakeProgressTokenFromGameboard) {
                        Music.playSoundEffect("ClickButton");
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        Set.alert(alert, "Prendre ce jeton ?", "Etes-vous sûr de vouloir prendre ce jeton ?", "ProgressTokenBack");
                        Optional<ButtonType> result = alert.showAndWait();
                    }
                });
            }
        };
    }

    private void printProgressToken() {
        if (printProgressTokenMessage) {
            for (ProgressToken progressToken : this.getProgressTokenList()) {
                printProgressMessage(progressToken.getName());
            }
            System.out.println();
        }
    }

    private void printProgressMessage(String message) {
        String ANSI_GREEN = "\u001B[0;92m";
        String ANSI_RESET = "\u001B[0m";
        System.out.println(ANSI_GREEN + message + ANSI_RESET + " ");
    }

}
