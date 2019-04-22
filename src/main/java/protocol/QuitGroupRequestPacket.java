package protocol;

import lombok.Data;

@Data
public class QuitGroupRequestPacket extends Packet {

    public static Byte QUIT_GROUP_REQUEST = 12;
    private String groupId;

    @Override
    public Byte getCommand() {

        return QUIT_GROUP_REQUEST;
    }
}