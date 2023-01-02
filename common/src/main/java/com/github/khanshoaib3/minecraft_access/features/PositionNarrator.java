package com.github.khanshoaib3.minecraft_access.features;

import com.github.khanshoaib3.minecraft_access.MainClass;
import com.github.khanshoaib3.minecraft_access.utils.PlayerPosition;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

/**
 * Adds key bindings to speak the player's position.<br><br>
 * Keybindings and combinations:<br>
 * 1. Speak Player Position Key (default: G) = Speaks the player's x y and z position.<br>
 * 2. Left Alt + X = Speaks only the x position.<br>
 * 3. Left Alt + C = Speaks only the y position.<br>
 * 4. Left Alt + Z = Speaks only the z position.<br>
 */
public class PositionNarrator {
    public final KeyBinding positionNarrationKey; //TODO create a separate class for initializing key binds

    public PositionNarrator() {
        positionNarrationKey = new KeyBinding(
                "minecraft_access.keys.other.player_position_key_name",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_G,
                "minecraft_access.keys.other.group_name"
        );
    }

    public void update() {
        try {
            MinecraftClient minecraftClient = MinecraftClient.getInstance();
            if (minecraftClient == null) return;
            if (minecraftClient.player == null) return;
            if (minecraftClient.currentScreen != null) return;

            boolean isLeftAltPressed = InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(),
                    InputUtil.fromTranslationKey("key.keyboard.left.alt").getCode());
            boolean isXPressed = InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(),
                    InputUtil.fromTranslationKey("key.keyboard.x").getCode());
            boolean isCPressed = InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(),
                    InputUtil.fromTranslationKey("key.keyboard.c").getCode());
            boolean isZPressed = InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(),
                    InputUtil.fromTranslationKey("key.keyboard.z").getCode());

            if (isLeftAltPressed) {
                if (isXPressed) {
                    MainClass.speakWithNarrator(new PlayerPosition(minecraftClient).getNarratableXPos(), true);
                    return;
                }

                if (isCPressed) {
                    MainClass.speakWithNarrator(new PlayerPosition(minecraftClient).getNarratableYPos(), true);
                    return;
                }

                if (isZPressed) {
                    MainClass.speakWithNarrator(new PlayerPosition(minecraftClient).getNarratableZPos(), true);
                    return;
                }
            }

            boolean isPositionNarrationKeyPressed = InputUtil.isKeyPressed(
                    minecraftClient.getWindow().getHandle(),
                    InputUtil.fromTranslationKey(positionNarrationKey.getBoundKeyTranslationKey()).getCode()
            );

            if (isPositionNarrationKeyPressed) {
                String posX = new PlayerPosition(minecraftClient).getNarratableXPos();
                String posY = new PlayerPosition(minecraftClient).getNarratableYPos();
                String posZ = new PlayerPosition(minecraftClient).getNarratableZPos();
                String text = String.format("%s, %s, %s", posX, posY, posZ);

                MainClass.speakWithNarrator(text, true);
            }
        } catch (Exception e) {
            MainClass.errorLog("An error occurred in PositionNarrator.");
            e.printStackTrace();
        }
    }
}