# Yahtzee-Game-with-a-Robot-Opponent
This is an abbreviated Yahtzee game where you may take on a robot opponent! Please see the game instructions in the README file!

PROJECT TITLE: Yahtzee Game with a Robot Opponent
PURPOSE OF PROJECT: Have a fully functional Yahtzee game
against a robot opponent that could realistically beat a 
human player
VERSION or DATE: 12/26/2022
AUTHOR(S): Evan Halverson

GAME INSTRUCTIONS:
This game has three six-sided dice for each player. The player
starts a turn with an initial roll, and then has up to 3 die 
rerolls per turn. After the third roll, the player must choose 
a score to keep from yahtzee, straight, and chance, if they have 
not already selected that choice. A yahtzee is three dice with 
the same value, which is worth 50 points. A straight is when the 
die with the largest value is two greater than the die with the 
lowest value and the die with the median value is one greater than 
the die with the lowest value. Additionally, these dice may be in 
any order. For example, if a player had the dice 5 6 4, this would 
qualify as a straight. This is worth 25 points. Finally, a 
chance score is worth the sum of all three dice. If by the end of 
the player's rerolls, the player does not have a yahtzee or straight 
and does not have or want to expend their chance scoring option, then, 
they may select to score their dice as yahtzee or straight and receive 
0 points. Once a player scores their dice, then it is the robot opponents 
turn. The robot opponent has the same options as the human player on
its turns. This game ends after the player and robot have both expended 
all of their scoring options. At that point, the player must have 
more points than the robot in order to win. 


STATE OF THIS PROJECT: This game is fully functional. 

PROCESS DESCRIPTION:
I first built the turn order, classes, objects, and methods
for a solo version of this game for my fourth project in 
my Object Oriented Programming I class. I started with
only running the turnorder and storing the state of the player 
within the Game class. For the enhanced version with a robot player, 
I first realized that I needed to create a Player class within the 
Game class. This way, I could more accurately represent the state of
the entire game, the robot player, and the human player. After this, 
I designed the robot's decision making process first as the attached
flowchart. Then, I modified a few methods to help run the game for
both the human and robot players. Finally, I updated the order of
events to properly move the game along and display the scores. Waits
were added during the robot's turn as it was too confusing for my
human tester to understand what decisions the robot was making on
its turns.



