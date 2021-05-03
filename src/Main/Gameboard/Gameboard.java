package Main.Gameboard;

import Main.ResponsiveImageView;
import javafx.scene.image.ImageView;

import static Main.Main.mainPath;
import static Main.Window.Play.isPlaying;

public class Gameboard {

    private ResponsiveImageView responsiveImageView;

    public ResponsiveImageView getResponsiveImageView() {
        return this.responsiveImageView;
    }

    public void setResponsiveImageView(ResponsiveImageView responsiveImageView) {
        this.responsiveImageView = responsiveImageView;
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


    public Gameboard(ResponsiveImageView responsiveImageView) {
        this.responsiveImageView = responsiveImageView;
        this.set(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }

    private void set(double x, double y, double width, double height) {
        if (isPlaying) {
            this.getResponsiveImageView().set(x, y, width, height);
        }
    }

}
