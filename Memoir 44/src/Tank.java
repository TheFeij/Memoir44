import java.util.ArrayList;

/**
 * This class represents a tank group.it is a type of force
 *
 * @author Feij
 * @since 2020.11.16
 * @version 1
 */
public class Tank extends Force{


    /**
     * A constructor to create a new tank
     * @param name           name of the tank
     * @param numberOfUnits  number of units
     */
    public Tank(String name, int numberOfUnits) {
        super(name, numberOfUnits, 3, 3);
        ArrayList<Integer> defendingDices = new ArrayList<>();
        defendingDices.add(2);
        defendingDices.add(5);
        setDefendingDices(defendingDices);
    }

    /**
     * A method to calculate total number of dices a force is allowed to have
     *
     * @param attackerGround ground type of the attacker
     * @param defenderGround ground type of the defender
     * @param distance distance between two forces
     * @return total number of dices a force is allowed to have
     */
    protected int calculateTotalNumberOfDices(String attackerGround, String defenderGround,
                                              int distance) {
        int numberOfDices = 3;

        //defender effects
        switch (defenderGround) {
            case "H":
                numberOfDices--;
                break;
            case "J":
                numberOfDices -= 2;
                break;
            case "T":
                numberOfDices -= 2;
                break;
            case "S":
                if (this.getName().contains("AL"))
                    numberOfDices -= 2;
                break;
        }

        //attacker effects
        if(attackerGround.equals("T"))
            numberOfDices -= 2;

        return numberOfDices;
    }
    

}
