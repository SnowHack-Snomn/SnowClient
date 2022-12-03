package net.snomn.snowclient.ui.screens.clickgui;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.snomn.snowclient.hack.Hack;
import net.snomn.snowclient.hack.Hack.Category;
import net.snomn.snowclient.hack.HackManager;
import net.snomn.snowclient.ui.screens.clickgui.setting.Component;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class Frame {

    public int x, y, width, height, dragX, dragY;
    public Category category;

    public boolean dragging, extended;

    private List<HackButton> buttons;

    protected MinecraftClient mc = MinecraftClient.getInstance();

    public Frame(Category category, int x, int y, int width, int height) {
        this.category = category;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.dragging = false;
        this.extended = false;

        buttons = new ArrayList<>();

        int offset = height;
        for (Hack hack : HackManager.INSTANCE.getHacksInCategory(category)) {
            buttons.add(new HackButton(hack, this, offset));
            offset += height;
        }
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        DrawableHelper.fill(matrices, x, y, x + width, y + height, Color.cyan.getRGB());
        int offset =  ((height / 2) - mc.textRenderer.fontHeight / 2);

        mc.textRenderer.drawWithShadow(matrices, category.name, x +  + offset, y + offset, -1);
        mc.textRenderer.drawWithShadow(matrices, extended ? "-" : "+", x + width - offset - 2 - mc.textRenderer.getWidth("+"), y + offset, -1);

        if(extended) {
            for (HackButton button : buttons) {
                button.render(matrices, mouseX, mouseY, delta);
            }
        }
    }

    public void mouseClicked(double mouseX, double mouseY, int button) {
        if(isHovered(mouseX, mouseY)) {
            if (button == 0) {
                dragging = true;
                dragX = (int) (mouseX - x);
                dragY = (int) (mouseY - y);
            } else if (button == 1) {
                extended = !extended;
            }
        }

        if(extended) {
            for (HackButton hb : buttons) {
                hb.mouseClicked(mouseX, mouseY, button);
            }
        }
    }

    public void mouseReleassed(double mouseX, double mouseY, int button) {
        if (button == 0 && dragging == true) dragging = false;

        for (HackButton hb : buttons) {
            hb.mouseReleased(mouseX, mouseY, button);
        }
     }

    public boolean isHovered(double mouseX, double mouseY) {
        return mouseX > x && mouseX < x + width && mouseY > y && mouseY < y + height;
    }

    public void updatePosition(double mouseX, double mouseY) {
        if (dragging) {
            x = (int) (mouseX - dragX);
            y = (int) (mouseY - dragY);
        }
    }

    public void updateButtons() {
        int offset = height;

        for (HackButton button : buttons) {
            button.offset = offset;
            offset += height;

            if (button.extended) {
               for (Component component : button.components) {
                   if (component.setting.isVisible()) offset += height;
               }
            }
        }
    }
}
