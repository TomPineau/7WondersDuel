package Main;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.util.Duration;

public class Event {

    public static void playEventOnTimelineFinished(EventHandler event, double time) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(time)));
        timeline.play();
        timeline.setOnFinished(event);
    }

}
