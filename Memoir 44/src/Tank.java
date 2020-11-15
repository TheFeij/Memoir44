import java.util.ArrayList;

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
        if(defenderGround.equals("H"))
            numberOfDices--;
        else if(defenderGround.equals("J"))
            numberOfDices -= 2;
        else if(defenderGround.equals("T"))
            numberOfDices -= 2;
        else if(defenderGround.equals("S"))
            if(this.getName().contains("AL"))
                numberOfDices -= 2;

        //attacker effects
        if(attackerGround.equals("T"))
            numberOfDices -= 2;

        return numberOfDices;
    }
    

}
