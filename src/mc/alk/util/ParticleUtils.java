package mc.alk.util;

import mc.alk.util.handlers.IParticleHandler;
import mc.alk.util.handlers.IParticleHandler.ParticleEffects;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class ParticleUtils
{
	private static class NullParticleHandler implements IParticleHandler
	{

		@Override
		public void sendEffect(Player player, ParticleEffects effectType,
				Location location, Vector offSet, int speed, int count)
		{
			/* Do nothing */
		}

	}

	private static IParticleHandler handler;

	static
	{
		try
		{
			final String pkg = Bukkit.getServer().getClass().getPackage()
					.getName();
			String version = pkg.substring(pkg.lastIndexOf('.') + 1);
			final Class<?> clazz;
			if (version.equalsIgnoreCase("craftbukkit"))
			{
				clazz = Class
						.forName("mc.alk.util.compat.v1_2_5.ParticleHandler");
			}
			else
			{
				clazz = Class.forName("mc.alk.util.compat." + version
						+ ".ParticleHandler");
			}
			Class<?>[] args = {};
			handler = (IParticleHandler) clazz.getConstructor(args)
					.newInstance((Object[]) args);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			handler = new NullParticleHandler();
		}
	}

	public static void sendEffect(Player player, ParticleEffects effectType,
			Location location, Vector offSet, int speed, int count)
	{
		handler.sendEffect(player, effectType, location, offSet, speed, count);
	}
}
