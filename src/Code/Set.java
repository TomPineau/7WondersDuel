package Code;

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

import static Code.Main.pane;
import static Code.Page.Welcome.mainPath;
import static Code.Page.Welcome.settingList;

public class Set {

    public static void image(@NotNull ImageView imageView) {
        imageView.fitWidthProperty().bind(pane.widthProperty());
        imageView.fitHeightProperty().bind(pane.heightProperty());
        imageView.setPreserveRatio(false);
        pane.getChildren().add(imageView);
    }

    public static void button(@NotNull Button button, ImageView imageView, double x, double y, double widthProportion, double heightProportion, int styleIndex) {
        double sceneWidth = pane.getScene().getWidth()*0.01;
        double sceneHeight = pane.getScene().getHeight()*0.01;
        double buttonWidth = sceneWidth*widthProportion;
        double buttonHeight = sceneHeight*heightProportion;
        double buttonX = sceneWidth*x;
        double buttonY = sceneHeight*y;
        buttonStyle(button, styleIndex);
        button.setMinSize(0,0);
        button.setPrefWidth(buttonWidth);
        button.setPrefHeight(buttonHeight);
        button.setTranslateX(buttonX);
        button.setTranslateY(buttonY);
        if (imageView != null) {
            button.setGraphic(imageView);
            imageView.fitWidthProperty().bind(button.widthProperty());
            imageView.fitHeightProperty().bind(button.heightProperty());
        }
        responsiveNode(button, x, y, widthProportion, heightProportion);
        pane.getChildren().add(button);
    }

    public static void text(String string, double x, double y, double widthProportion, double heightProportion, boolean isTitle) {
        double fontSize;
        //ArrayList<Double> widthHeightList = widthHeightList(x, y, widthProportion, heightProportion);
        double sceneWidth = pane.getScene().getWidth()*0.01;
        double sceneHeight = pane.getScene().getHeight()*0.01;
        double textWidth = sceneWidth*widthProportion;
        double textHeight = sceneHeight*heightProportion;
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
        textField.setDisable(true);
        responsiveNode(textField, x, y, widthProportion, heightProportion);
        pane.getChildren().add(textField);
    }

    public static void slider(@NotNull Slider slider, String string, double x, double y, double widthProportion, double heightProportion, boolean isTitle) {
        //ArrayList<Double> widthHeightList = widthHeightList(x, y, widthProportion, heightProportion);
        double sceneWidth = pane.getScene().getWidth()*0.01;
        double sceneHeight = pane.getScene().getHeight()*0.01;
        double sliderWidth = sceneWidth*widthProportion;
        double sliderHeight = sceneHeight*heightProportion;
        double sliderX = sceneWidth*x;
        double sliderY = sceneHeight*y;
        slider.setId("sliders");
        slider.setPrefWidth(sliderWidth);
        slider.setPrefHeight(sliderHeight);
        slider.setTranslateX(sliderX);
        slider.setTranslateY(sliderY);
        text(string, x, y - slider.getPrefHeight()*0.0625, widthProportion, heightProportion, isTitle);
        responsiveNode(slider, x, y, widthProportion, heightProportion);
        pane.getChildren().add(slider);
    }

