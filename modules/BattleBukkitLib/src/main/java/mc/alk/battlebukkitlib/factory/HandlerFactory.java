package mc.alk.battlebukkitlib.factory;

import mc.euro.version.VersionFactory;

public class HandlerFactory<T> {

    public T getNewInstance(String handlerName) {
        Object object = null;
        String version = VersionFactory.getNmsPackage();
        Class clazz = null;
        Class<?>[] args = {};
        try {
            clazz = Class.forName("mc.alk.util.battlebukkitlib." + version + "." + handlerName);
            object = clazz.getConstructor(args).newInstance((Object[]) args);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return (T) object;
    }

}
