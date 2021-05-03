package Main;

import Main.Gameboard.*;
import javafx.animation.ScaleTransition;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static Main.Main.pane;
import static Main.Main.mainPath;
import static Main.Window.Play.*;
import static Main.Window.Welcome.settingList;

import java.util.ArrayList;

public class Set {

    public static void text(String string, double x, double y, double width, double height, boolean isTitle) {
        double fontSize;
        double sceneWidth = pane.getScene().getWidth()*0.01;
        double sceneHeight = pane.getScene().getHeight()*0.01;
        double textWidth = sceneWidth*width;
        double textHeight = sceneHeight*height;
        double textX = sceneWidth*x;
        double textY = sceneHeight*y;
        TextField textField = new TextField();
        textField.setText(string);
        textField.setId("parametersTitle");
        textField.setPrefWidth(textWidth);
        textField.setPrefHeight(textHeight);
        textField.setTranslateX(textX);
        textField.setTranslateY(textY);
        textField.setAlignment(Pos.CENTER);
        textField.setDisable(true);
        if (isTitle) {
            textField.setId("settingTitle");
            fontSize = settingList.get(3);
        } else {
            textField.setId("sliderName");
            fontSize = settingList.get(4);
        }
        DoubleProperty fontSizeButton = new SimpleDoubleProperty(16);
        fontSizeButton.bind(pane.widthProperty().add(pane.heightProperty()).divide(fontSize));
        textField.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSizeButton.asString(), "px;"));
        responsiveNode(textField, x, y, width, height);
        pane.getChildren().add(textField);
    }

    public static void slider(@NotNull Slider slider, String string, double x, double y, double width, double height, boolean isTitle) {
        double sceneWidth = pane.getScene().getWidth()*0.01;
        double sceneHeight = pane.getScene().getHeight()*0.01;
        double sliderWidth = sceneWidth*width;
        double sliderHeight = sceneHeight*height;
        double sliderX = sceneWidth*x;
        double sliderY = sceneHeight*y;
        slider.setId("sliders");
        slider.setPrefWidth(sliderWidth);
        slider.setPrefHeight(sliderHeight);
        slider.setTranslateX(sliderX);
        slider.setTranslateY(sliderY);
        text(string, x, y - slider.getPrefHeight()*0.0625, width, height, isTitle);
        responsiveNode(slider, x, y, width, height);
        pane.getChildren().add(slider);
    }

    public static void alert(@NotNull Alert alert, String title, String message, String iconPath) {
        alert.setContentText(null);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.getDialogPane().getStylesheets().add(
                Set.class.getResource("Alert.css").toExternalForm());
        alert.getDialogPane().getStyleClass().add("dialog");
        alert.getDialogPane().setPrefWidth(275);
        ImageView imageView = new ImageView("file:" + mainPath + "/src/Picture/Icones/" + iconPath + ".png");
        imageView.setFitWidth(40);
        imageView.setFitHeight(40);
        alert.getDialogPane().setGraphic(imageView);
    }

    public static void responsiveNode(@NotNull Node node, double x, double y, double width, double height) {
        if (node instanceof Slider) {
            Slider slider = (Slider) node;
            pane.widthProperty().addListener((observable, oldValue, newValue) -> responsiveSlider(slider, x, width, newValue, "x"));
            pane.heightProperty().addListener((observable, oldValue, newValue) -> responsiveSlider(slider, y, height, newValue, "y"));
        } else if (node instanceof TextField) {
            TextField textField = (TextField) node;
            pane.widthProperty().addListener((observable, oldValue, newValue) -> responsiveTextField(textField, x, width, newValue, "x"));
            pane.heightProperty().addListener((observable, oldValue, newValue) -> responsiveTextField(textField, y, height, newValue, "y"));
        }
    }

    private static void responsiveSlider(@NotNull Slider slider, double axis, double proportion, Number newValue, @NotNull String property) {
        double percentage = (double) newValue*0.01;
        if (property.equals("x")) {
            slider.setPrefWidth(percentage * proportion);
            slider.setTranslateX(percentage * axis);
        } else if (property.equals("y")) {
            slider.setPrefHeight(percentage * proportion);
            slider.setTranslateY(percentage * axis);
        }
    }

    private static void responsiveTextField(@NotNull TextField textField, double axis, double proportion, Number newValue, @NotNull String property) {
        double percentage = (double) newValue*0.01;
        if (property.equals("x")) {
            textField.setPrefWidth(percentage * proportion);
            textField.setTranslateX(percentage * axis);
        } else if (property.equals("y")) {
            textField.setPrefHeight(percentage * proportion);
            textField.setTranslateY(percentage * axis);
        }
    }

    /*public Effect effect(int effectIndex) {
        switch (effectIndex) {
            case 0:
                Glow glow = new Glow();
                glow.setLevel(0.8);
                return glow;
            case 1:
                return new BoxBlur();
            case 2:
                Reflection reflection = new Reflection();
                reflection.setBottomOpacity(0.5);
                return reflection;
            case 3:
                return new Shadow();
            case 4:
                return new DropShadow();
            default:
                return null;
        }
    }*/

}