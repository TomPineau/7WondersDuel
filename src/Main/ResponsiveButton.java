package Main;

import javafx.animation.ScaleTransition;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static Main.Main.pane;

public class ResponsiveButton {

    private Button button;
    private ImageView imageView;
    private double x;
    private double y;
    private double width;
    private double height;

    public Button getButton() {
        return this.button;
    }

    public void setButton(Button button) {
        this.button = button;
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

    public ResponsiveButton(ImageView imageView, double x, double y, double width, double height, String style) {
        this.button = new Button();
        this.imageView = imageView;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.setStyle(style);
        this.set(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }

    public ResponsiveButton(ImageView imageView, double width, double height, String style) {
        this.button = new Button();
        this.imageView = imageView;
        this.width = width;
        this.height = height;
        this.setStyle(style);
        //this.set(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }

    public void set(double x, double y, double width, double height) {
        this.setX(x);
        this.setY(y);
        this.setWidth(width);
        this.setHeight(height);
        Button button = this.getButton();
        ImageView imageView = this.getImageView();
        double sceneWidth = pane.getScene().getWidth()*0.01;
        double sceneHeight = pane.getScene().getHeight()*0.01;
        double buttonX = sceneWidth*x;
        double buttonY = sceneHeight*y;
        double buttonWidth = sceneWidth*width;
        double buttonHeight = sceneHeight*height;
        button.setMinSize(0,0);
        button.setTranslateX(buttonX);
        button.setTranslateY(buttonY);
        button.setPrefWidth(buttonWidth);
        button.setPrefHeight(buttonHeight);
        if (imageView != null) {
            button.setGraphic(imageView);
            imageView.fitWidthProperty().bind(button.widthProperty());
            imageView.fitHeightProperty().bind(button.heightProperty());
        }
        this.setResponsive(x, y, width, height);
        pane.getChildren().add(button);
    }

    public void resetButton(double x, double y, double width, double height) {
        pane.getChildren().removeAll(button);
        this.set(x, y, width, height);
    }

    public void setResponsive(double x, double y, double width, double height) {
        Button button = this.getButton();
        pane.widthProperty().addListener((observable, oldValue, newValue) -> {
            double percentage = (double) newValue*0.01;
            button.setTranslateX(percentage * x);
            button.setPrefWidth(percentage * width);
        });
        pane.heightProperty().addListener((observable, oldValue, newValue) -> {
            double percentage = (double) newValue*0.01;
            button.setTranslateY(percentage * y);
            button.setPrefHeight(percentage * height);
        });
    }

    public void setStyle(String style) {
        DoubleProperty fontSizeButton;
        switch (style) {
            case "ICON":
                this.setId("icons");
                this.setShape("RECTANGLE", 8.75);
                break;
            case "START":
                this.setId(style);
                this.setText("Commencer la partie");
                this.setShape("RECTANGLE", 20);
                fontSizeButton = new SimpleDoubleProperty(20);
                fontSizeButton.bind(button.widthProperty().add(button.heightProperty()).divide(15));
                this.getButton().styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSizeButton.asString(), "px;"));
                this.zoomOnHover(0.2, 1.05);
                break;
            case "SAVE":
                this.setId(style);
                this.setText("Enregistrer les modifications");
                this.setShape("RECTANGLE", 20.0);
                fontSizeButton = new SimpleDoubleProperty(20);
                fontSizeButton.bind(button.widthProperty().add(button.heightProperty()).divide(20));
                this.getButton().styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSizeButton.asString(), "px;"));
                this.zoomOnHover(0.2, 1.05);
                break;
            case "PROGRESSTOKEN":
                this.setId("progress");
                //button.setShape(shape(button, 2, 34.5));
                this.zoomOnHover(0.2, 1.1);
                break;
            case "WONDER":
                this.zoomOnHover(0.2, 1.1);
        }
    }

    public void setShape(String shape, double value) {
        Button button = this.getButton();
        Rectangle rectangle = new Rectangle();
        Circle circle = new Circle();
        switch (shape) {
            case "RECTANGLE": //Commencer la partie - Enregistrer - Icones
                rectangle.setArcHeight(value);
                rectangle.setArcWidth(value);
                rectangle.widthProperty().bind(button.widthProperty());
                rectangle.heightProperty().bind(button.heightProperty());
                button.setShape(rectangle);
                break;
            case "CIRCLE": //ProgressToken
                circle.setRadius(value);
                circle.radiusProperty().bind(button.widthProperty());
                circle.radiusProperty().bind(button.heightProperty());
                button.setMinSize(0, 0);
                button.setMaxSize(2* value, 2* value);
                button.setShape(circle);
                break;
        }
    }

    public void zoomOnHover(double time, double scale) {
        Button button = this.getButton();
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

    public void setOnAction(EventHandler event) {
        this.getButton().setOnAction(event);
    }

    public void setDisable(boolean bool) {
        this.getButton().setDisable(bool);
    }

    public void setVisible(boolean bool) {
        this.getButton().setVisible(bool);
    }

    public void clear() {
        this.setDisable(true);
        this.setVisible(false);
    }

    public void setId(String id) {
        this.getButton().setId(id);
    }

    public void setText(String text) {
        this.getButton().setText(text);
    }

    public void setMinSize(double width, double height) {
        this.getButton().setMinSize(width, height);
    }


}
