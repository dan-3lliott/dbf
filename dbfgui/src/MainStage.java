import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainStage extends Stage {
    private static final int width = 1200;
    private static final int height = 900;
    private static final int controlAreaWidth = 600;
    private static final int controlAreaHeight = 600;
    private double mX = width / 2;
    private double mY = height / 2;
    private byte aileronPos = 0;
    private byte prevAileronPos = aileronPos;
    private byte elevatorPos = 0;
    private byte prevElevatorPos = elevatorPos;
    private byte rudderPos = 0;
    private byte prevRudderPos = rudderPos;
    private boolean active = false;
    public MainStage() {
        //set up window
        setTitle("don't crash ya plane g");
        setWidth(width);
        setHeight(height);
        initStyle(StageStyle.UNDECORATED);
        //serial communication controls
        TextField serialPorts = new TextField("COM3");
        Button serialConnect = new Button("Connect");
        VBox serialBox = new VBox(serialConnect, serialPorts);
        serialBox.setSpacing(10);
        serialBox.setPadding(new Insets(10));
        serialBox.setAlignment(Pos.TOP_CENTER);
        //create shapes for the control area
        //surrounding rectangle
        Rectangle controlRect = new Rectangle((width - controlAreaWidth) / 2, (height - controlAreaHeight) / 2, controlAreaWidth, controlAreaHeight);
        controlRect.setStroke(Color.BLACK);
        controlRect.setFill(Color.TRANSPARENT);
        //center crosshair
        Line vertLine = new Line(width / 2, (height / 2) + 15, width / 2, (height / 2) - 15);
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
        //pointer
        Circle pointer = new Circle(mX, mY, 3, Color.CYAN);
        pointer.setStrokeWidth(1);
        pointer.setStroke(Color.BLACK);
        //create pane for control area
        Pane controlPane = new Pane(serialBox, vertLine, horizLine, left1Line, left2Line, right1Line, right2Line, elev1Line, elev2Line, elevNeg1Line, elevNeg2Line, pointer, controlRect);
        //mouse event handling
        controlRect.setOnMouseMoved(event -> {
            if (active) {
                mX = event.getX();
                mY = event.getY();
                aileronPos = (byte) ((mX - (width / 2)) / (controlAreaWidth / 6));
                elevatorPos = (byte) (((height / 2) - mY) / (controlAreaHeight / 6));
                rudderPos = 0;
                pointer.setCenterX(mX);
                pointer.setCenterY(mY);
                if (aileronPos != prevAileronPos || elevatorPos != prevElevatorPos || rudderPos != prevRudderPos) {
                    Transmitter.transmit(new byte[]{aileronPos, elevatorPos, rudderPos});
                }
            }
        });
        controlRect.setOnMouseClicked(event -> {
            if (active) {
                getScene().setCursor(Cursor.DEFAULT);
            }
            else {
                mX = event.getX();
                mY = event.getY();
                pointer.setCenterX(mX);
                pointer.setCenterY(mY);
                getScene().setCursor(Cursor.NONE);
            }
            active = !active;
        });
        //serial connectivity
        serialConnect.setOnAction(event -> {
            if (Transmitter.connectToPort(serialPorts.getText())) {
                serialPorts.setDisable(true);
                serialConnect.setDisable(true);
            }
        });
        //ensure that com port gets closed
        setOnCloseRequest(event -> {
            Transmitter.closePort(serialPorts.getText());
        });
        //add scene
        setScene(new Scene(controlPane));
        //add style to the scene
        getScene().getStylesheets().add(Main.style);
        //move window to front
        toFront();
    }
}