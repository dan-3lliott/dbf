import com.fazecast.jSerialComm.*;

public class Transmitter {
    private static SerialPort serialPort;
    public static void transmit(byte[] pos) {
        serialPort.writeBytes(pos, 3);
    }
    public static boolean connectToPort(String portName) {
        serialPort = SerialPort.getCommPort(portName);
        return serialPort.openPort();
    }
    public static boolean closePort(String portName) {
        return serialPort.closePort();
    }
}