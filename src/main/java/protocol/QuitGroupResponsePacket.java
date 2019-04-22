package protocol;

import lombok.Data;

@Data
public class QuitGroupResponsePacket extends Packet {
    public static Byte QUIT_GROUP_RESPONSE = 11;
    private String groupId;

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return QUIT_GROUP_RESPONSE;
    }
}