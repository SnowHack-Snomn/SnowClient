package net.snomn.snowclient.hack.movement;

import net.snomn.snowclient.hack.Hack;
import net.snomn.snowclient.hack.settings.BooleanSetting;
import net.snomn.snowclient.hack.settings.ModeSetting;
import net.snomn.snowclient.hack.settings.NumberSetting;
import org.lwjgl.glfw.GLFW;

public class Flight extends Hack {

    public NumberSetting speed = new NumberSetting("Speed", 0, 10, 0.1, 0.1);
    public BooleanSetting testBool = new BooleanSetting("Check", true);
    public ModeSetting testMode = new ModeSetting("Mode", "Test", "Test", "Test2", "Test3");

    public Flight() {
        super("Flight", "Allows you to fly", Category.MOVEMENT);
        this.setKey(GLFW.GLFW_KEY_G);
        addSettings(speed, testBool, testMode);
    }

    @Override
    public void onTick() {
        if(mc.player !=null) {
            mc.player.getAbilities().flying = true;
            mc.player.getAbilities().setFlySpeed(speed.getValueFloat());
        }
        super.onTick();
    }

    @Override
    public void onDisable() {
        if(mc.player !=null) {
            mc.player.getAbilities().flying = false;
        }
        super.onDisable();
    }
}
