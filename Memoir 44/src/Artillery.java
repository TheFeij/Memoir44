import java.util.ArrayList;

public class Artillery extends Force{




    /**
     * A constructor to create a new Artillery
     * @param name           name of the Artillery
     * @param numberOfUnits  number of units
     * @param movesPerTurn   maximum number of moves an Artillery can do
     * @param attackRange    attack range of the Artillery
     */
    public Artillery(String name, int numberOfUnits, int movesPerTurn, int attackRange) {
        super(name, numberOfUnits, movesPerTurn, attackRange);
        ArrayList<Integer> defendingDices = new ArrayList<>();
        defendingDices.add(5);
        setDefendingDices(defendingDices);
    }



}
