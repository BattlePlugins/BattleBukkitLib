package mc.alk.util.factory;

import mc.alk.battlebukkitlib.handlers.IDamageHandler;

/**
 * 
 * @author Nikolai
 */
public class DamageHandlerFactory {
    
    private static HandlerFactory<IDamageHandler> factory = new HandlerFactory<IDamageHandler>();
    
    public static IDamageHandler getNewInstance() {
        IDamageHandler handler = factory.getNewInstance("DamageHandler");
        return (handler == null) ? IDamageHandler.DEFAULT_HANDLER : handler;
    }

}
