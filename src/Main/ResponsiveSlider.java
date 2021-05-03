package Main;

import javafx.scene.Node;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import static Main.Main.pane;

public class ResponsiveSlider extends ResponsiveObject{

    private Slider slider;
    private ResponsiveTextField responsiveTextField;
    private double min;
    private double max;
    private double value;
    private double x;
    private double y;
    private double width;
    private double height;

    public Slider getSlider() {
        return this.slider;
    }

    public void setSlider(Slider slider) {
        this.slider = slider;
    }

    public ResponsiveTextField getResponsiveTextField() {
        return this.responsiveTextField;
    }

    public void setResponsiveTextField(ResponsiveTextField responsiveTextField) {
        this.responsiveTextField = responsiveTextField;
    }

    public double getMin() {
        return this.min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return this.max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getValue() {
        return this.value;
    }

    public void setValue(double value) {
        this.value = value;
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

    public ResponsiveSlider(double min, double max, double value, String name, double x, double y, double width, double height) {
        this.min = min;
        this.max = max;
        this.value = value;
        this.slider = new Slider(this.getMin(), this.getMax(), this.getValue());
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.responsiveTextField = new ResponsiveTextField(name, this.getX(), this.getY() - this.getHeight()*0.4, this.getWidth(), this.getHeight(), false);
        this.set();
    }

    public void set() {
        Slider slider = this.getSlider();
        double sceneWidth = pane.getScene().getWidth()*0.01;
        double sceneHeight = pane.getScene().getHeight()*0.01;
        double sliderWidth = sceneWidth*this.getWidth();
        double sliderHeight = sceneHeight*this.getHeight();
        double sliderX = sceneWidth*this.getX();
        double sliderY = sceneHeight*this.getY();
        slider.setId("sliders");
        slider.setPrefWidth(sliderWidth);
        slider.setPrefHeight(sliderHeight);
        slider.setTranslateX(sliderX);
        slider.setTranslateY(sliderY);
        this.setResponsive(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        pane.getChildren().add(slider);
    }

    /*public void setResponsive(double x, double y, double width, double height) {
        Slider slider = this.getSlider();
        pane.widthProperty().addListener((observable, oldValue, newValue) -> {
            double percentage = (double) newValue*0.01;
            slider.setTranslateX(percentage * x);
            slider.setPrefWidth(percentage * width);
        });
        pane.heightProperty().addListener((observable, oldValue, newValue) -> {
            double percentage = (double) newValue*0.01;
            slider.setTranslateY(percentage * y);
            slider.setPrefHeight(percentage * height);
        });
    }*/
}
