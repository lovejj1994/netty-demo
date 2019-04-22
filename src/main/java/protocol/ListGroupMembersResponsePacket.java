package protocol;

import lombok.Data;
import session.Session;

import java.util.List;

@Data
public class ListGroupMembersResponsePacket extends Packet {

    public static Byte LIST_GROUP_MEMBERS_RESPONSE = 7;
    private String groupId;

    private List<Session> sessionList;

    @Override
    public Byte getCommand() {
        return LIST_GROUP_MEMBERS_RESPONSE;
    }
}