import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.image.Image;

private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
private final Image rvImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

/**
 * The main GUI logic
 */
public class Main extends Application {
    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!");
        Scene scene = new Scene(helloWorld);
        stage.setScene(scene);
        stage.show();
    }
}
