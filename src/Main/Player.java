package Main;

import Main.Cards.CardList;
import Main.Gameboard.ProgressTokenList;

import java.util.ArrayList;

public class Player {

    private String name;
    private String side;
    private int money;
    private boolean isYourTurn;
    private boolean canPlayerTakeProgressTokenFromGameboard;
    private CardList cardList;
    private ProgressTokenList progressTokenList;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSide() {
        return this.side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public int getMoney() {
        return this.money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public boolean isYourTurn() {
        return this.isYourTurn;
    }

    public void setYourTurn(boolean yourTurn) {
        this.isYourTurn = yourTurn;
    }

    public boolean isCanPlayerTakeProgressTokenFromGameboard() {
        return this.canPlayerTakeProgressTokenFromGameboard;
    }

    public void setCanPlayerTakeProgressTokenFromGameboard(boolean canPlayerTakeProgressTokenFromGameboard) {
        this.canPlayerTakeProgressTokenFromGameboard = canPlayerTakeProgressTokenFromGameboard;
    }

    public CardList getCardList() {
        return this.cardList;
    }

    public void setCardList(CardList cardList) {
        this.cardList = cardList;
    }

    public ProgressTokenList getProgressTokenList() {
        return this.progressTokenList;
    }

    public void setProgressTokenList(ProgressTokenList progressTokenList) {
        this.progressTokenList = progressTokenList;
    }

    public Player(String name) {
        this.name = name;
    }


}
