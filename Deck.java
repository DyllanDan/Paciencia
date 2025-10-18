import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.LinkedList;
import java.util.Iterator;


public class Deck 
{
    private LinkedList<Card> cards;
    private int size;

    public Deck()
    {
        this.cards = new LinkedList<>();
        this.size = 0;
    }

    public void addCard(Card card)
    {
        //if size > 13, throw condition
        cards.add(card);
        size++;
    }

    public void printDeck()
    {
        Iterator<Card> iterator = cards.iterator();

        while (iterator.hasNext())
        {
            Card card = iterator.next();
            card.printFace();
        }
    }

    public void createFullDeck()
    {
        String[] suits = {"Hearts", "Dimonds", "Clubs", "Spades"};
        String[] numbers = {"A", "2", "3", "4", "5", "6", "7", "8",
                            "9", "10", "J", "Q", "K"};
        for (String suit : suits)
        {
            for (String num : numbers)
            {
                Card card = new Card(num, suit);
                addCard(card);
            }
        }
    }

    public int getSize()
    {
        return size;
    }

    public Card drawTop()
    {
        return cards.pollFirst();
    }

    public void shuffledDeck()
    {
        Collections.shuffle(cards);
    }

    public LinkedList<Card> getCards()
    {
        return cards;
    }

    public String lastCardNum()
    {
        ListIterator<Card> iterator = cards.listIterator(this.size);

        Card lastCard = iterator.previous();

        return (lastCard.getNumber());
    }
}
