import java.util.ArrayList;
import java.util.Scanner;


/**
 * program starts in this class.it manages the game
 *
 * @author Feij
 * @since 2020.11.19
 * @version 1.2
 */
public class Game {




    public static void main(String[] args){

        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("---->Ground Symbols :  H : Hill,  S : Shelter,  R : River,  B : Bridge");
        System.out.println("                  J : Jungle,  T: Town and Village");
        System.out.println("---->Forces Symbols :  S : Soldier,  T : Tank,  A : Artillery");
        System.out.println("---->AX : Axis,  AL : Allied");
        System.out.println("---->Forces will be displayed like this  AX-S3-4   ----> Axis-Soldier3-4 units remaining" );
        System.out.println("--------------------------------------------------------------------------------------------");


        //create a Map object
        Map map = new Map();
        //create a bank of cards
        Cards cards = new Cards();
        //create a list of players
        ArrayList<Player> players = new ArrayList<>();

        //create a new scanner
        Scanner input = new Scanner(System.in);

        //creating an allied player
        System.out.println("Please enter a name for the Allied player");
        String alliedName = input.nextLine();
        Player alliedPlayer = new Allied(alliedName);
        //giving allied player 4 initial cards
        for(int i = 0 ; i < 4 ; i++)
            alliedPlayer.addCard(cards.getCard());

        //creating an axis player
        System.out.println("Please enter a name for the Axis player");
        String axisName = input.nextLine();
        Player axisPlayer = new Axis(axisName);
        //giving axis player 2 initial cards
        for(int i = 0 ; i < 2 ; i++)
            axisPlayer.addCard(cards.getCard());

        //adding players to the list
        players.add(alliedPlayer);
        players.add(axisPlayer);


        map.displayMap();
        System.out.println();

        //game process
        while(true){

            Player player;

            //process of doing each players actions
            for(int i = 0 ; i < 2 ; i++){
                player = players.get(i);

                System.out.printf("%s's turn. Please choose a card\n", player.getName());
                //displaying players cards
                player.displayCards();

                //process of choosing a card
                String card;
                while(true){
                     card = input.nextLine();
                    if(player.useACard(card)){
                        player.addCard(cards.getCard());
                        break;
                    }
                    else
                        System.out.println("Entered card is not valid. Please try again");
                }//end of card choosing process


                int numberOfChoices = Integer.parseInt(String.valueOf(card.charAt(6)));
                boolean sameType = false;
                if(card.contains("same type"))
                    sameType = true;


                //process of moving
                ArrayList<Force> forces = new ArrayList<>();
                for(int counter = 0 ; counter < numberOfChoices ; counter++){
                    System.out.println(numberOfChoices);
                    System.out.println(counter);

                    //process of choosing a force
                    System.out.println("Please choose a force to move and then to attack" +
                            "(in this format for example \"AL-T1\")");
                    while(true){
                        String forceName = input.nextLine();
                        Force force = player.getAForce(forceName);

                        if(searchForce(forces, force)){
                            System.out.println("You cannot move a force twice. Please try again");
                            continue;
                        }
                        if(force == null){
                            System.out.println("Entered force is not valid. Please try again");
                            continue;
                        }
                        if(counter > 0){
                            if(sameType){
                                if(checkSameType(forces.get(0), force)) {
                                    System.out.println("Forces should have the same type. Please try again");
                                    continue;
                                }
                            }
                        }

                        forces.add(force);
                        break;

                    }//end of force choosing process


                    //process of receiving the move command
                    System.out.println("Please Enter a command move for this force or enter 0 to do nothing");
                    ArrayList<String> moveCommands = new ArrayList<>();
                    while(true){
                        String stringCommand = input.nextLine();
                        if(stringCommand.equals("")){
                            System.out.println("Command is not valid. Please try again");
                            continue;
                        }
                        if(stringCommand.equals("0"))
                            break;
                        moveCommands = generateMoveCommand(stringCommand);
                        if(moveCommands == null) {
                            System.out.println("Command is not valid. Please try again");
                            continue;
                        }
                        if(!forces.get(counter).checkMoves(moveCommands.size())) {
                            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$");
                            System.out.println("This force cannot go that far. Please try again");
                        }
                        else
                            break;
                    }//end of process of receiving the move command


                    //move the force in the map
                    if(moveCommands.size() > 0)
                        map.move(forces.get(counter), moveCommands, 0);

                    map.displayMap();


                }//end of the process of moving


                //process of attacking
                for (Force attacker : forces) {

                    if (!attacker.getCanAttack()) {
                        System.out.printf("%s cannot attack this turn\n", attacker.getName());
                        if(forces.get(forces.size() - 1) == attacker)
                            checkScores(players.get(0).getMedals(), players.get(1).getMedals(), map.checkSpecialHouses());
                        continue;
                    }
                    System.out.printf("Please choose a target for %s to attack or enter 0 to refuse to attack\n"
                            , attacker.getName());

                    while (true) {


                        Force defender = null;
                        //process of choosing a defender
                        while (true) {
                            String defenderName = input.nextLine();
                            if (defenderName.equals("0"))
                                break;
                            if (i == 0) {
                                defender = players.get(1).getAForce(defenderName);
                            } else {
                                defender = players.get(0).getAForce(defenderName);
                            }
                            if (defender == null)
                                System.out.println("defender is not valid. Please try again");
                            else
                                break;

                        }

                        if (defender == null)
                            break;

                        int attackChecker = attacker.attack(defender, map.getForceGround(attacker.getName())
                                , map.getForceGround(defender.getName()), map.calculateDistance(attacker, defender));

                        if (attackChecker == -1) {
                            System.out.println("attack on this defender is not possible." +
                                    " Please try again and choose another defender");
                            continue;
                        }
                        else if(attackChecker == 1)
                            System.out.println("Attack was successful");
                        else
                            System.out.println("Attack was unsuccessful");


                        map.updateForceStatus(defender.getName(), defender.getNumberOfUnits());
                        int medals;
                        if(i == 0) {
                            medals = players.get(1).removeCasualties();
                            players.get(0).increaseMedals(medals);
                        }
                        else {
                            medals = players.get(0).removeCasualties();
                            players.get(1).increaseMedals(medals);
                        }
                        checkScores(players.get(0).getMedals(), players.get(1).getMedals(), map.checkSpecialHouses());
                        break;
                    }

                    map.displayMap();

                }//end process of attacking

                for(Force force : forces){
                    force.enableAttack();
                }

            }//end of the process of doing each players actions


        }//end of game process


    }//end method main

