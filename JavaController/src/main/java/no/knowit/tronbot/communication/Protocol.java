package no.knowit.tronbot.communication;

public class Protocol {
    // Header
    // | Type (8 bits) | ID (32 bits) | ContentLength (32 bits) | Reserved (32 bits)
    
    // header size in bytes
    private int headerSize = (8 + 32 + 32 + 32) / 8;
    
    protected byte[] getMessageBytes(Message msg) {
        int offset = 0;
        byte[] msgBytes = new byte[headerSize + msg.getContentLength()];
        // type
        msgBytes[offset] = msg.getType();
        offset++;
        // id
        byte[] msgId = intToByteArray(msg.getId());
        System.arraycopy(msgId, 0, msgBytes, offset, msgId.length);
        offset += msgId.length;
        // contentLength
        byte[] contentLength = intToByteArray(msg.getContentLength());
        System.arraycopy(contentLength, 0, msgBytes, offset, contentLength.length);
        offset += contentLength.length;
        // reserved
        byte[] reserved = new byte[] {(byte)1, (byte)1, (byte)1, (byte)1}; 
        System.arraycopy(reserved, 0, msgBytes, offset, reserved.length);
        offset += reserved.length;
        // content
        byte[] contentBytes = msg.getPayload().getBytes();
        System.arraycopy(contentBytes, 0, msgBytes, offset, contentBytes.length);
        
        return msgBytes;
    }
    
    protected Message getMessage(byte[] msgBytes) {
        int offset = 0;
        // type
        byte type = msgBytes[0];
        offset++; 
        // id
        byte[] idBytes = new byte[4];
        System.arraycopy(msgBytes, offset, idBytes, 0, idBytes.length);
        offset += idBytes.length;
        int id = byteArrayToInt(idBytes);
        // contentLength
        byte[] contentLengthBytes = new byte[4];
        System.arraycopy(msgBytes, offset, contentLengthBytes, 0, contentLengthBytes.length);
        offset += contentLengthBytes.length;
        int contentLength = byteArrayToInt(contentLengthBytes);
        // reserved
        offset += 4;
        // content
        byte[] contentBytes = new byte[contentLength];
        System.arraycopy(msgBytes, offset, contentBytes, 0, contentBytes.length);
        String content = new String(contentBytes);
        
        Message msg = new Message(id, content);
        msg.setType(type);
        return msg;
    }
    
    
    private byte[] intToByteArray(int value) {
        return new byte[] {
                (byte)((value >> 24) & 0xFF),
                (byte)((value >> 16) & 0xFF),
                (byte)((value >> 8) & 0xFF),
                (byte)(value & 0xFF)};
    }
    
    private byte[] shortToByteArray(short value) {
        return new byte[] {
                (byte)((value >> 8) & 0xFF),
                (byte)(value & 0xFF)};
    }

    private int byteArrayToInt(byte [] b) {
        return (b[0] << 24)
                + ((b[1] & 0xFF) << 16)
                + ((b[2] & 0xFF) << 8)
                + (b[3] & 0xFF);
    }

    private short byteArrayToShort(byte [] b) {
        return (short)((b[2] << 8)
                + (b[3] & 0xFF));
    }
}
