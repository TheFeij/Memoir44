import java.util.ArrayList;
import java.util.Collections;

/**
 * this class represents a bank of cards which holds unused cards and
 * used cards. it can shuffle and give cards
 */
public class Cards {

    //list of unused cards
    private ArrayList<String> unusedCards;
    //list of used cards
    private ArrayList<String> usedCards;



    /**
     * A constructor to create a bank of cards
     */
    public Cards(){
        unusedCards = new ArrayList<>();
        usedCards = new ArrayList<>();

        for(int counter = 0 ; counter < 6 ; counter++)
            unusedCards.add("order 1 unit");
        for(int counter = 0 ; counter < 13 ; counter++)
            unusedCards.add("order 2 unit");
        for(int counter = 0 ; counter < 10 ; counter++)
            unusedCards.add("order 3 unit");
        for(int counter = 0 ; counter < 6 ; counter++)
            unusedCards.add("order 4 unit");
        for(int counter = 0 ; counter < 5 ; counter++)
            unusedCards.add("order 3 unit same type");

        Collections.shuffle(unusedCards);
    }


    /**
     * A method to get a card from bank of cards
     * if there is no unused card left, we pick the used cards
     * then we shuffle them and use them as unused cards
     *
     * @return first card in the bank of cards
     */
    public String getCard(){
        if(unusedCards.size() == 0){
            unusedCards = usedCards;
            Collections.shuffle(unusedCards);
            usedCards.clear();
        }
        String card = unusedCards.get(0);
        usedCards.add(card);
        unusedCards.remove(0);
        return card;

    }

}
