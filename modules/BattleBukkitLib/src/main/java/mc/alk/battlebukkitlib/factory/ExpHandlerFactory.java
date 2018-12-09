package mc.alk.battlebukkitlib.factory;

import mc.alk.battlebukkitlib.handlers.IExpHandler;

/**
 * 
 * @author Nikolai
 */
public class ExpHandlerFactory {
    
    private static HandlerFactory<IExpHandler> factory = new HandlerFactory<IExpHandler>();
    
    public static IExpHandler getNewInstance() {
        IExpHandler handler = factory.getNewInstance("ExpHandler");
        return (handler == null) ? IExpHandler.DEFAULT_HANDLER : handler;
    }

}
