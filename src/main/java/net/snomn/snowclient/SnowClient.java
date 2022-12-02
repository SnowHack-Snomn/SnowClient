package net.snomn.snowclient;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SnowClient implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("snowclient");

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");
	}
}
