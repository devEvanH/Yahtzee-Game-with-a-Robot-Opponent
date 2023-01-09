import java.util.Random;
/**
 * This class outlines the properties of the dice object (the most recent roll and 
 * its number of sides) and the different methods that can be used to roll a dice
 * and get the value of the most recent roll
 *
 * @author Evan Halverson
 * @version 12/24/2022
 */

public class Dice {
        // Store attributes of the dice class
        public static final int NUM_SIDES_OF_DICE = 6; 
        private int diceRoll; 
        
        // Import random number generator and side lengths for dice
        static Random randDie = new Random();
        
        /**
         * Dice Constructor
         * This constructor sets the value of a dice to a randomly generated number
         */
        public Dice() {
            diceRoll = roll();                                                 
        }
        
        /**
         * Method reroll
         * This reassigns that dice's value by randomly generating a new value
         *
         */
        public void reroll() {
            diceRoll = roll();
        }
        
        /**
         * Method roll
         * This method generates a random number between 1 and the number of sides on the dice (6)
         *
         * @return the randomly generated int
         */
        public int roll() {
            return randDie.nextInt(NUM_SIDES_OF_DICE) + 1;
        }
        
        /**
         * Method getDiceRoll
         * This is a getter method that takes the diceRoll value stored for a die and returns it
         *
         * @return the int of the dice object's roll
         */
        public int getDiceRoll() {
            return diceRoll;
        }
        
    
    } 