import java.util.ArrayList;
import java.util.Random;
public class Deck {
    private ArrayList<Card> deckOfCards;
    private Random r = new Random();
    public Deck() { // here is create the deck of cards
        Symbol[] symbols = Symbol.values();
        Value[] values = Value.values();
        deckOfCards = new ArrayList<>();

        for (Symbol symbol: symbols){
            int s = 1;
            for (Value value: values){
                s++;
                deckOfCards.add(new Card(symbol, value, s));
            }
        }
    }
    public ArrayList<Card> getDeckOfCards() {
        return deckOfCards;
    }

    public void shufleCards (){ // this method suffles cards in deck
        for (int i = 0; i<deckOfCards.size(); i++){
            int j = r.nextInt(deckOfCards.size());
            Card card1 = deckOfCards.get(i);
            Card card2 = deckOfCards.get(j);
            deckOfCards.set(i, card2);
            deckOfCards.set(j, card1);
        }
    }

    @Override
    public String toString() {
        return "" + deckOfCards;
    }
}
