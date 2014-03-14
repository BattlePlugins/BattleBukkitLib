package mc.alk.util;

import java.util.ArrayList;
import java.util.List;

import mc.alk.util.handlers.IHologramHandler;
import mc.alk.util.objects.Hologram;

import org.bukkit.Location;

public class HologramUtils
{
	@SuppressWarnings("unused")
	private static class NullHologramHandler implements IHologramHandler
	{
		@Override
		public boolean destroyHologram(Hologram hologram)
		{
			return false;
		}

		@Override
		public List<Integer> showLine(Location location, String text)
		{
			return new ArrayList<Integer>();
		}
	}

	public static IHologramHandler handler;

	public static void sendHologram(Hologram hologram)
	{
		Location first = hologram
				.getLocation()
				.clone()
				.add(0,
						(hologram.getLines().size() / 2)
								* hologram.getDistanceBetweenLines(), 0);
		for (int i = 0; i < hologram.getLines().size(); i++)
		{
			hologram.getIds()
					.addAll(handler.showLine(first.clone(), hologram.getLines()
							.get(i)));
			first.subtract(0, hologram.getDistanceBetweenLines(), 0);
		}
		hologram.setShowing(true);
	}

	public static void changeHologram(Hologram hologram)
	{
		destroyHologram(hologram);
		sendHologram(hologram);
	}

	public static boolean destroyHologram(Hologram hologram)
	{
		if (!hologram.isShowing())
		{
			return false;
		}
		return handler.destroyHologram(hologram);
	}
}
