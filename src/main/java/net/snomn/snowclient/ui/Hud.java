package net.snomn.snowclient.ui;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.snomn.snowclient.hack.Hack;
import net.snomn.snowclient.hack.HackManager;

import java.util.Comparator;
import java.util.List;

public class Hud {

    private static MinecraftClient mc = MinecraftClient.getInstance();

    public static void render(MatrixStack matrices, float tickDelta) {
        renderArrayList(matrices);
    }

    public static void renderArrayList(MatrixStack matrices) {
        int index = 0;
        int sWidth = mc.getWindow().getScaledWidth();
        int sHeight = mc.getWindow().getScaledHeight();

        List<Hack> enabled = HackManager.INSTANCE.getEnabledHacks();

        enabled.sort(Comparator.comparingInt(m -> (int)mc.textRenderer.getWidth(((Hack)m).getDisplayName())).reversed());

        for (Hack hack : enabled) {
            mc.textRenderer.drawWithShadow(matrices, hack.getDisplayName(), (sWidth - 4) - mc.textRenderer.getWidth(hack.getDisplayName()), 10 + (index * mc.textRenderer.fontHeight), -1);
            index++;
        }
    }

}
