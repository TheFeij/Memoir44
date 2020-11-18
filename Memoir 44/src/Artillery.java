import java.util.ArrayList;

/**
 * This class represents an Artillery.it is a type of force
 *
 * @author Feij
 * @since 2020.11.16
 * @version 1
 */
public class Artillery extends Force{

    /**
     * A constructor to create a new Artillery
     * @param name           name of the Artillery
     * @param numberOfUnits  number of units
     */
    public Artillery(String name, int numberOfUnits) {
        super(name, numberOfUnits, 1, 6);
        ArrayList<Integer> defendingDices = new ArrayList<>();
        defendingDices.add(5);
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
            case 2:
                numberOfDices += 3;
                break;
            case 3:
            case 4:
                numberOfDices += 2;
                break;
            case 5:
            case 6:
                numberOfDices += 1;
                break;
            default:
                return 0;
        }

        return numberOfDices;
    }


}//end class Artillery
