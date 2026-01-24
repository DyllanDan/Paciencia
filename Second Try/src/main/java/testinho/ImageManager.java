package testinho;
import javafx.*;
import java.util.*;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageManager 
{
    private static final Map<String, Image> cache = new HashMap<>();

    public static Image getImage(String imageFile)
    {
        if (!cache.containsKey(imageFile))
        {
            String path = "/Images/" + imageFile;
            Image img = new Image(ImageManager.class.getResourceAsStream(path));
            cache.put(imageFile, img);
        }
        return (cache.get(imageFile));
    }

    public ImageView creatCardView(Card card)
    {
        String imageFile;
        Image image;
        ImageView imgView;

        if (card.getFace())
        {
            imageFile = card.getRank() + "_of_" + card.getSuit() + ".png";
        }
        else
        {
            imageFile = "back.png";
        }
        image = ImageManager.getImage(imageFile);
        imgView = new ImageView(image);
        imgView.setFitWidth(75);
        imgView.setPreserveRatio(true);
        imgView.setUserData(card);
        return imgView;
    }
}
