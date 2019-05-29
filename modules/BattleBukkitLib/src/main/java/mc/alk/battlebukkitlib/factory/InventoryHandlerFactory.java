package mc.alk.battlebukkitlib.factory;

import mc.alk.battlebukkitlib.handlers.IInventoryHandler;

public class InventoryHandlerFactory {

    private static HandlerFactory<IInventoryHandler> factory = new HandlerFactory<IInventoryHandler>();

    public static IInventoryHandler getNewInstance() {
        IInventoryHandler handler = factory.getNewInstance("InventoryHandler");
        return (handler == null) ? IInventoryHandler.NULL_HANDLER : handler;
    }

}
