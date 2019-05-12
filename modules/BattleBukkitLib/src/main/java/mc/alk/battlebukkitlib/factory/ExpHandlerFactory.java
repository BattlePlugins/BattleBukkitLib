package mc.alk.battlebukkitlib.factory;

import mc.alk.battlebukkitlib.handlers.IExpHandler;
import mc.euro.version.Version;
import mc.euro.version.VersionFactory;
import org.bukkit.Server;

/**
 * 
 * @author Nikolai
 */
public class ExpHandlerFactory {

    public static IExpHandler getNewInstance() {
        Version<Server> server = VersionFactory.getServerVersion();
        IExpHandler handler = null;
        Class clazz = null;
        try {
            Class<?>[] args = {};
            if (server.isGreaterThanOrEqualTo("1.2.5")) {
                clazz = Class.forName("mc.alk.battlebukkitlib.compat.v1_2_5.ExpHandler");
            } else if (server.isGreaterThan("1.2.5") && server.isGreaterThanOrEqualTo("1.3.2")) {
                clazz = Class.forName("mc.alk.battlebukkitlib.compat.v1_3_2.InventoryHandler");
            } else if (server.isGreaterThan("1.3.2") && server.isGreaterThanOrEqualTo("1.8")) {
                clazz = Class.forName("mc.alk.battlebukkitlib.compat.v1_8_R1.InventoryHandler");
            }

            handler = (IExpHandler) clazz.getConstructor(args).newInstance((Object[]) args);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return (handler == null) ? IExpHandler.DEFAULT_HANDLER : handler;
    }
}
