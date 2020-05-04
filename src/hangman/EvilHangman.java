package hangman;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class EvilHangman {

    public static void main(String[] args) throws IOException, EmptyDictionaryException, GuessAlreadyMadeException {
        boolean ifWin = false;
        Set<String> mySet;
        String myString = new String();
        EvilHangmanGame myGame = new EvilHangmanGame();
        int wordLength = Integer.parseInt(args[1]);
        try {
            myGame.startGame(new File(args[0]), wordLength);
        }

        catch(EmptyDictionaryException e)
        {
            System.out.println(e.getMessage());
        }
        Scanner scan = new Scanner(System.in);
        int numGuess = Integer.parseInt(args[2]);
        SortedSet<Character> GuessedLetters = new TreeSet<>();

        while (numGuess != 0) {
            boolean ifPlay = true;
            System.out.println("You have " + numGuess + " guesses left");
            GuessedLetters = myGame.getGuessedLetters();
            System.out.println("Used letters: " + GuessedLetters.toString());
            System.out.println("Word: " + myGame.getString());
            //System.out.println(myGame.getString()+"\n");
            System.out.println("Enter guess: ");
            String userIn = scan.nextLine();
            if (userIn.isEmpty()) {
                System.out.println("Invaild input");
                ifPlay = false;
            }

            else if(userIn.length()!= 1)
            {
                System.out.println("Invaild input");
                ifPlay = false;
            }


            else if (ifPlay == true) {
                char guess = userIn.charAt(0);
                if (Character.isLetter(guess) == false) {
                    System.out.println("Invaild input");
                } else {
                    try {
                        mySet = myGame.makeGuess(guess);
                        for(String thisString : mySet)
                        {
                            myString = thisString;
                        }
                    } catch (GuessAlreadyMadeException S) {
                        System.out.println(S.getMessage());
                    }
                    //System.out.println(myGame.getString() +"\n");
                    if (myGame.ifGuessMake(guess) == true) {

                    }

                    if(myGame.checkIfWin() == true)
                    {
                        ifWin = true;
                        break;
                    }

                    if(myGame.getIfCorrect() == false)
                    {
                        numGuess--;
                    }


                }

            }
        }

        if(ifWin == false)
        {
            System.out.println("You lost the game! :/");
            System.out.println("The correct word is " + myString);
        }

        else if(ifWin == true)
        {
            System.out.println("You win!!");
            System.out.println("The word is "+ myGame.getString());
        }
    }

}
