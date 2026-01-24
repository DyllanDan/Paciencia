package testinho;
import java.util.ArrayList;
import java.util.List;


public class SetDecks
{
    private final List<Deck> collumns;
    private final int NUMBER_OF_COLLUMNS = 7;

    public SetDecks() 
    {
        this.collumns = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_COLLUMNS; i++)
            collumns.add(new Deck());
    }

    public void SetupSetDeck(Deck shuffledMainDeck)
    {
        for (int i = 0; i < NUMBER_OF_COLLUMNS; i++)
        {
            for (int j = 0; j <= i; j++)
            {
                Card card = shuffledMainDeck.drawTop();
                if (card != null)
                {
                    if (j == i)
                        card.showFace();
                    else
                        card.hideFace();
                    collumns.get(i). addCard(card);
                }
            }
        }
    }

    public List<Deck> getSetDecks()
    {
        return collumns;
    }

}
