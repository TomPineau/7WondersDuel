package Main;

import javafx.scene.image.ImageView;
import org.jetbrains.annotations.NotNull;

import static Main.Main.pane;

public class ResponsiveImageView {

    private ImageView imageView;
    private double x;
    private double y;
    private double width;
    private double height;

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

    public ResponsiveImageView(@NotNull ImageView imageView, String Id) {
        this.imageView = imageView;
        this.imageView.fitWidthProperty().bind(pane.widthProperty());
        this.imageView.fitHeightProperty().bind(pane.heightProperty());
        this.imageView.setPreserveRatio(false);
        this.imageView.setId(Id);
        pane.getChildren().add(this.getImageView());
    }

    public ResponsiveImageView(ImageView imageView, double x, double y, double width, double height) {
        this.imageView = imageView;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void set(double x, double y, double width, double height) {
        ImageView imageView = this.getImageView();
        double sceneWidth = pane.getScene().getWidth()*0.01;
        double sceneHeight = pane.getScene().getHeight()*0.01;
        double imageWidth = sceneWidth*width;
        double imageHeight = sceneHeight*height;
        double imageX = sceneWidth*x;
        double imageY = sceneHeight*y;
        imageView.setFitWidth(imageWidth);
        imageView.setFitHeight(imageHeight);
        imageView.setTranslateX(imageX);
        imageView.setTranslateY(imageY);
        imageView.setPreserveRatio(false);
        this.setResponsive( x, y, width, height);
        pane.getChildren().add(imageView);
    }

    public void reset(double x, double y, double width, double height) {
        pane.getChildren().removeAll(imageView);
        this.set(x, y, width, height);
    }

    private void setResponsive(double x, double y, double width, double height) {
        ImageView imageView = this.getImageView();
        pane.widthProperty().addListener((observable, oldValue, newValue) -> {
            double percentage = (double) newValue*0.01;
            imageView.setTranslateX(percentage * x);
            imageView.setFitWidth(percentage * width);
        });
        pane.heightProperty().addListener((observable, oldValue, newValue) -> {
            double percentage = (double) newValue*0.01;
            imageView.setTranslateY(percentage * y);
            imageView.setFitHeight(percentage * height);
        });
    }

}
