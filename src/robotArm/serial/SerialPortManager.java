package robotArm.serial;

import jssc.SerialPort;
import jssc.SerialPortException;

public class SerialPortManager {
    public static final String END_CHAR = "\r";
    public static final String COM_PORT = "COM5";
    private SerialPort serialPort;

    public static void main(String[] args) throws InterruptedException, SerialPortException {
        SerialPortManager serialPortManager = new SerialPortManager();
        serialPortManager.initSerialPort(new PortReaderAction() {
            @Override
            public void doAction(byte[] buffer) {
                for (int i = 0; i < buffer.length; i++) {
                    System.out.print((char) buffer[i]);
                }
            }
        });

        serialPortManager.writeToSerialPort("M18");

        serialPortManager.closePort();

    }

    private void closePort() throws SerialPortException {
        serialPort.closePort();
    }

    public void writeToSerialPort(String command) throws SerialPortException {
        serialPort.writeString(command + END_CHAR);
    }

    public void initSerialPort(PortReaderAction readerAction) throws InterruptedException {
        serialPort = new SerialPort(COM_PORT);
        try {
            serialPort.openPort();
            serialPort.setParams(SerialPort.BAUDRATE_9600,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
            serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN | SerialPort.FLOWCONTROL_RTSCTS_OUT);
            serialPort.addEventListener(new SerialPortReader(serialPort, readerAction), SerialPort.MASK_RXCHAR);
        } catch (SerialPortException ex) {
            System.out.println(ex);
        }
    }
}
