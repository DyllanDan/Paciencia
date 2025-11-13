import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Iterator;
import java.util.LinkedList;
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
        int i = 1;

        while (setIterator.hasNext())
        {
            Deck indexDeck = setIterator.next();
            System.out.printf("%d|  ", i);
            i++;
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
        
        int refNumber;
        if (placeIn != null)
            refNumber = placeIn.refCard();
        else
            refNumber = 0;
        if (refNumber != 0)
            moveDecks(moveFrom, placeIn, refNumber);
    }

    public List<Deck> getSetDecks()
    {
        return playableDeck;
    }

    public void moveDecks(Deck moveFrom, Deck placeIn, int refNumber)
    {
        int startIndex = 0;
        int endIndex = 0;
        int aux = 0;
        LinkedList<Card> deckItr;
        if (moveFrom == null)
            deckItr = new LinkedList<>();
        else
            deckItr = moveFrom.getCards();
        aux = moveFrom.getSize() - 1;
        startIndex = aux;
        int currentValue = 0;
        while(aux >= 0)
        {
            Card card = deckItr.get(aux);
            int auxNum = card.getValue();
            if (card.showFace == false || currentValue >= auxNum)
                break ;
            else if (refNumber > auxNum)
            {
                startIndex--;
                currentValue = auxNum;
            }
            else
                break ;
            aux--;
        }
        endIndex = moveFrom.getSize();
        if (startIndex == endIndex)
            return ;
        List<Card> cards = moveFrom.getCards(); // método getter que retorna a lista interna
        List<Card> movingCards = new ArrayList<>(cards.subList(startIndex, endIndex));
        cards.subList(startIndex, endIndex).clear();
        if (moveFrom.getSize() > 0)
            moveFrom.showLastCard();
        for (Card c : movingCards) 
        {
            placeIn.addCard(c);
        }
    }
}
