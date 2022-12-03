package net.snomn.snowclient;

import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;
import net.snomn.snowclient.hack.Hack;
import net.snomn.snowclient.hack.HackManager;
import net.snomn.snowclient.ui.screens.clickgui.ClickGUI;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SnowClient implements ModInitializer {

	public static final SnowClient INSTANCE = new SnowClient();
	public static final Logger LOGGER = LoggerFactory.getLogger("snowhack");


	private MinecraftClient mc = MinecraftClient.getInstance();

	@Override
	public void onInitialize() {
		LOGGER.info("Hello World Of Cheating!");
	}

	public void onKeyPress(int key, int action) {
		if (action == GLFW.GLFW_PRESS) {
			for(Hack hack : HackManager.INSTANCE.getHacks()) {
				if (key == hack.getKey()) hack.toggle();
			}

			if(key == GLFW.GLFW_KEY_RIGHT_SHIFT) mc.setScreen(ClickGUI.INSTANCE);
		}
	}

	public void onTick() {
		if (mc.player != null) {
			for (Hack hack : HackManager.INSTANCE.getEnabledHacks()) {
				hack.onTick();
			}
		}
	}
}
