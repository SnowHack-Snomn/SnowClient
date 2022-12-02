package net.snomn.snowclient.util;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.snomn.snowclient.SnowClient;

public class SnowClientScreen extends Screen {

    private final Screen parent;
    private final GameOptions settings;

    public SnowClientScreen(Screen parent, GameOptions gameOptions) {
        super(Text.literal("SnowClient"));
        this.parent = parent;
        this.settings = gameOptions;
    }


    public MutableText autoFishingText() {
        if(SnowClient.autoFishingEnabled)
            return Text.literal("AutoFishing is enabled");
        else
            return Text.literal("AutoFishing is disabled");
    }

    protected void init() {
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, this.height / 6 + 90, 200, 100,
                autoFishingText(), (button) -> {
                    SnowClient.autoFishingEnabled = !SnowClient.autoFishingEnabled;
                    button.setMessage(autoFishingText());
        }));
        // Back
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, this.height / 6 + 138, 200, 100,
                ScreenTexts.BACK, (button) -> {
            this.client.setScreen(this.parent);
        }));
    }
}
