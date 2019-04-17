package handle.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import protocol.LoginRequestPacket;
import protocol.LoginResponsePacket;
import session.Session;
import util.SessionUtil;

import java.util.UUID;

@Slf4j
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) {
        // 登录校验
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(loginRequestPacket.getVersion());
        if (valid(loginRequestPacket)) {
            Session session = new Session(UUID.randomUUID().toString(), loginRequestPacket.getUserName());
            loginResponsePacket.setSession(session);

            SessionUtil.bindSession(session, ctx.channel());
            loginResponsePacket.setSuccess(true);
        } else {
            loginResponsePacket.setReason("账号密码校验失败");
            loginResponsePacket.setSuccess(false);
        }
        // 编码
        ctx.channel().writeAndFlush(loginResponsePacket);
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }

    // 用户断线之后取消绑定
    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        log.info("用户断线之后取消绑定 {}", SessionUtil.getSession(ctx.channel()));
        SessionUtil.unBindSession(ctx.channel());
    }
}