package sample;

import static java.lang.String.*;

public class Controller {
    public static final int xStep = 1;
    public static final int yStep = 1;
    public static final int zStep = 1;

    public static final int gripperStep = 1;
    public static final String COMMAND_ON = "M17";
    public static final String COMMAND_OFF = "M18";
    public static final String COMMAND_GRIPPER_CLOSE = "M3 T%s";
    public static final String COMMAND_GRIPPER_OPEN = "M5 T%s";
    public static final String COMMAND_MOVE_TO_XYZ = "G0 X%s Y%s Z%s";

    private int x;
    private int y;
    private int z;

    private int gripper;

    public void up() {
        System.out.println("up");
        z += zStep;
        sendLocationToSerialPort(x, y, z);
    }

    public void down() {
        System.out.println("down");
        z -= zStep;
        sendLocationToSerialPort(x, y, z);
    }

    public void left() {
        System.out.println("left");
        x += xStep;
        sendLocationToSerialPort(x, y, z);
    }

    public void right() {
        System.out.println("right");
        x -= xStep;
        sendLocationToSerialPort(x, y, z);
    }

    public void forward() {
        System.out.println("forward");
        y += yStep;
        sendLocationToSerialPort(x, y, z);
    }

    public void back() {
        System.out.println("back");
        y -= yStep;
        sendLocationToSerialPort(x, y, z);
    }

    public void gripperClose() {
        System.out.println("gripperClose");
        gripper -= gripperStep;
        closeGripper(gripper);
    }

    public void gripperOpen() {
        System.out.println("gripperOpen");
        gripper -= gripperStep;
        openGripper(gripper);
    }

    public void on() {
        System.out.println("on");
        sendToSerialPort(COMMAND_ON);

    }

    public void off() {
        System.out.println("off");
        sendToSerialPort(COMMAND_OFF);
    }

    private void closeGripper(int gripper) {
        sendToSerialPort(String.format(COMMAND_GRIPPER_CLOSE, gripper));
    }

    private void openGripper(int gripper) {
        sendToSerialPort(String.format(COMMAND_GRIPPER_OPEN, gripper));
    }

    private void sendLocationToSerialPort(int x, int y, int z) {
        sendToSerialPort(format(COMMAND_MOVE_TO_XYZ, x, y, z));
    }


    private void sendToSerialPort(String command) {

    }


}
