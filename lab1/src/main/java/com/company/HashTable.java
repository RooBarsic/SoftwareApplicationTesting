package com.company;

import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * Implemented HashTable
 *
 * Author: Farrukh Karimov
 * Last modification: 05/10/2020
 */
public class HashTable<T> {
    private final int BUCKETS_NUMBER = 100;
    private int tableSize = 0;
    @NotNull
    private final List<T>[] hashTableElements;

    public HashTable() {
        hashTableElements = new LinkedList[BUCKETS_NUMBER];
        for (int i = 0; i < BUCKETS_NUMBER; i++) {
            hashTableElements[i] = new LinkedList<>();
        }
    }

    /**
     * Функция для нахождения номера корзины для элемента
     * @param var - элемент для проверки
     * @return - возвращает номер корзины элемента
     */
    private int getBucketId(@NotNull T var) {
        return Math.abs(var.hashCode()) % BUCKETS_NUMBER;
    }

    private boolean contains(@NotNull final T var, final int bucketId) {
        return hashTableElements[bucketId].contains(var);
    }

    /**
     * Функция для проверки нахождения элемента в таблице
     * @param var - элемент для проверки
     * @return - возвращает true если элемент найден в таблице, иначе false
     */
    public boolean contains(@NotNull final T var) {
        final int bucketId = getBucketId(var);
        return contains(var, bucketId);
    }

    /**
     * Функция для добавления элемента в таблицу
     * @param var - элемент для добавления
     * @return - возвращает true если такого элемента нет в таблице и элемент добавлен в таблицу, иначе false
     */
    public boolean put(@NotNull final T var) {
        final int bucketId = getBucketId(var);
        if (!contains(var, bucketId)) {
            tableSize++;
            return hashTableElements[bucketId].add(var);
        }
        return false;
    }

    /**
     * Функция для удаления элемента из таблицу
     * @param var - элемент для добавления
     * @return - возвращает true если элемент найден в таблице и удалось его удалить от туда, иначе false
     */
    public boolean remove(@NotNull final T var) {
        final int bucketId = getBucketId(var);
        if (contains(var, bucketId)) {
            tableSize--;
            return hashTableElements[bucketId].remove(var);
        }
        return false;
    }

    /**
     * @return - возвращает количество элементов в таблице
     */
    public int size() {
        return tableSize;
    }

    /**
     * Функция для таблицы
     */
    public void clear() {
        for (int bucketId = 0; bucketId < BUCKETS_NUMBER; bucketId++) {
            hashTableElements[bucketId].clear();
        }
        tableSize = 0;
    }

    /**
     * @return - возвращает список элементов в таблице
     */
    public List<T> elements() {
        final List<T> tableElements = new ArrayList<>();
        for (int bucketId = 0; bucketId < BUCKETS_NUMBER; bucketId++) {
            tableElements.addAll(hashTableElements[bucketId]);
        }
        return tableElements;
    }

    /**
     * @return - возвращает количество корзин в таблице
     */
    public int getBucketsNumber() {
        return BUCKETS_NUMBER;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HashTable<?> hashTable = (HashTable<?>) o;
        return tableSize == hashTable.tableSize && Arrays.equals(hashTableElements, hashTable.hashTableElements);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(BUCKETS_NUMBER, tableSize);
        result = 31 * result + Arrays.hashCode(hashTableElements);
        return result;
    }
}
