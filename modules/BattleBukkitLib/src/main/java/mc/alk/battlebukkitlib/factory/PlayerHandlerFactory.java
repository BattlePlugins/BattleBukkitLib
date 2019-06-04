package mc.alk.battlebukkitlib.factory;

import mc.alk.battlebukkitlib.handlers.IPlayerHandler;
import mc.euro.version.Version;
import mc.euro.version.VersionFactory;
import org.bukkit.Server;

public class PlayerHandlerFactory {

    public static IPlayerHandler getNewInstance() {
        Version<Server> server = VersionFactory.getServerVersion();
        IPlayerHandler handler = null;
        Class clazz = null;
        try {
            Class<?>[] args = {};
            if (server.isGreaterThanOrEqualTo("1.2.5") && server.isLessThan("1.6.1")) {
                clazz = Class.forName("mc.alk.battlebukkitlib.compat.v1_2_5.PlayerHandler");
            } else if (server.isGreaterThanOrEqualTo("1.6.1") && server.isLessThan("1.7.8")) {
                clazz = Class.forName("mc.alk.battlebukkitlib.compat.v1_6_R1.PlayerHandler");
            } else if (server.isGreaterThanOrEqualTo("1.7.8") && server.isLessThan("1.9")) {
                clazz = Class.forName("mc.alk.battlebukkitlib.compat.v1_7_R3.PlayerHandler");
            } else if (server.isGreaterThanOrEqualTo("1.9")) {
                clazz = Class.forName("mc.alk.battlebukkitlib.compat.v1_9_R1.PlayerHandler");
            }

            handler = (IPlayerHandler) clazz.getConstructor(args).newInstance((Object[]) args);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return (handler == null) ? IPlayerHandler.NULL_HANDLER : handler;
    }
}
