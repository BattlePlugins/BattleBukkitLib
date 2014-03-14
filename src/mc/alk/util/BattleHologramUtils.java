package mc.alk.util;

import mc.alk.util.handlers.BattleHologram;
import mc.alk.util.handlers.IBattleHologramHandler;

import org.bukkit.entity.Player;

public class BattleHologramUtils
{
	private static class NullBattleHologramHandler implements
			IBattleHologramHandler
	{

		@Override
		public void sendBattleHologram(Player player, BattleHologram hologram)
		{
			try
			{
				throw new Exception("Unsupported version of minecraft");
			}
			catch (Exception ex)
			{

			}
		}
	}
}
