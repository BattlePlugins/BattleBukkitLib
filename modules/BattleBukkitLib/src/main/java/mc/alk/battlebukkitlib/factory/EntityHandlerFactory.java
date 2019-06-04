package mc.alk.battlebukkitlib.factory;

import mc.alk.battlebukkitlib.handlers.IEntityHandler;
import mc.alk.battlebukkitlib.handlers.IEventHandler;
import mc.euro.version.Version;
import mc.euro.version.VersionFactory;
import org.bukkit.Server;

public class EntityHandlerFactory {

    public static IEntityHandler getNewInstance() {
        Version<Server> server = VersionFactory.getServerVersion();
        IEntityHandler handler = null;
        Class clazz = null;
        try {
            Class<?>[] args = {};
            if (server.isGreaterThanOrEqualTo("1.2.5") && server.isLessThan("1.4.5")) {
                clazz = Class.forName("mc.alk.battlebukkitlib.compat.v1_2_5.EntityHandler");
            } else if (server.isGreaterThanOrEqualTo("1.4.5")) {
                clazz = Class.forName("mc.alk.battlebukkitlib.compat.v1_4_5.EntityHandler");
            }

            handler = (IEntityHandler) clazz.getConstructor(args).newInstance((Object[]) args);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return (handler == null) ? IEntityHandler.NULL_HANDLER : handler;
    }
}
