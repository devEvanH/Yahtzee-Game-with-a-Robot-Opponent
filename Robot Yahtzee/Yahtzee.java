import java.util.Random;
import java.util.Scanner;
/**
 * Establish all the necessary classes, methods, and states to enable the 
 * YahtzeePlayer class and game to function properly. 
 *
 * @author Evan Halverson
 * @version 12/24/2022
 */
public class Yahtzee
{
    // Establish variables that track state and key values
    public static final int NUM_DICE = 3;
    private boolean displayReroll = true;
    private int numberOfRerolls = 0;
    private boolean gameOver = false;
    private boolean turnOver = true;
    
    // Declare the players
    Player humanPlayer = new Player();
    Player robotPlayer = new Player();
    
    
    /**
     * Method getPlayersTurn
     * This is a getter method that returns who's turn it is
     *
     * @return a boolean to show if its the player's turn
     */
    
    public boolean getPlayersTurn() {
        return (humanPlayer.getPlayersTurn()) ? humanPlayer.getPlayersTurn() : robotPlayer.getPlayersTurn();
    }
    
    /**
     * Method startTurn
     * Reset the conditions of the state at the start of a turn.
     * This involves ensuring that rerolls can be seen and are at 0
     * and all the dice are rerolled. 
     */
    public void startTurn() {
        // Reset the state of the turn
        numberOfRerolls = 0;
        displayReroll = true;
        turnOver = false;
        
        // Reroll the dice
        rerollAllDice();
    }
    
    /**
     * Method displayDice
     * Print the dice roll values of each die. This method only prints
     * the dice for a specific player
     */
    public void displayDice() {
        System.out.print("Dice are: ");
        if(humanPlayer.getPlayersTurn()) {
            System.out.println(humanPlayer.dieOne.getDiceRoll() + " " + 
            humanPlayer.dieTwo.getDiceRoll() + " " + humanPlayer.dieThree.getDiceRoll());
        } else if (robotPlayer.getPlayersTurn()){
            System.out.println(+ robotPlayer.dieOne.getDiceRoll() + " " + 
            robotPlayer.dieTwo.getDiceRoll() + " " + robotPlayer.dieThree.getDiceRoll());
        }
    }
    
    /**
     * Method displayOptions
     * Display the actions that a player may take if they have not already
     * been chosen or if the number of rerolls has been exceeded
     */
    public void displayOptions() {
        //Only display the scoring and rollling option if not previously expended
        // The scoring options correspond to the player's turn
        if (numberOfRerolls < 3) {
            System.out.println("1 to roll a single dice.");
        }
        if (humanPlayer.getPlayersTurn()) {
            if (humanPlayer.getDisplayYahtzee()) {
                System.out.println("2 to for yahtzee score");
            }
            if (humanPlayer.getDisplayStraight()) {
                System.out.println("3 to for straight score");
            }
            if (humanPlayer.getDisplayChance()) {
                System.out.println("4 to for chance score");
            }
        } else if (robotPlayer.getPlayersTurn()) {
            if (robotPlayer.getDisplayYahtzee()) {
                System.out.println("2 to for yahtzee score");
                }
            if (robotPlayer.getDisplayStraight()) {
                System.out.println("3 to for straight score");
            }
            if (robotPlayer.getDisplayChance()) {
                System.out.println("4 to for chance score");
            }
        }
        System.out.print("Enter a choice: ");
        
    }
    
    /**
     * Method promptUser
     * A user enters any input into the scanner, but only if the input is 
     * "1", "2", "3", and "4" and the state of the corresponding display is true
     * will the input be accepted; otherwise, a error message appears, the
     * user is shown their options, and the user continues to enter in inputs until
     * they enter a correct input. 
     *
     * @param a user enters an input into the scanner
     * @return a valid int that the user input
     */
    public int promptUser (Scanner scanner) {
        // User enters their input
        int userText = scanner.nextInt();                                           
        
        // Take any int input, but only allow valid numbers
        // if one of the options have already been inputted, ask for another input
        boolean noInputErrors = false;
        while (!noInputErrors) {
            // Take the user's int and if that option has not already been selected
            // then assign noInputErrors to true; otherwise,
            // assign noInputErrors to false
            switch (userText) {
                case 1:
                    noInputErrors = (displayReroll) ? true : false;
                    break;
                case 2:
                    noInputErrors = (humanPlayer.getDisplayYahtzee()) ? true : false;
                    break;
                case 3:
                    noInputErrors = (humanPlayer.getDisplayStraight()) ? true : false;
                    break;
                case 4:
                    noInputErrors = (humanPlayer.getDisplayChance()) ? true : false;
                    break;
            }
            
            // If there is an input error, output error message and ask for another input
            if (!noInputErrors) {
                System.out.println("ERROR, try again.");
                displayOptions();
                userText = scanner.nextInt();
            }
        }
        
        // If the user has selected option 1, ask for which die they want to reroll
        if (userText == 1) {
            System.out.print("What die would you like to roll: ");
        }
        
        return userText;
    }
    
