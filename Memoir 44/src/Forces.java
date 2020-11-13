import java.lang.reflect.Array;

/**
 * this class represents a force. each force has a number of units
 * forces can attack and move. they have location in the map. if they
 * get attacked, number of their units will decrease
 *
 * @author Feij
 * @since 2020.11.13
 * @version 1
 */
public class Forces {

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


    /**
     * A constructor to create a new force
     * @param name name of the force
     * @param numberOfUnits number of units
     * @param location location of the force in map
     * @param attackRange attack range of the force
     */
    public Forces(String name, int numberOfUnits, int movesPerTurn,
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
        if (movesPerTurn >= numberOfMoves)
            return true;
        return false;
    }



}
