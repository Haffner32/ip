import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;


/**
 * represents the graphical interface for the dialogue box
 */
public class DialogBox extends HBox {
    private final Label text = new Label();
    private final ImageView displayPicture = new ImageView();

    /**
     *  Constructor for the dialogue box
     * @param s message
     * @param i image
     */
    private DialogBox(String s, Image i) {
        super(8);
        text.setText(s);
        text.setWrapText(true);

        displayPicture.setImage(i);
        displayPicture.setFitWidth(40);
        displayPicture.setFitHeight(40);
        displayPicture.setPreserveRatio(true);

        setAlignment(Pos.TOP_RIGHT);
        setPadding(new Insets(8, 10, 8, 10));
        getChildren().addAll(text, displayPicture);
    }

    /**
     *  flips the dialogue to the left for Arvee
     */
    private void flip() {
        setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> nodes = FXCollections.observableArrayList(getChildren());
        Collections.reverse(nodes);
        getChildren().setAll(nodes);
    }

    /**
     * factory method for user dialog (right aligned)
     * @param msg message to be sent
     * @param img avatar
     * @return the Dialogue box
     */
    public static DialogBox getUserDialog(String msg, Image img) {
        return new DialogBox(msg, img);
    }

    /**
     * factory method for Arvee
     * @param msg message to be set
     * @param img avatar
     * @return the dialogueBox
     */
    public static DialogBox getArveeDialog(String msg, Image img) {
        DialogBox db = new DialogBox(msg, img);
        db.flip();
        return db;
    }
}
