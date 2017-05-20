package robotArm.serial;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;


class SerialPortReader implements SerialPortEventListener {

    private SerialPort serialPort;

    private PortReaderAction action;

    public SerialPortReader(SerialPort serialPort, PortReaderAction action) {
        this.serialPort = serialPort;
        this.action = action;
    }

    public void serialEvent(SerialPortEvent event) {
        if (event.isRXCHAR()) {//If data is available
            //System.out.println(event.getEventValue());
            if (event.getEventValue() > 4) {//Check bytes count in the input buffer

                //Read data, if 10 bytes available
                try {
                    byte buffer[] = serialPort.readBytes();
                    action.doAction(buffer);
                } catch (SerialPortException ex) {
                    System.out.println(ex);
                }
            }
        } else if (event.isCTS()) {//If CTS line has changed state
            if (event.getEventValue() == 1) {//If line is ON
                System.out.println("CTS - ON");
            } else {
                System.out.println("CTS - OFF");
            }
        } else if (event.isDSR()) {///If DSR line has changed state
            if (event.getEventValue() == 1) {//If line is ON
                System.out.println("DSR - ON");
            } else {
                System.out.println("DSR - OFF");
            }
        }
    }
}