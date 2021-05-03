package Main.Gameboard;

import Main.ResponsiveImageView;
import Main.Transition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import static Main.Main.pane;
import static Main.Window.Play.*;

public class Shield {

    private boolean printShieldMessage = true;

    private int value;
    private ResponsiveImageView responsiveImageView;

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
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

    public void setX(double x) {
        this.getResponsiveImageView().setX(x);
        this.reset();
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


    public Shield(@NotNull ResponsiveImageView responsiveImageView) {
        this.value = 0;
        this.responsiveImageView = responsiveImageView;
        double gameboardWidth = gameboard.getWidth();
        double gameboardHeight = gameboard.getHeight();
        double shieldX = gameboard.getX() + gameboardWidth*responsiveImageView.getX()/100;
        double shieldY = gameboard.getY() + gameboardHeight*responsiveImageView.getY()/100;
        double shieldWidth = gameboardWidth*responsiveImageView.getWidth()/100;
        double shieldHeight = gameboardHeight*responsiveImageView.getHeight()/100;
        this.set(shieldX, shieldY, shieldWidth, shieldHeight);
    }

    private void set(double x, double y, double width, double height) {
        if (isPlaying) {
            this.getResponsiveImageView().set(x, y, width, height);
        }
    }

    private void reset() {
        if (isPlaying) {
            double sceneWidth = pane.getScene().getWidth() / 100;
            double sceneHeight = pane.getScene().getHeight() / 100;
            double imageViewX = this.shieldXToPixel() / sceneWidth;
            double imageViewY = this.shieldYToPixel() / sceneHeight;
            double imageViewWidth = this.shieldWidthToPixel() / sceneWidth;
            double imageViewHeight = this.shieldHeightToPixel() / sceneHeight;
            pane.getChildren().removeAll(this.getImageView());
            this.set(imageViewX, imageViewY, imageViewWidth, imageViewHeight);
        }
    }

    public void move(int nb) {
        double percentage = 4.725;
        double time = absoluteValue(nb);
        double newX = this.getX() + percentage*nb;
        Transition.shieldTransition(this, time, newX);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(time)));
        timeline.play();
        timeline.setOnFinished(event -> {
            int newValue = this.getValue() + nb;
            this.setValue(newValue);
            this.setX(newX);
            update(newValue);
        });
    }

    private void update(int newValue) {
        if (newValue >= -2 && newValue <= 2) {
            printShieldMessage("Tout va bien");
        } else if (newValue >= 3 && newValue <= 5) {
            if (!right2.isFaded()) {
                right2.fade();
            }
        } else if (newValue >= 6 && newValue <= 8) {
            if (!right2.isFaded() && !right5.isFaded()) {
                right2.fade();
                right5.fade();
            } else if (!right5.isFaded()) {
                right5.fade();
            }
        } else if (newValue >= -5 && newValue <= -3) {
            if (!left2.isFaded()) {
                left2.fade();
            }
        } else if (newValue >= -8 && newValue <= -6) {
            if (!left2.isFaded() && !left5.isFaded()) {
                left2.fade();
                left5.fade();
            } else if (!left5.isFaded()) {
                left5.fade();
            }
        } else if (newValue == -9) {
            printShieldMessage("Le joueur de gauche a gagné");
        } else if (newValue == 9) {
            printShieldMessage("Le joueur de droite a gagné");
        } else {
            printShieldMessage("Ce n'est pas normal");
        }
    }

    @Contract(pure = true)
    private double absoluteValue(int n) {
        if (n < 0) {
            return -n;
        } else {
            return n;
        }
    }

    public double shieldXToPixel() {
        double sceneWidth = pane.getScene().getWidth()/100;
        return sceneWidth*(gameboard.getX() + (gameboard.getWidth()/100)* this.getX());
    }

    private double shieldYToPixel() {
        double sceneHeight = pane.getScene().getHeight()/100;
        return sceneHeight*(gameboard.getY() + (gameboard.getHeight()/100)* this.getY());
    }

    private double shieldWidthToPixel() {
        double sceneWidth = pane.getScene().getWidth();
        return (this.getWidth()/100*gameboard.getWidth()/100)*sceneWidth;
    }

    private double shieldHeightToPixel() {
        double sceneHeight = pane.getScene().getHeight();
        return (this.getHeight()/100*gameboard.getHeight()/100)*sceneHeight;
    }

    private void printShieldMessage(String message) {
        if (printShieldMessage) {
            String ANSI_PURPLE = "\u001B[35m";
            String ANSI_RESET = "\u001B[0m";
            System.out.println(ANSI_PURPLE + message + ANSI_RESET);
        }
    }

}
