package Main.Cards;

import java.awt.*;
import java.util.ArrayList;

public class Card{

    private String name;
    private Color color;
    private ArrayList<Object> cost;
    private ArrayList<Object> profit;
    private boolean isLinkedTo;
    private int linkNumber;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public ArrayList<Object> getCost() {
        return this.cost;
    }

    public void setCost(ArrayList<Object> cost) {
        this.cost = cost;
    }

    public ArrayList<Object> getProfit() {
        return this.profit;
    }

    public void setProfit(ArrayList<Object> profit) {
        this.profit = profit;
    }

    public boolean isLinkedTo() {
        return this.isLinkedTo;
    }

    public void setLinkedTo(boolean linkedTo) {
        isLinkedTo = linkedTo;
    }

    public int getLinkNumber() {
        return this.linkNumber;
    }

    public void setLinkNumber(int linkNumber) {
        this.linkNumber = linkNumber;
    }

    public Card(String name, Color color, ArrayList<Object> cost, ArrayList<Object> profit, boolean isLinkedTo, int linkNumber) {
        this.name = name;
        this.color = color;
        this.cost = cost;
        this.profit = profit;
        this.isLinkedTo = isLinkedTo;
        this.linkNumber = linkNumber;
    }

    public void test() {
        System.out.println("hey");
    }

}