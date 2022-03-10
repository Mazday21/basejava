package ru.basejava.storage;

import ru.basejava.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage {
    private final Map<Integer, Resume> storage = new HashMap<>();
    private int counter = 0;

    @Override
    public void clear() {
        storage.clear();
        counter = 0;
    }

    @Override
    public Resume[] getAll() {
        return storage.values().toArray(new Resume[0]);
    }

    public int size() {
        return counter;
    }

    @Override
    protected int getIndex(String uuid) {
        for (Map.Entry<Integer, Resume> entry : storage.entrySet()) {
            if (uuid.equals(entry.getValue().getUuid())) {
                return entry.getKey();
            }
        }
        return -1;
    }

    @Override
    protected void updateResume(Resume r, int index) {
        storage.put(index, r);
    }

    @Override
    protected void saveResume(Resume r, int index) {
        storage.put(counter, r);
        counter++;
    }

    @Override
    protected void deleteResume(int index) {
        storage.remove(index);
        counter--;
    }

    @Override
    protected Resume getResume(int index) {
        return storage.get(index);
    }
}
