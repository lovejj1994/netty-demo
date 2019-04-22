package protocol;

import lombok.Data;

import java.util.List;

@Data
public class CreateGroupResponsePacket extends Packet {

    public static Byte CREATE_GROUP_RESPONSE = 5;

    private String groupId;
    private boolean success;

    private List<String> UserNameList;

    @Override
    public Byte getCommand() {
        return CREATE_GROUP_RESPONSE;
    }
}