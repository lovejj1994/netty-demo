package protocol;

public class HeartBeatRequestPacket extends Packet {
    public static Byte HEARTBEAT_REQUEST = 15;

    @Override
    public Byte getCommand() {
        return HEARTBEAT_REQUEST;
    }
}