package  testinho;
import javafx.scene.paint.Color;

public enum Suit
{
    HEARTS("hearts", Color.RED),
    DIAMONDS("diamonds", Color.RED),
    CLUBS("clubs", Color.BLACK),
    SPADES("spades", Color.BLACK);

    private final String suit;
    private final Color color;
    
    Suit(String name, Color color)
    {
        this.suit = name;
        this.color = color;
    }

    public String getSuit()
    {
        return suit;
    }

    public Color getColor()
    {
        return color;
    }
}