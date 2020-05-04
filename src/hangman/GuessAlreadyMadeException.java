package hangman;

public class GuessAlreadyMadeException extends Exception {

    GuessAlreadyMadeException(String myString)
    {
        super(myString);
    }
}
