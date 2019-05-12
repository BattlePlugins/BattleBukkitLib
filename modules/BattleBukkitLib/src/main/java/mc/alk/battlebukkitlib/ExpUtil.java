package mc.alk.battlebukkitlib;

import mc.alk.battlebukkitlib.factory.ExpHandlerFactory;
import mc.alk.battlebukkitlib.handlers.IExpHandler;

/**
 * 
 * @author Nikolai
 */
public class ExpUtil {

    private static IExpHandler handler = ExpHandlerFactory.getNewInstance();

    public static int getExpToLevel(final int level) {
        return handler.getExpToLevel(level);
    }

    public static int getExpAtLevel(final int level) {
        return handler.getExpAtLevel(level);
    }
}
