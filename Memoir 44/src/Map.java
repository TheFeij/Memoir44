import java.util.ArrayList;

/**
 * this class represents a 9X13 map with hexagon houses.each house has
 * a ground type and can have a force in it. each house can have only one
 * force at a time.
 *
 * @author Feij
 * @since 2020.11.16
 * @version 0.8
 */
public class Map {

    //a 3d array to keep map information, 1st dimension is for the rows
    //2nd dimension is for columns and the 3rd dimension is to keep information
    //of that house in a map(name of the force on that house if there is any and
    // type of the house ground)
    private String[][][] map;


    /**
     * A constructor to create a new map
     */
    public Map(){
        map = new String[9][13][2];
        //1st row
        map[0][0][0] = "H";
        map[0][1][0] = "H";
        map[0][2][0] = "N";
        map[0][3][0] = "T";
        map[0][4][0] = "B";
        map[0][5][0] = "N";
        map[0][6][0] = "N";
        map[0][7][0] = "N";
        map[0][8][0] = "N";
        map[0][9][0] = "N";
        map[0][10][0] = "N";
        map[0][11][0] = "N";
        map[0][12][0] = "N";
        ///////////////////
        //2nd row
        map[1][0][0] = "N";
        map[1][1][0] = "R";
        map[1][2][0] = "R";
        map[1][3][0] = "R";
        map[1][4][0] = "S";
        map[1][5][0] = "N";
        map[1][6][0] = "H";
        map[1][7][0] = "N";
        map[1][8][0] = "N";
        map[1][9][0] = "N";
        map[1][10][0] = "N";
        map[1][11][0] = "N";
        ///////////////////
        //TO BE COMPLETED


    }


    /**
     * A method to find the fastest location to reach a destination
     * @param location the location where we are
     * @param destination the location we want to reach
     * @return location of the fastest move
     */
    private int[] fastestMove(int[] location, int[] destination){
        int i1 = location[0];
        int j1 = location[1];
        int i2 = destination[0];
        int j2 = destination[1];

        if(i1 > i2){

            if(j2 > j1){
                int[] temp = moveOneDirection(location, "UR");
                i1 = temp[0];
                j1 = temp[1];
            }
            else if(j2 < j1){
                int[] temp = moveOneDirection(location, "UL");
                i1 = temp[0];
                j1 = temp[1];
            }
            else{
                if(i1 - i2 == 1){
                    i1 = i2;
                    j1 = j2;
                }
                else{
                    int[] temp = moveOneDirection(location, "UR");

                    if(temp[0] == location[0] && temp[1] == location[1]){
                        temp = moveOneDirection(location, "UL");
                        i1 = temp[0];
                        j1 = temp[1];
                    }
                    else{
                        i1 = temp[0];
                        j1 = temp[1];
                    }

                }

            }

        }
        else if(i1 < i2){
            if(j2 > j1){
                int[] temp = moveOneDirection(location, "DR");
                i1 = temp[0];
                j1 = temp[1];
            }
            else if(j2 < j1){
                int[] temp = moveOneDirection(location, "DL");
                i1 = temp[0];
                j1 = temp[1];
            }
            else{
                if(i2 - i1 == 1){
                    i1 = i2;
                    j1 = j2;
                }
                else{
                    int[] temp = moveOneDirection(location, "DR");

                    if(temp[0] == location[0] && temp[1] == location[1]){
                        temp = moveOneDirection(location, "DL");
                        i1 = temp[0];
                        j1 = temp[1];
                    }
                    else{
                        i1 = temp[0];
                        j1 = temp[1];
                    }


                }
            }


        }
        else{
            if(j2 > j1)
                j1++;
            else
                j1--;
        }

        int[] newLocation = new int[2];
        newLocation[0] = i1;
        newLocation[1] = j1;
        return newLocation;

    }

    /**
     * A method to move one house in the given direction
     * directions are UR, UL, DR, DL, R, L.
     * @param location the location where we are
     * @param direction the direction of the move
     * @return destination location
     */
    private int[] moveOneDirection(int[] location, String direction){
        int i = location[0];
        int j = location[1];

        switch (direction) {
            case "R":
                j++;
                break;
            case "L":
                j--;
                break;
            case "DL":
                if (i % 2 == 0) {
                    i++;
                    j--;
                }
                else {
                    i++;
                }

                break;
            case "DR":
                if (i % 2 == 0) {
                    i++;
                }
                else {
                    i++;
                    j++;
                }

                break;
            case "UL":
                if (i % 2 == 0) {
                    i--;
                    j--;
                }
                else {
                    i--;
                }

                break;
            case "UR":
                if (i % 2 == 0) {
                    i--;
                }
                else {
                    i--;
                    j++;
                }

                break;
            default:
                System.out.println("The given direction is not valid");
                return location;
        }

        if(!checkLocationValidity(i, j))
            return location;

        int[] destination = new int[2];
        destination[0] = i;
        destination[1] = j;
        return destination;

    }

