import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DiscordBot {

    public static DiscordBot INSTANCE;

    public JDABuilder jBuilder;
    private CommandManager cmdMan;

    public static void main(String args[]){
        try {
            new DiscordBot();
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }
    public DiscordBot() throws LoginException {

        INSTANCE = this;
        JDABuilder builder = JDABuilder.createDefault("NzIyNzM2Mjc3MjgzMTQzNzQw.XunanA._u809k145iuNw-dVzADjPfNIWOU");


        builder.setStatus(OnlineStatus.ONLINE);
        jBuilder = builder;


        this.cmdMan = new CommandManager();
        jBuilder.addEventListeners(new CommandListener());

        jBuilder.build();
        System.out.println("Server On");

        shutdown();
    }

    public void shutdown(){

        new Thread(() -> {
            String line = "";
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                while ((line = reader.readLine()) != null) {
                    if (line.equalsIgnoreCase("exit")) {
                        if (jBuilder != null) {
                            jBuilder.setStatus(OnlineStatus.OFFLINE);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public CommandManager getCmdMan() {
        return cmdMan;
    }
}
