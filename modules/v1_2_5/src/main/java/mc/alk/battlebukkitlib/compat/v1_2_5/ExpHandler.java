package mc.alk.battlebukkitlib.compat.v1_2_5;

import mc.alk.battlebukkitlib.handlers.IExpHandler;

public class ExpHandler implements IExpHandler {

    /**
     * Used for getting the proper experience calculations
     *
     * @see net.minecraft.server.EntityHuman.getExpToLevel()
     * @returns The proper experience calculation for the current version
     */
    @Override
    public int getExpToLevel(int level) {
        return 7 + (level * 7 >> 1);
    }

    @Override
    public int getExpAtLevel(int level) {
        if (level > 30) {
            return (9 * level) - 158;
        } else if (level > 15) {
            return (5 * level) - 38;
        }

        return (2 * level) + 7;
    }
}
