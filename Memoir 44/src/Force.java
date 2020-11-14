import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

/**
 * this class represents a force. each force has a number of units
 * forces can attack and move. they have location in the map. if they
 * get attacked, number of their units will decrease
 *
 * @author Feij
 * @since 2020.11.13
 * @version 1
 */
abstract public class Force {

    //name of the force
    String name;
    //number of the units
    int numberOfUnits;
    //maximum moves a force can travel per a turn
    int movesPerTurn;
    //attack range of the force
    int attackRange;
    //shows if the unit is able to attack in this turn
    boolean canAttack;
    // a list of dices with which this force can be attack
    ArrayList<Integer> defendingDices;


    /**
     * A constructor to create a new force
     * @param name name of the force
     * @param numberOfUnits number of units
     * @param location location of the force in map
     * @param attackRange attack range of the force
     */
    public Force(String name, int numberOfUnits, int movesPerTurn,
                  String location, String groundType, int attackRange){

        this.name = name;
        this.movesPerTurn = movesPerTurn;
        this.numberOfUnits = numberOfUnits;
        this.attackRange = attackRange;
        //any force is able to attack each turn in usual circumstances
        this.canAttack = true;

    }


    /**
     * A method to check if this number of moves is possible
     * for this force at this turn or not
     * @param numberOfMoves number of moves we wish to to
     * @return true if the move is possible
     */
    public boolean chechMoves(int numberOfMoves) {
        return movesPerTurn >= numberOfMoves;
    }

    /**
     * A method to decrease number of units by one
     */
    private void decreaseUnit(){
        numberOfUnits--;
    }

    /**
     * A method to generate mentioned number of dices
     * @param numberOfDices number of dices to be generated
     * @return an array containing generated dices
     */
    private int[] generateDices(int numberOfDices){
        int[] dices = new int[numberOfDices];
        Random random = new Random();

        for(int counter = 0 ; counter < numberOfDices ; counter++)
            dices[counter] = random.nextInt(6) + 1;

        return dices;
    }

    /**
     * A method to check if attack range of the force
     * can reach enemy or not
     * @param distance distance between enemy and attacker
     * @return true if attack range can each enemy
     */
    private boolean checkAttackRange(int distance){
        return attackRange >= distance;
    }

    /**
     * A method to calculate total number of dices a force is allowed to have
     * @param attackerGround ground type of the attacker
     * @param defenderGround ground type of the defender
     * @return total number of dices a force is allowed to have
     */
    protected abstract int calculateTotalNumberOfDices(String attackerGround, String defenderGround);

    /**
     * A method to check if the attack is possible or not
     * @param numberOfDices number of dices this force can have
     * @param distance distance between attacker and defender
     * @return true if attack is possible
     */
    protected boolean checkAttackpossibility(int numberOfDices, int distance){
        return numberOfDices > 0 && checkAttackRange(distance) && canAttack;
    }

    /**
     * A method to attack an enemy force
     * @param force the force to be attacked
     */
    protected abstract void attack(Force force, int distance, String attackerGround,
                                   String defenderGround);

}
