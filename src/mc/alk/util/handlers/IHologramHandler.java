package mc.alk.util.handlers;

import java.util.List;

import mc.alk.util.objects.Hologram;

import org.bukkit.Location;

public interface IHologramHandler
{
	public boolean destroyHologram(Hologram hologram);

	public List<Integer> showLine(Location location, String text);
}
