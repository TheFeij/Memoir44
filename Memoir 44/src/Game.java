import java.util.ArrayList;
import java.util.Scanner;

public class Game {




    public static void main(String[] args){

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

        //game process
        while(true){

            Player player;

            //process of doing each players actions
            for(int i = 0 ; i < 2 ; i++){
                player = players.get(i);

                //displaying game map
                map.displayMap();
                System.out.println();

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


                int numberOfChoices = card.charAt(6);
                boolean sameType = false;
                if(card.contains("same type"))
                    sameType = true;


                //process of moving
                ArrayList<Force> forces = new ArrayList<>();
                for(int counter = 0 ; counter < numberOfChoices ; counter++){

                    //process of choosing a force
                    System.out.println("Please choose a force to move and then to attack" +
                            "(in this format for example \"AL-T1\")");
                    while(true){
                        String forceName = input.nextLine();
                        Force force = player.getAForce(forceName);

                        if(force == null){
                            System.out.println("Entered force is not valid. Please try again");
                            continue;
                        }
                        if(counter > 1){
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
                        if(stringCommand.equals("0"))
                            break;
                        moveCommands = generateMoveCommand(stringCommand);
                        if(moveCommands == null) {
                            System.out.println("Command is not valid. Please try again");
                            continue;
                        }
                        if(!forces.get(counter).checkMoves(moveCommands.size())) {
                            System.out.println("This force cannot go that far. Please try again");
                        }
                        else
                            break;
                    }//end of process of receiving the move command


                    //move the force in the map
                    if(moveCommands.size() > 0)
                        map.move(forces.get(counter), moveCommands, 0);


                }//end of the process of moving


                //process of attacking
                for (Force attacker : forces) {

                    if (!attacker.getCanAttack()) {
                        System.out.printf("%s cannot attack this turn\n", attacker.getName());
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

                        map.updateForceStatus(defender.getName(), defender.getNumberOfUnits());
                        break;
                    }


                }//end process of attacking

                for(Force force : forces){
                    force.enableAttack();
                }

                int medals;
                if(i == 0) {
                    medals = players.get(1).removeCasualties();
                    players.get(0).increaseMedals(medals);
                }
                else {
                    medals = players.get(0).removeCasualties();
                    players.get(1).increaseMedals(medals);
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


            //checks if command is valid or not
            if(!checkMoveCommand(simpleCommand))
                return null;

            moveCommands.add(simpleCommand);
            stringCommand = stringCommand.substring(index + 1);

        }

        return moveCommands;
    }


}
