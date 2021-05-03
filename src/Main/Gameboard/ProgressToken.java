package Main.Gameboard;

import Main.Location;
import Main.Music;
import Main.ResponsiveButton;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import org.jetbrains.annotations.Contract;

import static Main.Main.mainPath;
import static Main.Window.Play.gameboard;
import static Main.Window.Play.isPlaying;

public class ProgressToken {

    private String name;
    private String location;
    private ResponsiveButton responsiveButton;
    private Location location2;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ResponsiveButton getResponsiveButton() {
        return this.responsiveButton;
    }

    public void setResponsiveButton(ResponsiveButton responsiveButton) {
        this.responsiveButton = responsiveButton;
    }

    public ImageView getImageView() {
        return new ImageView("file:" + mainPath + "/src/Picture/ProgressToken/" + this.getName() + ".png");
    }

    public ProgressToken(String name) {
        this.name = name;
        this.location = "DECK";
        this.responsiveButton = new ResponsiveButton(this.getImageView(), (gameboard.getWidth()/100)*7.25, (gameboard.getHeight()/100)*25, "PROGRESSTOKEN");
        this.getResponsiveButton().setDisable(true);
        this.getResponsiveButton().setMinSize(0,0);
    }

    public void set(double x, double y, double width, double height) {
        if (isPlaying) {
            this.getResponsiveButton().set(x, y, width, height);
            Music.playSoundEffect("ProgressTokenStart");
        }
    }

    @Contract(pure = true)
    public EventHandler reset(double newX, double newY, double width, double height) {
        return event -> {
            if (isPlaying) {
                this.getResponsiveButton().resetButton(newX, newY, width, height);
                this.getResponsiveButton().setDisable(false);
                Music.playSoundEffect("ProgressTokenEnd");
            }
        };
    }

}
