package mc.alk.util.handlers;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Location;

public class BattleHologram
{
	private double distanceBetweenLines;
	private ArrayList<String> lines;
	private Location location;

	public BattleHologram(String leaderboardName, double distanceBetweenLines,
			Location location, String... lines)
	{
		this.lines = new ArrayList<String>();
		this.distanceBetweenLines = distanceBetweenLines;
		this.lines.set(0, leaderboardName);
		this.lines.addAll(Arrays.asList(lines));
		this.location = location;
	}

	public double getDistanceBetweenLines()
	{
		return distanceBetweenLines;
	}

	public ArrayList<String> getLines()
	{
		return lines;
	}

	public Location getLocation()
	{
		return location;
	}
}
