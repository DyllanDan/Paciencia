package testinho;
import java.util.Collections;
import java.util.LinkedList;

public class Deck 
{
    private LinkedList<Card> cards;
    private int size;

    public Deck()
    {
        this.cards = new LinkedList<>();
    }

    public void addCard(Card card)
    {
        if (card != null)
            cards.add(card);
    }

    public void createFullDeck()
    {
        for (Suit suit : Suit.values())
        {
            for (Rank rank : Rank.values())
            {
                Card card = new Card(rank, suit);
                card.hideFace();
                addCard(card);
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
        if (cards.isEmpty())
            throw new IllegalStateException("Deck is empty.");
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
            if (!cards.isEmpty())
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
        if (last.getFace() == false)
            last.flipFace();
    }

    public int refCard()
    {
        if (cards == null || cards.isEmpty())
            return (0);
        return (lastCardNum());
    }

    public boolean acceptMoveCard(Card toPlace)
    {
        Card last = cards.getLast();

        if (this.size == 0)
            return (true);
        if (last.getValue() > toPlace.getValue())
            return (true);
        else
            return (false);
    }
}