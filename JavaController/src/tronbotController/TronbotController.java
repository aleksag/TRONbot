package tronbotController;

import java.io.IOException;
import java.io.OutputStream;

import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.UnsupportedCommOperationException;
import tronbotSerial.TronbotSerial;

public class TronbotController {
	public static final char LEFT = '1';
	public static final char RIGHT = '2';
	public static final char BACKWARD_LEFT = '3';
	public static final char BACKWARD_RIGHT = '4';
	public static final char FORWARD = '5';
	public static final char BACKWARD = '6';
	public static final char STOP = '7';
	private TronbotSerial serialComm;
	
	public TronbotController(String serialPort) throws Exception{
		init(serialPort);
	}
	public void init(String port) throws Exception{
		serialComm = new TronbotSerial(port, 115200);
		try {
			serialComm.openPort();
		} catch (UnsupportedCommOperationException e) {
			serialComm.close();
			throw new Exception("Unknown error");
		} catch (NoSuchPortException e) {
			serialComm.close();
			throw new Exception("Could not find port");
		} catch (PortInUseException e) {
			serialComm.close();
			throw new Exception("Port already in use");
		}
	}
	public void run(char direction) throws Exception{
		if(serialComm == null){
			throw new Exception("No connection to TRONbot");
		}
		OutputStream serialOutputStream = serialComm.getSerialOutputStream();
		serialOutputStream.write(direction);
		serialOutputStream.flush();
	}
	
	public void closeConnection() throws Exception{
		if(serialComm == null){
			throw new Exception("No connection to TRONbot");
		}
		serialComm.close();
	}
	
}
