import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * The main GUI logic
 */
public class Main extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image rvImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @Override
    public void start(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox(8);
        userInput = new TextField();
        sendButton = new Button("Send");

        scrollPane.setContent(dialogContainer);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPadding(new Insets(10));
        userInput.setPromptText("Type a message…");

        AnchorPane root = new AnchorPane();
        root.getChildren().addAll(scrollPane, userInput, sendButton);

        // anchor the components (so they resize nicely)
        AnchorPane.setTopAnchor(scrollPane, 8.0);
        AnchorPane.setLeftAnchor(scrollPane, 8.0);
        AnchorPane.setRightAnchor(scrollPane, 8.0);
        AnchorPane.setBottomAnchor(scrollPane, 56.0);

        AnchorPane.setLeftAnchor(userInput, 8.0);
        AnchorPane.setRightAnchor(userInput, 84.0);
        AnchorPane.setBottomAnchor(userInput, 8.0);

        AnchorPane.setRightAnchor(sendButton, 8.0);
        AnchorPane.setBottomAnchor(sendButton, 8.0);
        sendButton.setPrefWidth(68);

        dialogContainer.getChildren().add(
                DialogBox.getArveeDialog("Hello!", rvImage)
        );

        dialogContainer.heightProperty().addListener((obs, oldV, newV) -> scrollPane.setVvalue(1.0));

        Scene scene = new Scene(root, 400, 600);
        stage.setTitle("Arvee");
        stage.setScene(scene);
        stage.show();
    }
}
