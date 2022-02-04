import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.exceptions.ErrorResponseException;
import net.dv8tion.jda.api.utils.ConcurrentSessionController;

import java.sql.SQLOutput;
import java.util.concurrent.TimeUnit;

public class StubsCommand implements ServerCommand{
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

    @Override
    public void performCommand(Member m, TextChannel channel, Message message) {
        channel.deleteMessageById(message.getId()).queue();
        if(m.hasPermission(channel, Permission.MESSAGE_MANAGE)){

            String args[] = message.getContentDisplay().split(" ");

            Member member = null;
            VoiceChannel fromChannel = null;
            if (args[0].equalsIgnoreCase("#stubs")) {
                if(m.getVoiceState().getChannel() != null){
                    fromChannel = (VoiceChannel) m.getVoiceState().getChannel();
                    for(Member mem : fromChannel.getMembers()){

                        if(mem.getNickname() != null){
                            if(mem.getNickname().equalsIgnoreCase(args[1])){
                                member = mem;
                            }
                        }
                        if(mem.getUser().getName() != null){
                            if(mem.getUser().getName().equalsIgnoreCase(args[1])){
                                member = mem;
                            }
                        }
                    }
                    System.out.print(member);
                }
                VoiceChannel toChannel = m.getGuild().getVoiceChannelById(939202443697324072l);
                VoiceChannel toChannel2 = m.getGuild().getVoiceChannelById(939202297689415722l);
                VoiceChannel fromChannel2 = m.getGuild().getVoiceChannelById(fromChannel.getId());
                System.out.println(toChannel);

                int amount = 3;


                    for (int i = 0; i < amount; i++) {
                        toChannel.getGuild().moveVoiceMember(member, toChannel).queue();
                        toChannel2.getGuild().moveVoiceMember(member, toChannel2).queue();
                    }
                    fromChannel2.getGuild().moveVoiceMember(member, fromChannel2).queue();
                }
            }
        }

    }
