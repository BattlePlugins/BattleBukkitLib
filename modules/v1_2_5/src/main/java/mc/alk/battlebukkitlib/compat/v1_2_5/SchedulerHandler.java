package mc.alk.battlebukkitlib.compat.v1_2_5;

import mc.alk.battlebukkitlib.handlers.ISchedulerHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class SchedulerHandler implements ISchedulerHandler {

    @Override
    public int scheduleAsyncTask(Plugin plugin, Runnable task, long ticks) {
        return Bukkit.getScheduler().scheduleAsyncDelayedTask(plugin, task, ticks);
    }
}
