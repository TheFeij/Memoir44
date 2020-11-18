/**
 * this class represents an allied player
 */
public class Allied extends Player{


    /**
     * A constructor to create a new Allied player
     *
     * @param name name of the player
     */
    public Allied(String name) {
        super(name);

        Tank tank1 = new Tank("AL-T1", 3);
        Tank tank2 = new Tank("AL-T2", 3);
        Tank tank3 = new Tank("AL-T3", 3);

        Soldier soldier1 = new Soldier("AL-S1", 4);
        Soldier soldier2 = new Soldier("AL-S2", 4);
        Soldier soldier3 = new Soldier("AL-S3", 4);
        Soldier soldier4 = new Soldier("AL-S4", 4);
        Soldier soldier5 = new Soldier("AL-S5", 4);
        Soldier soldier6 = new Soldier("AL-S6", 4);
        Soldier soldier7 = new Soldier("AL-S7", 4);
        Soldier soldier8 = new Soldier("AL-S8", 4);
        Soldier soldier9 = new Soldier("AL-S9", 4);

        Artillery artillery1 = new Artillery("AL-A1", 2);
        Artillery artillery2 = new Artillery("AL-A2", 2);

        forces.add(tank1);
        forces.add(tank2);
        forces.add(tank3);
        forces.add(soldier1);
        forces.add(soldier2);
        forces.add(soldier3);
        forces.add(soldier4);
        forces.add(soldier5);
        forces.add(soldier6);
        forces.add(soldier7);
        forces.add(soldier8);
        forces.add(soldier9);
        forces.add(artillery1);
        forces.add(artillery2);
    }

}//end class allied
