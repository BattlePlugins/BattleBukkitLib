package mc.alk.util.handlers;

import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

public interface ISignHandler
{
	public static final NullSignHandler NULL_HANDLER = new NullSignHandler();

	public class NullSignHandler implements ISignHandler
	{
		private NullSignHandler()
		{
		}

		@Override
		public void sendLines(Player player, Sign sign, String[] lines)
		{/* do nothing */
		}
	}

	void sendLines(Player player, Sign sign, String[] lines);

}
