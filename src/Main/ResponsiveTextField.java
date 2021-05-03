package Main;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import org.jetbrains.annotations.NotNull;

import static Main.Main.pane;
import static Main.Window.Welcome.settingList;

public class ResponsiveTextField {

    private TextField textField;
    private double x;
    private double y;
    private double width;
    private double height;

    public TextField getTextField() {
        return this.textField;
    }

    public void setTextField(TextField textField) {
        this.textField = textField;
    }

    public String getText() {
        return this.getTextField().getText();
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

    public ResponsiveTextField(String text, @NotNull ResponsiveSlider responsiveSlider) {
        this.textField = new TextField(text);
        this.x = responsiveSlider.getX();
        this.y = responsiveSlider.getY();
        this.width = responsiveSlider.getWidth();
        this.height = responsiveSlider.getHeight();
        Set.text(responsiveSlider.getResponsiveTextField().getText(), responsiveSlider.getX(), responsiveSlider.getY() - responsiveSlider.getHeight()*0.5, responsiveSlider.getWidth(), responsiveSlider.getHeight(), false);
    }

    public ResponsiveTextField(String text, double x, double y, double width, double height, boolean isTitle) {
        this.textField = new TextField(text);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        //this.set(this.getX(), this.getY(), this.getWidth(), this.getHeight(), isTitle);
        Set.text(this.getText(), this.getX(), this.getY(), this.getWidth(), this.getHeight(), isTitle);
    }

    public void set(double x, double y, double width, double height, boolean isTitle) {
        double fontSize;
        double sceneWidth = pane.getScene().getWidth()*0.01;
        double sceneHeight = pane.getScene().getHeight()*0.01;
        double textWidth = sceneWidth*width;
        double textHeight = sceneHeight*height;
        double textX = sceneWidth*x;
        double textY = sceneHeight*y;
        TextField textField = new TextField();
        textField.setText(this.getText());
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
        this.setResponsive(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        pane.getChildren().add(this.getTextField());
    }

    private void setResponsive(double x, double y, double width, double height) {
        TextField textField = this.getTextField();
        pane.widthProperty().addListener((observable, oldValue, newValue) -> {
            double percentage = (double) newValue*0.01;
            textField.setTranslateX(percentage * x);
            textField.setPrefWidth(percentage * width);
        });
        pane.heightProperty().addListener((observable, oldValue, newValue) -> {
            double percentage = (double) newValue*0.01;
            textField.setTranslateY(percentage * y);
            textField.setPrefHeight(percentage * height);
        });
    }

}
