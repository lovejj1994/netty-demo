package handle.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import protocol.QuitGroupResponsePacket;

@Slf4j
public class QuitGroupResponseHandler extends SimpleChannelInboundHandler<QuitGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupResponsePacket responsePacket) {
        if (responsePacket.isSuccess()) {
            log.info("退出群聊[" + responsePacket.getGroupId() + "]成功！");
        } else {
            log.info("退出群聊[" + responsePacket.getGroupId() + "]失败！");
        }

    }
}