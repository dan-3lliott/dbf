import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static final String style = "stylesheets/darktheme.css";
    Stage stage = new MainStage();
    public static void main(String[] args) {
        //launch application
        launch(args);
    }
    @Override
    public void start(Stage stage) {
        //set and show window
        stage = this.stage;
        stage.show();
    }
}