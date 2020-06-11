package Code;

import Code.Gameboard.Shield;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;
import org.jetbrains.annotations.NotNull;

import static Code.Window.Play.gameboard;
import static Code.Main.pane;

public class Transition {

    public static void translateTransition(Node node, double time, double x, double y) {
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.seconds(time));
        translateTransition.setNode(node);
        translateTransition.setByX(x);
        translateTransition.setByY(y);
        translateTransition.play();
    }

    public static void fadeTransition(Node node, double time) {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.seconds(time));
        fadeTransition.setNode(node);
        fadeTransition.setToValue(0.0);
        fadeTransition.play();
    }

    public static void shieldTransition(@NotNull Shield shield, double time, double x) {
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(time));
        translateTransition.setNode(shield.getImageView());
        double sceneWidth = pane.getScene().getWidth()/100;
        double newX = sceneWidth*(gameboard.getX() + (gameboard.getWidth()/100)*x) - shield.shieldXToPixel();
        translateTransition.setByX(newX);
        translateTransition.play();
    }

}
