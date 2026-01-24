package testinho;
import java.util.List;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Render 
{
    private Pane root;
    private ImageManager imgManager;
    private GameEventManager eventManager;

    public Render(Pane root, ImageManager imgManager)
    {
        this.root = root;
        this.imgManager = imgManager;
        this.eventManager = new GameEventManager();
    }

    public void renderGame(SetDecks setDeck, Deck fullDeck)
    {
        double startX = 20;
        double startY = 50;
        double xSpace = 100;
        double offSet = 25;
        root.getChildren().clear();
        List<Deck> allColumns = setDeck.getSetDecks();
        for (int i = 0; i < allColumns.size(); i++)
        {
            Deck currentDeckCol = allColumns.get(i);
            List<Card> cardsInCol = currentDeckCol.getCards();

            for (int j = 0; j < cardsInCol.size(); j++)
            {
                Card card  = cardsInCol.get(j);

                ImageView cardImg = this.imgManager.creatCardView(card);

                cardImg.setLayoutX(startX + (i * xSpace));
                cardImg.setLayoutY(startY + (j * offSet));
                if (card.getFace())
                {
                    eventManager.MouseEventHandler(setDeck, card, cardImg, allColumns.get(i), root, imgManager, fullDeck);
                }
                root.getChildren().add(cardImg);
            }
        }
        if (!fullDeck.getCards().isEmpty())
        {
            Card buyer = fullDeck.getCards().get(0);
            ImageView cardImg = this.imgManager.creatCardView(buyer);
            cardImg.setLayoutX(610);
            cardImg.setLayoutY(575);
            eventManager.buyCards(cardImg, fullDeck, setDeck, root, imgManager, this);
            root.getChildren().add(cardImg);
        }
    }

    public void updatePane(SetDecks setDecks, Deck fullDeck)
    {
        root.getChildren().clear();
        this.renderGame(setDecks, fullDeck);
    }
}
