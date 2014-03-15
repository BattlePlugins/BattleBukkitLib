package mc.alk.util.objects;

import org.bukkit.Location;

public class LeaderboardHologram extends Hologram
{
	private String leaderboardName;

	public LeaderboardHologram(String leaderboardName,
			double distanceBetweenLines, Location location, String[] lines)
	{
		super(distanceBetweenLines, location, lines);
		setLeaderboardName(leaderboardName);
	}

	public LeaderboardHologram(String leaderboardName,
			VerticalTextSpacing type, Location location, String[] lines)
	{
		super(type.spacing(), location, lines);
		setLeaderboardName(leaderboardName);
	}

	public String getLeaderboardName()
	{
		return leaderboardName;
	}

	public void setLeaderboardName(String name)
	{
		this.leaderboardName = name;
		this.getLines().add(0, leaderboardName);
	}
}
