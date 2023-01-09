import java.util.Scanner;
/**
 * Take the steps in order to run a Yahtzee game for a human player against a robot player
 *
 * @author Evan Halverson
 * @version 12/24/2022
 */
public class YahtzeePlayer
{
    public static void main(String args[]){ 
        
        Scanner scanner = new Scanner(System.in);

        // create a new game
        Yahtzee game = new Yahtzee();

        int curTurn = 1;
        // while the game isn't over, 
        // let the user take a turn
        while(!game.isOver(curTurn)){ 
            System.out.printf("-------------------- Turn %d --------------------\n", curTurn);
            // for each turn,
            // the user starts with a given start roll
            
            
            game.humanPlayer.swapPlayersTurn();
            game.startTurn();
            System.out.println("Player's Turn:"); 
            
            // Then, while the user's current turn hasn't ended
            // (In a given turn, the user has 3 possible re-rolls, 
            // and then the user has to choose a score
            // The user may also choose a score prior to 3 rolls, 
            // which should also end the current turn)
            while(!game.turnEnded()) {
                // for the current turn,
                // the user needs to see the current dice
                game.displayDice();

                // then the user needs to see their current options
                // (if they've already rolled 3 times, they won't see the 
                //  option to roll again; if they've already used a score,
                //  they won't see that score option)
                game.displayOptions();

                // prompt user for a their choice of option
                // the promptUser method must handle invalid choices
                // so that by the time the method returns, the 
                // choice is valid (the switch below and this game play 
                // loop only handles valid choices)
                int choice = game.promptUser(scanner);
                                                
                switch(choice) {
                    case 1:
                        // choice was to roll dice, 
                        // pick which one to roll  
                        int die = game.pickDieToRoll(scanner);                          
                        // roll the die picked
                        game.roll(die);
                        break;

                    case 2:
                        // choice was to score a yahtzee
                        game.scoreYahtzee();
                        break;

                    case 3:
                        // choice was to score a straight
                        game.scoreStraight();
                        break;

                    case 4:
                        // choice was to score chance
                        game.scoreChance();
                } // end switch
                
                // display a new line between each iteration of the current turn
                System.out.println();
            }
            
            // display total the end of each turn (current score)
            game.displayScores();
            
            // Reset the number of rerolls,change the turn from player to robot,
            // and reroll the robot's dice
            game.robotsTurn();
            
            while(!game.turnEnded()) {
                // Show the robot's turn and its dice
                game.robotPauses();
                System.out.println("Robot's Turn:");
                game.displayDice();
                game.robotPauses();
                
                // Show the options that the robot can choose from
                game.displayOptions();
                game.robotPauses();
                
                // Robot decides what action it should take 
                // based off the dice it has
                int choice = game.robotMakesDecision();
                game.robotPauses();
                
                switch(choice) {
                    case 1:
                        // choice was to roll dice, 
                        // shows the robot's decision  
                        int die = game.robotShowsChosenDie();                          
                        // roll the die picked
                        game.roll(die);
                        break;

                    case 2:
                        // choice was to score a yahtzee
                        game.scoreYahtzee();
                        break;

                    case 3:
                        // choice was to score a straight
                        game.scoreStraight();
                        break;

                    case 4:
                        // choice was to score chance
                        game.scoreChance();
                } // end switch
                
                // display a new line between each iteration of the current turn
                System.out.println();
            }
            
            // display total the end of each turn (current score)
            game.robotPauses();
            game.displayScores();
            
            // Reroll the robotPlayer's dice and end its turn
            game.rerollAllDice();
            game.robotPlayer.swapPlayersTurn();
            
            // Increment for next turn
            curTurn++;
        }

        // Tell the player if the won or lost
        game.robotPauses();
        if (game.humanPlayer.displayScore() > game.robotPlayer.displayScore()) {
            System.out.println("Congratulations! You won!");
        } else {
            System.out.println("Too bad! You failed to defeat the robot!");
        }
        
        
    }
}
