import java.util.ArrayList;

public class Table {
    private ArrayList <Card> cardsOnTable = new ArrayList<>();
    private int pot;
    private int highestBid;
    private Player highestBidder;

    public int getHighestBid() {
        return highestBid;
    }

    public void setHighestBid(int highestBid) {
        this.highestBid = highestBid;
    }

    public Player getHighestBidder() {
        return highestBidder;
    }

    public void setHighestBidder(Player highestBidder) {
        this.highestBidder = highestBidder;
    }

    public void setPot(int pot) {
        this.pot = pot;
    }

    public int getPot() {
        return pot;
    }

    public ArrayList<Card> getCardsOnTable() {
        return cardsOnTable;
    }

    public void addCard (Card card){
        cardsOnTable.add(card);
    }
}
