package mc.alk.util.handlers;

import mc.alk.util.objects.Hologram;

import org.bukkit.Server;
import org.bukkit.entity.Player;

public interface IHologramHandler
{
	public void sendHologram(Server server, Hologram hologram);

	public void sendHologram(Player player, Hologram hologram);
}
