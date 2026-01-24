package  testinho;

public class Card
{
    private final Rank rank;
    private final Suit suit;
    private boolean faceUp;


    public Card(Rank rank, Suit suit)
    {
        this.rank = rank;
        this.suit = suit;
        this.faceUp = true;
    }

    public String getSuit()
    {
        return suit.getSuit();
    }

    public String getRank()
    {
        return rank.getName();
    }

    public int getValue()
    {
        return rank.getValue();
    }

    public void hideFace()
    {
        this.faceUp = false;
    }

    public void showFace()
    {
        this.faceUp = true;
    }

    public boolean getFace()
    {
        return faceUp;
    }

    public void flipFace()
    {
        faceUp = !faceUp;
    }
}
