import java.util.Set;
import java.util.Scanner;
import java.util.Iterator;

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
        System.out.println("----------Paciência sem GUI----------");
        while (true)
        {
            newSet.showSets();
            System.out.println("Buy cards[Y/N]?: ");
            moveFrom = scanner.nextLine();
            if (moveFrom.equals("Y"))
            {
                for (Deck group : newSet.getSetDecks())
                {
                    Card newCard = newDeck.drawTop();
                    group.addCard(newCard);
                }
                continue ;
            }
            else if (moveFrom.equals("N"))
            {
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
}
