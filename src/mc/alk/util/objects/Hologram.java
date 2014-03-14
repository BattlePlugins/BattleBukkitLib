package mc.alk.util.objects;

import org.bukkit.Location;

import java.util.ArrayList;
import java.util.Arrays;

public class Hologram
{
    public enum VerticalTextSpacing {
        COMPACT(0.23), SPACED(0.25);
        final double spacing;
        VerticalTextSpacing(double spacing) {
            this.spacing = spacing;
        }

        public double spacing() {
            return spacing;
        }
    }
    private double distanceBetweenLines;
    private ArrayList<String> lines;
    private Location location;

    public Hologram(String leaderboardName, VerticalTextSpacing spacing,
                    Location location, String... lines){
        this(leaderboardName, spacing.spacing(), location, lines);
    }

    public Hologram(String leaderboardName, double distanceBetweenLines,
                    Location location, String... lines){
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
