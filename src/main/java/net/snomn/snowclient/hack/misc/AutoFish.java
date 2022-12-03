package net.snomn.snowclient.hack.misc;

import net.minecraft.util.Hand;
import net.snomn.snowclient.hack.Hack;
import net.snomn.snowclient.hack.settings.NumberSetting;
import org.lwjgl.glfw.GLFW;

public class AutoFish extends Hack {

    public static NumberSetting castDelay = new NumberSetting("Cast Delay", 0, 20, 20, 1);

    public static boolean AutoFishEnabled;

    public static int recastRod = -1;

    public AutoFish() {
        super("AutoFishing", "Autofishes for you", Category.MISC);
        this.setKey(GLFW.GLFW_KEY_H);
        this.AutoFishEnabled = false;
        addSetting(castDelay);
    }

    @Override
    public void onEnable() {
        AutoFishEnabled = true;
        super.onEnable();
    }

    @Override
    public void onDisable() {
        AutoFishEnabled = false;
        super.onDisable();
    }

    @Override
    public void onTick() {
        if(recastRod > 0) {
            recastRod--;
        }

        if( recastRod == 0 && AutoFishEnabled) {
            mc.interactionManager.interactItem(mc.player, Hand.MAIN_HAND);
            recastRod = -1;
        }
        super.onTick();
    }

    public static void RecastRodDelay() {
        recastRod = castDelay.getValueInt();
    }
}