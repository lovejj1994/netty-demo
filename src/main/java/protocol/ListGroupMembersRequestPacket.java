package protocol;

import lombok.Data;

@Data
public class ListGroupMembersRequestPacket extends Packet {

    public static Byte LIST_GROUP_MEMBERS_REQUEST = 8;

    private String groupId;

    @Override
    public Byte getCommand() {
        return LIST_GROUP_MEMBERS_REQUEST;
    }
}