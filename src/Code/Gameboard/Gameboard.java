package Code.Gameboard;

import javafx.scene.image.ImageView;

import static Code.Main.mainPath;
import static Code.Set.image;

public class Gameboard {

    private double x;
    private double y;
    private double width;
    private double height;

    public double getX() {
        return this.x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return this.y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getWidth() {
        return this.width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return this.height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Gameboard(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        ImageView gameboard = new ImageView("file:" + mainPath + "/src/Picture/Gameboard.png");
        image(gameboard, x, y, width, height);
    }

}
