package no.knowit.tronbot.communication;

public class Message {

    private String payload;
	
    private int id;

    private int contentLength;
    
    private byte type;
	
	public Message(int id, String payload) {
		this.id = id;
		this.payload = payload;
		setContentLength();
	}

	public String getPayload() {
	    return payload;
	}

	/**
	 * The type of the message 
	 * 
	 * @return
	 */
    public byte getType() {
        return type;
    }

    protected void setType(byte type) {
        this.type = type;
    }
	
	/**
	 * Identifies the message
	 * @return
	 */
	protected int getId() {
		return id;
	}

    protected int getContentLength() {
        return contentLength;
    }

    private void setContentLength() {
        if (payload == null) {
            contentLength = 0;
        } else {
            contentLength = payload.getBytes().length;
        }
    }
}
