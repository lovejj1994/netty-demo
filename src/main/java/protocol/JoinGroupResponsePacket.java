package protocol;

import lombok.Data;

@Data
public class JoinGroupResponsePacket extends Packet {
    public static Byte JOIN_GROUP_RESPONSE = 10;
    private String groupId;

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return JOIN_GROUP_RESPONSE;
    }
}