    /**
     * Method pickDieToRoll
     * A user enters any input into the scanner, but only if the input is 
     * "1", "2", or "3" will the input be accepted; otherwise, a error message appears, the
     * user is prompted for a valid die, and the user continues to enter in inputs until
     * they enter a correct input. 
     * 
     * @param a user enters an input into the scanner
     * @return a valid int that the user input
     */
    public int pickDieToRoll(Scanner scanner) {
        // Take user's input
        int userText = scanner.nextInt();                                 
        
        //Increment the number of rerolls
        //Only allow rerolls when the number of rerolls is less than 3
        ++numberOfRerolls;
        if (numberOfRerolls > 2) {
            displayReroll = false;
        }
        
        // Take any input, but only allow valid numbers
        while (userText < 1 || userText > 3) {
            System.out.println("Not a valid die.");
            System.out.print("What die would like to roll: ");
            userText = scanner.nextInt();
        }
        
        return userText;
    }
    
    /**
     * Method robotMakesDecision
     * The robot uses data regarding its scoring options available and 
     * currently obtained dice rolls to determine if it should score 
     * its dice and what is the best scoring option or if it should
     * reroll a dice and which dice would be the best for it to reroll.
     * 
     * @return an int showing which option the robot has decided
     */
    public int robotMakesDecision() {
        // Set default variable values to check dice values
        // make a decision and show a state of if a decision 
        // has been made or not
        int decision = 4;
        boolean decisionMade = false;
        int firstDie = robotPlayer.dieOne.getDiceRoll();
        int secondDie = robotPlayer.dieTwo.getDiceRoll();
        int thirdDie = robotPlayer.dieThree.getDiceRoll();
        
        // If the option to score a Yahtzee is availible, then check if 
        // the dice are all equal. If so, make the decision to score 
        // a Yahtzee. If not and the robot has less than 3 rerolls,
        // then check if two of the dice are equal. If so, chose to 
        // reroll the unequal die. 
        if (robotPlayer.getDisplayYahtzee()) {
            if (firstDie == secondDie && firstDie == thirdDie) {
                decision = 2;
                decisionMade = true;
            } else if (numberOfRerolls < 3) {
                if (firstDie == secondDie) {
                    decision = 1;
                    robotPlayer.changeTheChosenDie(3);
                    decisionMade = true;
                } else if (firstDie == thirdDie) {
                    decision = 1;
                    robotPlayer.changeTheChosenDie(2);
                    decisionMade = true;
                } else if (secondDie == thirdDie) {
                    decision = 1;
                    robotPlayer.changeTheChosenDie(1);
                    decisionMade = true;
                }
            } 
        }
        
        
        // Order the dice from least to greatest. If the option to score a 
        // Straight is available, then check if the dice increase by one 
        // for each subsequent die. If the dice do not follow that pattern
        // and the number of rerolls is less than 3, then check if two 
        // out of the three dice meet follow the desired pattern.
        // If that is the case, then the robot decides to reroll the 
        // die that is causing the straight to fail
        if (!decisionMade) {
            // Create variables to help order dice 
            int least = firstDie;
            int middle = secondDie;
            int greatest = thirdDie;
            int temp = firstDie; 
            
            // A variable that shows the original die number after the dice 
            // being ordered
            int actualDie = 1;
            
            // Order dice in least to greatest order by swapping spots
            // if the dice are in the incorrect order
            if (least > middle) {
                temp = least;
                least = middle;
                middle = temp;
            }
            if (middle > greatest) {
                temp = middle;
                middle = greatest;
                greatest = temp;
            }
            if (least > middle) {
                temp = least;
                least = middle;
                middle = temp;
            }   
            
            //Check if the option to score a straight is availible
            if (robotPlayer.getDisplayStraight()) {
                // If the the die roll with the least value plus one equals
                // the middle value, then check to see if the die roll with 
                // the middle value plus one equals the greatest dice roll 
                // value. If both of these are true, then set the decision 
                // to score a straight and undo the incrementation.
                // Otherwise, undo the incrementation performed in the appropriate check
                if (++least == middle) {
                    if (++middle == greatest) {                     
                        --least;
                        --middle;
                        decision = 3;
                        decisionMade = true; 
                    } else {
                        --least;
                        --middle;
                    }
                } else {
                    --least;
                }
                
                // If a decision hasn't been made and the number of rerolls of is less than 3, 
                // check to see if the greatest, least, or middle value breaks the straight 
                // pattern. If that value breaks the pattern, then unincrement the values, 
                // determine the die that broke the pattern, save that value to be rerolled
                // for later, and reassign the decision values to reroll that value. 
                // Otherwise, just unincrement at the end of each check. 
                if (!decisionMade && numberOfRerolls < 3) {
                    if (++least == middle) {
                        --least;
                        robotPlayer.changeTheChosenDie((unOrderDice(secondDie, thirdDie, greatest)));                        
                        decision = 1;
                        decisionMade = true;
                    } else {
                        --least;
                    }
                    if (!decisionMade && ++middle == greatest) {
                        --middle;
                        robotPlayer.changeTheChosenDie((unOrderDice(secondDie, thirdDie, least)));
                        decision = 1;
                        decisionMade = true;
                    } else {
                        --middle;
                    }
                    if (!decisionMade && least + 2 == greatest) {
                        robotPlayer.changeTheChosenDie((unOrderDice(secondDie, thirdDie, middle)));
                        decision = 1;
                        decisionMade = true;
                    } else if (!decisionMade) {
                        // If no decision has been made, then the straight is the highest scoring 
                        // option available, but the dice are too different to start building 
                        // a straight. Roll the dice with the least value to try and get a new
                        // value that may help create a straight or at least increase the score
                        // for a chance score
                        robotPlayer.changeTheChosenDie((unOrderDice(secondDie, thirdDie, least)));
                        decision = 1;
                        decisionMade = true;
                    }
                } 
            } 
            
            // At this point the robot has already used a Yahtzee and Straight, 
            // so the optimal strategy to increase points should be to 
            // reroll dice that are below 4 if the robot has rerolls availible.
            // If all dice are at 4 or are above, then the robot is ready to score.
            if (!decisionMade && robotPlayer.getDisplayChance()) {
                if (numberOfRerolls < 3) {
                    if (robotPlayer.dieOne.getDiceRoll() < 4) {
                        robotPlayer.changeTheChosenDie(1);
                        decision = 1;
                        decisionMade = true;
                    } else if (robotPlayer.dieTwo.getDiceRoll() < 4) {
                        robotPlayer.changeTheChosenDie(2);
                        decision = 1;
                        decisionMade = true;
                    } else if (robotPlayer.dieThree.getDiceRoll() < 4) {
                        robotPlayer.changeTheChosenDie(3);
                        decision = 1;
                        decisionMade = true;
                    } else {
                        decision = 4;
                        decisionMade = true;
                    }
                } else {
                    decision = 4;
                    decisionMade = true;
                }
            }
            
            // If something should go wrong, this ensures that the robot
            // will reroll the dice with the least value to maximize its score
            if ((!decisionMade && numberOfRerolls < 3)) {
                robotPlayer.changeTheChosenDie((unOrderDice(secondDie, thirdDie, least)));
                decision = 1;
                decisionMade = true;
            }
            
            // If the robot has failed to score a Yahtzee and/or a Straight
            // and has run out of rerolls, then expend the option with the lowest 
            // maximum score from the robot's still availible options
            if (!decisionMade && numberOfRerolls == 3) {
                if (robotPlayer.getDisplayChance()) {
                    decision = 4;
                    decisionMade = true;
                } else if (robotPlayer.getDisplayStraight()) {
                    decision = 3;
                    decisionMade = true;
                } else {
                    decision = 2;
                    decisionMade = true;
                }
            }
        }
        
        // Print and return the robots' decision 
        System.out.println(" " + decision);
        return decision;
    }
    
