/**
 * this class represents an axis player
 */
public class Axis extends Player{


    /**
     * A constructor to create a new Axis player
     *
     * @param name name of the player
     */
    public Axis(String name) {
        super(name);

        Tank tank1 = new Tank("AX-T1", 4);
        Tank tank2 = new Tank("AX-T2", 4);
        Tank tank3 = new Tank("AX-T3", 4);
        Tank tank4 = new Tank("AX-T3", 4);
        Tank tank5 = new Tank("AX-T3", 4);
        Tank tank6 = new Tank("AX-T3", 4);

        Soldier soldier1 = new Soldier("AX-S1", 4);
        Soldier soldier2 = new Soldier("AX-S2", 4);
        Soldier soldier3 = new Soldier("AX-S3", 4);
        Soldier soldier4 = new Soldier("AX-S4", 4);
        Soldier soldier5 = new Soldier("AX-S5", 4);
        Soldier soldier6 = new Soldier("AX-S6", 4);
        Soldier soldier7 = new Soldier("AX-S7", 4);

        forces.add(tank1);
        forces.add(tank2);
        forces.add(tank3);
        forces.add(tank4);
        forces.add(tank5);
        forces.add(tank6);
        forces.add(soldier1);
        forces.add(soldier2);
        forces.add(soldier3);
        forces.add(soldier4);
        forces.add(soldier5);
        forces.add(soldier6);
        forces.add(soldier7);

    }

}//end class Axis
