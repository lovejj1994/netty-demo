package handle.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;
import util.SessionUtil;

import java.util.concurrent.TimeUnit;

@Slf4j
public class IMIdleStateHandler extends IdleStateHandler {

    private static final int READER_IDLE_TIME = 15;

    public IMIdleStateHandler() {
        super(READER_IDLE_TIME, 0, 0, TimeUnit.SECONDS);
    }

    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) {
        log.info(READER_IDLE_TIME + "秒内未读到数据，关闭连接,客户信息为 {}", SessionUtil.getSession(ctx.channel()));
        ctx.channel().close();
    }
}