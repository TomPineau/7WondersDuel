package Main.Wonder;

import Main.Main;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import Main.ResponsiveButton;

import static Main.Main.mainPath;
import static Main.Main.pane;
import static Main.Window.Play.gameboard;
import static Main.Window.Play.isPlaying;

public class Wonder {

    private String name;
    private ResponsiveButton responsiveButton;
    private boolean isPlayed;
    private String location;
    private int stoneCost;
    private int woodCost;
    private int brickCost;
    private int glassCost;
    private int papyrusCost;
    private boolean doReplay;
    private String doRemoveCard;
    private boolean drawProgressToken;
    private boolean drawCardFromDeck;
    private int shield;
    private int moneyGain;
    private int moneyLost;
    private boolean stoneGain;
    private boolean woodGain;
    private boolean brickGain;
    private boolean glassGain;
    private boolean papyrusGain;
    private int crown;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ResponsiveButton getResponsiveButton() {
        return this.responsiveButton;
    }

    public void setResponsiveButton(ResponsiveButton responsiveButton) {
        this.responsiveButton = responsiveButton;
    }

    public boolean isPlayed() {
        return this.isPlayed;
    }

    public void setPlayed(boolean played) {
        this.isPlayed = played;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getStoneCost() {
        return this.stoneCost;
    }

    public void setStoneCost(int stoneCost) {
        this.stoneCost = stoneCost;
    }

    public int getWoodCost() {
        return this.woodCost;
    }

    public void setWoodCost(int woodCost) {
        this.woodCost = woodCost;
    }

    public int getBrickCost() {
        return this.brickCost;
    }

    public void setBrickCost(int brickCost) {
        this.brickCost = brickCost;
    }

    public int getGlassCost() {
        return this.glassCost;
    }

    public void setGlassCost(int glassCost) {
        this.glassCost = glassCost;
    }

    public int getPapyrusCost() {
        return this.papyrusCost;
    }

    public void setPapyrusCost(int papyrusCost) {
        this.papyrusCost = papyrusCost;
    }

    public boolean isDoReplay() {
        return this.doReplay;
    }

    public void setDoReplay(boolean doReplay) {
        this.doReplay = doReplay;
    }

    public String getDoRemoveCard() {
        return this.doRemoveCard;
    }

    public void setDoRemoveCard(String doRemoveCard) {
        this.doRemoveCard = doRemoveCard;
    }

    public boolean isDrawProgressToken() {
        return this.drawProgressToken;
    }

    public void setDrawProgressToken(boolean drawProgressToken) {
        this.drawProgressToken = drawProgressToken;
    }

    public boolean isDrawCardFromDeck() {
        return this.drawCardFromDeck;
    }

    public void setDrawCardFromDeck(boolean drawCardFromDeck) {
        this.drawCardFromDeck = drawCardFromDeck;
    }

    public int getShield() {
        return this.shield;
    }

    public void setShield(int shield) {
        this.shield = shield;
    }

    public int getMoneyGain() {
        return this.moneyGain;
    }

    public void setMoneyGain(int moneyGain) {
        this.moneyGain = moneyGain;
    }

    public int getMoneyLost() {
        return this.moneyLost;
    }

    public void setMoneyLost(int moneyLost) {
        this.moneyLost = moneyLost;
    }

    public boolean isStoneGain() {
        return this.stoneGain;
    }

    public void setStoneGain(boolean stoneGain) {
        this.stoneGain = stoneGain;
    }

    public boolean isWoodGain() {
        return this.woodGain;
    }

    public void setWoodGain(boolean woodGain) {
        this.woodGain = woodGain;
    }

    public boolean isBrickGain() {
        return this.brickGain;
    }

    public void setBrickGain(boolean brickGain) {
        this.brickGain = brickGain;
    }

    public boolean isGlassGain() {
        return this.glassGain;
    }

    public void setGlassGain(boolean glassGain) {
        this.glassGain = glassGain;
    }

    public boolean isPapyrusGain() {
        return this.papyrusGain;
    }

    public void setPapyrusGain(boolean papyrusGain) {
        this.papyrusGain = papyrusGain;
    }

    public int getCrown() {
        return this.crown;
    }

    public void setCrown(int crown) {
        this.crown = crown;
    }

    public ImageView getImageView() {
        return new ImageView("file:" + mainPath + "/src/Picture/Wonders/" + this.getName() + ".png");
    }

    public double getX() {
        return this.getResponsiveButton().getX();
    }

    public double getY() {
        return this.getResponsiveButton().getY();
    }

    public double getWidth() {
        return this.getResponsiveButton().getWidth();
    }

    public double getHeight() {
        return this.getResponsiveButton().getHeight();
    }

    public Wonder(String name, boolean isPlayed, String location, int stoneCost, int woodCost, int brickCost, int glassCost, int papyrusCost, boolean doReplay, String doRemoveCard, boolean drawProgressToken, boolean drawCardFromDeck, int shield, int moneyGain, int moneyLost, boolean stoneGain, boolean woodGain, boolean brickGain, boolean glassGain, boolean papyrusGain, int crown) {
        double sceneWidth = pane.getScene().getWidth()*0.01;
        double sceneHeight = pane.getScene().getHeight()*0.01;
        this.name = name;
        this.responsiveButton = new ResponsiveButton(this.getImageView(), (sceneWidth/10)*10, (sceneHeight/10)*10*1.5333, "PROGRESSTOKEN");
        this.isPlayed = isPlayed;
        this.location = location;
        this.stoneCost = stoneCost;
        this.woodCost = woodCost;
        this.brickCost = brickCost;
        this.glassCost = glassCost;
        this.papyrusCost = papyrusCost;
        this.doReplay = doReplay;
        this.doRemoveCard = doRemoveCard;
        this.drawProgressToken = drawProgressToken;
        this.drawCardFromDeck = drawCardFromDeck;
        this.shield = shield;
        this.moneyGain = moneyGain;
        this.moneyLost = moneyLost;
        this.stoneGain = stoneGain;
        this.woodGain = woodGain;
        this.brickGain = brickGain;
        this.glassGain = glassGain;
        this.papyrusGain = papyrusGain;
        this.crown = crown;
        this.set(50, 50, this.getWidth(), this.getHeight());
    }

    private void set(double x, double y, double width, double height) {
        if (isPlaying) {
            this.getResponsiveButton().set(x, y, width, height);
        }
    }

    public void print() {
        System.out.println("|name : " + this.getName()
                + "| |isPlayed : " + this.isPlayed()
                + "| |location : " + this.getLocation()
                + "| |stoneCost : " + this.getStoneCost()
                + "| |woodCost : " + this.getWoodCost()
                + "| |brickCost : " + this.getBrickCost()
                + "| |glassCost : " + this.getGlassCost()
                + "| |papyrusCost : " + this.getPapyrusCost()
                + "| |doReplay : " + this.isDoReplay()
                + "| |doRemoveCard : " + this.getDoRemoveCard()
                + "| |drawProgressToken : " + this.isDrawProgressToken()
                + "| |drawCardFromDeck : " + this.isDrawCardFromDeck()
                + "| |shield : " + this.getShield()
                + "| |moneyGain : " + this.getMoneyGain()
                + "| |moneyLost : " + this.getMoneyLost()
                + "| |stoneGain : " + this.isStoneGain()
                + "| |woodGain : " + this.isWoodGain()
                + "| |brickGain : " + this.isBrickGain()
                + "| |glassGain : " + this.isGlassGain()
                + "| |papyrusGain : " + this.isPapyrusGain()
                + "| |crown : " + this.getCrown());
    }

}
