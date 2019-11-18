package unsw.dungeon;

import javafx.scene.image.ImageView;

/**
 * Add image to dungeon loader
 * 
 */
public class CustomImage {

    private ImageView image;

    public CustomImage(ImageView img) {
        this.image = img;
    }

    public void setImage(ImageView value) {
        image = value;
    }

    public ImageView getImage() {
        return image;
    }
}