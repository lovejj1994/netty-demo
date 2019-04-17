package protocol;

import lombok.Data;

@Data
public class MessageRequestPacket extends Packet {

    public static Byte MESSAGE_REQUEST = 3;

    private String toUserId;

    private String message;

    public MessageRequestPacket(String toUserId, String message) {
        this.toUserId = toUserId;
        this.message = message;
    }

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}