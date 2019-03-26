package protocol;

import lombok.Data;

@Data
public class LoginResponsePacket extends Packet {

    public static Byte LOGIN_RESPONSE_REQUEST = 2;

    private String reason;

    private boolean success;

    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE_REQUEST;
    }
}