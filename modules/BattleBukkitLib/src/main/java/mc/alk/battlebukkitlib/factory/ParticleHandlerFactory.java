package mc.alk.battlebukkitlib.factory;

import mc.alk.battlebukkitlib.handlers.IParticleHandler;

public class ParticleHandlerFactory {

    private static HandlerFactory<IParticleHandler> factory = new HandlerFactory<IParticleHandler>();

    public static IParticleHandler getNewInstance() {
        IParticleHandler handler = factory.getNewInstance("ParticleHandler");
        return (handler == null) ? IParticleHandler.NULL_HANDLER : handler;
    }

}
