import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainStage extends Stage {
    private static final int width = 1600;
    private static final int height = 900;
    public MainStage() {
        //set up window
        setTitle("Vision");
        setWidth(width);
        setHeight(height);
        initStyle(StageStyle.UNDECORATED);
        //add scene
        setScene(new Scene(new Button("test")));
        //add style to the scene
        getScene().getStylesheets().add(Main.style);
        //move window to front
        toFront();
    }
}