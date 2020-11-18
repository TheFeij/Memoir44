import java.util.ArrayList;
import java.util.Iterator;

/**
 *This class represents a player, it has cards, forces and medals
 *
 * @author Feij
 * @since 2020.11.15
 * @version 1
 */
public class Player {

    //name of the player
    private String name;
    //number of player's medals
    private int medals;
    //a list of player's cards
    private ArrayList<String> cards;
    //a list of player's forces
    protected ArrayList<Force> forces;


    /**
     * A constructor to create a new player
     * @param name name of the player
     */
    public Player(String name){
        this.name = name;
        this.medals = 0;
        this.cards = new ArrayList<>();
        this.forces = new ArrayList<>();
        //to be completed
    }


    /**
     * A method to get players name
     * @return name of the player
     */
    public String getName(){
        return name;
    }

    /**
     * A method to print all cards of the player in a suitable way
     */
    public void displayCards(){
        int numberOfCards = cards.size();
        for(int counter = 0 ; counter < numberOfCards ; counter++)
            System.out.print("|$$$$$$$$$$$$$$$$$$$$$$$$$| ");
        System.out.println();
        for(int counter = 0 ; counter < numberOfCards ; counter++)
            System.out.print("|                         | ");
        System.out.println();
        for(int counter = 0 ; counter < numberOfCards ; counter++){
            String card = cards.get(counter);
            int length = card.length();
            int preScapes = (25 - length)/2;
            int postScapes = (25 - length) - preScapes;
            System.out.print("|");
            for(int i = 0 ; i < preScapes ; i++)
                System.out.print(" ");
            System.out.print(card);
            for(int i = 0 ; i < postScapes ; i++)
                System.out.print(" ");
            System.out.print("| ");
        }
        System.out.println();
        for(int counter = 0 ; counter < numberOfCards ; counter++)
            System.out.print("|                         | ");
        System.out.println();
        for(int counter = 0 ; counter < numberOfCards ; counter++)
            System.out.print("|$$$$$$$$$$$$$$$$$$$$$$$$$| ");
        System.out.println();
        System.out.println();
    }

    /**
     * A method to check if the player has the mentioned card or not
     * @param card the card to be checked
     * @return true if the player has the card
     */
    private boolean checkCard(String card){
        for(String crd : cards)
            if(crd.equals(card))
                return true;
        return false;
    }

    /**
     * A method to use the mentioned card
     * @param card the card to be used
     * @return true if the card used successfully and false if the card
     * doesnt exists
     */
    public boolean useACard(String card){
        if(!checkCard(card))
            return false;
        cards.remove(card);
        return true;
    }

    /**
     * A method to add a card
     * @param card the card to be added
     */
    public void addCard(String card){
        cards.add(card);
    }

    /**
     * A method to get a force by its name
     * @param name name of the force we want to get
     * @return the force we want or null if the force doesnt exist
     */
    public Force getAForce(String name){
        for(Force force : forces)
            if(force.getName().equals(name))
                return force;

        return null;
    }

    /**
     * A method to remove destroyed forces and get number of casualties
     * @return number of casualties
     */
    public int removeCasualties(){
        int numberOfCasualties = 0;
        Iterator<Force> iterator = forces.iterator();
        while(iterator.hasNext()){
            Force force = iterator.next();
            if(force.isDestroyed()){
                numberOfCasualties++;
                iterator.remove();
            }
        }
        return numberOfCasualties;
    }


    /**
     * A method to increase number of medals
     * @param numberOfIncrement the amount of increment
     */
    public void increaseMedals(int numberOfIncrement){
        medals += numberOfIncrement;
    }


}
