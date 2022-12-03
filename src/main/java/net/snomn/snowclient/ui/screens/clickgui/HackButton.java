package net.snomn.snowclient.ui.screens.clickgui;

import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.snomn.snowclient.hack.Hack;
import net.snomn.snowclient.hack.settings.BooleanSetting;
import net.snomn.snowclient.hack.settings.ModeSetting;
import net.snomn.snowclient.hack.settings.NumberSetting;
import net.snomn.snowclient.hack.settings.Setting;
import net.snomn.snowclient.ui.screens.clickgui.setting.CheckBox;
import net.snomn.snowclient.ui.screens.clickgui.setting.Component;
import net.snomn.snowclient.ui.screens.clickgui.setting.ModeBox;
import net.snomn.snowclient.ui.screens.clickgui.setting.Slider;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class HackButton {

    public Hack hack;
    public Frame parent;
    public int offset;
    public List<Component> components;
    public boolean extended;

    public HackButton(Hack hack, Frame parent, int offset) {
        this.hack = hack;
        this.parent = parent;
        this.offset = offset;
        this.extended = false;
        this.components = new ArrayList<>();

        int setOffset = parent.height;
        for (Setting setting : hack.getSettings()) {
            if(setting instanceof BooleanSetting) {
                components.add(new CheckBox(setting, this, setOffset));
            } else if(setting instanceof ModeSetting) {
                components.add(new ModeBox(setting, this, setOffset));
            } else if(setting instanceof NumberSetting) {
                components.add(new Slider(setting, this, setOffset));
            }
            setOffset += parent.height;
        }
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        DrawableHelper.fill(matrices, parent.x, parent.y + offset, parent.x + parent.width, parent.y + offset + parent.height, new Color(0, 0, 0, 160).getRGB());
        if (isHovered(mouseX, mouseY)) DrawableHelper.fill(matrices, parent.x, parent.y + offset, parent.x + parent.width, parent.y + offset + parent.height, new Color(0, 0, 0, 160).getRGB());

        int textOffset =  ((parent.height / 2) - parent.mc.textRenderer.fontHeight / 2);
        parent.mc.textRenderer.drawWithShadow(matrices, hack.getName(), parent.x + textOffset, parent.y + offset + textOffset, hack.isEnabled() ? Color.cyan.getRGB() : -1);

        if (extended) {
            for(Component component : components) {
                component.render(matrices, mouseX, mouseY, delta);
            }
        }
    }

    public void mouseClicked(double mouseX, double mouseY, int button) {
        if(isHovered(mouseX, mouseY)) {
            if (button == 0) {
                hack.toggle();
            } else if (button == 1) {
                extended = !extended;
                parent.updateButtons();
            }
        }

        for(Component component : components) {
            component.mouseClicked(mouseX, mouseY, button);
        }
    }

    public void mouseReleased(double mouseX, double mouseY, int button) {
        for(Component component : components) {
            component.mouseReleased(mouseX, mouseY, button);
        }
    }

    public boolean isHovered(double mouseX, double mouseY) {
        return mouseX > parent.x && mouseX < parent.x + parent.width && mouseY > parent.y + offset && mouseY < parent.y + offset + parent.height;
    }
}
