package com.CalorieAPI.model;

public enum Goal {
    LOSE_WEIGHT("Похудение"),
    MAINTAIN("Поддержание веса"),
    GAIN_WEIGHT("Набор массы");

    private final String displayName;

    Goal(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
