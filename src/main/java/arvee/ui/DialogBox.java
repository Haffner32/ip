package arvee.ui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;


    /**
     *  Constructor for the dialogue box
     * @param s message
     * @param i image
     */
    private DialogBox(String s, Image i) {
        try {
            FXMLLoader fxml = new FXMLLoader(getClass().getResource("/view/DialogBox.fxml"));
            fxml.setController(this);
            fxml.setRoot(this);
            fxml.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        dialog.setText(s);
        if (i != null) {
            displayPicture.setImage(i);
        } else {
            displayPicture.setVisible(false);
            displayPicture.setManaged(false);
        }
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
