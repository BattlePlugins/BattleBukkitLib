package mc.alk.util.factory;

import mc.alk.battlebukkitlib.handlers.IInventoryHandler;
import mc.euro.version.Version;
import mc.euro.version.VersionFactory;

import org.bukkit.Server;

public class InventoryHandlerFactory {

    public static IInventoryHandler getNewInstance() {
        Version<Server> server = VersionFactory.getServerVersion();
        IInventoryHandler handler = null;
        Class clazz = null;

        try {
            Class<?>[] args = {};
            if (server.isGreaterThanOrEqualTo("1.4.5")) {
                clazz = Class.forName("mc.alk.util.compat.v1_4_5.InventoryHandler");
            } else if (server.isLessThan("1.4.5")) {
                clazz = Class.forName("mc.alk.util.compat.v1_4_2.InventoryHandler");
            }
            handler = (IInventoryHandler) clazz.getConstructor(args).newInstance((Object[]) args);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return (handler == null) ? IInventoryHandler.NULL_HANDLER : handler;
    }

}
