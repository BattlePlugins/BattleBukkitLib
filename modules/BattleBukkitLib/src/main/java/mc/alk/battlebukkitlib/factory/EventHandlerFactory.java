package mc.alk.battlebukkitlib.factory;

import mc.alk.battlebukkitlib.handlers.IEventHandler;
import mc.euro.version.Version;
import mc.euro.version.VersionFactory;
import org.bukkit.Server;

public class EventHandlerFactory {

    public static IEventHandler getNewInstance() {
        Version<Server> server = VersionFactory.getServerVersion();
        IEventHandler handler = null;
        Class clazz = null;
        try {
            Class<?>[] args = {};
            if (server.isGreaterThanOrEqualTo("1.2.5") && server.isLessThan("1.6.1")) {
                clazz = Class.forName("mc.alk.battlebukkitlib.compat.v1_2_5.EventHandler");
            } else if (server.isGreaterThanOrEqualTo("1.6.1")) {
                clazz = Class.forName("mc.alk.battlebukkitlib.compat.v1_6_R1.EventHandler");
            }

            handler = (IEventHandler) clazz.getConstructor(args).newInstance((Object[]) args);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return (handler == null) ? IEventHandler.NULL_HANDLER : handler;
    }
}
