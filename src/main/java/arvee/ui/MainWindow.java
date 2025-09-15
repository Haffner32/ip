package arvee.ui;
import arvee.core.ArveeBot;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/**
 * Logic for the graphical interface
 */
public class MainWindow {

    @FXML private ScrollPane scrollPane;
    @FXML private VBox dialogContainer;
    @FXML private TextField userInput;
    @FXML private Button sendButton;

    private ArveeBot bot;

    private final Image userImage = safeImage("/images/DaUser.png");
    private final Image arveeImage = safeImage("/images/DaDuke.png");

    private Image safeImage(String path) {
        var url = getClass().getResource(path);
        return (url == null) ? null : new Image(url.toExternalForm());
    }

    /**
     * initializes dialog window
     */
    @FXML
    public void initialize() {
        // auto-scroll to bottom when new content arrives
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.prefWidthProperty().bind(
               scrollPane.widthProperty().subtract(16)
        );
        sendButton.disableProperty().bind(userInput.textProperty().isEmpty());
    }

    /** Inject the backend after FXML is loaded */
    public void setBot(ArveeBot bot) {
        this.bot = bot;
        dialogContainer.getChildren().add(
                DialogBox.getArveeDialog(bot.getGreeting(), arveeImage)
        );
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input == null || input.isBlank()) {
            return;
        }

        String response = bot.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getArveeDialog(response, arveeImage)
        );

        userInput.clear();

        if (bot.shouldExit(input)) {
            userInput.setDisable(true);
            sendButton.setDisable(true);
        }
    }
}

