package ru.basejava.storage;

import ru.basejava.exception.ExistStorageException;
import ru.basejava.exception.NotExistStorageException;
import ru.basejava.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage {
    private final Map<String, Resume> storage = new HashMap<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public void update(Resume r) {
        checkNotExist(r.getUuid());
        storage.put(r.getUuid(), r);
    }

    @Override
    public void save(Resume r) {
        checkExist(r.getUuid());
        storage.put(r.getUuid(), r);
    }

    @Override
    public void delete(String uuid) {
        checkNotExist(uuid);
        storage.remove(uuid);
    }

    @Override
    public Resume[] getAll() {
        return storage.values().toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public Resume get(String uuid) {
        checkNotExist(uuid);
        return storage.get(uuid);
    }

    private void checkNotExist(String uuid) {
        if (!storage.containsKey(uuid)) {
            throw new NotExistStorageException(uuid);
        }
    }

    private void checkExist(String uuid) {
        if (storage.containsKey(uuid)) {
            throw new ExistStorageException(uuid);
        }
    }

    /**
     * Unused methods
     */
    @Override
    protected int getIndex(String uuid) {
        return 0;
    }

    @Override
    protected void updateResume(Resume r, int index) {
    }

    @Override
    protected void saveResume(Resume r, int index) {
    }

    @Override
    protected void deleteResume(int index) {
    }

    @Override
    protected Resume getResume(int index) {
        return null;
    }
}
