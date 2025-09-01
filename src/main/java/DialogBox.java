import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * represents the graphical interface for the dialogue box
 */
public class DialogBox extends HBox {
    private final Label text;
    private final ImageView displayPicture;

    /**
     *  Constructor for the dialogue box
     * @param s message
     * @param i image
     */
    public DialogBox(String s, Image i) {
        text = new Label(s);
        displayPicture = new ImageView(i);
        getChildren().addAll(text, displayPicture);
        setAlignment(Pos.TOP_RIGHT);
    }
}
