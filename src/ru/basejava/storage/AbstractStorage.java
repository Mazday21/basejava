package ru.basejava.storage;

import ru.basejava.exception.ExistStorageException;
import ru.basejava.exception.NotExistStorageException;
import ru.basejava.model.Resume;

import java.util.Comparator;

public abstract class AbstractStorage<T> implements Storage {
    protected abstract T getSearchKey(String uuid);

    protected abstract void doUpdate(Resume r, T searchKey);

    protected abstract boolean isExist(T searchKey);

    protected abstract void doSave(Resume r, T searchKey);

    protected abstract Resume doGet(T searchKey);

    protected abstract void doDelete(T searchKey);

    public void update(Resume r) {
        T searchKey = getExistedSearchKey(r.getUuid());
        doUpdate(r, searchKey);
    }

    public void save(Resume r) {
        T searchKey = getNotExistedSearchKey(r.getUuid());
        doSave(r, searchKey);
    }

    public void delete(String uuid) {
        T searchKey = getExistedSearchKey(uuid);
        doDelete(searchKey);
    }

    public Resume get(String uuid) {
        T searchKey = getExistedSearchKey(uuid);
        return doGet(searchKey);
    }

    private T getExistedSearchKey(String uuid) {
        T searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private T getNotExistedSearchKey(String uuid) {
        T searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }
}
