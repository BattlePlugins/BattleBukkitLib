package mc.alk.battlebukkitlib.handlers;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public interface ISchedulerHandler {

    int scheduleAsyncTask(Plugin plugin, Runnable task, long ticks);

    public static final ISchedulerHandler DEFAULT_HANDLER = new ISchedulerHandler() {

        @Override
        public int scheduleAsyncTask(Plugin plugin, Runnable task, long ticks) {
            return Bukkit.getScheduler().scheduleAsyncDelayedTask(plugin, task,ticks);
        }
    };
}
