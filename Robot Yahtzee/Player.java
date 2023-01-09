//import java.util.Random;
/**
 * A class to store a player's state and dice and then 
 * retrieve and modify instances of state. 
 *
 * @author Evan Halverson
 * @version 12/24/2022
 */
public class Player
{
    // Establish variables that track state and key values
    private int score = 0;
    private boolean displayYahtzee = true;
    private boolean displayStraight = true;
    private boolean displayChance = true;
    private boolean playersTurn = false;
    private int dieChosenToReroll = 1;
    
    Dice dieOne = new Dice();
    Dice dieTwo = new Dice();
    Dice dieThree = new Dice();
    
    /**
     * Method displayScore
     * This is a getter method that returns the player's score
     *
     * @return an int score of the player
     */
    
    public int displayScore() {
        return score;
    }
    
    /**
     * Method increaseScore
     * increment the score by the number of points
     *
     */
    
    public void increaseScore(int numPoints) {
        score += numPoints;        
    }
        
    /**
     * Method getDisplayYahtzee
     * This is a getter method that takes the displayYahtzee value stored for a die and returns it
     *
     * @return a boolean of whether or not to display the yahtzee text
     */
    
    public boolean getDisplayYahtzee() {
        return displayYahtzee;
    }
    
    /**
     * Method stopYahtzeeDisplay
     * stop displaying and using the yahtzee text
     *
     */
    
    public void swapYahtzeeDisplay() {
        displayYahtzee = !displayYahtzee;
    }
    
    /**
     * Method getDisplayStraight
     * This is a getter method that takes the displayStraight value stored for a die and returns it
     *
     * @return a boolean of whether or not to display the straight text
     */
    
    public boolean getDisplayStraight() {
        return displayStraight;
    }
    
    /**
     * Method stopStraightDisplay
     * stop displaying and using the straight text
     *
     */
    
    public void swapStraightDisplay() {
        displayStraight = !displayStraight;
    }
    
    /**
     * Method getDisplayChance
     * This is a getter method that takes the displayChance value stored for a die and returns it
     *
     * @return a boolean of whether or not to display the chance text
     */
    
    public boolean getDisplayChance() {
        return displayChance;
    }
    
    /**
     * Method stopChanceDisplay
     * stop displaying and using the chance text
     *
     */
    
    public void swapChanceDisplay() {
        displayChance = !displayChance;
    }
    
    /**
     * Method getPlayersTurn
     * This is a getter method that takes the displayChance value stored for a die and returns it
     *
     * @return a boolean of whether or not to display the chance text
     */
    
    public boolean getPlayersTurn() {
        return playersTurn;
    }
    
    /**
     * Method swapPlayersTurn
     * stop displaying and using the chance text
     *
     */
    
    public void swapPlayersTurn() {
        playersTurn = !playersTurn;
    }
    
    /**
     * Method getChosenDie
     * This is a getter method that takes the chosen die stored for the robot player and returns it
     *
     * @return an int that corresponds to the die to reroll
     */
    
    public int getChosenDie() {
        return dieChosenToReroll;
    }
    
    /**
     * Method changeTheChosenDie()
     * Set the chosen die to reroll to another die
     * 
     * @param an int representing the die the robot wants to reroll in the future
     */
    
    public void changeTheChosenDie(int dieChosenToReroll) {
        this.dieChosenToReroll = dieChosenToReroll;
    }
    
}

