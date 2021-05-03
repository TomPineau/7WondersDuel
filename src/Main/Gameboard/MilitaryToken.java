package Main.Gameboard;

import Main.ResponsiveImageView;
import Main.Transition;
import javafx.scene.image.ImageView;
import org.jetbrains.annotations.NotNull;

import static Main.Window.Play.gameboard;
import static Main.Window.Play.isPlaying;

public class MilitaryToken {

    private boolean faded;
    private ResponsiveImageView responsiveImageView;

    public boolean isFaded() {
        return this.faded;
    }

    public void setFaded(boolean faded) {
        this.faded = faded;
    }

    public ResponsiveImageView getResponsiveImageView() {
        return this.responsiveImageView;
    }

    public void setResponsiveImageView(ResponsiveImageView responsiveImageView) {
        this.responsiveImageView = responsiveImageView;
    }

    public ImageView getImageView() {
        return this.getResponsiveImageView().getImageView();
    }

    public double getX() {
        return this.getResponsiveImageView().getX();
    }

    public double getY() {
        return this.getResponsiveImageView().getY();
    }

    public double getWidth() {
        return this.getResponsiveImageView().getWidth();
    }

    public void setWidth(double width) {
        this.getResponsiveImageView().setWidth(width);
    }

    public double getHeight() {
        return this.getResponsiveImageView().getHeight();
    }

    public MilitaryToken(@NotNull ResponsiveImageView responsiveImageView) {
        this.faded = false;
        this.responsiveImageView = responsiveImageView;
        double gameboardWidth = gameboard.getWidth();
        double gameboardHeight = gameboard.getHeight();
        double militaryX = gameboard.getX() + gameboardWidth*responsiveImageView.getX()/100;
        double militaryY = gameboard.getY() + gameboardHeight*responsiveImageView.getY()/100;
        double militaryWidth = gameboardWidth*responsiveImageView.getWidth()/100;
        double militaryHeight = gameboardHeight*responsiveImageView.getHeight()/100;
        this.set(militaryX, militaryY, militaryWidth, militaryHeight);
    }

    private void set(double x, double y, double width, double height) {
        if (isPlaying) {
            this.getResponsiveImageView().set(x, y, width, height);
        }
    }

    public void fade() {
        Transition.fadeTransition(this.getImageView(), 5);
        this.setFaded(true);
    }





}
