package mc.alk.util;

import mc.alk.util.handlers.IHologramHandler;
import mc.alk.util.objects.Hologram;

import org.bukkit.Server;
import org.bukkit.entity.Player;

public class HologramUtils
{
	private static class NullHologramHandler implements IHologramHandler
	{

		@Override
		public void sendHologram(Player player, Hologram hologram)
		{
		}

		@Override
		public void sendHologram(Server server, Hologram hologram)
		{
		}
	}
}
