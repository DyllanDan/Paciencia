import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Iterator;
import javax.lang.model.util.ElementScanner14;

public class SetDecks extends Deck
{
    private List<Deck> playableDeck;
    private int index;

    public SetDecks() 
    {
        this.playableDeck = new ArrayList<>();
        index = 7;
    }

    public void createSet(Deck cards)
    {
        int aux = 1;
        while (aux <= index)
        {
            Deck set = new Deck();
            while (set.getSize() < aux - 1)
            {
                Card cardToAdd = cards.drawTop();
                cardToAdd.hideFace();
                set.addCard(cardToAdd);
            }
            Card cardToAdd = cards.drawTop();
            set.addCard(cardToAdd);
            playableDeck.add(set);
            aux++;
        }
    }

    public void showSets()
    {
        Iterator<Deck> setIterator = playableDeck.iterator();

        while (setIterator.hasNext())
        {
            Deck indexDeck = setIterator.next();

            indexDeck.printDeck();
            System.out.printf("%n");
        }
    }

    public void moveCards(String to_move, String to_place)
    {
        Deck placeIn = null;
        Deck moveFrom = null;
        int i = 1;
        for (Deck deckSet : playableDeck)
        {
            if (i == Integer.parseInt(to_move))
                moveFrom = deckSet;
            if (i == Integer.parseInt(to_place))
                placeIn = deckSet;
            i++;
        }
        String refNumber = placeIn.lastCardNum();
        int auxRefNum;
        if (refNumber.equals("A"))
            auxRefNum = 0;
        else if (refNumber.equals("J"))
            auxRefNum = 11;
        else if (refNumber.equals("Q"))
            auxRefNum = 12;
        else if (refNumber.equals("K"))
            auxRefNum = 13;
        else
        {
            auxRefNum = Integer.parseInt(refNumber);
        }
        int startIndex = 0;
        int endIndex = 0;
        List<Card> deckItr = moveFrom.getCards();
        for(Card card : deckItr)
        {
            int auxNum;
            if (card.getNumber().equals("A"))
                auxNum = 0;
            else if (card.getNumber().equals("J"))
                auxNum = 11;
            else if (card.getNumber().equals("Q"))
                auxNum = 12;
            else if (card.getNumber().equals("K"))
                auxNum = 13;
            else
            {
                auxNum = Integer.parseInt(card.getNumber());
            }
            if (auxRefNum < auxNum)
                startIndex++;
            endIndex++;
        }
        List<Card> cards = moveFrom.getCards(); // método getter que retorna a lista interna
        List<Card> movingCards = new ArrayList<>(cards.subList(startIndex, endIndex));
        cards.subList(startIndex, endIndex).clear();
        for (Card c : movingCards) {
            placeIn.addCard(c);
}
    }
}
