package Code.Gameboard;

import Code.Set;
import Code.Transition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import org.jetbrains.annotations.Contract;

import static Code.Main.mainPath;
import static Code.Main.pane;
import static Code.Set.image;
import static Code.Set.responsiveNode;
import static Code.Window.Play.*;

public class Shield {

    private boolean printShieldMessage = true;

    private int value;
    private ImageView imageView;
    private double x;
    private double y;
    private double width;
    private double height;

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
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
        this.resetImageView();
    }

    public double getY() {
        return this.y;
    }

    public void setY(double y) {
        this.y = y;
        this.resetImageView();
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

    public Shield(ImageView imageView, double x, double y, double width, double height) {
        this.value = 0;
        this.imageView = imageView;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        double gameboardWidth = gameboard.getWidth();
        double gameboardHeight = gameboard.getHeight();
        double shieldX = gameboard.getX() + gameboardWidth*x/100;
        double shieldY = gameboard.getY() + gameboardHeight*y/100;
        double shieldWidth = gameboardWidth*width/100;
        double shieldHeight = gameboardHeight*height/100;
        image(imageView, shieldX, shieldY, shieldWidth, shieldHeight);
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

    public void update(int newValue) {
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
        double shieldX = sceneWidth*(gameboard.getX() + (gameboard.getWidth()/100)*this.getX());
        return shieldX;
    }

    private double shieldYToPixel() {
        double sceneHeight = pane.getScene().getHeight()/100;
        double shieldY = sceneHeight*(gameboard.getY() + (gameboard.getHeight()/100)*this.getY());
        return shieldY;
    }

    private double shieldWidthToPixel() {
        double sceneWidth = pane.getScene().getWidth();
        double shieldWidth = (this.getWidth()/100*gameboard.getWidth()/100)*sceneWidth;
        return shieldWidth;
    }

    private double shieldHeightToPixel() {
        double sceneHeight = pane.getScene().getHeight();
        double shieldHeight = (this.getHeight()/100*gameboard.getHeight()/100)*sceneHeight;
        return shieldHeight;
    }

    private void resetImageView() {
        if (isPlaying) {
            double sceneWidth = pane.getScene().getWidth() / 100;
            double sceneHeight = pane.getScene().getHeight() / 100;
            double imageViewX = this.shieldXToPixel() / sceneWidth;
            double imageViewY = this.shieldYToPixel() / sceneHeight;
            double imageViewWidth = this.shieldWidthToPixel() / sceneWidth;
            double imageViewHeight = this.shieldHeightToPixel() / sceneHeight;
            Set.resetImageView(shield.getImageView(), imageViewX, imageViewY, imageViewWidth, imageViewHeight);
        }
    }

    private void printShieldMessage(String message) {
        if (printShieldMessage) {
            String ANSI_PURPLE = "\u001B[35m";
            String ANSI_RESET = "\u001B[0m";
            System.out.println(ANSI_PURPLE + message + ANSI_RESET);
        }
    }

}
