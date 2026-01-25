package testinho;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class GameEventManager 
{
    private Card card;
    private SetDecks setDecks;

    public void setDecks(SetDecks setDecks)
    {
        this.setDecks = setDecks;
    }

    public void MouseEventHandler(SetDecks setDecks, Card card, ImageView cardView, Deck originDeck, Pane root, ImageManager manager, Deck fullDeck)
    {
        cardView.setOnDragDetected((event) -> {
            int colIndex;
            int cardIndex;
            String data;
            ClipboardContent content = new ClipboardContent();
            Dragboard db = cardView.startDragAndDrop(TransferMode.MOVE);

            colIndex = setDecks.getSetDecks().indexOf(originDeck);
            cardIndex = originDeck.getCards().indexOf(card);
            data = colIndex + ":" + cardIndex;
            content.putString(data);
            db.setContent(content);

            VBox cardContainer = new VBox(-75);
            for (int k = cardIndex; k < originDeck.getSize(); k++)
            {
                Card currentCard = originDeck.getCards().get(k);
                ImageView viewCopy = manager.creatCardView(currentCard);
                viewCopy.setPreserveRatio(true);
                cardContainer.getChildren().add(viewCopy);
            }
            new Scene(cardContainer);
            db.setDragView(cardContainer.snapshot(null,null));
            event.consume();
        });

        cardView.setOnDragOver(event ->{
            if (event.getGestureSource() != cardView && event.getDragboard().hasString())
            {
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });

        cardView.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            if(db.hasString())
            {
                String[] data = db.getString().split(":");
                int originColIdx = Integer.parseInt(data[0]);
                int originCardIdx = Integer.parseInt(data[1]);
                int placeColIdx = setDecks.getSetDecks().indexOf(originDeck);

                moveCardsLogic(setDecks, originColIdx, originCardIdx, placeColIdx);
                Platform.runLater(() -> {
                    root.getChildren().clear();
                    Render renderer = new Render(root, manager);
                    renderer.renderGame(setDecks, fullDeck);
                });
                root.getChildren().clear();
                event.setDropCompleted(true);
            }
            event.consume();
        });
    }

    private void moveCardsLogic(SetDecks allSet, int originCol, int originCard, int placeCol)
    {
        Deck origin = allSet.getSetDecks().get(originCol);
        Deck place = allSet.getSetDecks().get(placeCol);
        Card refCard = origin.getCards().get(originCard);


        if (place.getSize() == 0 || place.acceptMoveCard(refCard))
        {
            List<Card> toMove = new ArrayList<>(origin.getCards().subList(originCard, origin.getSize()));
            origin.getCards().removeAll(toMove);
    
            for (Card card : toMove)
            {
                place.addCard(card);
            }
    
            if (!origin.getCards().isEmpty())
            {
                origin.getCards().getLast().showFace();
            }
        }
    }

    public void buyCards(ImageView cardView, Deck fullDeck, SetDecks setDecks, Pane root, ImageManager manager, Render renderer)
    {
        cardView.setOnMouseClicked((event) -> {
            
            for (Deck deck : setDecks.getSetDecks())
            {
                if (fullDeck.getCards().isEmpty())
                    break ;
                Card card = fullDeck.drawTop();
                card.flipFace();
                deck.addCard(card);
            }
            root.getChildren().clear();
            renderer.renderGame(setDecks, fullDeck);
            event.consume();
        });
    }

    public void setupAnchorEvents(Rectangle anchor, int i, SetDecks setDeck, Pane root, Render renderer, Deck fullDeck)
    {
        anchor.setOnDragOver(event -> {
        if (event.getGestureSource() != anchor && event.getDragboard().hasString()) {
                    event.acceptTransferModes(TransferMode.MOVE);
                }
        event.consume();
        });

        anchor.setOnDragDropped(event -> {        
        Dragboard db = event.getDragboard();
        if (db.hasString()) 
        {
            String[] data = db.getString().split(":");
            int originColIdx = Integer.parseInt(data[0]);
            int originCardIdx = Integer.parseInt(data[1]);

            moveCardsLogic(setDeck, originColIdx, originCardIdx, i);

            Platform.runLater(() -> {
                renderer.renderGame(setDeck, fullDeck);
            });
            
            event.setDropCompleted(true);
        }
        event.consume();
        });
    }
}
