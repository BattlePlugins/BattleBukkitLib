package mc.alk.util.objects;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Location;

public class Hologram
{
	public static final double SPACED_TXT = 0.25;
	public static final double COMPACT_TEXT = 0.23;
	private double distanceBetweenLines;
	private ArrayList<String> lines;
	private Location location;

	public Hologram(String leaderboardName, double distanceBetweenLines,
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