    /**
     * a method to check if the location is valid or not
     * @param i number of the row
     * @param j number of the column
     * @return true if the location is valid
     */
    private boolean checkLocationValidity(int i, int j){
        if(i <= 9 && i >= 0){
            if(i % 2 == 0){
                return j >= 0 && j <= 12;
            }
            else{
                return j >= 0 && j <= 11;
            }
        }
        else
            return false;

    }

    /**
     * A method to calculate distance between two locations
     * @param location the 1st location
     * @param destination the 2nd location
     * @return distance between 2 locations
     */
    public int calculateDistance(int[] location, int[] destination){
        int[] currentLocation = location;

        int distance = 0;
        while(!(destination[0] == currentLocation[0] && destination[1] == currentLocation[1])){
            currentLocation = fastestMove(currentLocation, destination);
            distance++;
        }
        return distance;
    }

    /**
     * A method to move a force as the command says
     * @param force the force we want to move
     * @param commands command that gives the directions of the move
     * @param moveCounter counts number of moves. its initial value should
     * be 0.
     */
    public void move(Force force, ArrayList<String> commands, int moveCounter){
        if(commands.size() == 0) {
            if(moveCounter == 2 && force instanceof Soldier)
                force.disableAttack();
            return;
        }

        int[] forceLocation = getForceLocation(force.getName());
        int[] newLocation = moveOneDirection(forceLocation,commands.get(0));

        //it means further move is impossible
        if(forceLocation[0] == newLocation[0] && forceLocation[1] == newLocation[1]){
            if(moveCounter == 2 && force instanceof Soldier)
                force.disableAttack();
            return;
        }
        int checker = checkLocation(newLocation,force.getName());
        if(checker == 0){
            if(moveCounter == 2 && force instanceof Soldier)
                force.disableAttack();
            return;
        }

        map[forceLocation[0]][forceLocation[1]][1] = "empty";
        map[newLocation[0]][newLocation[1]][1] = force.getName();
        commands.remove(0);
        moveCounter++;

        if(checker == -1){
            commands.clear();
            force.disableAttack();
        }
        move(force, commands, moveCounter);

    }

    /**
     * A method to check if moving to this location is possible or not
     * @param location location we want to check
     * @param forceName name of the force that wants to move to the location
     * @return 1 if moving is possible. -1 moving is possible but disables the attack for
     * that turn. 0 moving to the location is impossible
     */
    private int checkLocation(int[] location, String forceName){
        int i = location[0];
        int j = location[1];

        String houseForce = map[i][j][1];
        String houseGround = map[i][j][0];

        //force cannot enter an occupied house
        if(!houseForce.equals("empty"))
            return 0; //moving to this location is impossible because its occupied
        //rivers cannot be passed
        if(houseGround.equals("R"))
            return 0;
        //Tanks and Artillery cannot enter towns and villages
        if(houseGround.equals("S") && (forceName.contains("T") || forceName.contains("A")))
            return 0;

        //forces that enter a jungle or town must stay there and their attack will be
        //disabled for that turn
        if(houseGround.equals("J") || houseGround.equals("T"))
            return -1; //will be kept here and disables attack for this turn

        return 1; //moving to this location is possible
    }

    /**
     * A method to get location of a force
     * @param forceName name of the force
     * @return location of the mentioned force
     */
    private int[] getForceLocation(String forceName){
        for(int counter = 0 ; counter < 9 ; counter++) {
            for (int counter2 = 0; counter2 < 13; counter2++) {
                if (map[counter][counter2][1].equals(forceName)){
                    int[] forceLocation = new int[2];
                    forceLocation[0] = counter;
                    forceLocation[1] = counter2;
                    return forceLocation;
                }
            }
        }
        return null;
    }

    /**
     * A method to remove a destroyed force from the map
     * @param forceName name of the force to be removed
     */
    public void destroyAForce(String forceName){
        int[] location = getForceLocation(forceName);
        int i = location[0];
        int j = location[1];
        map[i][j][1] = "empty";
    }

}
