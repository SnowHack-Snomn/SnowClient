package net.snomn.snowclient.hack.misc;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.snomn.snowclient.hack.Hack;
import org.lwjgl.glfw.GLFW;

public class NoFall extends Hack {

    public NoFall() {
        super("NoFall", "NoFall for you", Category.MISC);
        this.setKey(GLFW.GLFW_KEY_J);
    }



    @Override
    public void onEnable() {
        super.onEnable();
    }



    @Override
    public void onDisable() {
        super.onDisable();
    }



    @Override
    public void onTick() {

        ClientPlayerEntity player = mc.player;

        if(player.fallDistance <= (player.isFallFlying() ? 1 : 2))

            return;



        if(player.isFallFlying() && player.isSneaking()

                && !isFallingFastEnoughToCauseDamage(player))

            return;



        player.networkHandler.sendPacket(new PlayerMoveC2SPacket.OnGroundOnly(true));

    }



    private boolean isFallingFastEnoughToCauseDamage(ClientPlayerEntity player)

    {

        return player.getVelocity().y < -0.5;

    }

}