    /**
     * Method unOrderDice 
     * This method undoes the ordering process to reveal where the least,
     * middle, and greatest dice should actually be rerolled. 
     * This method takes the least, middle, or greatest value and checks to 
     * see if that value matches the second or third die's value. When
     * a match occurs, then that value is returned. If neither are a match,
     * then the actual die that corresponded the first of the robot's dice.
     * 
     * @param an int representing the robot's second Die
     * @param an int representing the robot's third Die
     * @param an int that we want to match to one of the robot's die
     * @return an int that represents which die that corresponded to the die 
     *         value that the method was searching for 
     */
    private int unOrderDice(int secondDie, int thirdDie, int desiredDieValue) {
        int actualDie = 1;
        if (desiredDieValue == secondDie) {
            actualDie = 2;
        } else if (desiredDieValue == thirdDie) {
            actualDie = 3;
        }
        return actualDie;
    }
    
    /**
     * Method robotShowsChosenDie
     * This method increments the number of rerolls and if the number
     * reaches its limit, then disable future rerolling for that turn.
     * Then, this method gets the die that the robot wants to roll,
     * prints the robots decision, and returns that decision. 
     * 
     * @return a valid int that the user inputrobot wants to reroll
    */
    public int robotShowsChosenDie() {
        //Increment the number of rerolls
        //Only allow rerolls when the number of rerolls is less than 3
        ++numberOfRerolls;
        if (numberOfRerolls > 2) {
            displayReroll = false;
        }
        
        // Get, print, and return the robot's decision
        int chosenDie = robotPlayer.getChosenDie();
        System.out.println("What die would you like to roll: " + chosenDie);
        return chosenDie;
    }
    
