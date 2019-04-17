package json.codec;

import io.netty.buffer.ByteBuf;
import json.JSONSerializer;
import json.Serializer;
import protocol.*;

import java.util.HashMap;
import java.util.Map;

import static protocol.LoginRequestPacket.LOGIN_REQUEST;
import static protocol.LoginResponsePacket.LOGIN_RESPONSE_REQUEST;
import static protocol.MessageRequestPacket.MESSAGE_REQUEST;
import static protocol.MessageResponsePacket.MESSAGE_RESPONSE;


/**
 * @program: netty-demo
 * @description: ${description}
 * @author: qian.pan
 * @create: 2019/03/25 18:17
 **/
public class PacketCodeC {

    public static final PacketCodeC INSTANCE = new PacketCodeC();

    public static final int MAGIC_NUMBER = 0x12345678;
    private static final Map<Byte, Class<? extends Packet>> packetTypeMap;
    private static final Map<Byte, Serializer> serializerMap;

    static {
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(LOGIN_REQUEST, LoginRequestPacket.class);
        packetTypeMap.put(LOGIN_RESPONSE_REQUEST, LoginResponsePacket.class);
        packetTypeMap.put(MESSAGE_REQUEST, MessageRequestPacket.class);
        packetTypeMap.put(MESSAGE_RESPONSE, MessageResponsePacket.class);

        serializerMap = new HashMap<>();
        Serializer serializer = new JSONSerializer();
        serializerMap.put(serializer.getSerializerAlgorithm(), serializer);
    }


    public ByteBuf encode(ByteBuf byteBuf, Packet packet) {
        // 2. 序列化 Java 对象
        byte[] bytes = Serializer.DEFAULT.serialize(packet);
        // 3. 实际编码过程

//        魔数
        byteBuf.writeInt(MAGIC_NUMBER);
//        版本号
        byteBuf.writeByte(packet.getVersion());
//        序列号算法
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
//        指令
        byteBuf.writeByte(packet.getCommand());
//        数据长度
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);

        return byteBuf;
    }

    public Packet decode(ByteBuf byteBuf) {
        // 跳过 magic number
        byteBuf.skipBytes(4);

        // 跳过版本号
        byteBuf.skipBytes(1);

        // 序列化算法标识
        byte serializeAlgorithm = byteBuf.readByte();

        // 指令
        byte command = byteBuf.readByte();

        // 数据包长度
        int length = byteBuf.readInt();

        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        Class<? extends Packet> requestType = getRequestType(command);
        Serializer serializer = getSerializer(serializeAlgorithm);

        if (requestType != null && serializer != null) {
            return serializer.deserialize(requestType, bytes);
        }

        return null;
    }

    private Serializer getSerializer(byte serializeAlgorithm) {
        return serializerMap.get(serializeAlgorithm);
    }

    private Class<? extends Packet> getRequestType(byte command) {
        return packetTypeMap.get(command);
    }

}
