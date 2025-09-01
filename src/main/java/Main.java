import arvee.core.ArveeBot;
import arvee.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * The main GUI logic
 */
public class Main extends Application {

    private final ArveeBot bot = new ArveeBot();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxml = new FXMLLoader(getClass().getResource("/view/MainWindow.fxml"));
            AnchorPane root = fxml.load();
            MainWindow controller = fxml.getController();
            controller.setBot(bot);
            Scene scene = new Scene(root);
            stage.setTitle("Arvee");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
