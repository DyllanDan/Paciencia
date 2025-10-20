
public class Card
{
    protected String number;
    protected String suit;
    boolean showFace;


    public Card(String number, String suit)
    {
        this.number = number;
        this.suit = suit;
        this.showFace = true;
    }

    public void printFace()
    {
        if (showFace == true)
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
        this.showFace = false;
    }

    public void showFace()
    {
        this.showFace = true;
    }

    public void flipFace()
    {
        showFace = !showFace;
    }
}