    /**
     * Method roll
     * Randomly generate a new value for a die, depeding on who's turn it is
     *
     * @param an int between 1 and 3 that corresponds 
     *        to the left to right place of that die
     */
    public void roll(int die) {
        // Reroll only the die in the spot
        if (humanPlayer.getPlayersTurn()) {
            switch (die) {
                case 1:
                    humanPlayer.dieOne.reroll();
                    break;
                case 2:
                    humanPlayer.dieTwo.reroll();
                    break;
                case 3:
                    humanPlayer.dieThree.reroll();
                    break;
            }
        } else if (robotPlayer.getPlayersTurn()) {
            switch (die) {
                case 1:
                    robotPlayer.dieOne.reroll();
                    break;
                case 2:
                    robotPlayer.dieTwo.reroll();
                    break;
                case 3:
                    robotPlayer.dieThree.reroll();
                    break;
            }
        }
    } 
    
    /**
     * Method roll
     * Randomly generate a new value for all of the robot or player's dice
     *
     */
    public void rerollAllDice() {
        // Reroll all dice depending on who's turn it is
        if (humanPlayer.getPlayersTurn()) { 
            humanPlayer.dieOne.reroll();
            humanPlayer.dieTwo.reroll();
            humanPlayer.dieThree.reroll();
        } else if (robotPlayer.getPlayersTurn()) {
            robotPlayer.dieOne.reroll();
            robotPlayer.dieTwo.reroll();
            robotPlayer.dieThree.reroll();
        }
    } 
    
    
    /**
     * Method scoreYahtzee
     * If the first, second, and third are all equal, add 50 points to the user's score
     * then disable the Yahtzee option and end the user's turn. 
     */
    public void scoreYahtzee() {
        // If all the dice roll values are equal, add 50 points to the score
        if (humanPlayer.getPlayersTurn() && (humanPlayer.dieOne.getDiceRoll() == humanPlayer.dieTwo.getDiceRoll() && 
            humanPlayer.dieOne.getDiceRoll() == humanPlayer.dieThree.getDiceRoll())) {
            humanPlayer.increaseScore(50);
        } else if (robotPlayer.dieOne.getDiceRoll() == robotPlayer.dieTwo.getDiceRoll() && 
            robotPlayer.dieOne.getDiceRoll() == robotPlayer.dieThree.getDiceRoll()) {
            robotPlayer.increaseScore(50);

        }
        
        // Disable the Yahtzee option and end the user's turn
        if (humanPlayer.getPlayersTurn()) {
            humanPlayer.swapYahtzeeDisplay();
        } else if (robotPlayer.getPlayersTurn()) {
            robotPlayer.swapYahtzeeDisplay();
        }
        turnOver = true;
    }
     
