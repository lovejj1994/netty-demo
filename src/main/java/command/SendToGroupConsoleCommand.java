package command;

import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import protocol.GroupMessageRequestPacket;

import java.util.Scanner;

@Slf4j
public class SendToGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        log.info("发送消息给某个某个群组：");

        String toGroupId = scanner.next();
        String message = scanner.next();
        channel.writeAndFlush(new GroupMessageRequestPacket(toGroupId, message));

    }
}