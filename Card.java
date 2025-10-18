
public class Card
{
    protected String number;
    protected String suit;
    int showFace;


    public Card(String number, String suit)
    {
        this.number = number;
        this.suit = suit;
        this.showFace = 1;
    }

    public void printFace()
    {
        if (showFace == 1)
            System.out.printf("(%s %s)", number, suit);
        else
            System.out.printf("(*)");
    }

    public String getSuit()
    {
        return suit;
    }

    public String getNumber()
    {
        return number;
    }

    public void hideFace()
    {
        this.showFace = 0;
    }

    public void showFace()
    {
        this.showFace = 1;
    }
}
