public class Map {


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
                int[] temp = move(location, "UR");
                i1 = temp[0];
                j1 = temp[1];
            }
            else if(j2 < j1){
                int[] temp = move(location, "UL");
                i1 = temp[0];
                j1 = temp[1];
            }
            else{
                if(i1 - i2 == 1){
                    i1 = i2;
                    j1 = j2;
                }
                else{
                    int[] temp = move(location, "UR");

                    if(temp[0] == location[0] && temp[1] == location[1]){
                        temp = move(location, "UL");
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
                int[] temp = move(location, "DR");
                i1 = temp[0];
                j1 = temp[1];
            }
            else if(j2 < j1){
                int[] temp = move(location, "DL");
                i1 = temp[0];
                j1 = temp[1];
            }
            else{
                if(i2 - i1 == 1){
                    i1 = i2;
                    j1 = j2;
                }
                else{
                    int[] temp = move(location, "DR");

                    if(temp[0] == location[0] && temp[1] == location[1]){
                        temp = move(location, "DL");
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
     * directions are
     * @param location the location where we are
     * @param direction the direction of the move
     * @return destination location
     */
    private int[] move(int[] location, String direction){
        int i = location[0];
        int j = location[1];

        if(direction.equals("R")){
            j++;
        }
        else if(direction.equals("L")){
            j--;
        }
        else if(direction.equals("DL")){
            if(i % 2 == 0){
                i++;
                j--;
            }
            else{
                i++;
            }

        }
        else if(direction.equals("DR")){
            if(i % 2 == 0){
                i++;
            }
            else{
                i++;
                j++;
            }

        }
        else if(direction.equals("UL")){
            if(i % 2 == 0){
                i--;
                j--;
            }
            else{
                i--;
            }

        }
        else if(direction.equals("UR")){
            if(i % 2 == 0){
                i--;
            }
            else{
                i--;
                j++;
            }

        }
        else{
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
                if(j >= 0 && j <= 12)
                    return true;
                return false;
            }
            else{
                if(j >= 0 && j <= 11)
                    return true;
                return false;
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

}
