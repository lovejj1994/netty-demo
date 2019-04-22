package command;

import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import protocol.MessageRequestPacket;

import java.util.Scanner;

@Slf4j
public class SendToUserConsoleCommand implements ConsoleCommand {

    private static final String USER_ID_SPLITER = ",";

    @Override
    public void exec(Scanner scanner, Channel channel) {
        log.info("请输入message信息: ");
        String toUserId = scanner.next();
        String message = scanner.next();
        channel.writeAndFlush(new MessageRequestPacket(toUserId, message));
    }

}