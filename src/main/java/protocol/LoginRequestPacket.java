package protocol;

import lombok.Data;

@Data
public class LoginRequestPacket extends Packet {

    public static Byte LOGIN_REQUEST = 1;

    private Integer userId;

    private String username;

    private String password;

    @Override
    public Byte getCommand() {
        return LOGIN_REQUEST;
    }
}