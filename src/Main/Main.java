package Main;

import Main.Window.Play;
import Main.Window.Settings;
import Main.Window.Welcome;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

public class Main extends Application {

    public static Pane pane = new Pane();
    public static String mainPath = System.getProperty("user.dir");

    public static void main(String[] args) {
        launch(args);
    }

    public void start(@NotNull Stage stage) {
        Scene scene = new Scene(pane);
        scene.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
        stage.setTitle("7Wonders Duel");
        stage.setMinWidth(640);
        stage.setMinHeight(400);
        stage.setFullScreen(true);
        stage.setScene(scene);
        stage.show();
        Data.add();
        //Welcome.welcome();
        //Play.play();
        Settings.settings();
    }

}
