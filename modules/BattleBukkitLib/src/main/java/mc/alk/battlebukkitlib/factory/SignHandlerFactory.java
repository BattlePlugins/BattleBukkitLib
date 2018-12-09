package mc.alk.battlebukkitlib.factory;

import mc.alk.battlebukkitlib.handlers.ISignHandler;

public class SignHandlerFactory {

    private static HandlerFactory<ISignHandler> factory = new HandlerFactory<ISignHandler>();

    public static ISignHandler getNewInstance() {
        ISignHandler handler = factory.getNewInstance("SignHandler");
        return (handler == null) ? ISignHandler.NULL_HANDLER : handler;
    }

}
