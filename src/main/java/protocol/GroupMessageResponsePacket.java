package protocol;

import lombok.Data;
import session.Session;

@Data
public class GroupMessageResponsePacket extends Packet {
    public static Byte GROUP_MESSAGE_RESPONSE = 14;
    private String fromGroupId;

    private Session fromUser;

    private String message;

    @Override
    public Byte getCommand() {
        return GROUP_MESSAGE_RESPONSE;
    }
}