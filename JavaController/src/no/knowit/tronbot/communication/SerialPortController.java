package no.knowit.tronbot.communication;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

public class SerialPortController implements Runnable, SerialPortEventListener {
	/** The port we're normally going to use. */
	private static final String PORT_NAMES[] = { "/dev/tty.usbserial-A6008lIf", // Mac
		// OS
		// X
		"/dev/ttyUSB0", // Linux
		"COM12", // Windows
	};
	/** Buffered input stream from the port */
	private InputStream input;
	/** The output stream to the port */
	private OutputStream output;
	/** Milliseconds to block while waiting for port open */
	private static final int TIME_OUT = 2000;
	/** Default bits per second for COM port. */
	private static final int DATA_RATE = 9600;

	SerialPort serialPort = null;

	MessageController msgController;
	private volatile boolean running = false;

	public SerialPortController(MessageController messageController) {
		this.msgController = messageController;
		initialize();
	}

	@Override
	public void run() {
		while (running ) {
			if (msgController.hasMessage()) {
				Message msg = msgController.getNextMessage();
				try {
					sendMessage(msg);
				} catch (IOException e) {}
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {}
		}
	}

	@SuppressWarnings("unchecked")
	private void initialize() {
		CommPortIdentifier portId = null;
		Enumeration<CommPortIdentifier> portEnum = CommPortIdentifier.getPortIdentifiers();

		// iterate through, looking for the port
		while (portEnum.hasMoreElements()) {
			CommPortIdentifier currPortId = portEnum
			.nextElement();
			for (String portName : PORT_NAMES) {
				if (currPortId.getName().equals(portName)) {
					portId = currPortId;
					break;
				}
			}
		}

		if (portId == null) {
			throw new RuntimeException("Could not find COM port.");
		}

		try {
			// open serial port, and use class name for the appName.
			serialPort = (SerialPort) portId.open(this.getClass().getName(),
					TIME_OUT);

			// set port parameters
			serialPort.setSerialPortParams(DATA_RATE, SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

			// open the streams
			input = serialPort.getInputStream();
			output = serialPort.getOutputStream();

			// add event listeners
			serialPort.addEventListener(this);
			serialPort.notifyOnDataAvailable(true);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


	public void start() {
		running = true;
	}

	private void sendMessage(Message msg) throws IOException {
		String s = msg.getId() + msg.getPayload().toString();
		output.write(s.getBytes());
	}

	/**
	 * Handle an event on the serial port. Read the data and print it.
	 */
	public synchronized void serialEvent(SerialPortEvent oEvent) {
		if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			try {
				int available = input.available();
				byte chunk[] = new byte[available];
				input.read(chunk, 0, available);
				msgController.ackReceived(Integer.parseInt(new String(chunk)));
			} catch (Exception e) {
				System.err.println(e.toString());
			}
		}
		// Ignore all the other eventTypes, but you should consider the other
		// ones.
	}

	/**
	 * This should be called when you stop using the port. This will prevent
	 * port locking on platforms like Linux.
	 */
	public synchronized void close() {
		running = false;
		if (serialPort != null) {
			serialPort.removeEventListener();
			serialPort.close();
		}
	}
}
