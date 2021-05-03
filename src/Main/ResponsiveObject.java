package Main;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import static Main.Main.pane;

public class ResponsiveObject {

    private Node node;
    private double x;
    private double y;
    private double width;
    private double height;

    public Node getNode() {
        return node;
    }

    public void setResponsive(double x, double y, double width, double height) {
        Node node = this.getNode();
        System.out.println(node.getClass());
        if (true){//node instanceof Slider || node instanceof TextField || node instanceof Button) {
            pane.widthProperty().addListener((observable, oldValue, newValue) -> {
                double percentage = (double) newValue * 0.01;
                node.setTranslateX(percentage * x);
                ((Control) node).setPrefWidth(percentage * width);
            });
            pane.heightProperty().addListener((observable, oldValue, newValue) -> {
                double percentage = (double) newValue * 0.01;
                node.setTranslateY(percentage * y);
                ((Control) node).setPrefHeight(percentage * height);
            });
        }
    }

}
