import java.util.ArrayList;
import java.util.TreeSet;

public class Player {
    private String name;
    private TreeSet <Card> cardsToPlayWith = new TreeSet<>();
    private ArrayList <Card> cardsOnHand = new ArrayList<>();
    private int chips;
    private int handStrength;

    public Player(String name, int chips) {
        this.name = name;
        this.chips = chips;
    }

    public String getName() {
        return name;
    }

    public int getChips() {
        return chips;
    }

    public void setChips(int chips) {
        this.chips = chips;
    }

    public TreeSet<Card> getCardsToPlayWith() {
        return cardsToPlayWith;
    }

    public ArrayList<Card> getCardsOnHand() {
        return cardsOnHand;
    }

    public void addHandCard (Card card){
        cardsOnHand.add(card);
    }

    public void addAllCards (Card card){
        cardsToPlayWith.add(card);
    }

    @Override
    public String toString() {
        return name + "";
    }

    public int handEvaluation (){ // this method is evaluating each players strength and based on this it determines the winner
        int numberOfPairs = 0;
        boolean threeOfAKind = false;
        boolean fourOfAKind = false;
        int heartSum = 0;
        int spadeSum = 0;
        int diamondSum = 0;
        int clubSum = 0;

        for (Card card: cardsToPlayWith){
            cardsToPlayWith.remove(card);
            if (card.getSymbol() == Symbol.HEART){
                heartSum++;
            }else if (card.getSymbol() == Symbol.SPADE){
                spadeSum++;
            }else if (card.getSymbol() == Symbol.DIAMOND){
                diamondSum++;
            }else if (card.getSymbol() == Symbol.CLUB){
                clubSum++;
            }

            int cardsWithSameStrength = 1;
            int cardsInStraight = 1;
            for (Card otherCard: cardsToPlayWith){
                if (card.getStrength() == otherCard.getStrength()){
                    cardsWithSameStrength++;
                }
                if (cardsWithSameStrength == 2){
                    numberOfPairs++;
                }else if (cardsWithSameStrength == 3){
                    threeOfAKind = true;
                    numberOfPairs--;
                }else if (cardsWithSameStrength == 4){
                    threeOfAKind = false;
                    fourOfAKind = true;
                }else if (card.getStrength() == otherCard.getStrength()-cardsInStraight){
                    cardsInStraight++;
                }else if (card.getStrength() != otherCard.getStrength()-cardsInStraight || cardsInStraight<5){
                    cardsInStraight = 0;
                }
            }
            if (fourOfAKind){
                handStrength = 700 + card.getStrength();
            }else if (threeOfAKind && numberOfPairs > 0){
                handStrength = 600 + card.getStrength();
            }else if (heartSum >= 5 || spadeSum >= 5 || diamondSum >= 5 || clubSum >= 5 || handStrength<500){
                handStrength = 500;
            }else if (cardsInStraight >= 5){
                handStrength = 400 + card.getStrength();
            }else if (threeOfAKind){
                handStrength = 300 + card.getStrength();
            }else if (numberOfPairs == 2){
                handStrength = 200 + card.getStrength();
            }else if (numberOfPairs == 1){
                handStrength = 100 + card.getStrength();
            }else if (handStrength < card.getStrength()){
                handStrength = card.getStrength();
            }
        }
        return handStrength;
    }
}