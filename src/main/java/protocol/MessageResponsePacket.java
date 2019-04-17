package protocol;

import lombok.Data;

@Data
public class MessageResponsePacket extends Packet {

    public static Byte MESSAGE_RESPONSE = 4;

    private String message;
    private String fromUserId;
    private String fromUserName;

    @Override
    public Byte getCommand() {
        return MESSAGE_RESPONSE;
    }
}