package handle.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import protocol.LoginResponsePacket;
import util.SessionUtil;

import java.util.Date;

@Slf4j
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
//        log.info(new Date() + ": 客户端开始登录");

        // 创建登录对象
//        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
//        loginRequestPacket.setUserName("flash");
//        loginRequestPacket.setPassword("pwd");
//
//        // 写数据
//        ctx.channel().writeAndFlush(loginRequestPacket);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket loginResponsePacket) {
        if (loginResponsePacket.isSuccess()) {
            SessionUtil.bindSession(loginResponsePacket.getSession(), ctx.channel());
            log.info(new Date() + ": 客户端登录成功 ，用户信息为: " + loginResponsePacket.getSession());
        } else {
            log.info(new Date() + ": 客户端登录失败，原因：" + loginResponsePacket.getReason());
        }
    }
}