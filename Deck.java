import java.lang.reflect.Array;
import java.util.ArrayList;
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
        size = cards.size();
        return size;
    }

    public void setSize(int newSize)
    {
        size = newSize;
    }

    public Card drawTop()
    {
        size--;
        return cards.pollFirst();
    }

    public void shuffledDeck()
    {
        Collections.shuffle(cards);
    }

    public LinkedList<Card> getCards()
    {
        if (size > 0)
            return cards;
        else
            return (new LinkedList<>());
    }

    public String lastCardNum()
    {
        Card last = null;
        for (Card card : cards)
            last = card;

        if (last != null)
            return (last.getNumber());
        else
            return ("");
    }

    public void showLastCard()
    {
        Card last = cards.getLast();
        if (last.showFace == false)
            last.flipFace();
    }
}
