package protocol;

import lombok.Data;
import session.Session;

@Data
public class LoginResponsePacket extends Packet {

    public static Byte LOGIN_RESPONSE_REQUEST = 2;

    private String reason;

    private boolean success;

    Session session;

    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE_REQUEST;
    }
}