    @Nullable
    private static Shape shape(Button button, int index, double value) {
        Rectangle rectangle = new Rectangle();
        Circle circle = new Circle();
        switch (index) {
            case 1: //Commencer la partie - Enregistrer - Icones
                rectangle.setArcWidth(value);
                rectangle.setArcHeight(value);
                rectangle.widthProperty().bind(button.widthProperty());
                rectangle.heightProperty().bind(button.heightProperty());
                return rectangle;
            case 2: //Progress
                circle.setRadius(value);
                circle.radiusProperty().bind(button.widthProperty());
                circle.radiusProperty().bind(button.heightProperty());
                button.setMinSize(0, 0);
                button.setMaxSize(2* value, 2* value);
                return circle;
        }
        return null;
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

    private static void buttonStyle(Button button, int styleIndex) {
        DoubleProperty fontSizeButton;
        switch (styleIndex) {
            case 0:
                button.setId("icons");
                button.setShape(shape(button, 1, 8.75));
                break;
            case 1:
                button.setId("beginning");
                button.setText("Commencer la partie");
                button.setShape(shape(button, 1, 20.0));
                fontSizeButton = new SimpleDoubleProperty(20);
                fontSizeButton.bind(button.widthProperty().add(button.heightProperty()).divide(15));
                button.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSizeButton.asString(), "px;"));
                buttonZoomOnHover(button, 0.2, 1.05);
                break;
            case 2:
                button.setId("save");
                button.setText("Enregistrer les modifications");
                button.setShape(shape(button, 1, 20.0));
                fontSizeButton = new SimpleDoubleProperty(20);
                fontSizeButton.bind(button.widthProperty().add(button.heightProperty()).divide(20));
                button.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSizeButton.asString(), "px;"));
                buttonZoomOnHover(button, 0.2, 1.05);
                break;
            case 3:
                button.setId("progress");
                button.setShape(shape(button, 2, 34.5));
                buttonZoomOnHover(button, 0.2, 1.1);
        }
    }

    private static void buttonZoomOnHover(Button button, double time, double scale) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(time), button);
        scaleTransition.setFromX(1.0);
        scaleTransition.setFromY(1.0);
        scaleTransition.setToX(scale);
        scaleTransition.setToY(scale);
        button.setOnMouseEntered(event -> {
            scaleTransition.stop();
            scaleTransition.setRate(1.0);
            scaleTransition.play();
        });
        button.setOnMouseExited(event -> {
            scaleTransition.stop();
            scaleTransition.setRate(-1.0);
            scaleTransition.play();
        });
    }

    private static void responsiveNode(@NotNull Node node, double x, double y, double widthProportion, double heightProportion) {
        if (node instanceof Button) {
            Button button = (Button) node;
            pane.widthProperty().addListener((observable, oldValue, newValue) -> responsiveButton(button, x, widthProportion, newValue, "x"));
            pane.heightProperty().addListener((observable, oldValue, newValue) -> responsiveButton(button, y, heightProportion, newValue, "y"));
        } else if (node instanceof Slider) {
            Slider slider = (Slider) node;
            pane.widthProperty().addListener((observable, oldValue, newValue) -> responsiveSlider(slider, x, widthProportion, newValue, "x"));
            pane.heightProperty().addListener((observable, oldValue, newValue) -> responsiveSlider(slider, y, heightProportion, newValue, "y"));
        } else if (node instanceof TextField) {
            TextField textField = (TextField) node;
            pane.widthProperty().addListener((observable, oldValue, newValue) -> responsiveTextField(textField, x, widthProportion, newValue, "x"));
            pane.heightProperty().addListener((observable, oldValue, newValue) -> responsiveTextField(textField, y, heightProportion, newValue, "y"));
        }
    }

    private static void responsiveButton(@NotNull Button button, double axis, double proportion, Number newValue, @NotNull String property) {
        double percentage = (double) newValue*0.01;
        if (property.equals("x")) {
            button.setPrefWidth(percentage * proportion);
            button.setTranslateX(percentage * axis);
        } else if (property.equals("y")) {
            button.setPrefHeight(percentage * proportion);
            button.setTranslateY(percentage * axis);
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

//    private static ArrayList<Double> widthHeightList(double x, double y, double widthProportion, double heightProportion) {
//        double sceneWidth = pane.getScene().getWidth()*0.01;
//        double sceneHeight = pane.getScene().getHeight()*0.01;
//        double nodeWidth = sceneWidth*widthProportion;
//        double nodeHeight = sceneHeight*heightProportion;
//        double nodeX = sceneWidth*x;
//        double nodeY = sceneHeight*y;
//        widthHeightList.set(0, nodeWidth);
//        widthHeightList.set(1, nodeHeight);
//        widthHeightList.set(2, nodeX);
//        widthHeightList.set(3, nodeY);
//        return widthHeightList;
//    }



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
