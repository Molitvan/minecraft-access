package com.github.khanshoaib3.minecraft_access.test_utils;

import com.github.khanshoaib3.minecraft_access.utils.condition.Interval;

public class MockInterval extends Interval {
    boolean ready = false;

    public MockInterval(long lastRunTime, long delayInNanoTime) {
        super(lastRunTime, delayInNanoTime);
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    @Override
    public boolean isReady() {
        return ready;
    }
}