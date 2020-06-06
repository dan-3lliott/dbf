import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainStage extends Stage {
    private static final int width = 1600;
    private static final int height = 900;
    private static final int controlAreaWidth = 600;
    private static final int controlAreaHeight = 600;
    public MainStage() {
        //set up window
        setTitle("don't crash ya plane g");
        setWidth(width);
        setHeight(height);
        initStyle(StageStyle.UNDECORATED);
        //create shapes for the control area
        //surrounding rectangle
        Rectangle controlRect = new Rectangle((width - controlAreaWidth) / 2, (height - controlAreaHeight) / 2, controlAreaWidth, controlAreaHeight);
        controlRect.setStroke(Color.BLACK);
        controlRect.setFill(Color.TRANSPARENT);
        //center crosshair
        Line vertLine = new Line(width / 2, (height / 2) + 15, width / 2, (height/2) - 15);
        Line horizLine = new Line((width / 2) + 15, height / 2, (width / 2) - 15, height / 2);
        //aileron lines
        Line left1Line = new Line((width / 2) - (controlAreaWidth / 6), (height / 2) + 30, (width / 2) - (controlAreaWidth / 6), (height / 2) - 30);
        left1Line.setStroke(Color.ORANGERED);
        Line left2Line = new Line((width / 2) - (controlAreaWidth / 3), (height / 2) + 40, (width / 2) - (controlAreaWidth / 3), (height / 2) - 40);
        left2Line.setStroke(Color.RED);
        Line right1Line = new Line((width / 2) + (controlAreaWidth / 6), (height / 2) + 30, (width / 2) + (controlAreaWidth / 6), (height / 2) - 30);
        right1Line.setStroke(Color.ORANGERED);
        Line right2Line = new Line((width / 2) + (controlAreaWidth / 3), (height / 2) + 40, (width / 2) + (controlAreaWidth / 3), (height / 2) - 40);
        right2Line.setStroke(Color.RED);
        //elevator lines
        Line elev1Line = new Line((width / 2) + (controlAreaWidth / 6), (height / 2) - (controlAreaHeight) / 6, (width / 2) - (controlAreaWidth / 6), (height / 2) - (controlAreaHeight) / 6);
        elev1Line.setStroke(Color.GREEN);
        Line elev2Line = new Line((width / 2) + (controlAreaWidth / 3), (height / 2) - (controlAreaHeight) / 3, (width / 2) - (controlAreaWidth / 3), (height / 2) - (controlAreaHeight) / 3);
        elev2Line.setStroke(Color.YELLOW);
        Line elevNeg1Line = new Line((width / 2) + (controlAreaWidth / 6), (height / 2) + (controlAreaHeight) / 6, (width / 2) - (controlAreaWidth / 6), (height / 2) + (controlAreaHeight) / 6);
        elevNeg1Line.setStroke(Color.GREEN);
        Line elevNeg2Line = new Line((width / 2) + (controlAreaWidth / 3), (height / 2) + (controlAreaHeight) / 3, (width / 2) - (controlAreaWidth / 3), (height / 2) + (controlAreaHeight) / 3);
        elevNeg2Line.setStroke(Color.YELLOW);
        //create pane for control area
        Pane controlPane = new Pane(controlRect, vertLine, horizLine, left1Line, left2Line, right1Line, right2Line, elev1Line, elev2Line, elevNeg1Line, elevNeg2Line);
        //add scene
        setScene(new Scene(controlPane));
        //add style to the scene
        getScene().getStylesheets().add(Main.style);
        //move window to front
        toFront();
    }
}