package handle.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import protocol.MessageRequestPacket;
import protocol.MessageResponsePacket;
import session.Session;
import util.SessionUtil;

import java.util.Date;

@Slf4j
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket messageRequestPacket) {

        // 1.拿到消息发送方的会话信息
        Session session = SessionUtil.getSession(ctx.channel());
        log.info(new Date() + "服务端收到 {} 的客户端消息: {}", session, messageRequestPacket.getMessage());

        // 2.通过消息发送方的会话信息构造要发送的消息
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setFromUserId(session.getUserId());
        messageResponsePacket.setFromUserName(session.getUserName());
        messageResponsePacket.setMessage(messageRequestPacket.getMessage());

        // 3.拿到消息接收方的 channel
        Channel toUserChannel = SessionUtil.getChannel(messageRequestPacket.getToUserId());

        // 4.将消息发送给消息接收方
        if (toUserChannel != null && SessionUtil.hasLogin(toUserChannel)) {
            toUserChannel.writeAndFlush(messageResponsePacket);
        } else {
            log.error("[{}] 不在线，发送失败!", messageRequestPacket.getToUserId());
        }


        // 处理消息
//        log.info(new Date() + ": 收到客户端消息: " + messageRequestPacket.getMessage());
//
//        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
//        messageResponsePacket.setMessage("服务端回复【" + messageRequestPacket.getMessage() + "】");
//        ctx.channel().writeAndFlush(messageResponsePacket);
    }
}