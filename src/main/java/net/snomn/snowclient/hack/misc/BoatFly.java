package net.snomn.snowclient.hack.misc;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import net.snomn.snowclient.hack.Hack;
import net.snomn.snowclient.hack.settings.ModeSetting;
import net.snomn.snowclient.hack.settings.NumberSetting;
import org.lwjgl.glfw.GLFW;

public class BoatFly extends Hack {

    public NumberSetting speed = new NumberSetting("Speed", 0, 10, 3, 0.1);
    public ModeSetting modes = new ModeSetting("Modes", "Bypass", "Bypass", "Free");

    public BoatFly() {
        super("BoatFly", "Makes boats fly ;)", Category.MISC);
        this.setKey(GLFW.GLFW_KEY_B);
        addSettings(speed, modes);;
    }

    int toggle = 0;
   // int MAX_SPEED = 3;
    double FALL_SPEED = -0.04;
   double acceleration = 0.1;

    MinecraftClient mc = MinecraftClient.getInstance();

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
        if(mc.player != null) {
            boolean jumpPressed = mc.options.jumpKey.isPressed();
            boolean forwardPressed = mc.options.forwardKey.isPressed();
            boolean leftPressed = mc.options.leftKey.isPressed();
            boolean rightPressed = mc.options.rightKey.isPressed();
            boolean backPressed = mc.options.backKey.isPressed();

            Entity object = mc.player;
            if(mc.player.hasVehicle()) {
                object = mc.player.getVehicle();
            }

            if(mc.player.hasVehicle()) {
                Vec3d velocity = object.getVelocity();
                Vec3d newVelocity = new Vec3d(velocity.x, -FALL_SPEED, velocity.z);
                if(jumpPressed) {

                    if(forwardPressed) {
                        newVelocity = mc.player.getRotationVector().multiply(acceleration);
                    }
                    if(leftPressed && !mc.player.hasVehicle()) {
                        newVelocity = mc.player.getRotationVector().multiply(acceleration).rotateY(3.14159227F/2);
                        newVelocity = new Vec3d(newVelocity.x, 0, newVelocity.z);
                    }
                    if(rightPressed && !mc.player.hasVehicle()) {
                        newVelocity = mc.player.getRotationVector().multiply(acceleration).rotateY(-3.14159227F/2);
                        newVelocity = new Vec3d(newVelocity.x, 0, newVelocity.z);
                    }
                    if(backPressed) {
                        newVelocity = mc.player.getRotationVector().negate().multiply(acceleration);
                    }

                    newVelocity = new Vec3d(newVelocity.x, (modes.isMode("Bypass") && toggle == 0 && newVelocity.y > FALL_SPEED) ? FALL_SPEED : newVelocity.y, newVelocity.z);
                    object.setVelocity(newVelocity);

                    if(forwardPressed || leftPressed || rightPressed || backPressed) {
                        if(acceleration < speed.getValueFloat())
                            acceleration += 0.1;
                    } else if (acceleration > 0.2) {
                        acceleration -= 0.2;
                    }
                }

                if (toggle == 0 || newVelocity.y <= -0.04) {
                    toggle = 40;
                }
                toggle--;
            }
        }
        super.onTick();
    }
}
