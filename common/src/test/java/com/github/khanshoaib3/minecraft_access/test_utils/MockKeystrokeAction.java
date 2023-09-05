package com.github.khanshoaib3.minecraft_access.test_utils;

import java.util.function.BooleanSupplier;

/**
 * Combining a changeable boolean variable with supplier.
 */
public class MockKeystrokeAction {
    Boolean pressed;
    public BooleanSupplier supplier;

    public void revertKeystrokeResult() {
        this.pressed = !this.pressed;
    }

    public MockKeystrokeAction(boolean initPressed) {
        this.pressed = initPressed;
        this.supplier = () -> this.pressed;
    }

    public void setPress() {
        this.pressed = true;
    }

    public void setRelease() {
        this.pressed = false;
    }

    public static MockKeystrokeAction pressed() {
        return new MockKeystrokeAction(true);
    }

    public static MockKeystrokeAction released() {
        return new MockKeystrokeAction(false);
    }
}