import java.util.ArrayList;
import java.util.Random;

/**
 * this class represents a force. each force has a number of units
 * forces can attack and move. if they get attacked, number of their
 * units will decrease.
 *
 * @author Feij
 * @since 2020.11.13
 * @version 1.2
 */
abstract public class Force {

    //name of the force
    private String name;
    //number of the units
    private int numberOfUnits;
    //maximum moves a force can travel per a turn
    private int movesPerTurn;
    //attack range of the force
    private int attackRange;
    //shows if the unit is able to attack in this turn
    private boolean canAttack;
    // a list of dices with which this force can be attack
    private ArrayList<Integer> defendingDices;


    /**
     * A constructor to create a new force
     * @param name name of the force
     * @param numberOfUnits number of units
     * @param attackRange attack range of the force
     * @param movesPerTurn maximum number of moves a Force can do
     */
    public Force(String name, int numberOfUnits, int movesPerTurn, int attackRange){

        this.name = name;
        this.movesPerTurn = movesPerTurn;
        this.numberOfUnits = numberOfUnits;
        this.attackRange = attackRange;
        //any force is able to attack each turn in usual circumstances
        this.canAttack = true;
        this.defendingDices = new ArrayList<>();

    }


    /**
     * A method to get name of the force
     * @return name of the force
     */
    protected String getName(){
        return name;
    }

    /**
     * A method to get maximum number of moves a force can do per turn
     * @return maximum number of moves a force can do per turn
     */
    public int getMovesPerTurn(){
        return movesPerTurn;
    }

    /**
     * A method to check if this number of moves is possible
     * for this force at this turn or not
     * @param numberOfMoves number of moves we wish to to
     * @return true if the move is possible
     */
    public boolean checkMoves(int numberOfMoves) {
        return movesPerTurn >= numberOfMoves;
    }

    /**
     * A method to decrease number of units by one
     */
    private void decreaseUnit(){
        numberOfUnits--;
    }

    /**
     * A method to disable a force attack possibility for one turn
     */
    public void disableAttack(){
        canAttack = false;
    }

    /**
     * A method to enable attack possibility of the force at the
     * end of the turn
     */
    public void enableAttack(){
        canAttack = true;
    }

    /**
     * A method to see if the force can attack or not
     * @return true if the force can attack
     */
    public boolean getCanAttack(){
        return canAttack;
    }

    /**
     * A method to get number of units
     * @return number of units
     */
    public int getNumberOfUnits() {
        return numberOfUnits;
    }

    /**
     * A method to generate mentioned number of dices
     * @param numberOfDices number of dices to be generated
     * @return an array containing generated dices
     */
    protected int[] generateDices(int numberOfDices){
        int[] dices = new int[numberOfDices];
        Random random = new Random();

        for(int counter = 0 ; counter < numberOfDices ; counter++)
            dices[counter] = random.nextInt(6) + 1;

        System.out.print("Dices: ");
        for(int counter = 0 ; counter < numberOfDices ; counter++){
            System.out.print(dices[counter] + " ");
        }
        System.out.println();
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
     * @param distance distance between two forces
     * @return total number of dices a force is allowed to have
     */
    protected abstract int calculateTotalNumberOfDices(String attackerGround, String defenderGround,
                                                       int distance);

    /**
     * A method to check if the attack is possible or not
     * @param distance distance between attacker and defender
     * @return true if attack is possible
     */
    protected boolean checkAttackPossibility(int numberOfDices, int distance){
        if(!checkAttackRange(distance))
            return false;

        return numberOfDices >= 1;
    }

    /**
     * A method to check if the force is destroyed or not
     * @return true if the force is destroyed
     */
    public boolean isDestroyed(){
        return numberOfUnits == 0;
    }

    /**
     * A method to get defendingDices
     * @return DefendingDices
     */
    protected ArrayList<Integer> getDefendingDices(){
        return defendingDices;
    }

    /**
     * A method to attack an enemy force
     * @param force the force to be attacked
     * @param attackerGround ground type of the attacker
     * @param defenderGround ground type of the defender
     * @param distance distance between attacker and defender
     * @return 1 if attack was successful. -1 if attack is not possible. 0 if attack was unsuccessful
     */
    public int attack(Force force,String attackerGround, String defenderGround, int distance){
        int numberOfDices = calculateTotalNumberOfDices(attackerGround, defenderGround, distance);

        if(!checkAttackPossibility(numberOfDices, distance))
            return -1; //attack not possible
        if(!checkDices(numberOfDices, force.getDefendingDices()))
            return 0; //attack unsuccessful

        force.decreaseUnit();
        return 1; //attack successful
    }

    /**
     * A method to check dices to see if the attack will be successful or not
     * @param numberOfDices number rof dices
     * @return true if attack is successful
     */
    public boolean checkDices(int numberOfDices, ArrayList<Integer> defendingDices){
            int[] dices = generateDices(numberOfDices);
            for(int dice : dices)
                for(int defendingDice : defendingDices)
                    if(dice == defendingDice)
                        return true;
            return false;
    }

    /**
     * A method to set defending dices
     * @param defendingDices dices with which this force can be attacked
     */
    public void setDefendingDices(ArrayList<Integer> defendingDices){
        this.defendingDices = defendingDices;
    }



}
