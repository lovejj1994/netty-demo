package protocol;

import lombok.Data;

import java.util.List;

@Data
public class CreateGroupRequestPacket extends Packet {

    public static Byte CREATE_GROUP_REQUEST = 6;

    private List<String> userIdList;

    @Override
    public Byte getCommand() {
        return CREATE_GROUP_REQUEST;
    }
}