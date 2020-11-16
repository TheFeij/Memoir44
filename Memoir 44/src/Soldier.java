import java.util.ArrayList;

/**
 * This class represents a soldier group.it is a type of force
 *
 * @author Feij
 * @since 2020.11.16
 * @version 1
 */
public class Soldier extends Force{



    /**
     * A constructor to create a new soldier
     * @param name           name of the soldier
     * @param numberOfUnits  number of units
     * @param movesPerTurn   maximum number of moves a soldier can do
     * @param attackRange    attack range of the soldier
     */
    public Soldier(String name, int numberOfUnits, int movesPerTurn, int attackRange) {
        super(name, numberOfUnits, movesPerTurn, attackRange);
        ArrayList<Integer> defendingDices = new ArrayList<>();
        defendingDices.add(1);
        defendingDices.add(5);
        defendingDices.add(6);
        setDefendingDices(defendingDices);
    }

    /**
     * A method to calculate total number of dices a force is allowed to have
     *
     * @param attackerGround ground type of the attacker
     * @param defenderGround ground type of the defender
     * @param distance       distance between two forces
     * @return total number of dices a force is allowed to have
     */
    protected int calculateTotalNumberOfDices(String attackerGround, String defenderGround, int distance) {
        int numberOfDices = 0;

        switch (distance) {
            case 1:
                numberOfDices += 3;
                break;
            case 2:
                numberOfDices += 2;
                break;
            case 3:
                numberOfDices += 1;
                break;
            default:
                return 0;
        }

        //defenders effects
        switch (defenderGround) {
            case "H":
            case "J":
            case "T":
                numberOfDices--;
                break;
            case "S":
                if (this.getName().contains("AL"))
                    numberOfDices -= 1;
                break;
        }

        return numberOfDices;
    }

}//end class Soldier
