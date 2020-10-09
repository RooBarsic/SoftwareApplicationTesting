package com.company.domain;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Clothes {
    @NotNull
    private final ClothesType type;
    @NotNull
    private Color color;
    @Nullable
    private Brand brand;

    public Clothes(@NotNull final ClothesType type, @NotNull final Color color, @Nullable final Brand brand) {
        this.type = type;
        this.color = color;
        this.brand = brand;
    }

    public ClothesType getType() {
        return type;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        this.brand = null;
    }

    public Brand getBrand() {
        return brand;
    }
}