    /**
     * Method scoreStraight
     * If the lowest dice role value is two below the greatest dice role value and
     * the middle dice role value is one below, then add 25 points to the user's score.
     */
    public void scoreStraight() {        
        // Set the order from least to greatest with default values to be ordered
        int least = 1;
        int middle = 2;
        int greatest = 3;
        int temp;
        
        if(humanPlayer.getPlayersTurn()) {
            least = humanPlayer.dieOne.getDiceRoll();
            middle = humanPlayer.dieTwo.getDiceRoll();
            greatest = humanPlayer.dieThree.getDiceRoll();
        } else if (robotPlayer.getPlayersTurn()) {
            least = robotPlayer.dieOne.getDiceRoll();
            middle = robotPlayer.dieTwo.getDiceRoll();
            greatest = robotPlayer.dieThree.getDiceRoll();
        }
        
        // Determine if the dice are in least to greatest order
        // If two dice are in the incorrect order, swap their positioning
        if (least > middle) {
            temp = least;
            least = middle;
            middle = temp;
        }
        if (middle > greatest) {
            temp = middle;
            middle = greatest;
            greatest = temp;
        }
        if (least > middle) {
            temp = least;
            least = middle;
            middle = temp;
        }   
        
        
        // If the ordered values are equal to least, least + 1, 
        // and least + 2, then add 25 points to the score
        // Disable the Yahtzee option and end the user's turn
        if (humanPlayer.getPlayersTurn() && (++least == middle && ++middle == greatest)) {
            humanPlayer.increaseScore(25);
        } else if (robotPlayer.getPlayersTurn() && ++least == middle && ++middle == greatest) {
            robotPlayer.increaseScore(25);
        }
        
        // Disable the Yahtzee option and end the user's turn
        if (humanPlayer.getPlayersTurn()) {
            humanPlayer.swapStraightDisplay();
        } else if (robotPlayer.getPlayersTurn()) {
            robotPlayer.swapStraightDisplay();
        }
        turnOver = true;
    }
    
    /**
     * Method scoreChance
     * Sum all the dice values, end the user's turn, and disable the chance option. 
     */
    public void scoreChance() {       
        if (humanPlayer.getPlayersTurn()) {
            humanPlayer.increaseScore((humanPlayer.dieOne.getDiceRoll()
            + humanPlayer.dieTwo.getDiceRoll() + humanPlayer.dieThree.getDiceRoll()));
        } else if (robotPlayer.getPlayersTurn()) {
            robotPlayer.increaseScore((robotPlayer.dieOne.getDiceRoll()
            + robotPlayer.dieTwo.getDiceRoll() + robotPlayer.dieThree.getDiceRoll()));
        }
        
        // Disable the Yahtzee option and end the user's turn
        if (humanPlayer.getPlayersTurn()) {
            humanPlayer.swapChanceDisplay();
        } else if (robotPlayer.getPlayersTurn()){
            robotPlayer.swapChanceDisplay();
        }
        turnOver = true;
    }
    
    /**
     * Method turnEnded
     *
     * @return a boolean representing if the player's turn is over
     */
    public boolean turnEnded() {
        return turnOver;
    }
    
    public void robotsTurn() {
        // Reset the reroll option and start a turn
        numberOfRerolls = 0;
        displayReroll = true;
        turnOver = false;
        
        // Stop the human player's turn and start the robot's
        robotPlayer.swapPlayersTurn();
        humanPlayer.swapPlayersTurn();
        
        // Reroll the dice
        rerollAllDice();
    }
    
    /**
     * Method displayScores
     * Print a message stating the human and robot player's score
     */
    public void displayScores() {
        System.out.println("Your current total is: " + humanPlayer.displayScore());
        System.out.println();
        System.out.println("The robot's current total is: " + robotPlayer.displayScore());
        System.out.println();
    }
    
    /**
     * Method isOver
     * At the end of the third turn, the game is over.
     * 
     * @return a boolean representing if there is nothing more to do in the game or not
     */
        public boolean isOver(int curTurn) {
        if (curTurn > 3) {
            gameOver = true;
        }
        return gameOver;
    } 
    
    /**
     * Method robotPauses
     * Pause the robot briefly so that the human player may more easily
     * understand what the robot is doing
     * 
     */
        public void robotPauses() {
        try {
            Thread.sleep(1500);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    } 
    
    
    
}

