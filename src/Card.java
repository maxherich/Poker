public class Card implements Comparable<Card>{
    private Symbol symbol;
    private Value value;
    private int strength;

    public Card(Symbol symbol, Value value, int strength) {
        this.symbol = symbol;
        this.value = value;
        this.strength = strength;
    }

    public int getStrength() {
        return strength;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public Value getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value + "of" + symbol;
    }

    public String getImageOfCard (){
        return "./Cards/" + toString() + ".png";
    }


    @Override
    public int compareTo(Card o) {
        if (this.strength>=o.strength){
            return 1;
        }
        return -1;
    }
}
