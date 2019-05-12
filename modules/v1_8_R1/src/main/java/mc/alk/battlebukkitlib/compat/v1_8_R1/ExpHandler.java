package mc.alk.battlebukkitlib.compat.v1_8_R1;

import mc.alk.battlebukkitlib.handlers.IExpHandler;

public class ExpHandler implements IExpHandler {

    /**
     * Used for getting the proper experience calculations
     *
     * @see net.minecraft.server.v1_8_R1.EntityHuman.getExpToLevel()
     * @returns The proper experience calculation for the current version
     */
    @Override
    public int getExpToLevel(final int level) {
        if (level >= 30) {
            return (112 + (level - 30) * 9);
        } else if (level >= 15) {
            return (37 + (level - 15) * 5);
        } else {
            return 7 + level * 2;
        }
    }

    /**
     * New XP math for Minecraft 1.8 Credits go to andrewkm for the 1.8 code
     * @returns The proper experience calculation for the current version
     */
    @Override
    public int getExpAtLevel(int level) {
        if (level <= 15) {
            return (2 * level) + 7;
        }
        if ((level >= 16) && (level <= 30)) {
            return (5 * level) - 38;
        }
        return (9 * level) - 158;
    }
}
