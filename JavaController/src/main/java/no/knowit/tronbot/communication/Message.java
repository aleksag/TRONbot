package no.knowit.tronbot.communication;

public class Message {

	private int id;
	
	private Payload payload;
	
	public Message(int id, Payload payload) {
		this.id = id;
		this.payload = payload;
	}
	
	public int getId() {
		return id;
	}
	
	public Payload getPayload() {
		return payload;
	}
}
