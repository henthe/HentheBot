import javax.security.auth.login.LoginException;

import io.github.cdimascio.dotenv.Dotenv;
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
        Dotenv dotenv = Dotenv.load();

        INSTANCE = this;
        System.out.println(dotenv.get("BOT_TOKEN"));
        JDABuilder builder = JDABuilder.createDefault(dotenv.get("BOT_TOKEN").toString());


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
