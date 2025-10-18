import java.util.Set;
import java.util.Scanner;

public class Test 
{
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        String moveFrom;
        String placeIn;
        Deck newDeck = new Deck();
        SetDecks newSet = new SetDecks();

        newDeck.createFullDeck();
        newDeck.shuffledDeck();
        newSet.createSet(newDeck);
        while (true)
        {
            newSet.showSets();
            System.out.println("Digite moveFrom: ");
            moveFrom = scanner.nextLine();
            System.out.println("Digite placeIn: ");
            placeIn = scanner.nextLine();
            if (moveFrom.equals("x"))
                break ;
            newSet.moveCards(moveFrom, placeIn);
        }
    }
}
