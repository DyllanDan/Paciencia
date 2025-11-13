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
        int value;
        for (String suit : suits)
        {
            value = 1;
            for (String num : numbers)
            {
                Card card = new Card(num, suit, value);
                addCard(card);
                value++;
            }
        }
    }


    public int getSize()
    {
        return cards.size();
    }

    public Card drawTop() //must use try-catch when called
    {
        if (cards == null)
            throw new IllegalStateException("Deck not initialized: cards is null.");
        if (size <= 0)
            throw new IllegalStateException("Deck is empty.");
        size--;
        return cards.pollFirst();
    }

    public void shuffledDeck()
    {
        Collections.shuffle(cards);
    }

    public LinkedList<Card> getCards()
    {
        try 
        {
            if (cards == null) 
            {
                throw new IllegalStateException("Deck not initialized: cards is null.");
            }
            if (size < 0) 
            {
                throw new IllegalStateException("Invalid deck size: " + size);
            }
            if (size > 0)
                return cards;
            else
                return new LinkedList<>();
        } 
        catch (IllegalStateException exc) 
        {
            System.err.println("[ERRO] " + exc.getMessage());
            return new LinkedList<>();
        }
    }

    public int lastCardNum()
    {
        if (cards == null || cards.isEmpty())
            return (0);
        Card last = cards.getLast();
        if (last.getValue() != 0)
            return (last.getValue());
        else
            return(0);
    }

    public void showLastCard()
    {
        Card last = cards.getLast();
        if (last.showFace == false)
            last.flipFace();
    }

    public int refCard()
    {
        if (cards == null || cards.isEmpty())
            return (0);
        return (lastCardNum());
    }
}

/*public int getCardValue(String num) {
    return switch (num) {
        case "A" -> 1;
        case "J" -> 11;
        case "Q" -> 12;
        case "K" -> 13;
        default -> Integer.parseInt(num);
    };
} */