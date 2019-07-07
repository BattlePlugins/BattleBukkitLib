package mc.alk.battlebukkitlib.factory;

import mc.alk.battlebukkitlib.handlers.IPlayerHandler;

public class PlayerHandlerFactory {

    private static HandlerFactory<IPlayerHandler> factory = new HandlerFactory<IPlayerHandler>();

    public static IPlayerHandler getNewInstance() {
        IPlayerHandler handler = factory.getNewInstance("PlayerHandler");
        return (handler == null) ? IPlayerHandler.NULL_HANDLER : handler;
    }
}
