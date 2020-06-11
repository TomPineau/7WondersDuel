package Code.Gameboard;

import Code.Transition;
import javafx.scene.image.ImageView;

import static Code.Window.Play.gameboard;
import static Code.Set.image;

public class MilitaryToken {

    private boolean faded;
    private ImageView imageView;
    private double x;
    private double y;
    private double width;
    private double height;

    public boolean isFaded() {
        return this.faded;
    }

    public void setFaded(boolean faded) {
        this.faded = faded;
    }

    public ImageView getImageView() {
        return this.imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

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

    public MilitaryToken(ImageView imageView, double x, double y, double width, double height) {
        this.faded = false;
        this.imageView = imageView;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        double gameboardWidth = gameboard.getWidth();
        double gameboardHeight = gameboard.getHeight();
        double militaryX = gameboard.getX() + gameboardWidth*x/100;
        double militaryY = gameboard.getY() + gameboardHeight*y/100;
        double militaryWidth = gameboardWidth*width/100;
        double militaryHeight = gameboardHeight*height/100;
        image(imageView, militaryX, militaryY, militaryWidth, militaryHeight);
    }

    public void fade() {
        Transition.fadeTransition(this.getImageView(), 5);
        this.setFaded(true);
    }





}
