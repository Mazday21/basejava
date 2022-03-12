package ru.basejava.storage;

import ru.basejava.exception.ExistStorageException;
import ru.basejava.exception.NotExistStorageException;
import ru.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void update(Resume r) {
        checkNotExist(r.getUuid());
        updateResume(r, getIndex(r.getUuid()));
    }

    public void save(Resume r) {
        checkExist(r.getUuid());
        saveResume(r, getIndex(r.getUuid()));
    }

    public void delete(String uuid) {
        checkNotExist(uuid);
        deleteResume(getIndex(uuid));
    }

    public Resume get(String uuid) {
        checkNotExist(uuid);
        return getResume(getIndex(uuid));
    }

    public void checkNotExist(String uuid) {
        if (getIndex(uuid) < 0) {
            throw new NotExistStorageException(uuid);
        }
    }

    private void checkExist(String uuid) {
        if (getIndex(uuid) > 0) {
            throw new ExistStorageException(uuid);
        }
    }

    protected abstract int getIndex(String uuid);

    protected abstract void updateResume(Resume r, int index);

    protected abstract void saveResume(Resume r, int index);

    protected abstract void deleteResume(int index);

    protected abstract Resume getResume(int index);
}
