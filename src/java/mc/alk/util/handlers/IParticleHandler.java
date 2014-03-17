package mc.alk.util.handlers;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public interface IParticleHandler
{
	public enum ParticleEffects
	{
		HUGE_EXPLOSION("hugeexplosion"), LARGE_EXPLODE("largeexplode"), FIREWORKS_SPARK(
				"fireworksSpark"), BUBBLE("bubble"), SUSPEND("suspend"), DEPTH_SUSPEND(
				"depthSuspend"), TOWN_AURA("townaura"), CRIT("crit"), MAGIC_CRIT(
				"magicCrit"), MOB_SPELL("mobSpell"), MOB_SPELL_AMBIENT(
				"mobSpellAmbient"), SPELL("spell"), INSTANT_SPELL(
				"instantSpell"), WITCH_MAGIC("witchMagic"), NOTE("note"), PORTAL(
				"portal"), ENCHANTMENT_TABLE("enchantmenttable"), EXPLODE(
				"explode"), FLAME("flame"), LAVA("lava"), FOOTSTEP("footstep"), SPLASH(
				"splash"), LARGE_SMOKE("largesmoke"), CLOUD("cloud"), RED_DUST(
				"reddust"), SNOWBALL_POOF("snowballpoof"), DRIP_WATER(
				"dripWater"), DRIP_LAVA("dripLava"), SNOW_SHOVEL("snowshovel"), SLIME(
				"slime"), HEART("heart"), ANGRY_VILLAGER("angryVillager"), HAPPY_VILLAGER(
				"happyVillager"), ICONCRACK("iconcrack_"), TILECRACK(
				"tilecrack_");

		private String particleName;

		ParticleEffects(String particleName)
		{
			this.particleName = particleName;
		}

		public String getParticleName()
		{
			return this.particleName;
		}

	}

	void sendEffect(Player player, ParticleEffects effectType,
			Location location, Vector offSet, int speed, int count);
}
