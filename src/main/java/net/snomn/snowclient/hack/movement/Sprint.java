package net.snomn.snowclient.hack.movement;

import net.snomn.snowclient.hack.Hack;
import org.lwjgl.glfw.GLFW;

public class Sprint extends Hack {

    public Sprint() {
        super("Sprint", "Keeps your sprint", Category.MOVEMENT);
        this.setKey(GLFW.GLFW_KEY_V);
    }

    @Override
    public void onTick() {
        if(!mc.player.isUsingItem() && !mc.player.isSneaking()) {
            mc.player.setSprinting(true);
            super.onTick();
        }
    }
}
