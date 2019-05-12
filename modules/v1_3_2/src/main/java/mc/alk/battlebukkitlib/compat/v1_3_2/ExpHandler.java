package mc.alk.battlebukkitlib.compat.v1_3_2;

import mc.alk.battlebukkitlib.handlers.IExpHandler;

public class ExpHandler implements IExpHandler {

    /**
     * Used for getting the proper experience calculations
     *
     * @see net.minecraft.server.EntityHuman.getExpToLevel()
     * @returns The proper experience calculation for the current version
     */
    @Override
    public int getExpToLevel(final int level) {
        if (level < 16){
            return 17;
        } else if (level < 31){
            return 3*level - 31;
        } else {
            return 7*level - 155;
        }
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
