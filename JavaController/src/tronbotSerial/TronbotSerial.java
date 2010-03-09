package tronbotSerial;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

public class TronbotSerial {

	private String serialport;
	private int baudrate;
	SerialPort serialPort = null;

	public TronbotSerial(String serialport, int baudrate) {
		this.serialport = serialport;
		this.baudrate = baudrate;
	}

	public void openPort() throws UnsupportedCommOperationException,
			NoSuchPortException, PortInUseException {
		CommPortIdentifier portId = CommPortIdentifier
				.getPortIdentifier(serialport);
		serialPort = (SerialPort) portId.open("TRONbot", 5000);
		serialPort.setSerialPortParams(baudrate, SerialPort.DATABITS_8,
				SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
		serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
	}

	public InputStream getSerialInputStream() {
		try {
			return serialPort.getInputStream();
		} catch (IOException e) {
			return null;
		}
	}
	public OutputStream getSerialOutputStream() {
		try {
			return serialPort.getOutputStream();
		} catch (IOException e) {
			return null;
		}
	}

	public void close() {
		serialPort.close();
	}
}
