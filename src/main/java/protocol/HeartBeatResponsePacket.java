package protocol;

public class HeartBeatResponsePacket extends Packet {
    public static Byte HEARTBEAT_RESPONSE = 16;

    @Override
    public Byte getCommand() {
        return HEARTBEAT_RESPONSE;
    }
}