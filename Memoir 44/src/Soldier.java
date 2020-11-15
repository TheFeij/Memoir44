import java.util.ArrayList;

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
}
