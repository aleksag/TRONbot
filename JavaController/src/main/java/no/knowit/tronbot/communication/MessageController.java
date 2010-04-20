package no.knowit.tronbot.communication;

import java.util.ArrayList;
import java.util.List;

public class MessageController {

	List<Message> outQueue = new ArrayList<Message>();

	volatile int nextOutId = 0;

	public void sendMessage(Payload payload) {
		outQueue.add(new Message(getNextOutId(), payload.toString()));
	}
	
	public void sendMessage(String payload) {
	    outQueue.add(new Message(getNextOutId(), payload));
	}

	public void receiveMessage(Message msg) {
	    
	}

	private synchronized int getNextOutId() {
		int next = nextOutId++;

		if (next == Integer.MAX_VALUE) {
			next = 0;
			nextOutId = 0;
		}
		return next;
	}

	synchronized void ackReceived(int id) {
		if (outQueue.isEmpty()) {
			return;
		}

		Message message = outQueue.get(0);
		if (message.getId() == id) {
			outQueue.remove(0);
		}
	}

	boolean hasMessage() {
		return !outQueue.isEmpty();
	}

	synchronized Message getNextMessage() {
		if (outQueue.isEmpty()) {
			throw new NullPointerException("No message on stack.");
		}

		return outQueue.get(0);
	}

}
