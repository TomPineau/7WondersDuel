package Main.Cards;

import org.jetbrains.annotations.Contract;

import java.util.ArrayList;

public class CardList extends ArrayList{

    private ArrayList<Card> cardList;

    public ArrayList<Card> getCardList() {
        return this.cardList;
    }

    public void setCardList(ArrayList<Card> cardList) {
        this.cardList = cardList;
    }

    public CardList(ArrayList<Card> cardList) {
        this.cardList = cardList;
    }
}
