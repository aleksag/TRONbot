package no.knowit.tronbot;

import java.awt.EventQueue;

import no.knowit.tronbot.communication.MessageController;
import no.knowit.tronbot.communication.SerialPortController;
import no.knowit.tronbot.gui.RemoteControl;

public class TronBot {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		final MessageController messageController = new MessageController();
		final SerialPortController serialPortController = new SerialPortController(messageController);
		
		Runtime.getRuntime().addShutdownHook(new Thread() {
		    public void run() { 
		    	serialPortController.close(); 
	    	}
		});
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new RemoteControl(messageController);
					serialPortController.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
