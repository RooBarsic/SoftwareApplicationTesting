package com.company.domain;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Human {
    private String firstName;
    private String lastName;
    private int health;
    private int power;
    private Map<ClothesType, Clothes> clothesByType;

    public Human(@NotNull final String firstName, @NotNull final String lastName, @NotNull List<Clothes> clothesList) {
        this.firstName = firstName;
        this.lastName = lastName;
        clothesByType = new HashMap<>();
        for (@NotNull final Clothes clothes : clothesList) {
            clothesByType.put(clothes.getType(), clothes);
        }
        health = 100;
        final Random random = new Random();
        power = Math.abs(random.nextInt()) % 15 + 10;
    }

    public void addClothes(@NotNull final Clothes clothes) {
        clothesByType.put(clothes.getType(), clothes);
    }

    public Clothes removeClothes(@NotNull final ClothesType clothesType) {
        return clothesByType.remove(clothesType);
    }

    public List<Clothes> showClothes() {
        return new ArrayList<>(clothesByType.values());
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public int getPower() {
        return power;
    }

    private int makeHit() {
        final int oldPower = power;
        power--;
        if (power < 10)
            power = 10;
        return oldPower;
    }

    private void gotHit(int hitPower) {
        health -= hitPower;
        if (health < 0)
            health = 0;
    }

    public void gotHitFrom(@NotNull final Human human) {
        gotHit(human.makeHit());
    }
}
