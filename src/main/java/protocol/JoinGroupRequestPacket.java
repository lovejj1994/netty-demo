package protocol;

import lombok.Data;

@Data
public class JoinGroupRequestPacket extends Packet {
    public static Byte JOIN_GROUP_REQUEST = 9;
    private String groupId;

    @Override
    public Byte getCommand() {
        return JOIN_GROUP_REQUEST;
    }
}