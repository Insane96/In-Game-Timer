package net.insane96mcp.ingametimer.events;

import net.insane96mcp.ingametimer.lib.Properties;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import scala.sys.Prop;

public class RenderGameOverlay {	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void onRenderGameOverlay(RenderGameOverlayEvent.Text event) {

		Minecraft mc = Minecraft.getMinecraft();
		EntityPlayerSP player = mc.player;
		
		String finalString = "";
		if (Properties.General.showText)
			finalString += "In-Game Timer: ";
		
		if (Properties.General.showTime)
			finalString += FormatTime(player.world.getTotalWorldTime());
		
		if (Properties.General.showTicks)
			finalString += String.format("(%d)", player.world.getTotalWorldTime());
		
		event.getLeft().add(finalString);
	}
	
	private static String FormatTime(long time) {
		int hours = (int) (time / 72000);
		int minutes = (int) (time / 1200 - (hours * 60));
		int seconds = (int) (time / 20 - (hours * 1200 + minutes * 60));
		int ticks = (int) (time - (hours * 72000 + minutes * 1200 + seconds * 20)) * 5;
		
		
		return String.format("%02d:%02d:%02d:%02d", hours, minutes, seconds, ticks);
	}
}