import net.dv8tion.jda.api.entities.AudioChannel;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.concurrent.TimeUnit;

public class CommandListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String message = event.getMessage().getContentDisplay();

        if (event.isFromType(ChannelType.TEXT)) {
            if (message.startsWith("#")) {

                String[] args = message.substring(1).split(" ");

                if (args.length > 0) {
                    if (!DiscordBot.INSTANCE.getCmdMan().perform(args[0], event.getMember(), event.getTextChannel(), event.getMessage())) {
                        event.getChannel().sendMessage("Syntax").complete().delete().queueAfter(3, TimeUnit.SECONDS);
                    }
                }

            }
        }
    }
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}
