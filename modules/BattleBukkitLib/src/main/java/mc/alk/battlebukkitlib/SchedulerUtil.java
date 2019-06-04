package mc.alk.battlebukkitlib;

import mc.alk.battlebukkitlib.factory.SchedulerHandlerFactory;
import mc.alk.battlebukkitlib.handlers.ISchedulerHandler;
import org.bukkit.plugin.Plugin;

public class SchedulerUtil {

    private static ISchedulerHandler handler = SchedulerHandlerFactory.getNewInstance();

    public static int scheduleAsyncTask(Plugin plugin, Runnable task, long ticks) {
        return handler.scheduleAsyncTask(plugin, task, ticks);
    }
}