    /**
     * A method to check if two forces has same type or not
     * @param force1 first force
     * @param force2 second force
     * @return true if two forces has the same type
     */
    private static boolean checkSameType(Force force1, Force force2){
        return force1.getClass() == force2.getClass();
    }

    /**
     * A method to check a simple command move is valid or not
     * @param moveCommand move command to be checked
     * @return true if the command is valid
     */
    public static boolean checkMoveCommand(String moveCommand){
        return moveCommand.equals("R") || moveCommand.equals("L")
                || moveCommand.equals("UR") || moveCommand.equals("UL")
                || moveCommand.equals("DR") || moveCommand.equals("DL");
    }

    /**
     * A method to generate a commandMove
     * @param stringCommand string that has to be converted to a list of commands
     * @return list of command moves
     */
    public static ArrayList<String>  generateMoveCommand(String stringCommand){

        int index = 0;
        ArrayList<String> moveCommands = new ArrayList<String>();
        while(index != -1){

            index = stringCommand.indexOf(' ');
            if(index == 0){
                stringCommand = stringCommand.substring(index + 1);
                if(stringCommand.length() == 0)
                    break;
                continue;
            }
            String simpleCommand;

            if(index == -1)
                simpleCommand = stringCommand;

            else
                simpleCommand = stringCommand.substring(0, index);

            ArrayList<String> extendedCommands = extendCommand(simpleCommand);
            for(String command : extendedCommands)
                if(!checkMoveCommand(command))
                    return null;

            moveCommands.addAll(extendedCommands);
            stringCommand = stringCommand.substring(index + 1);

        }

        return moveCommands;
    }

    /**
     * A method to extend a command. for example changes 2U to U U
     * @param command the command to be extended
     * @return extended command
     */
    public static ArrayList<String> extendCommand(String command){
        ArrayList<String> extendedCommand = new ArrayList<>();
        if(command.charAt(0) == '2' || command.charAt(0) == '3'){
            int repetition = Integer.parseInt(String.valueOf(command.charAt(0)));
            for(int counter = 0 ; counter < repetition ; counter++)
                extendedCommand.add(command.substring(1));
        }
        else
            extendedCommand.add(command);
        return extendedCommand;

    }

    /**
     * A method to search if the mentioned force exist in a list of forces
     * or not
     * @param forces list of forces
     * @param force the force to be checked
     * @return true if the mentioned force exist in a list of forces
     */
    public static boolean searchForce(ArrayList<Force> forces, Force force){
        for(Force frc : forces){
            if(frc == force)
                return true;
        }
        return false;
    }


    /**
     * A method to check and show scores and to end the game if any team
     * scores 6
     * @param alliedMedals allied medals
     * @param axisMedals axis medals
     * @param specialHousesCode 1 if allied has captured axis town, -1 if axis
     *                          has captured allied town. 0 if both has done this and
     *                          2 if none has done this.
     */
    public static void checkScores(int alliedMedals, int axisMedals, int specialHousesCode){
        if(specialHousesCode == 1)
            alliedMedals++;
        else if(specialHousesCode == -1)
            axisMedals++;
        else if(specialHousesCode == 0){
            alliedMedals++;
            axisMedals++;
        }

        if(alliedMedals == 6){
            System.out.println("Allied won!!");
            System.out.println("Scores: Allied = " + alliedMedals + "; Axis = " + axisMedals);
            System.exit(0);
        }
        else if(axisMedals == 6){
            System.out.println("Axis won!!");
            System.out.println("Scores: Axis = " + axisMedals + "; Allied = " + alliedMedals);
            System.exit(0);
        }
        else
            System.out.println("Scores: Axis = " + axisMedals + "; Allied = " + alliedMedals);
    }


}
