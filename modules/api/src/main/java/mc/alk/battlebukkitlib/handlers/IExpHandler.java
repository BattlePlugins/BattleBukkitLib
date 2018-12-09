package mc.alk.battlebukkitlib.handlers;

/**
 * 
 * @author Nikolai
 */
public interface IExpHandler {
    
    /**
     * net.minecraft.server.vVersion.EntityHuman.getExpToLevel()
     */
    public int getExpToLevel(final int level);
    
    /**
     * https://github.com/BattlePlugins/BattleArena/blob/master/src/java/mc/alk/arena/util/ExpUtil.java#L42
     */
    public int getExpAtLevel(final int level);
    
    public static final IExpHandler DEFAULT_HANDLER = new IExpHandler() {

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

        @Override
        public int getExpAtLevel(final int level) {
            if (level > 30) {
                return (9 * level) - 158;
            } else if (level > 15) {
                return (5 * level) - 38;
            }
            return (2 * level) + 7;
        }
    };

}
