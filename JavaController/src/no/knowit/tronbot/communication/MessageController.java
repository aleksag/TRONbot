package no.knowit.tronbot.communication;

import java.util.Stack;

public class MessageController {
	
	Stack<Message> outQueue = new Stack<Message>();
	
	volatile int nextOutId = 0;
	
	public void sendMessage(Payload payload) {
		outQueue.add(new Message(getNextOutId(), payload));
	}
	
	private synchronized int getNextOutId() {
		int next = nextOutId++;
		
		if (next == Integer.MAX_VALUE) {
			next = 0;
			nextOutId = 0;
		}
		return next;
	}

	void ackReceived(int id) {
		Message message = outQueue.peek();
		if (message != null && message.getId() == id) {
			outQueue.pop();
		}
	}
	
	boolean hasMessage() {
		return !outQueue.isEmpty();
	}
	
	Message getNextMessage() {
		Message message = outQueue.peek();
		if (message == null) {
			throw new NullPointerException("No message on stack.");
		}
		return message;
	}
}
