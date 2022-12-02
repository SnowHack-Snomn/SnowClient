package net.snomn.snowclient.hacks;

import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Hand;
import net.snomn.snowclient.SnowClient;

public class AutoFishing {

    public int recastRod = -1;

    public void tick(MinecraftClient client) {
        if(recastRod > 0) {
            recastRod--;
        }

        if(recastRod == 0 && SnowClient.autoFishingEnabled) {
            client.interactionManager.interactItem(client.player, Hand.MAIN_HAND);
            recastRod =-1;
        }
    }

    public void setRecastRod(int countdown) {
        recastRod = countdown;
    }

}
